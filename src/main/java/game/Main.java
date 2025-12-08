package game;

import engine.core.GameLoop;
import engine.core.Window;
import engine.graphics.DebugTextRenderer;
import engine.graphics.SpriteRenderer;
import engine.core.Input;
import game.monsters.types.Slime.Slime;
import game.player.Player;
import game.player.PlayerController;
import game.ui.DebugHUD;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        // ========================
        // WINDOW
        // ========================
        Window window = new Window(1020, 780, "Dungeon Game");
        window.create();

        // ========================
        // SYSTEMS
        // ========================
        Input input = new Input(window.getWindow());
        SpriteRenderer renderer = new SpriteRenderer(window);
        DebugTextRenderer font = new DebugTextRenderer("resources/fonts/Consolas.ttf");
        float deltaTime = 0.002f;

        // ========================
        // PLAYER
        // ========================
        Player player = new Player(0.0f, 0.0f, "Test");
        Slime slime = new Slime();
        PlayerController controller = new PlayerController(player, input);
        //DebugHUD debugHUD = new DebugHUD(font, player);

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
                    renderer.renderMonster(slime);
                }
        );
        renderer.end();
        gameLoop.run();
    }
}