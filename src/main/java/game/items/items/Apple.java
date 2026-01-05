package game.items.items;

import engine.graphics.Texture;
import game.items.Item;
import game.utils.Enums;

public class Apple extends Item {
    public Apple(){
        setName("Apple");
        setStackSize(64);
        setItemCategory(Enums.ItemCategory.CONSUMABLE);
        setItemRarity(Enums.ItemRarity.COMMON);
        setTexture(new Texture("src/main/resources/textures/items/apple.png"));
    }
}