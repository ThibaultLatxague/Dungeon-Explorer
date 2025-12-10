package game;

import engine.core.GameLoop;
import engine.core.Window;
import engine.graphics.TextRenderer;
import engine.graphics.SpriteRenderer;
import engine.core.Input;
import game.monsters.types.Slime.Slime;
import game.player.Player;
import game.player.PlayerController;
import game.ui.DebugHUD;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Main {

    static long lastTime = System.nanoTime();
    static long lastTime2 = System.nanoTime();

    public static void main(String[] args) throws IOException {

        // ========================
        // WINDOW
        // ========================
        Window window = new Window(1280, 720, "Dungeon Game");
        window.create();

        // ========================
        // SYSTEMS
        // ========================
        Input input = new Input(window.getWindow());
        SpriteRenderer renderer = new SpriteRenderer(window);
        TextRenderer textRenderer = new TextRenderer("src/main/resources/fonts/Consolas.ttf", 16);
        //float deltaTime = 0.002f;

        // ========================
        // PLAYER
        // ========================
        Player player = new Player(0.0f, 0.0f, "Test");
        player.setHealth(100);
        Slime slime = new Slime();
        player.setTarget(slime);
        PlayerController controller = new PlayerController(player, input);
        DebugHUD debugHUD = new DebugHUD(textRenderer, player);
        debugHUD.setTarget(slime);

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
                    // Sauvegarder l'état de projection actuel
                    glMatrixMode(GL_PROJECTION);
                    glPushMatrix();
                    glLoadIdentity();

                    // Projection en pixels pour le HUD
                    glOrtho(0, window.getWidth(), 0, window.getHeight(), -1, 1);

                    glMatrixMode(GL_MODELVIEW);
                    glPushMatrix();
                    glLoadIdentity();

                    // Rendu du HUD (en dernier pour être au dessus)
                    debugHUD.render(0);

                    // Restaurer les matrices
                    glMatrixMode(GL_PROJECTION);
                    glPopMatrix();
                    glMatrixMode(GL_MODELVIEW);
                    glPopMatrix();

                    // Rendu du jeu
                    if(player.isAlive()) { renderer.renderPlayer(player); }
                    if(!slime.isDead()) { renderer.renderMonster(slime); }
                }
        );
        renderer.end();
        gameLoop.run();
    }
}