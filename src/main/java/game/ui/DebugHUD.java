package game.ui;

import game.player.Player;
import game.monsters.Monster;
import engine.graphics.DebugTextRenderer;

public class DebugHUD {

    private DebugTextRenderer text;
    private boolean visible = true;

    private Player player;
    private Monster focusedMonster;

    public DebugHUD(DebugTextRenderer renderer, Player player) {
        this.text = renderer;
        this.player = player;
    }

    public void setTarget(Monster monster) {
        this.focusedMonster = monster;
    }

    public void toggle() {
        visible = !visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void render(float fps) {

        if (!visible) return;

        float y = 700;

        text.drawText("=== DEBUG MODE ===", 10, y, 1f, 1, 1, 0); y -= 25;
        text.drawText("FPS : " + (int) fps, 10, y, 1f, 0, 1, 0); y -= 25;

        // PLAYER
        text.drawText("-- PLAYER --", 10, y, 1, 0, 1, 1); y -= 20;
        text.drawText("HP : " + player.getHealth(), 10, y, 1, 1, 1, 1); y -= 20;
        text.drawText("X : " + player.getX(), 10, y, 1, 1, 1, 1); y -= 20;
        text.drawText("Y : " + player.getY(), 10, y, 1, 1, 1, 1); y -= 20;
        //text.drawText("State : " + player.getState(), 10, y, 1, 1, 1, 1); y -= 30;


        // MONSTER
        if (focusedMonster != null) {
            text.drawText("-- MONSTER --", 10, y, 1, 1, 0, 0); y -= 20;
            text.drawText("Type : " + focusedMonster.getName(), 10, y, 1, 1, 1, 1); y -= 20;
            text.drawText("HP   : " + focusedMonster.getHealth(), 10, y, 1, 1, 1, 1); y -= 20;
            //text.drawText("AI   : " + focusedMonster.getAI().getCurrentState(), 10, y, 1, 1, 1, 1);
        }
    }
}