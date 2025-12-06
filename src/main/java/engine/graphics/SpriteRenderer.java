package engine.graphics;

import engine.core.Window;
import game.player.Player;
import game.monsters.*;
import game.world.Tile;
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

    public void renderPlayer(Player player){
        // Paramètres du joueur
        float x = player.getX();
        float y = player.getY();
        float height = player.getHeight();
        float width = player.getWidth();

        // Tu remplaceras plus tard par un sprite
    }

    public void renderMonster(Monster monster){
        /**
         * TODO
         * Render a monster
         */
    }

    public void renderWorld(Tile[][] map){
        /**
         * TODO
         * Render map
         */
    }

    // ========================
    // OPTIONS
    // ========================

    public void setWindow(Window w){ this.window = w; }

    public Window getWindow() { return this.window; }
}
