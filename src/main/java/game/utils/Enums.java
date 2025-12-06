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
        CHASE,
        ATTACK,
        FLEE,
        DEAD
    }
}
