package com.mygdx.game.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.mygdx.game.Assets;

public class ColorDrawable extends BaseDrawable {
    private float r, g, b, a;
    private Color savedBatchColor = new Color();

    public ColorDrawable(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        // Save the batch colour as we are about to change it
        savedBatchColor.set(batch.getColor());
        batch.setColor(r, g, b, a);
        // Draw a white texture with the current batch colour

        batch.setColor(savedBatchColor);
    }
}