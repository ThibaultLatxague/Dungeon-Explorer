package engine.core;
import static org.lwjgl.opengl.GL11.*;

public class GameLoop {

    private Window window;
    private Runnable update;
    private Runnable render;

    public GameLoop(Window window, Runnable update, Runnable render) {
        this.window = window;
        this.update = update;
        this.render = render;
    }

    public void run() {
        float lastTime = (float)System.nanoTime() / 1_000_000_000.0f;

        while(!window.shouldClose()) {
            float now = (float)System.nanoTime() / 1_000_000_000.0f;
            float delta = now - lastTime;
            lastTime = now;

            glClear(GL_COLOR_BUFFER_BIT);

            update.run();
            render.run();

            window.update();
        }

        window.destroy();
    }
}
