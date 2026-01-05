package game.inventory;

import game.items.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerInventory implements Inventory {

    private final int capacity;
    private final List<Item> items;

    public PlayerInventory(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    @Override
    public boolean addItem(Item item) {
        if (item == null) return false;
        if (isFull()) return false;

        items.add(item);
        return true;
    }

    @Override
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    @Override
    public boolean contains(Item item) {
        return items.contains(item);
    }

    @Override
    public List<Item> getItems() {
        // On renvoie une vue non modifiable pour éviter les modifications externes
        return Collections.unmodifiableList(items);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isFull() {
        return items.size() >= capacity;
    }

    // ===== Méthodes utilitaires (optionnelles mais utiles) =====

    public int getSize() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }
}
