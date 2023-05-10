package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.encounterscreens.combat.CombatRewards;

public class CombatRewardsWindow extends Table {

    private CombatRewards combatRewards;

    public CombatRewardsWindow(){
        this.add(rewardsWindow()).expand().fill();
        this.setSize(Gdx.graphics.getWidth() - 150,Gdx.graphics.getHeight() - 150);
    }

    private Table rewardsWindow(){
        Table table = new Table(Assets.skin());
        table.setBackground(Assets.skin().getDrawable("button-up"));
        Label label = new Label("Choose a reward", Assets.skin());
        table.add(new CombatRewardOption());
        table.add(new CombatRewardOption());
        table.add(new CombatRewardOption());
        return table;
    }
}
