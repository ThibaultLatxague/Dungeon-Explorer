package engine.graphics;

import static org.lwjgl.opengl.GL11.*;

public class SpriteRenderer {

    public void render(float x, float y, float w, float h) {
        glBegin(GL_QUADS);

        glVertex2f(x, y);
        glVertex2f(x + w, y);
        glVertex2f(x + w, y + h);
        glVertex2f(x, y + h);

        glEnd();
    }
}
