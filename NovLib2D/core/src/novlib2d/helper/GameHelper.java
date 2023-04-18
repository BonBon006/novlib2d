package novlib2d.helper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import novlib2d.base.GameCore;
import novlib2d.manager.EntityManager;
import novlib2d.manager.SceneManager;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameHelper {
    public GameCore core;
    protected AssetHelper assetHelper;
    protected SpriteBatch spriteBatch;
    protected EntityManager entityManager;
    protected SceneManager sceneManager;
    protected ExecutorService executorService;
    private int currentAssetErrs;
    private boolean isEnabled;

    public GameHelper() {
        core = GameCore.initialize();
        spriteBatch = new SpriteBatch();
        assetHelper = new AssetHelper();
        isEnabled = false;
    }

    public void enable() {
        entityManager = new EntityManager(core);
        sceneManager = new SceneManager(entityManager, core);

        executorService = Executors.newCachedThreadPool();
        assetHelper.setExecutorService(executorService);
        currentAssetErrs = 0;
        isEnabled = true;
    }

    public void apply() {
        if (isEnabled) {
            entityManager.apply();
            sceneManager.apply();
        }
    }

    public <T> void load(String internalPath, Class<T> type, boolean isPriority) {
        if (isEnabled) assetHelper.load(internalPath, type, isPriority);
    }

    public <T> void load(String[] internalPaths, Class<T> type, boolean isPriority) {
        if (isEnabled) assetHelper.load(internalPaths, type, isPriority);
    }

    private void printAssetErrs(ArrayList<String> assetErrs) {
        if (currentAssetErrs < assetErrs.size()) {
            for (int i = currentAssetErrs; i < assetErrs.size(); i++)
                {System.out.println("Failed to load asset: " + assetErrs.get(i));
                currentAssetErrs++;
            }
        }
    }

    public void update(float deltaTime) {
        if (isEnabled) {
            assetHelper.updateLists(core.getIterationLimit());
            printAssetErrs(assetHelper.getAssetErrs());
            entityManager.addEntities(assetHelper.getTextures(), core.getIterationLimit());
            entityManager.update(deltaTime);
        }
    }

    public void draw() {
        if (isEnabled) {
            spriteBatch.begin();
            entityManager.draw(spriteBatch);
            spriteBatch.end();
        }
    }

    public void dispose() {
        spriteBatch.dispose();
        assetHelper.dispose();
        if (executorService != null) executorService.shutdown();
    }

    public boolean isEnabled() { return isEnabled; }
}
