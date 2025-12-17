package engine.world.world;

import game.utils.Enums;

public class TileMap {
    private Tile[][] map;
    private float height = 10;
    private float width = 10;

    public void generate() {

        TileType stone = new TileType(Enums.TileType.STONE, "src/main/resources/textures/world/tile_stone.png", true);
        Tile[][] map = new Tile[(int)getWidth()][(int)getHeight()];
        float tileSize = 0.1f;
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                // 🔹 UN TILE PAR CASE
                Tile tile = new Tile(
                        stone,
                        x * tileSize,
                        y * tileSize,
                        tileSize,
                        tileSize
                );
                map[x][y] = tile;
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

    // Dans votre classe TileMap
    public float getTileWidth() {
        return width; // ou la taille de vos tiles
    }

    public float getTileHeight() {
return height;
    }
}
