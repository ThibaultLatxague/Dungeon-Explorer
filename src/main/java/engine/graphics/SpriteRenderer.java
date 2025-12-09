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
        float h = renderReducer(player.getHeight());
        float w = renderReducer(player.getWidth());

        // Joueur = carré rouge
        glColor3f(0.9f, 0.2f, 0.2f);

        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + w, y);
        glVertex2f(x + w, y + h);
        glVertex2f(x, y + h);
        glEnd();

        renderOutlineCircle(x+w/2, y+h/2, renderReducer(player.getAttackRange()));
    }

    public void renderMonster(Monster monster){
        /**
         * TODO
         * Render a monster
         */
        // Monster values
        float x = monster.getX();
        float y = monster.getY();
        float h = renderReducer(monster.getHeight());
        float w = renderReducer(monster.getWidth());

        // Joueur = carré rouge
        glColor3f(1f, 1f, 1f);

        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + w, y);
        glVertex2f(x + w, y + h);
        glVertex2f(x, y + h);
        glEnd();

        renderOutlineCircle(x+w/2, y+h/2, renderReducer(monster.getAggroRange()));
        renderOutlineCircle(x+w/2, y+h/2, renderReducer(monster.getAttackRange()));
    }

    public void renderOutlineCircle(float x, float y, float radius){
        int segments = 100; // nombre de points pour lisser le cercle

        glColor3f(1.0f, 1.0f, 1.0f); // couleur blanche
        glBegin(GL_LINE_LOOP);

        for (int i = 0; i < segments; i++) {
            double angle = 2 * Math.PI * i / segments;
            float px = x + (float)Math.cos(angle) * radius;
            float py = y + (float)Math.sin(angle) * radius;
            glVertex2f(px, py);
        }

        glEnd();
    }

    public void renderWorld(Tile[][] map){
        /**
         * TODO
         * Render map
         */
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
