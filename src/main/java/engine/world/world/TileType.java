package engine.world.world;

import game.utils.Enums;

public class TileType {
    private Enums.TileType type;
    private String imagePath;
    private boolean walkable = true;

    public TileType(Enums.TileType type, String imagePath, boolean walkable) {
        this.type = type;
        this.imagePath = imagePath;
        this.walkable = walkable;
    }

    public Enums.TileType getType() {
        return type;
    }

    public void setType(Enums.TileType type) {
        this.type = type;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }
}
