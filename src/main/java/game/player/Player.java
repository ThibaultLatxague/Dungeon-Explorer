package game.player;

public class Player {
    private float xCoordinate;
    private float yCoordinate;
    private String name;

    public float getX(){
        return xCoordinate;
    }

    public float getY(){
        return yCoordinate;
    }

    public void setPosition(float x, float y){
        xCoordinate = x;
        yCoordinate = y;
    }
}