package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.SkinLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    private AssetManager assetManager;
    public static final AssetDescriptor<Skin> SKIN = new AssetDescriptor<>("pixel-pro.json", Skin.class, new SkinLoader.SkinParameter("pixel-pro.atlas"));
    private static InternalFileHandleResolver _filePathResolver =  new InternalFileHandleResolver();
    public static float MASTER_VOLUME = 0.02f;

    private static Assets instance;
    private Assets(){
        assetManager = new AssetManager();
        assetManager.load("overthrow.atlas", TextureAtlas.class);
        assetManager.load(SKIN);
        loadSoundAsset("axe.mp3");
        loadSoundAsset("joust.mp3");
        loadSoundAsset("man-growl.mp3");
        loadSoundAsset("purchase.mp3");
        loadSoundAsset("coin-reward.mp3");
        loadSoundAsset("select-option.mp3");
        loadSoundAsset("dog-growl.mp3");
        loadSoundAsset("bottle.mp3");
        loadSoundAsset("keys.mp3");
        loadSoundAsset("fire.mp3");
        assetManager.finishLoading();
    }

    public static Assets getInstance(){
        if(instance == null){
            instance = new Assets();
        }
        return instance;
    }


    public void loadSoundAsset(String soundFilenamePath){
        if( soundFilenamePath == null || soundFilenamePath.isEmpty() ){
            return;
        }

        if( assetManager.isLoaded(soundFilenamePath) ){
            return;
        }

        //load asset
        if( _filePathResolver.resolve(soundFilenamePath).exists() ){
            assetManager.setLoader(Sound.class, new SoundLoader(_filePathResolver));
            assetManager.load(soundFilenamePath, Sound.class);
            //Until we add loading screen, just block until we load the map
            assetManager.finishLoadingAsset(soundFilenamePath);

        }
    }

    public Sound getSoundAsset(String soundFilenamePath){
        Sound sound = null;

        // once the asset manager is done loading
        if( assetManager.isLoaded(soundFilenamePath) ){
            sound = assetManager.get(soundFilenamePath,Sound.class);
        }
        return sound;
    }

    public static AssetManager getAssetManager() {
        return instance.getInstance().assetManager;
    }

    public static Skin skin(){
        return instance.getInstance().assetManager.get(SKIN);
    }
}
