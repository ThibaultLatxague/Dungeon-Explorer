package engine.graphics;

import engine.core.Window;
import game.player.Player;

import static org.lwjgl.opengl.GL11.*;

public class SpriteRenderer {
    private Window window;

    public SpriteRenderer(Window window){
        this.window = window;
    }

    public void renderPlayer(Player player){
        // Pour le moment : un simple carré
        float x = player.getX();
        float y = player.getY();

        // Tu remplaceras plus tard par un sprite
    }

    public void begin(){
        // TO-DO
    }

    public void end(){
        // TO-DO
    }
}
