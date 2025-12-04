package game;

import engine.core.*;
import engine.graphics.*;

public class Main {

    public static void main(String[] args) {

        Window window = new Window(1280, 720, "Dungeon Explorer");
        window.create();

        SpriteRenderer renderer = new SpriteRenderer();

        GameLoop loop = new GameLoop(window,
                () -> {
                    // UPDATE (logique)
                },
                () -> {
                    // RENDER
                    renderer.render(-0.5f, -0.5f, 0.2f, 0.2f);
                }
        );

        loop.run();
    }
}
