package game.monsters.types.Slime;

import game.monsters.Monster;
import game.utils.Log;
import game.utils.RandomUtils;

public class Slime extends Monster {
    private final RandomUtils random = new RandomUtils();

    public Slime(){

        setInitialHealth(70,100);
        Log.log.info("SLIME HEALTH: " + getHealth());
        setX(0.5f);
        setY(0.5f);
        setHeight(0.1f);
        setWidth(0.1f);
        setLevel(10);
        setAttackRange(0.2f);
        setAggroRange(0.5f);
        setSpeed(0.5f);
        setAttackSpeed(2f);
        setDamageMin(1);
        setDamageMax(5);
        setName("Slime");
    }
}