package novlib2d.manager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import novlib2d.base.GameCore;
import novlib2d.data.CharacterData;
import novlib2d.entity.Entity;
import novlib2d.entity.character.Player;
import novlib2d.utils.Pair;

import java.util.ArrayList;

public class EntityManager {
    private final GameCore core;
    private final ArrayList<Entity> entities;
    private ArrayList<CharacterData> playerDatabase;
    private ArrayList<CharacterData> npcDatabase;
    private int currentIndex;

    public EntityManager(GameCore core) {
        this.core = core;
        entities = new ArrayList<>();
        currentIndex = 0;
    }

    public void apply() {
        playerDatabase = core.getPlayerDatabase();
        npcDatabase = core.getNpcDatabase();
    }

    public void addEntities(ArrayList<Pair<String, Texture>> textures, int iterationLimit) {
        if (!textures.isEmpty()) {
            if (currentIndex == 0) addEntitiesLoop(textures, iterationLimit);
            if (currentIndex != textures.size() - 1) addEntitiesLoop(textures, iterationLimit);
        }
    }

    private void addEntitiesLoop(ArrayList<Pair<String, Texture>> assets, int iterationLimit) {
        boolean matchFound = false;
        for (int i = currentIndex; i < assets.size(); i++) {
            for (CharacterData playerData : playerDatabase) {
                if (assets.get(i).getVal_K().contains(playerData.name())) {
                    entities.add(new Player(core, playerData.logic(), playerData.name(), assets.get(i).getVal_V(),
                            playerData.width(), playerData.height(), playerData.actionCount(),
                            playerData.frameCount(), playerData.health(), playerData.mana(),
                            playerData.stamina(), playerData.speed(), playerData.skillsEnabled(),
                            playerData.cameraEnabled()));
                    currentIndex++;
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                for (CharacterData npcData : npcDatabase) {
                    if (assets.get(i).getVal_K().contains(npcData.name())) {
                        entities.add(new Player(core, npcData.logic(), npcData.name(), assets.get(i).getVal_V(),
                                npcData.width(), npcData.height(), npcData.actionCount(),
                                npcData.frameCount(), npcData.health(), npcData.mana(),
                                npcData.stamina(), npcData.speed(), npcData.skillsEnabled(),
                                npcData.cameraEnabled()));
                        currentIndex++;
                        matchFound = true;
                        break;
                    }
                }
            }
            matchFound = false;
            if (i == iterationLimit) break;
        }
    }

    public void update(float deltaTime) {
        if (!entities.isEmpty()) {
            for (Entity entity : entities) entity.update(deltaTime);
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        if (!entities.isEmpty()) for (Entity entity : entities) entity.draw(spriteBatch);
    }

    public ArrayList<Entity> getEntities() { return entities; }
}
