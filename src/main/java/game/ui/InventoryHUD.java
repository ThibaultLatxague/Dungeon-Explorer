package game.ui;

import engine.graphics.Renderer;
import game.inventory.Inventory;
import game.items.Item;
import game.utils.Log;

public class InventoryHUD {
    private final Inventory inventory;
    private boolean visible = false;

    private final float slotSize = 48;
    private final float columns = 5;

    public InventoryHUD(Inventory inventory) {
        this.inventory = inventory;
    }

    public void toggle() {
        Log.log.info("HUD toggled");
        visible = !visible;
    }

    public void render(Renderer renderer, float startX, float startY) {
        if (!visible) return;

        float x = startX;
        float y = startY;
        float index = 0;

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
    }
}
