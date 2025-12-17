package engine.graphics;

import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader {

    private static Map<String, Integer> textureCache = new HashMap<>();

    public static int loadTexture(String path) {
        // Vérifier si la texture est déjà en cache
        if (textureCache.containsKey(path)) {
            return textureCache.get(path);
        }

        // Charger la texture (votre code existant)
        int textureId = loadTextureFromFile(path);

        // Mettre en cache
        textureCache.put(path, textureId);

        return textureId;
    }

    private static int loadTextureFromFile(String path) {
        // Votre code de chargement existant ici
        // ...
        try {
            BufferedImage image = ImageIO.read(new File(path));
            int width = image.getWidth();
            int height = image.getHeight();

            // Convertir l'image en tableau de pixels
            int[] pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);

            // Convertir en ByteBuffer pour OpenGL
            ByteBuffer buffer = ByteBuffer.allocateDirect(width * height * 4);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = pixels[y * width + x];
                    buffer.put((byte) ((pixel >> 16) & 0xFF)); // Rouge
                    buffer.put((byte) ((pixel >> 8) & 0xFF));  // Vert
                    buffer.put((byte) (pixel & 0xFF));         // Bleu
                    buffer.put((byte) ((pixel >> 24) & 0xFF)); // Alpha
                }
            }

            buffer.flip();

            // Créer la texture OpenGL
            int textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureID);

            // Paramètres de filtrage
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            // Charger les données de texture
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0,
                    GL_RGBA, GL_UNSIGNED_BYTE, buffer);

            return textureID;

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void clearCache() {
        textureCache.clear();
    }
}