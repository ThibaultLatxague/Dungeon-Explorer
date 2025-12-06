package game.monsters;

import game.player.Player;
import game.utils.Enums.*;
import game.utils.RandomUtils;

public class Monster{
    private float x;
    private float y;
    private float height;
    private float width;
    private float health;
    private float minHealth;
    private float maxHealth;
    private float damageMin;
    private float damageMax;
    private float attackRange;
    private float attackSpeed;
    private boolean isDead = false;
    private MonsterAIType monsterAIType = MonsterAIType.NEUTRAL;
    private MonsterState monsterState = MonsterState.IDLE;

    private RandomUtils random = new RandomUtils();

    Monster(float x, float y, float h, float w){
        this.x = x;
        this.y = y;
        this.height = h;
        this.width = w;
    }

    public void takeDamage(int amount){
        if(amount >= getHealth()){
            setHealth(0);
            setDead(true);
        } else {
            setHealth(getHealth() - amount);
        }
    }

    public boolean canAttack(){
        /**
         * TODO
         * Regarder le cooldown en fonction du delta time
         * return attackSpeed >= Time.deltaTime;
         */
        return true;
    }

    public float getDamage(){
        return random.randomFloat(damageMin, damageMax);
    }

    public void attack(Player player){
        if(canAttack()){
            player.takeDamage(getDamage());
        }
    }

    // ========================
    // OPTIONS
    // ========================

    public float getX() { return this.x; }
    public float getY() { return this.y; }
    public float getHeight() { return this.height; }
    public float getWidth() { return this.width; }
    public float getHealth() { return this.health; }
    public boolean isDead() { return this.isDead; }
    public MonsterAIType getMonsterAIType() { return this.monsterAIType; }
    public MonsterState getMonsterState() { return this.monsterState; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setHeight(float h) { this.height = h; }
    public void setWidth(float w) { this.width = w; }
    public void setHealth(float h) { this.health = h; }
    public void setDead(boolean d) { this.isDead = d; }
    public void setMonsterAIType(MonsterAIType t) { this.monsterAIType = t; }
    public void setMonsterState(MonsterState s) { this.monsterState = s; }
}