package game;

import engine.core.*;
import engine.graphics.*;

public class Main {

    static void main() {

        Window window = new Window(1280, 720, "Dungeon Explorer");
        window.create();
        Input input = new Input(window.getWindow());
        SpriteRenderer renderer = new SpriteRenderer();

        GameLoop loop = new GameLoop(window,
                () -> {
                    if(input.up()) {
                        GameLogger.log.info("KEY: Up pressed");
                    }
                    if(input.down()) {
                        GameLogger.log.info("KEY: Down pressed");
                    }
                    if(input.left()) {
                        GameLogger.log.info("KEY: Left pressed");
                    }
                    if(input.right()) {
                        GameLogger.log.info("KEY: Right pressed");
                    }
                },
                () -> {
                    // RENDER
                    renderer.render(-0.5f, -0.5f, 0.2f, 0.2f);
                }
        );

        loop.run();
    }
}
