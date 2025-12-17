package engine.graphics;

import engine.core.Window;
import engine.world.world.Tile;
import engine.world.world.TileMap;
import game.player.Player;
import game.monsters.*;
import engine.core.Camera;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;

public class SpriteRenderer {
    private Window window;

    public SpriteRenderer(Window window){
        this.window = window;
    }

    public void begin(){
        /**
         * TODO :
         * Prépare l'écran (clear + fond)
         */
        glClearColor(0.05f, 0.05f, 0.08f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glLoadIdentity(); // Reset de la matrice
    }

    public void end(){
        /**
         * TODO
         * Affiche la frame (swap buffer)
         */
        glfwSwapBuffers(getWindow().getWindow()); // Long int
    }

    public void renderPlayer(Player player) {
        int playerIdleTextureId = TextureLoader.loadTexture("src/main/resources/textures/player/player_idle.png");

        // Paramètres du joueur
        float x = player.getX();
        float y = player.getY();
        float h = player.getHeight();
        float w = player.getWidth();

        // Corriger les coordonnées pour l'aspect ratio
        float correctedX = x / getWindow().getAspectRatio();
        float correctedW = w / getWindow().getAspectRatio();

        // Activer les textures
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, playerIdleTextureId);

        // Activer la transparence (si ton image a un canal alpha)
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Couleur blanche pour ne pas teinter la texture
        // glColor3f(1.0f, 1.0f, 1.0f);

        glBegin(GL_QUADS);
        // Coin bas-gauche
        glTexCoord2f(0, 1);
        glVertex2f(correctedX, y);

        // Coin bas-droit
        glTexCoord2f(1, 1);
        glVertex2f(correctedX + correctedW, y);

        // Coin haut-droit
        glTexCoord2f(1, 0);
        glVertex2f(correctedX + correctedW, y + h);

        // Coin haut-gauche
        glTexCoord2f(0, 0);
        glVertex2f(correctedX, y + h);
        glEnd();

        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);

