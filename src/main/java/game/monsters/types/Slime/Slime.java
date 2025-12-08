package game.monsters.types.Slime;

import game.monsters.Monster;
import game.utils.RandomUtils;

public class Slime extends Monster {
    private final RandomUtils random = new RandomUtils();

    public Slime(){
        setFinalHealth(random.randomFloat(70,100));
        setX(0.1f);
        setY(0.1f);
        setHeight(1f);
        setWidth(1f);
        setLevel(10);
        setName("Slime");
    }
}