package engine.core;

import static org.lwjgl.glfw.GLFW.*;

public class Input {

    private long window;

    // On donne la fenêtre à Input
    public Input(long window) {
        this.window = window;
    }

    public boolean isKeyDown(int key) {
        return glfwGetKey(window, key) == GLFW_PRESS;
    }

    // Méthodes pratiques
    public boolean up() {
        return isKeyDown(GLFW_KEY_W) || isKeyDown(GLFW_KEY_UP);
    }

    public boolean down() {
        return isKeyDown(GLFW_KEY_S) || isKeyDown(GLFW_KEY_DOWN);
    }

    public boolean left() {
        return isKeyDown(GLFW_KEY_A) || isKeyDown(GLFW_KEY_LEFT);
    }

    public boolean right() {
        return isKeyDown(GLFW_KEY_D) || isKeyDown(GLFW_KEY_RIGHT);
    }

    public boolean attack() {
        return isKeyDown(GLFW_KEY_SPACE);
    }
}