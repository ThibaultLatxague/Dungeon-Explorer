package engine.ecs;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    public float x, y;
    private List<Component> components = new ArrayList<>();

    public void addComponent(Component c){
        components.add(c);
    }

    public void update(float dt){
        for(Component c : components)
            c.update(dt);
    }

    public void render(){
        for(Component c : components)
            c.render();
    }
}
