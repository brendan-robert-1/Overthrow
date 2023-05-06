package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.game.Assets;
import com.mygdx.game.state.Character;

public class AbilitiesPanel extends Table {
    private Character activeCharacter;
    public AbilitiesPanel(Character activeCharacter) {
        this.activeCharacter = activeCharacter;
        this.setBackground(Assets.skin().getDrawable("button-up"));
        this.setWidth(700);
        this.setHeight(60);
        this.setPosition(Gdx.graphics.getWidth()/2 - this.getWidth()/2, 0);
        Label label = new Label("Abilities for: " + activeCharacter.getName(), Assets.skin());
        PixelProTextButton ability1 = new PixelProTextButton(activeCharacter.getFirstBasicAbility().name());
        PixelProTextButton ability2 = new PixelProTextButton(activeCharacter.getSecondBasicAbility().name(), Assets.skin());
        PixelProTextButton ult = new PixelProTextButton(activeCharacter.getUltimateAbility().name(), Assets.skin());
        Table labels = new Table();
        labels.add(label).expand().top().left().padTop(20).padLeft(15);
        labels.add(ability1).expand().top().left().padTop(20).padLeft(15);
        labels.add(ability2).expand().top().left().padTop(20).padLeft(15);
        labels.add(ult).expand().top().left().padTop(20).padLeft(15);
        this.add(labels);
    }
}
