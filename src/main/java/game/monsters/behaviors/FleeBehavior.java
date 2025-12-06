package game.monsters.behaviors;

import game.monsters.Monster;
import game.player.Player;
import game.utils.MathUtils;

public class FleeBehavior implements MonsterBehavior {

    @Override
    public void execute(Monster monster, Player player, float deltaTime) {

        float dx = monster.getX() - player.getX();
        float dy = monster.getY() - player.getY();

        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx /= length;
            dy /= length;
        }

        // Court dans la direction opposée
        monster.move(dx * monster.getSpeed(), dy * monster.getSpeed(), deltaTime);
    }

    @Override
    public String getName() {
        return "Flee";
    }
}
