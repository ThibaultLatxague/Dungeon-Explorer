package game.items.weapons;

public class Weapon {
    private float damage; // Useless?
    private float currentDamage; // Useless?
    private float maxDamage;
    private float minDamage;
    private int durability; // Useless?
    private float attackRange;
    // TODO: private Recipe recipe

    // ========================
    // OPTIONS
    // ========================

    public float getDamage(){ return this.damage; }
    public float getCurrentDamage(){ return this.currentDamage; }
    public float getMaxDamage(){ return this.maxDamage; }
    public float getMinDamage(){ return this.minDamage; }

    public void setMinDamage(float m){ this.minDamage = m; }
    public void setMaxDamage(float m){ this.maxDamage = m; }
}
