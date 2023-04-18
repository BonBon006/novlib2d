package novlib2d.helper;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;
import novlib2d.utils.Pair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class AssetHelper extends AssetManager {
    public static final int TEXTURE = 0;
    public static final int BITMAP_FONT = 1;
    public static final int MUSIC = 2;
    public static final int SOUND = 3;
    public static final int SKIN = 4;
    public static final int PARTICLE_EFFECT = 5;
    public static final int TEXTURE_ATLAS = 6;
    public static final int I18NBUNDLE = 7;

    protected ExecutorService executorService;

    protected LinkedHashMap<String, Integer> assetMap;
    protected ArrayList<Pair<String, Texture>> textures;
    protected ArrayList<Pair<String, BitmapFont>> bitmapFonts;
    protected ArrayList<Pair<String, Music>> music;
    protected ArrayList<Pair<String, Sound>> sounds;
    protected ArrayList<Pair<String, Skin>> skins;
    protected ArrayList<Pair<String, ParticleEffect>> particleEffects;
    protected ArrayList<Pair<String, TextureAtlas>> textureAtlases;
    protected ArrayList<Pair<String, I18NBundle>> i18NBundles;

    protected ArrayList<String> assetErrs;

    public AssetHelper() {
        assetMap = new LinkedHashMap<>();
        textures = new ArrayList<>();
        bitmapFonts = new ArrayList<>();
        music = new ArrayList<>();
        sounds = new ArrayList<>();
        skins = new ArrayList<>();
        particleEffects = new ArrayList<>();
        textureAtlases = new ArrayList<>();
        i18NBundles = new ArrayList<>();

        assetErrs = new ArrayList<>();
    }

    public <T> void load(String internalPath, Class<T> type, boolean isPriority) {
        int classCode = getClassCode(type);
        if (isPriority) {
            try {
                load(internalPath, type);
                finishLoadingAsset(internalPath);
                updateLists(internalPath, classCode);
            }
            catch (Exception e) { assetErrs.add(internalPath); }
        }
        else {
            try {
                load(internalPath, type);
                assetMap.put(internalPath, classCode);
            }
            catch (Exception e) { assetErrs.add(internalPath); }
        }
    }

    public <T> void load(String[] internalPaths, Class<T> type, boolean isPriority) {
        int classCode = getClassCode(type);
        if (isPriority) {
            for (String path : internalPaths) {
                try {
                    load(path, type);
                    finishLoadingAsset(path);
                    updateLists(path, classCode);
                }
                catch(Exception e) { assetErrs.add(path); }
            }
        }
        else {
            for (String path : internalPaths) {
                try {
                    load(path, type);
                    assetMap.put(path, classCode);
                }
                catch(Exception e) { assetErrs.add(path); }
            }
        }
    }

    public void updateLists(int iterationLimit) {
        int count = 0;
        update(iterationLimit);
        if (!assetMap.isEmpty()) {
            for (Map.Entry<String, Integer> asset : assetMap.entrySet()) {
                if (isLoaded(asset.getKey())) {
                    updateLists(asset.getKey(), asset.getValue());
                    assetMap.remove(asset.getKey());
                }
                if (count > iterationLimit) break;
                else count++;
            }
        }
    }

    private void updateLists(String internalPath, int classCode) {
        executorService.execute(new updateLists(this, internalPath, classCode));
    }

    public ArrayList<Pair<String, Texture>> getTextures() { return textures; }
    public ArrayList<Pair<String, BitmapFont>> getBitmapFonts() { return bitmapFonts; }
    public ArrayList<Pair<String, Music>> getMusic() { return music; }
    public ArrayList<Pair<String, Sound>> getSounds() { return sounds; }
    public ArrayList<Pair<String, Skin>> getSkins() { return skins; }
    public ArrayList<Pair<String, ParticleEffect>> getParticleEffects() { return particleEffects; }
    public ArrayList<Pair<String, TextureAtlas>> getTextureAtlases() { return textureAtlases; }
    public ArrayList<Pair<String, I18NBundle>> getI18NBundles() { return i18NBundles; }

    public ArrayList<String> getAssetErrs() { return assetErrs; }
    public LinkedHashMap<String, Integer> getAssetMap() { return assetMap; }

    private <T> int getClassCode(Class<T> type) {
        int classCode = - 1;

        if (type == Texture.class) { classCode = TEXTURE; }
        else if (type == BitmapFont.class) { classCode = BITMAP_FONT; }
        else if (type == Music.class) { classCode = MUSIC; }
        else if (type == Sound.class) { classCode = SOUND; }
        else if (type == Skin.class) { classCode = SKIN; }
        else if (type == TextureAtlas.class) { classCode = TEXTURE_ATLAS; }
        else if (type == ParticleEffect.class) { classCode = PARTICLE_EFFECT; }
        else if (type == I18NBundle.class) { classCode = I18NBUNDLE; }

        return classCode;
    }

    public void setExecutorService(ExecutorService executorService) { this.executorService = executorService; }

    private record updateLists(AssetHelper assetHelper, String internalPath, int classCode) implements Runnable {
        @Override
            public void run() {
                if (assetHelper.isLoaded(internalPath)) {
                    switch (classCode) {
                        case TEXTURE -> {
                            if (assetHelper.isLoaded(internalPath, Texture.class))
                                assetHelper.getTextures().add(new Pair<>(internalPath, assetHelper.get(internalPath, Texture.class)));
                        }
                        case BITMAP_FONT -> {
                            if (assetHelper.isLoaded(internalPath, BitmapFont.class))
                                assetHelper.getBitmapFonts().add(new Pair<>(internalPath, assetHelper.get(internalPath, BitmapFont.class)));
                        }
                        case MUSIC -> {
                            if (assetHelper.isLoaded(internalPath, Music.class))
                                assetHelper.getMusic().add(new Pair<>(internalPath, assetHelper.get(internalPath, Music.class)));
                        }
                        case SOUND -> {
                            if (assetHelper.isLoaded(internalPath, Sound.class))
                                assetHelper.getSounds().add(new Pair<>(internalPath, assetHelper.get(internalPath, Sound.class)));
                        }
                        case SKIN -> {
                            if (assetHelper.isLoaded(internalPath, Skin.class))
                                assetHelper.getSkins().add(new Pair<>(internalPath, assetHelper.get(internalPath, Skin.class)));
                        }
                        case PARTICLE_EFFECT -> {
                            if (assetHelper.isLoaded(internalPath, ParticleEffect.class))
                                assetHelper.getParticleEffects().add(new Pair<>(internalPath, assetHelper.get(internalPath, ParticleEffect.class)));
                        }
                        case TEXTURE_ATLAS -> {
                            if (assetHelper.isLoaded(internalPath, TextureAtlas.class))
                                assetHelper.getTextureAtlases().add(new Pair<>(internalPath, assetHelper.get(internalPath, TextureAtlas.class)));
                        }
                        case I18NBUNDLE -> {
                            if (assetHelper.isLoaded(internalPath, I18NBundle.class))
                                assetHelper.getI18NBundles().add(new Pair<>(internalPath, assetHelper.get(internalPath, I18NBundle.class)));
                        }
                    }
                }
            }
        }
}