        // Cercle de portée (sans correction car déjà géré dans renderOutlineCircle)
        renderOutlineCircle(x + w/2, y + h/2, player.getWeapon().getAttackRange());
    }

    public void renderPlayerOld(Player player){
        // Paramètres du joueur
        float x = player.getX();
        float y = player.getY();
        float h = player.getHeight();
        float w = player.getWidth();

        float correctedX = x / getWindow().getAspectRatio();
        float correctedW = w / getWindow().getAspectRatio();

        // Joueur = carré rouge
        glColor3f(0.9f, 0.2f, 0.2f);

        glBegin(GL_QUADS);
        // Coin bas-gauche
        glTexCoord2f(0, 1);
        glVertex2f(correctedX, y);

        // Coin bas-droit
        glTexCoord2f(1, 1);
        glVertex2f(correctedX + correctedW, y);

        // Coin haut-droit
        glTexCoord2f(1, 0);
        glVertex2f(correctedX + correctedW, y + h);

        // Coin haut-gauche
        glTexCoord2f(0, 0);
        glVertex2f(correctedX, y + h);
        glEnd();

        renderOutlineCircle(x+w/2, y+h/2, player.getWeapon().getAttackRange());
    }

    public void renderMonster(Monster monster){
        /**
         * TODO
         * Render a monster
         */
        // Monster values
        float x = monster.getX();
        float y = monster.getY();
        float h = monster.getHeight();
        float w = monster.getWidth();

        float correctedX = x / getWindow().getAspectRatio();
        float correctedW = w / getWindow().getAspectRatio();

        // Joueur = carré rouge
        glColor3f(1f, 1f, 1f);

        glBegin(GL_QUADS);
        // Coin bas-gauche
        glTexCoord2f(0, 1);
        glVertex2f(correctedX, y);

        // Coin bas-droit
        glTexCoord2f(1, 1);
        glVertex2f(correctedX + correctedW, y);

        // Coin haut-droit
        glTexCoord2f(1, 0);
        glVertex2f(correctedX + correctedW, y + h);

        // Coin haut-gauche
        glTexCoord2f(0, 0);
        glVertex2f(correctedX, y + h);
        glEnd();

        renderOutlineCircle(x+w/2, y+h/2, monster.getAggroRange());
        renderOutlineCircle(x+w/2, y+h/2, monster.getAttackRange());
    }

    public void renderOutlineCircle(float x, float y, float radius){
        int segments = 100; // nombre de points pour lisser le cercle
        float aspectRatio = getWindow().getAspectRatio();

        glColor3f(1.0f, 1.0f, 1.0f); // couleur blanche
        glBegin(GL_LINE_LOOP);

        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            // Correction: diviser le rayon X par l'aspect ratio pour garder un cercle
            float px = x / aspectRatio + (float)Math.cos(angle) * radius / aspectRatio;
            float py = y + (float)Math.sin(angle) * radius;
            glVertex2f(px, py);
        }

        glEnd();
    }

    public void renderWorld(TileMap map, Camera camera, Window window) {
        // Activer les paramètres une seule fois
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        float height = map.getHeight();
        float width = map.getWidth();

        // === DEBUG: Informations sur la map ===
        System.out.println("=== DEBUG RENDER WORLD ===");
        System.out.println("Map dimensions: " + width + "x" + height);

        // Calculer les tiles visibles (culling)
        float zoom = camera.getZoom();
        float aspectRatio = window.getAspectRatio();
        float viewWidth = zoom * aspectRatio * 2;
        float viewHeight = zoom * 2;

        System.out.println("Camera pos: (" + camera.getX() + ", " + camera.getY() + ")");
        System.out.println("Zoom: " + zoom + ", AspectRatio: " + aspectRatio);
        System.out.println("View size: " + viewWidth + "x" + viewHeight);

        // Taille des tiles
        float tileWidth = map.getTileWidth();
        float tileHeight = map.getTileHeight();
        System.out.println("Tile size: " + tileWidth + "x" + tileHeight);

        int startX = Math.max(0, (int)((camera.getX() - viewWidth) / tileWidth));
        int endX = Math.min((int)width, (int)((camera.getX() + viewWidth) / tileWidth) + 1);
        int startY = Math.max(0, (int)((camera.getY() - viewHeight) / tileHeight));
        int endY = Math.min((int)height, (int)((camera.getY() + viewHeight) / tileHeight) + 1);

        System.out.println("Rendering tiles from (" + startX + "," + startY + ") to (" + endX + "," + endY + ")");
        System.out.println("Total tiles to render: " + ((endX - startX) * (endY - startY)));

        // Rendre uniquement les tiles visibles
        int tilesRendered = 0;
        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                Tile tile = map.getMap()[i][j];
                if (tilesRendered < 5) { // Debug des 5 premières tiles
                    System.out.println("Tile [" + i + "][" + j + "] at pos: (" + tile.getX() + ", " + tile.getY() + ")");
                }
                renderTile(tile, window);
                tilesRendered++;
            }
        }

        System.out.println("Actually rendered: " + tilesRendered + " tiles");
        System.out.println("========================\n");

        // Désactiver une seule fois
        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }

    public void renderWorldNoCulling(TileMap map, Window window) {
        System.out.println("=== RENDER ALL TILES (NO CULLING) ===");

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        float height = map.getHeight();
        float width = map.getWidth();

        System.out.println("Map size: " + width + "x" + height);

        int tilesRendered = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Tile tile = map.getMap()[i][j];
                if (tilesRendered < 5) {
                    System.out.println("Tile [" + i + "][" + j + "] at (" + tile.getX() + ", " + tile.getY() + ") size: " + tile.getWidth() + "x" + tile.getHeight());
                }
                renderTile(tile, window);
                tilesRendered++;
            }
        }

        System.out.println("Total rendered: " + tilesRendered + " tiles");

        glDisable(GL_TEXTURE_2D);
        glDisable(GL_BLEND);
    }

    public void renderTile(Tile tile, Window window) {
        // CHARGER LA TEXTURE UNE SEULE FOIS (avec cache)
        int textureId = TextureLoader.loadTexture(tile.getType().getImagePath());

        float x = tile.getX();
        float y = tile.getY();
        float h = tile.getHeight();
        float w = tile.getWidth();

        // Corriger pour l'aspect ratio
        float correctedX = x / window.getAspectRatio();
        float correctedW = w / window.getAspectRatio();

        // Bind texture
        glBindTexture(GL_TEXTURE_2D, textureId);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 1); glVertex2f(correctedX, y);
        glTexCoord2f(1, 1); glVertex2f(correctedX + correctedW, y);
        glTexCoord2f(1, 0); glVertex2f(correctedX + correctedW, y + h);
        glTexCoord2f(0, 0); glVertex2f(correctedX, y + h);
        glEnd();
    }

    public float renderReducer(float v){
        return (float)0.05*v;
    }

    // ========================
    // OPTIONS
    // ========================

    public void setWindow(Window w){ this.window = w; }

    public Window getWindow() { return this.window; }
}
