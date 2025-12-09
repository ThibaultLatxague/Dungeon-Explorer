package game.monsters;

import game.player.Player;
import game.utils.Enums.*;
import game.utils.Log;
import game.utils.RandomUtils;
import game.utils.MathUtils;

public class Monster{
    private float x;
    private float y;
    private float height;
    private float width;
    private float speed;
    private float health; // Actual health
    private float finalHealth; // Health set at spawn
    private float minHealth; // Minimum health possible
    private float maxHealth; // Maximum health possible
    private float damageMin; // Min damage value
    private float damageMax; // Max attack value
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
    private float velocityX = 0;
    private float velocityY = 0;
    private float attackTimer = 0f;
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
        //TODO : ajouter changement de position etc
        //monsterAI.update(deltaTime);
        //Log.log.info("Distance : " + MathUtils.distance(player.getX(), player.getY(), getX(), getY()));
        float distance = MathUtils.distance(player.getX()+player.getWidth()/2, player.getY()+player.getHeight()/2, getX()+getWidth()/2, getY()+getHeight()/2);

        if(distance <= getAggroRange() && distance > getAttackRange()){
            //Log.log.info("Player dans distance de detection");
            moveTowards(player.getX(), player.getY(), deltaTime);
        } else if (distance <= getAttackRange()) {
            //Log.log.info("Player dans distance d'attaque'");
            attack(player, deltaTime);
        }
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

    public boolean canAttack(float deltaTime) {
        // On réduit le cooldown restant
        attackTimer -= deltaTime;

        // Si encore en cooldown → impossible d’attaquer
        if (attackTimer > 0) {
            return false;
        }

        // Sinon on peut attaquer → on reset le cooldown
        attackTimer = getAttackSpeed(); // getAttackSpeed() = temps entre 2 attaques

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

    public void attack(Player player, float deltaTime){
        if(canAttack(deltaTime)){
            Log.log.info("Attaque realisee");
            float damage = getDamage();
            Log.log.info("Degats donnés : " + damage);
            player.takeDamage(damage);
        }
    }

    public void moveTowards(float playerX, float playerY, float deltaTime) {
        float monsterX = getX();
        float monsterY = getY();
        float speed = getSpeed();

        // Calcul du vecteur directionnel vers le joueur
        float dirX = playerX - monsterX;
        float dirY = playerY - monsterY;

        // Distance actuelle
        float distance = (float) Math.sqrt(dirX * dirX + dirY * dirY);

        // Éviter la division par zéro si le monstre est déjà sur le joueur
        if (distance == 0) return;

        // Normalisation du vecteur (direction unitaire)
        dirX /= distance;
        dirY /= distance;

        // Application du mouvement : vitesse * direction * deltaTime
        float newX = monsterX + dirX * speed * deltaTime;
        float newY = monsterY + dirY * speed * deltaTime;

        setX(newX);
        setY(newY);
    }

    public boolean isNear(float x, float y, float v) {
        //TODO
        return false;
    }

    public void moveAwayFrom(float x, float y, float deltaTime) {
        //TODO
    }

    public void shoot(Player player) {
        //TODO
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
    public float getSpeed() { return this.speed; }
    public String getName() { return this.name; }
    public float getAttackSpeed() { return this.attackSpeed; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setHeight(float h) { this.height = h; }
    public void setWidth(float w) { this.width = w; }
    public void setHealth(float h) { this.health = h; }
    public void setFinalHealth(float h) { this.finalHealth = h; }
    public void setDead(boolean d) { this.isDead = d; }
    public void setMonsterAIType(MonsterAIType t) { this.monsterAIType = t; }
    public void setMonsterState(MonsterState s) { this.monsterState = s; }
    public void setName(String n) { this.name = n; }
    public void setLevel(int l) { this.level = l; }
    public void setAttackRange(float a) { this.attackRange = a; }
    public void setAggroRange(float a) { this.aggroRange = a; }
    public void setSpeed(float s) { this.speed = s; }
    public void setAttackSpeed(float a) { this.attackSpeed = a; }
    public void setDamageMin(float dM) { this.damageMin = dM; }
    public void setDamageMax(float dM) { this.damageMax = dM; }
}