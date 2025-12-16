package game.recipes;

import game.items.Item;

import java.util.Map;

public class Recipe {
    private Map itemsRequired;
    private Item result;
    private int quantity;

    public Recipe(Map itemsRequired, Item result, int quantity){
        this.itemsRequired = itemsRequired;
        this.result = result;
        this.quantity = quantity;
    }
}
