package game.monsters.behaviors;

import game.monsters.Monster;
import game.player.Player;

public interface MonsterBehavior {

    /**
     * Appelé à chaque update de la MonsterAI
     */
    void execute(Monster monster, Player player, float deltaTime);

    /**
     * Nom du comportement (debug / UI / logs)
     */
    String getName();
}
