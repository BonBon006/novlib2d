package novlib2d.base;

import novlib2d.algorithm.Logic;
import novlib2d.data.CharacterData;
import novlib2d.graphics.Scene;

import java.util.ArrayList;

public class GameCore {
    private static GameCore core = null;

    public static final int PLATFORMER = 0;
    public static final int TOPDOWN = 1;

    public static final int CHARACTER_WIDTH_DEFAULT = 64;
    public static final int CHARACTER_HEIGHT_DEFAULT = 64;
    public static final int CHARACTER_ACTION_COUNT_DEFAULT = 3;
    public static final int CHARACTER_FRAME_COUNT_DEFAULT = 12;
    public static final float CHARACTER_HEALTH_DEFAULT = 100;
    public static final float CHARACTER_MANA_DEFAULT = 0;
    public static final float CHARACTER_STAMINA_DEFAULT = 0;
    public static final float CHARACTER_SPEED_DEFAULT = 2.5f;
    public static final boolean CHARACTER_SKILLS_ENABLED_DEFAULT = false;
    public static final boolean CHARACTER_CAMERA_ENABLED_DEFAULT = false;

    /**Helps in updating animation frames. By default, set to 16.666666667f. Works best with 60fps. */
    private float deltaTime;
    private int iterationLimit;
    private int gameCode;
    private int inventorySize;

    private final ArrayList<CharacterData> playerDatabase;
    private final ArrayList<CharacterData> npcDatabase;
    private final ArrayList<Scene> sceneDatabase;

    private GameCore() {
        playerDatabase = new ArrayList<>();
        npcDatabase = new ArrayList<>();
        sceneDatabase = new ArrayList<>();
        deltaTime = 16.666666667f;
        iterationLimit = 16;
        gameCode = PLATFORMER;
        inventorySize = 16;
    }

    public static GameCore initialize() {
        if (core == null) core = new GameCore();
        return core;
    }

    public void addPlayerData(CharacterData playerData) { playerDatabase.add(playerData); }
    public void addNpcData(CharacterData npcData) { playerDatabase.add(npcData); }
    public void addScene(Scene scene) { sceneDatabase.add(scene); }

    public void addPlayerData(Logic logic, String name) {
        playerDatabase.add(new CharacterData(logic, name, CHARACTER_WIDTH_DEFAULT, CHARACTER_HEIGHT_DEFAULT,
                CHARACTER_ACTION_COUNT_DEFAULT, CHARACTER_FRAME_COUNT_DEFAULT, CHARACTER_HEALTH_DEFAULT,
                CHARACTER_MANA_DEFAULT, CHARACTER_STAMINA_DEFAULT, CHARACTER_SPEED_DEFAULT,
                CHARACTER_SKILLS_ENABLED_DEFAULT, CHARACTER_CAMERA_ENABLED_DEFAULT));
    }

    public void addPlayerData(Logic logic, String name, int width, int height) {
        playerDatabase.add(new CharacterData(logic, name, width, height,
                CHARACTER_ACTION_COUNT_DEFAULT, CHARACTER_FRAME_COUNT_DEFAULT, CHARACTER_HEALTH_DEFAULT,
                CHARACTER_MANA_DEFAULT, CHARACTER_STAMINA_DEFAULT, CHARACTER_SPEED_DEFAULT,
                CHARACTER_SKILLS_ENABLED_DEFAULT, CHARACTER_CAMERA_ENABLED_DEFAULT));
    }

    public void addPlayerData(Logic logic, String name, int width, int height, int actionCount, int frameCount) {
        playerDatabase.add(new CharacterData(logic, name, width, height,
                actionCount, frameCount, CHARACTER_HEALTH_DEFAULT,
                CHARACTER_MANA_DEFAULT, CHARACTER_STAMINA_DEFAULT, CHARACTER_SPEED_DEFAULT,
                CHARACTER_SKILLS_ENABLED_DEFAULT, CHARACTER_CAMERA_ENABLED_DEFAULT));
    }

    public void addPlayerData(Logic logic, String name, int width, int height, int actionCount, int frameCount,
                              float health, float mana, float stamina, float speed, boolean skillsEnabled,
                              boolean hasCamera)
    {
        playerDatabase.add(new CharacterData(logic, name, width, height, actionCount, frameCount, health, mana, stamina,
                speed, skillsEnabled, hasCamera));
    }

    public void addNpcData(Logic logic, String name, int width, int height, int actionCount, int frameCount,
                              float health, float mana, float stamina, float speed, boolean skillsEnabled,
                              boolean hasCamera)
    {
        npcDatabase.add(new CharacterData(logic, name, width, height, actionCount, frameCount, health, mana, stamina,
                speed, skillsEnabled, hasCamera));
    }

    public int getGameCode() { return gameCode; }

    public ArrayList<CharacterData> getPlayerDatabase() { return playerDatabase; }
    public ArrayList<CharacterData> getNpcDatabase() { return npcDatabase; }
    public ArrayList<Scene> getSceneDatabase() { return sceneDatabase; }

    public int getInventorySize() { return inventorySize; }
    public float getDeltaTime() { return deltaTime; }
    public int getIterationLimit() { return iterationLimit; }

    public void setInventorySize(int inventorySize) { this.inventorySize = inventorySize; }
    public void setGameCode(int gameCode) { this.gameCode = gameCode; }
    public void setDeltaTime(float time) { deltaTime = time; }
    public void setIterationLimit(int limit) { iterationLimit = limit; }
}
