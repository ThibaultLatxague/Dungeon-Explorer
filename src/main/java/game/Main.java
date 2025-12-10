package game;

import engine.core.GameLoop;
import engine.core.Window;
import engine.graphics.DebugTextRenderer;
import engine.graphics.SpriteRenderer;
import engine.core.Input;
import game.monsters.types.Slime.Slime;
import game.player.Player;
import game.player.PlayerController;

import java.io.IOException;

public class Main {

    static long lastTime = System.nanoTime();

    public static void main(String[] args) throws IOException {

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
        DebugTextRenderer font = new DebugTextRenderer("resources/fonts/Consolas.ttf");
        //float deltaTime = 0.002f;

        // ========================
        // PLAYER
        // ========================
        Player player = new Player(0.0f, 0.0f, "Test");
        player.setHealth(100);
        Slime slime = new Slime();
        player.setTarget(slime);
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
                    long now = System.nanoTime();
                    float deltaTime = (now - lastTime) / 1_000_000_000f;
                    lastTime = now;
                    //Log.log.info("DeltaTime : " + deltaTime);

                    input.update();
                    controller.update(deltaTime);
                    if(!slime.isDead() && player.isAlive()) { slime.update(deltaTime, player); }
                },

                // RENDER
                () -> {
                    if(player.isAlive()) { renderer.renderPlayer(player); }
                    if(!slime.isDead()) { renderer.renderMonster(slime); }
                }
        );
        renderer.end();
        gameLoop.run();
    }
}