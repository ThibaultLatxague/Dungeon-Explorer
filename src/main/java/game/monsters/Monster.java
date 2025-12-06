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
    private float speed;
    private float finalHealth;
    private float minHealth;
    private float maxHealth;
    private float damageMin;
    private float damageMax;
    private float experienceRewardMin;
    private float experienceRewardMax;
    private float attackRange;
    private float aggroRange;
    private float attackSpeed;
    private boolean isDead = false;
    private String name;
    private int level;
    private MonsterAIType monsterAIType = MonsterAIType.NEUTRAL;
    private MonsterState monsterState = MonsterState.IDLE;
    private MonsterType monsterType;
    private MonsterAI monsterAI;
    // TODO : LootTable

    private final RandomUtils random = new RandomUtils();

    Monster(float x, float y, float h, float w){
        this.x = x;
        this.y = y;
        this.height = h;
        this.width = w;
    }

    public Monster() {
    }

    public void update(float deltaTime, Player player){
        //TODO : Boucle de jeu
        monsterAI.update(deltaTime);
        //TODO : ajouter changement de position etc
    }

    public void onHit(int damage){
        //TODO : Aggressive mode + damage + attack
        takeDamage(damage);
    }

    public void onDeath(){
        //TODO: Erase monster, drop exp, drop lootTable elements
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

    public float getExperienceReward(){
        return random.randomFloat(experienceRewardMin, experienceRewardMax);
    }

    public void setInitialHealth(){
        float h = random.randomFloat(minHealth, maxHealth);
        setHealth(h);
        setFinalHealth(h);
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
    public float getFinalHealth() { return this.finalHealth; }
    public float getAttackRange() { return this.attackRange; }
    public float getAggroRange() { return this.aggroRange; }
    public boolean isDead() { return this.isDead; }
    public MonsterAIType getMonsterAIType() { return this.monsterAIType; }
    public MonsterState getMonsterState() { return this.monsterState; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setHeight(float h) { this.height = h; }
    public void setWidth(float w) { this.width = w; }
    public void setHealth(float h) { this.health = h; }
    public void setFinalHealth(float h) { this.finalHealth = h; }
    public void setDead(boolean d) { this.isDead = d; }
    public void setMonsterAIType(MonsterAIType t) { this.monsterAIType = t; }
    public void setMonsterState(MonsterState s) { this.monsterState = s; }
}