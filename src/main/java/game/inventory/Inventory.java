package game.inventory;

import java.util.List;
import game.items.Item;

public interface Inventory {

    boolean addItem(Item item);

    boolean removeItem(Item item);

    boolean contains(Item item);

    List<Item> getItems();

    int getCapacity();

    boolean isFull();
}