package game.player;

import engine.core.Input;
import game.utils.Log;
import game.utils.Timer;
import static org.lwjgl.glfw.GLFW.*;

public class PlayerController {

    private final Player player;
    private final Input input;

    // ========================
    // CONFIG
    // ========================
    private float baseSpeed = 3.5f;   // vitesse normale
    private float sprintMultiplier = 1.8f;

    // ========================
    // STATE
    // ========================
    private float velocityX = 0;
    private float velocityY = 0;

    public PlayerController(Player player, Input input) {
        this.player = player;
        this.input = input;
    }

    /**
     * Appelée à chaque frame depuis ta GameLoop
     */
    public void update(float deltaTime) {
        handleMovement(deltaTime);
        if(player.isMoving() || player.isRunning()) {
            applyMovement(deltaTime);
        }
    }

    // ========================
    // MOVEMENT
    // ========================

    private void handleMovement(float deltaTime) {

        velocityX = 0;
        velocityY = 0;
        player.setMoving(false);
        player.setAttacking(false);

        float speed = player.getSpeed();

        if (input.isKeyDown(GLFW_KEY_LEFT_SHIFT)) {
            speed *= sprintMultiplier;
            //TODO : changer à setRunning
            player.setMoving(true);
        }

        if (input.up()) {
            velocityY += speed;
            player.setMoving(true);
        }

        if (input.down()) {
            velocityY -= speed;
            player.setMoving(true);
        }

        if (input.left()) {
            velocityX -= speed;
            player.setMoving(true);
        }

        if (input.right()) {
            velocityX += speed;
            player.setMoving(true);
        }

        if(input.attack()){
            player.setAttacking(true);
            Log.log.info("Player attacking");
        }

        normalizeDiagonal(speed);
    }

    private void applyMovement(float deltaTime) {
        float newX = player.getX() + velocityX * deltaTime;
        float newY = player.getY() + velocityY * deltaTime;

        // Future : gestion collisions ici
        //Log.log.info("Movement detected. New x = " + newX);
        //Log.log.info("Movement detected. New y = " + newY);
        player.setPosition(newX, newY);
    }

    /**
     * Empêche la vitesse diagonale d’être plus rapide que la normale
     */
    private void normalizeDiagonal(float speed) {
        if (velocityX != 0 && velocityY != 0) {
            float factor = (float) (1 / Math.sqrt(2));
            velocityX *= factor;
            velocityY *= factor;
        }
    }

    // ========================
    // OPTIONS
    // ========================

    public void setBaseSpeed(float baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public void setSprintMultiplier(float sprintMultiplier) {
        this.sprintMultiplier = sprintMultiplier;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }
}