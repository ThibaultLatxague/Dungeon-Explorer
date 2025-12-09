package game.monsters.types.Slime;

import game.monsters.Monster;
import game.utils.RandomUtils;

public class Slime extends Monster {
    private final RandomUtils random = new RandomUtils();

    public Slime(){
        setFinalHealth(random.randomFloat(70,100));
        setX(0.5f);
        setY(0.5f);
        setHeight(0.1f);
        setWidth(0.1f);
        setLevel(10);
        setAttackRange(0.2f);
        setAggroRange(0.5f);
        setSpeed(2f);
        setName("Slime");
    }
}