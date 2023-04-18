package novlib2d.manager;

import novlib2d.base.GameCore;
import novlib2d.entity.Entity;
import novlib2d.graphics.Scene;

import java.util.ArrayList;

public class SceneManager {
    private final GameCore core;
    private final EntityManager entityManager;
    private ArrayList<Scene> sceneDatabase;
    private ArrayList<Entity> entities;

    private int gameCode;

    public SceneManager(EntityManager entityManager, GameCore core) {
        this.core = core;
        this.entityManager = entityManager;
    }

    public void apply() {
        gameCode = core.getGameCode();
        sceneDatabase = core.getSceneDatabase();
        entities = entityManager.getEntities();
    }
}
