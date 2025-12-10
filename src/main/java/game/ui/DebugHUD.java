package game.ui;

import game.player.Player;
import game.monsters.Monster;
import engine.graphics.TextRenderer;

public class DebugHUD {

    private TextRenderer text;
    private boolean visible = true;

    private Player player;
    private Monster focusedMonster;

    public DebugHUD(TextRenderer renderer, Player player) {
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

        String[] lines = new String[20];
        int lineIndex = 0;

        lines[lineIndex++] = "=== DEBUG MODE ===";
        lines[lineIndex++] = "FPS : " + (int) fps;
        lines[lineIndex++] = "";
        lines[lineIndex++] = "-- PLAYER --";
        lines[lineIndex++] = "HP : " + player.getHealth();
        lines[lineIndex++] = "X : " + player.getX();
        lines[lineIndex++] = "Y : " + player.getY();

        lines[lineIndex++] = "";
        lines[lineIndex++] = "-- MONSTER --";
        lines[lineIndex++] = "Type : " + focusedMonster.getName();
        lines[lineIndex++] = "HP   : " + focusedMonster.getHealth();

        text.drawLines(lines, 0, 700);
    }
}