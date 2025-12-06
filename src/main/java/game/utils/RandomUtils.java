package game.utils;

public class RandomUtils{
    public float randomFloat(float min, float max){
        return min + (float) (Math.random() * ((max - min) + 1));
    }

    public int randomInt(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    public double randomDouble(float min, float max){
        return min + (double)(Math.random() * ((max - min) + 1));
    }
}