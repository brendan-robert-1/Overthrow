package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameWorld {

    private AssetManager assetManager;
    private SpriteBatch spriteBatch;
    private Viewport viewport;


    public GameWorld(AssetManager assetManager) {
        this.assetManager = assetManager;
        spriteBatch = new SpriteBatch();
        viewport = new ExtendViewport(1920, 1080);
    }



    public void update(float delta){

    }

    //abstract this out of here
    public void draw(){
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
    }



    public Viewport getViewport() {
        return viewport;
    }
}
