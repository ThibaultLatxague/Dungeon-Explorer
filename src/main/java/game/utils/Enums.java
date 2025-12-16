package game.utils;

public class Enums {
    public enum Direction{
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    public enum MonsterAIType{
        AGGRESSIVE,
        PASSIVE,
        NEUTRAL,
        BOSS
    }

    public enum MonsterState{
        IDLE,
        PATROL,
        CHASE,
        ATTACK,
        FLEE,
        DEAD
    }

    public enum MonsterType{
        GOBLIN,
        SLIME,
        DRAGON,
        UNDEAD
    }

    public enum Behaviour{
        AGGRESSIVE,
        FLEE,
        PASSIVE,
        PATROL,
        RANGED
    }

    public enum LogType{
        ERROR,
        INFO
    }

    public enum ItemCategory{
        CRAFTABLE,
        LOOTABLE,
        CONSUMABLE,
        MISC
    }

    public enum ItemRarity{
        COMMON,
        UNCOMMON,
        RARE,
        EPIC,
        LEGENDARY,
        MYTHIC,
        SPECIAL,
        SECRET
    }
}
