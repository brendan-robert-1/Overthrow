package com.mygdx.game;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    private static AssetManager assetManager  = new AssetManager();
    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<>("uiskin.json", Skin.class, new SkinLoader.SkinParameter("uiskin.atlas"));
    public static final AssetDescriptor<Texture> white = new AssetDescriptor<>("plain-white-background.jpg", Texture.class);

    public void loadAll(){
        assetManager.load(SKIN);
        assetManager.load(white);
    }




    public static AssetManager getAssetManager() {
        return assetManager;
    }
}
