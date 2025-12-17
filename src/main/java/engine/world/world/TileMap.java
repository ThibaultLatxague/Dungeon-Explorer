package engine.world.world;

import game.utils.Enums;

public class TileMap {
    private Tile[][] map;
    private float height = 10;
    private float width = 10;

    public void generate(){
        TileType stone = new TileType(Enums.TileType.STONE, "", true);
        Tile tile = new Tile(stone, 0,0,32,32);
        Tile[][] map = getMap();
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                tile.setX(10*j);
                tile.setY(10*i);
                map[i][j] = tile;
            }
        }
        setMap(map);
    }

    public Tile[][] getMap() {
        return map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
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
