package engine.graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Renderer de texte simple utilisant une font TrueType
 * Optimisé pour afficher plusieurs lignes facilement
 */
public class TextRenderer {

    private Font font;
    private FontMetrics metrics;
    private float lineHeight;

    /**
     * Crée un TextRenderer avec une font TrueType
     * @param fontPath Chemin vers le fichier .ttf (ex: "assets/fonts/arial.ttf")
     * @param fontSize Taille de la font en pixels
     */
    public TextRenderer(String fontPath, float fontSize) {
        try {
            // Charger la font TTF
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
            this.font = baseFont.deriveFont(fontSize);

            // Calculer les métriques
            BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = img.createGraphics();
            g.setFont(this.font);
            this.metrics = g.getFontMetrics();
            this.lineHeight = metrics.getHeight();
            g.dispose();

        } catch (FontFormatException | IOException e) {
            System.err.println("Erreur chargement font, utilisation de Monospaced par défaut");
            this.font = new Font("Monospaced", Font.PLAIN, (int) fontSize);

            BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = img.createGraphics();
            g.setFont(this.font);
            this.metrics = g.getFontMetrics();
            this.lineHeight = metrics.getHeight();
            g.dispose();
        }
    }

    /**
     * Constructeur simplifié avec font par défaut
     * @param fontSize Taille de la font
     */
    public TextRenderer(float fontSize) {
        this.font = new Font("Monospaced", Font.PLAIN, (int) fontSize);

        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setFont(this.font);
        this.metrics = g.getFontMetrics();
        this.lineHeight = metrics.getHeight();
        g.dispose();
    }

    /**
     * Dessine une seule ligne de texte
     * @param text Texte à afficher
     * @param x Position X
     * @param y Position Y
     * @param r Rouge (0-1)
     * @param g Vert (0-1)
     * @param b Bleu (0-1)
     */
    public void drawText(String text, float x, float y, float r, float g, float b) {
        if (text == null || text.isEmpty()) return;

        // Calculer la taille nécessaire
        int width = metrics.stringWidth(text) + 4;
        int height = (int) lineHeight + 4;

        // Créer une image temporaire
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();

        // Anti-aliasing
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        // Fond transparent
        graphics.setBackground(new Color(0, 0, 0, 0));
        graphics.clearRect(0, 0, width, height);

        // Dessiner le texte en blanc (la couleur sera appliquée par OpenGL)
        graphics.setFont(font);
        graphics.setColor(Color.WHITE);
        graphics.drawString(text, 2, metrics.getAscent() + 2);
        graphics.dispose();

        // Convertir en texture OpenGL
        int[] pixels = new int[width * height];
        image.getRGB(0, 0, width, height, pixels, 0, width);

        ByteBuffer buffer = ByteBuffer.allocateDirect(width * height * 4);
        for (int py = 0; py < height; py++) {
            for (int px = 0; px < width; px++) {
                int pixel = pixels[py * width + px];
                buffer.put((byte) ((pixel >> 16) & 0xFF)); // R
                buffer.put((byte) ((pixel >> 8) & 0xFF));  // G
                buffer.put((byte) (pixel & 0xFF));         // B
                buffer.put((byte) ((pixel >> 24) & 0xFF)); // A
            }
        }
        buffer.flip();

        // Créer et lier la texture
        int textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        // Dessiner la texture
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        glColor3f(r, g, b);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0); glVertex2f(x, y + height);
        glTexCoord2f(1, 0); glVertex2f(x + width, y + height);
        glTexCoord2f(1, 1); glVertex2f(x + width, y);
        glTexCoord2f(0, 1); glVertex2f(x, y);
        glEnd();

        glDisable(GL_BLEND);
        glDisable(GL_TEXTURE_2D);

        // Nettoyer la texture
        glDeleteTextures(textureID);
    }

    /**
     * Dessine plusieurs lignes de texte en colonne (alignées à gauche)
     * @param lines Tableau de lignes (max 20 lignes recommandé)
     * @param x Position X (alignement à gauche)
     * @param startY Position Y de départ (haut de la première ligne)
     */
    public void drawLines(String[] lines, float x, float startY) {
        float currentY = startY;
        for (String line : lines) {
            if (line != null && !line.isEmpty()) {
                drawText(line, x, currentY, 1, 1, 1); // Blanc par défaut
            }
            currentY -= lineHeight;
        }
    }

    /**
     * Dessine plusieurs lignes de texte avec couleur personnalisée
     * @param lines Tableau de lignes
     * @param x Position X
     * @param startY Position Y de départ
     * @param r Rouge (0-1)
     * @param g Vert (0-1)
     * @param b Bleu (0-1)
     */
    public void drawLines(String[] lines, float x, float startY, float r, float g, float b) {
        float currentY = startY;
        for (String line : lines) {
            if (line != null && !line.isEmpty()) {
                drawText(line, x, currentY, r, g, b);
            }
            currentY -= lineHeight;
        }
    }

    /**
     * Retourne la hauteur d'une ligne
     */
    public float getLineHeight() {
        return lineHeight;
    }

    /**
     * Retourne la largeur d'un texte
     */
    public float getTextWidth(String text) {
        return metrics.stringWidth(text);
    }
}