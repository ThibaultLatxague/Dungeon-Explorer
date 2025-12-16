package game.items.weapons;

import game.utils.RandomUtils;

public class Weapon {
    // ========================
    // PARAMETRES
    // ========================

    // Parametres de base
    private float maxDamage;
    private float minDamage;
    private int durability; // Useless?
    private float attackRange;
    private float attackSpeed;
    private float lastAttackDamage;
    private float attackWideness;
    // TODO: private Recipe recipe

    // Autre
    private final RandomUtils random = new RandomUtils();

    public float getDamageAmount(){
        float amount = random.randomFloat(getMinDamage(), getMaxDamage());
        setLastAttackDamage(amount);
        return amount;
    }

    // ========================
    // OPTIONS
    // ========================

    public float getMaxDamage(){ return this.maxDamage; }
    public float getMinDamage(){ return this.minDamage; }
    public float getAttackRange() { return this.attackRange; }
    public float getAttackSpeed() { return this.attackSpeed; }
    public float getLastAttackDamage() { return this.lastAttackDamage; }

    public void setMinDamage(float m){ this.minDamage = m; }
    public void setMaxDamage(float m){ this.maxDamage = m; }
    public void setLastAttackDamage(float l) { this.lastAttackDamage = l; }
    public void setAttackSpeed(float a) { this.attackSpeed = a; }
    public void setAttackRange(float a) { this.attackRange = a; }
    public void setAttackWideness(float a) { attackWideness = a; }
}
