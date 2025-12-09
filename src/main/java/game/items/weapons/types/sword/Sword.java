package game.items.weapons.types.sword;

import game.items.weapons.Weapon;

public class Sword extends Weapon {

    public Sword(){
        setMinDamage(3);
        setMaxDamage(5);
        setAttackSpeed(1);
        setAttackRange(0.3f);
    }
}
