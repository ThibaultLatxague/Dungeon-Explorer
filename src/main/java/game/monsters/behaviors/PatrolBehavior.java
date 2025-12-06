package game.monsters.behaviors;

import game.monsters.Monster;
import game.player.Player;
import game.utils.MathUtils;
import engine.math.Vector2;

import java.util.List;

public class PatrolBehavior implements MonsterBehavior {

    private List<Vector2> patrolPoints;
    private int currentIndex = 0;

    public PatrolBehavior(List<Vector2> points) {
        this.patrolPoints = points;
    }

    @Override
    public void execute(Monster monster, Player player, float deltaTime) {

        if (patrolPoints == null || patrolPoints.isEmpty())
            return;

        Vector2 target = patrolPoints.get(currentIndex);

        if (monster.isNear(target.getX(), target.getY(), 8f)) {
            currentIndex = (currentIndex + 1) % patrolPoints.size();
        }

        monster.moveTowards(target.getX(), target.getY(), deltaTime);
    }

    @Override
    public String getName() {
        return "Patrol";
    }
}
