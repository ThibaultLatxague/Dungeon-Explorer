package game.player;
import game.items.weapons.Weapon;
import game.items.weapons.types.sword.Sword;
import game.monsters.Monster;
import game.utils.Enums.*;
import game.utils.Log;
import game.utils.MathUtils;

public class Player {
    private float x;
    private float y;
    private float height = 0.25f; // Hauteur
    private float width = 0.1f; // Largeur
    private float health; // Vie
    private float maxHealth; // Vie maximale
    private String name;
    private Direction direction;
    private boolean isMoving = false;
    private boolean isRunning = false;
    private boolean isAttacking = false;
    private boolean isDead = false;
    private float speed = 1f;
    private Weapon weapon;

    //TODO : A supprimer ?
    private float attackTimer = 0f;
    private Monster target;

    public Player(float x, float y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
        this.weapon = new Sword();
    }

    public void takeDamage(float amount){
        if(amount >= getHealth()){
            setHealth(0);
            setDead(true);
        } else {
            setHealth(health - amount);
        }
        Log.log.info("ATTACKED: " + getHealth());
    }

    public void heal(float amount){
        // Return the smallest value of the two floats
        if(isAlive()) { setHealth(Math.min(amount + getHealth(), getMaxHealth())); }
    }

    public void attack(float deltaTime){
        //TODO Faire la fonction + donner en param target
        Monster target = getTarget();
        float distance = MathUtils.distance(getX()+getWidth()/2, getY()+getHeight()/2, target.getX()+target.getWidth()/2, target.getY()+target.getHeight()/2);
        if(isAttacking() && distance <= weapon.getAttackRange()){
            attackTarget(target, deltaTime);
        }
    }

    public Monster getTarget(){
        //TODO
        return target;
    }

    public void attackTarget(Monster target, float deltaTime){
        if(canAttack(deltaTime)){
            Log.log.info("Attaque du joueur realisee");
            float damage = weapon.getDamageAmount();
            Log.log.info("Degats donnés : " + damage);
            target.onHit(damage);
            setAttacking(false);
        }
    }

    public boolean canAttack(float deltaTime) {
        attackTimer += deltaTime;

        if (attackTimer >= weapon.getAttackSpeed()) {
            attackTimer = 0f;
            return true;
        }

        return false;
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
    public boolean isAlive() { return !isDead; }
    public float getSpeed() { return this.speed; }
    public Weapon getWeapon() { return this.weapon; }
    public float getAttackTimer() { return attackTimer; }

    public void setHealth(float h){ this.health = h; }
    public void setHeight(float h){ this.height = h; }
    public void setWidth(float w){ this.width = w; }
    public void setName(String name) { this.name = name; }
    public void setMoving(boolean moving) { isMoving = moving; }
    public void setRunning(boolean running) { isRunning = running; }
    public void setAttacking(boolean attacking) { isAttacking = attacking; }
    public void setDead(boolean dead) { isDead = dead; }
    public void setTarget(Monster t) { target = t; }
    public void setAttackTimer(float a) { attackTimer = a; }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
}