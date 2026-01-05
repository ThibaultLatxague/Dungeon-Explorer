package engine.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Renderer {

    public Renderer() {
        // Configuration OpenGL de base pour la 2D
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * Dessine un rectangle plein (utile pour slots d'inventaire, debug, UI)
     */
    public void drawRect(float x, float y, float width, float height) {

        glDisable(GL_TEXTURE_2D);

        glColor4f(0.2f, 0.2f, 0.2f, 0.8f); // gris semi-transparent

        glBegin(GL_QUADS);
        glVertex2f(x, y);
        glVertex2f(x + width, y);
        glVertex2f(x + width, y + height);
        glVertex2f(x, y + height);
        glEnd();

        glEnable(GL_TEXTURE_2D);
    }

    /**
     * Dessine une texture complète
     */
    public void drawTexture(Texture texture, float x, float y, float width, float height) {

        if (texture == null) return;

        glEnable(GL_TEXTURE_2D);
        glActiveTexture(GL_TEXTURE0);
        texture.bind();

        glColor4f(1f, 1f, 1f, 1f);

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0); glVertex2f(x, y);
        glTexCoord2f(1, 0); glVertex2f(x + width, y);
        glTexCoord2f(1, 1); glVertex2f(x + width, y + height);
        glTexCoord2f(0, 1); glVertex2f(x, y + height);
        glEnd();

        texture.unbind();
    }
}
