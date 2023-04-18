package novlib2d.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import novlib2d.entity.Entity;
import novlib2d.entity.character.Npc;
import novlib2d.entity.character.Player;

import java.util.ArrayList;

public class Scene {
    private final String sceneName;
    private final ArrayList<Entity> entities;

    public Scene(String sceneName) {
        this.sceneName = sceneName;
        entities = new ArrayList<>();
    }

    public void addEntity(Player player) {
        entities.add(player);
    }
    public void addEntity(Npc npc) {
        entities.add(npc);
    }

    public String getSceneName() { return sceneName; }

    public void draw(SpriteBatch spriteBatch) {

    }
}
