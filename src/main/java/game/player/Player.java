package game.player;
import game.utils.Enums.*;

public class Player {
    private float x;
    private float y;
    private float height; // Hauteur
    private float width; // Largeur
    private float health; // Vie
    private float maxHealth; // Vie maximale
    private String name;
    private Direction direction;

    public Player(float x, float y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public void takeDamage(int amount){
        if(amount >= getHealth()){
            setHealth(0);
        } else {
            setHealth(health - amount);
        }
    }

    public void heal(int amount){
        // Return the smallest value of the two floats
        setHealth(Math.min(amount + getHealth(), getMaxHealth()));
    }

    public boolean isAlive(){
        return getHealth() == 0f;
    }

    // ========================
    // OPTIONS
    // ========================

    public float getX(){ return x; }
    public float getY(){ return y; }
    public float getHeight() { return this.height; }
    public float getWidth() { return this.width; }
    public float getHealth() { return this.health; }
    public float getMaxHealth() { return this.maxHealth; }
    public Direction getDirection() { return this.direction; }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setHealth(float h){ this.health = h; }
    public void setHeight(float h){ this.height = h; }
    public void setWidth(float w){ this.width = w; }
}