package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    private AssetManager assetManager;
    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<>("uiskin.json", Skin.class, new SkinLoader.SkinParameter("uiskin.atlas"));


    private static Assets instance;
    private Assets(){
        assetManager = new AssetManager();
        loadAll();
        System.out.println("hello");
    }
    public static Assets getInstance(){
        if(instance == null){
            instance = new Assets();
        }
        return instance;
    }

    public void loadAll(){
        assetManager.load(SKIN);
        assetManager.load("plague_doctor.png", Texture.class);
        assetManager.load("MINOR_HEALTH_POT_64.png", Texture.class);
        assetManager.load("MINOR_POISON_RESIST_POT_64.png", Texture.class);
        assetManager.load("RUSTY_DAGGER_64.png", Texture.class);
        assetManager.load("HIDE_SHIELD_64.png", Texture.class);
        assetManager.load("64X64_BACKGROUND.png", Texture.class);
        assetManager.load("farms.png", Texture.class);
        assetManager.load("character_portrait.png", Texture.class);
        assetManager.load("slot_9patch.png", Texture.class);

        assetManager.finishLoading();
    }




    public static AssetManager getAssetManager() {
        return instance.getInstance().assetManager;
    }

    public static Skin skin(){
        return instance.getInstance().assetManager.get(SKIN);
    }
}
