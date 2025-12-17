package game.ui;

import game.inventory.Inventory;
import game.items.Item;

public class InventoryHUD {
    private final Inventory inventory;
    private boolean visible = false;

    private final int slotSize = 48;
    private final int columns = 5;

    public InventoryHUD(Inventory inventory) {
        this.inventory = inventory;
    }

    public void toggle() {
        visible = !visible;
    }

    /**
    public void render(Renderer renderer, int startX, int startY) {
        if (!visible) return;

        int x = startX;
        int y = startY;
        int index = 0;

        for (Item item : inventory.getItems()) {

            // slot background
            renderer.drawRect(x, y, slotSize, slotSize);

            // item icon
            renderer.drawTexture(item.getTexture(), x + 4, y + 4, slotSize - 8, slotSize - 8);

            index++;
            x += slotSize;

            if (index % columns == 0) {
                x = startX;
                y -= slotSize;
            }
        }
    }**/
}
