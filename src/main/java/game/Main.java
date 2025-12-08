package game;

import engine.core.GameLoop;
import engine.core.Window;
import engine.graphics.SpriteRenderer;
import engine.core.Input;
import game.player.Player;
import game.player.PlayerController;

public class Main {

    public static void main(String[] args) {

        // ========================
        // WINDOW
        // ========================
        Window window = new Window(1920, 1080, "Dungeon Game");
        window.create();

        // ========================
        // SYSTEMS
        // ========================
        Input input = new Input(window.getWindow());
        SpriteRenderer renderer = new SpriteRenderer(window);
        float deltaTime = 0.002f;

        // ========================
        // PLAYER
        // ========================
        Player player = new Player(0.0f, 0.0f, "Test");
        PlayerController controller = new PlayerController(player, input);

        // ========================
        // GAME LOOP
        // ========================
        renderer.begin();
        GameLoop gameLoop = new GameLoop(
                window,

                // UPDATE
                () -> {
                    input.update();
                    controller.update(deltaTime);
                },

                // RENDER
                () -> {
                    renderer.renderPlayer(player);
                }
        );
        renderer.end();
        gameLoop.run();
    }
}