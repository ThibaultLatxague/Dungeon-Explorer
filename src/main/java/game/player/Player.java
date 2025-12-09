package game.player;
import game.utils.Enums.*;

public class Player {
    private float x;
    private float y;
    private float height = 2.5f; // Hauteur
    private float width = 1f; // Largeur
    private float health; // Vie
    private float maxHealth; // Vie maximale
    private String name;
    private Direction direction;
    private boolean isMoving = false;
    private boolean isRunning = false;
    private boolean isAttacking = false;
    private boolean isDead = false;
    private float speed = 3.5f;

    //TODO : A SUPPRIMER et mettre dans weapon
    private float attackRange = 3f;

    public Player(float x, float y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public void takeDamage(float amount){
        if(amount >= getHealth()){
            setHealth(0);
            setDead(true);
        } else {
            setHealth(health - amount);
        }
    }

    public void heal(float amount){
        // Return the smallest value of the two floats
        if(!isDead()) { setHealth(Math.min(amount + getHealth(), getMaxHealth())); }
    }

    // ========================
    // OPTIONS
    // ========================

    public float getX(){ return x; }
    public float getY(){ return y; }
    public String getName() { return name; }
    public float getHeight() { return this.height; }
    public float getWidth() { return this.width; }
    public float getHealth() { return this.health; }
    public float getMaxHealth() { return this.maxHealth; }
    public Direction getDirection() { return this.direction; }
    public boolean isMoving() { return isMoving; }
    public boolean isRunning() { return isRunning; }
    public boolean isAttacking() { return isAttacking; }
    public boolean isDead() { return isDead; }
    public float getSpeed() { return this.speed; }
    //TODO : A SUPPRIMER
    public float getAttackRange() { return this.attackRange; }

    public void setHealth(float h){ this.health = h; }
    public void setHeight(float h){ this.height = h; }
    public void setWidth(float w){ this.width = w; }
    public void setName(String name) { this.name = name; }
    public void setMoving(boolean moving) { isMoving = moving; }
    public void setRunning(boolean running) { isRunning = running; }
    public void setAttacking(boolean attacking) { isAttacking = attacking; }
    public void setDead(boolean dead) { isDead = dead; }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
}