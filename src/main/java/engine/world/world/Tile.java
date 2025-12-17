package engine.world.world;

public class Tile {
    private TileType type;
    private float x;
    private float y;
    private float height;
    private float width;

    public Tile(TileType type, float x, float y, float height, float width) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }
}
