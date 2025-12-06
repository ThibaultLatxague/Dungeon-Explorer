package game.monsters.behaviors;

import game.monsters.Monster;
import game.player.Player;
import game.utils.MathUtils;

public class RangedBehavior implements MonsterBehavior {

    private float minDistance = 120f;
    private float attackCooldown = 1.2f;
    private float timer = 0f;

    @Override
    public void execute(Monster monster, Player player, float deltaTime) {
        timer -= deltaTime;

        float distance = MathUtils.distance(
                monster.getX(), monster.getY(),
                player.getX(), player.getY()
        );

        if (distance < minDistance) {
            // Recule
            monster.moveAwayFrom(player.getX(), player.getY(), deltaTime);
        }
        else if (distance <= monster.getVisionRange()) {
            // Tire à distance
            if (timer <= 0f) {
                monster.shoot(player);
                timer = attackCooldown;
            }
        }
    }

    @Override
    public String getName() {
        return "Ranged";
    }
}
