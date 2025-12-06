package game.monsters;

import game.monsters.behaviors.MonsterBehavior;
import game.player.Player;
import game.utils.Enums.*;

public class MonsterAI {
    private Monster monster;
    private Player target;
    private MonsterBehavior behavior;
    private float decisionTimer;
    private MonsterState currentState;

    public void update(float deltaTime){
        if(monster.isDead()){
            currentState = MonsterState.DEAD;
        }

        // Mesure la distance avec le joueur
        float distanceToPlayer = 10f;

        if(monster.getHealth() <= monster.getFinalHealth()*0.2){
            currentState = MonsterState.FLEE;
        } else if (distanceToPlayer <= monster.getAttackRange()) {
            monster.attack(target);
        } else if (distanceToPlayer <= monster.getAggroRange()) {
            currentState = MonsterState.CHASE;
        } else {
            currentState = MonsterState.PATROL;
        }

        if (behavior != null && !monster.isDead()) {
            behavior.execute(monster, target, deltaTime);
        }
    }

    // ========================
    // OPTIONS
    // ========================

    public void setBehaviour(MonsterBehavior m){ this.behavior = m; }
}
