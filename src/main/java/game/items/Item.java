package game.items;

import game.utils.Enums.*;

public class Item {
    private String name;
    private int stackSize;
    private ItemCategory itemCategory;
    private ItemRarity itemRarity;
    private String texture;

    public Item(String name, int stackSize, ItemCategory itemCategory, ItemRarity itemRarity, String texture) {
        this.name = name;
        this.stackSize = stackSize;
        this.itemCategory = itemCategory;
        this.itemRarity = itemRarity;
        this.texture = texture;
    }

    // ========================
    // OPTIONS
    // ========================

    public String getName(){ return name; }
    public int getStackSize(){return stackSize; }
    public ItemCategory getItemCategory(){ return itemCategory; }
    public ItemRarity getItemRarity() { return itemRarity; }

    public void setName(String name) {
        this.name = name;
    }

    public void setStackSize(int stackSize) {
        this.stackSize = stackSize;
    }

    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    public void setItemRarity(ItemRarity itemRarity) {
        this.itemRarity = itemRarity;
    }

    public String getTexture() {
        return this.texture;
    }
}