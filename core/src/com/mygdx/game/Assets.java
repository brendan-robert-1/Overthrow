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
    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<>("pixel-pro.json", Skin.class, new SkinLoader.SkinParameter("pixel-pro.atlas"));

    private static Assets instance;
    private Assets(){
        assetManager = new AssetManager();
        loadAll();
    }
    public static Assets getInstance(){
        if(instance == null){
            instance = new Assets();
        }
        return instance;
    }

    public void loadAll(){
        assetManager.load(SKIN);
        assetManager.finishLoading();
    }




    public static AssetManager getAssetManager() {
        return instance.getInstance().assetManager;
    }

    public static Skin skin(){
        return instance.getInstance().assetManager.get(SKIN);
    }
}
