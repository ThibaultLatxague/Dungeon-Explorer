package engine.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

    private long window;
    private int width, height;
    private String title;

    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    public void create() {
        if (!glfwInit())
            throw new IllegalStateException("Impossible d'initialiser GLFW");

        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        window = glfwCreateWindow(width, height, title, 0, 0);
        if (window == 0)
            throw new RuntimeException("Impossible de créer la fenêtre");

        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(1);

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    public void update() {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    public boolean shouldClose() {
        return glfwWindowShouldClose(window);
    }

    public void destroy() {
        glfwTerminate();
    }

    public long getWindow() {
        return window;
    }
}