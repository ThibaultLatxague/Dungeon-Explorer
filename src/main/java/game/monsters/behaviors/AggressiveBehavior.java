package game.monsters.behaviors;

import game.monsters.Monster;
import game.player.Player;
import game.utils.MathUtils;

public class AggressiveBehavior implements MonsterBehavior {

    private float attackCooldown = 0.8f; // secondes
    private float timer = 0f;

    @Override
    public void execute(Monster monster, Player player, float deltaTime) {
        timer -= deltaTime;

        float distance = MathUtils.distance(
                monster.getX(), monster.getY(),
                player.getX(), player.getY()
        );

        // Se déplace vers le joueur
        monster.moveTowards(player.getX(), player.getY(), deltaTime);

        // Si assez proche et cooldown terminé → attaque
        if (distance <= monster.getAttackRange() && timer <= 0f) {
            monster.attack(player);
            timer = attackCooldown;
        }
    }

    @Override
    public String getName() {
        return "Aggressive";
    }
}
