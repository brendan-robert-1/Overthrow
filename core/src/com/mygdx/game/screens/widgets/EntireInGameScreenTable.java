package com.mygdx.game.screens.widgets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.fight.AbilitySelectPanel;
import com.mygdx.game.screens.widgets.fight.EnemyTeam;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.state.Character;

public class EntireInGameScreenTable extends Table {
    private Array<Actor> hudToolTipActors = new Array<>();
    private Team team;
    private AbilitySelectPanel abilitySelectPanel;
    private HudTooltip hudTooltip = HudTooltip.getInstance();

    public EntireInGameScreenTable(){
        abilitySelectPanel = new AbilitySelectPanel(hudTooltip);
        abilitySelectPanel.setVisible(false);
        update();
    }

    public void update(){
        this.clearChildren();
        TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
        TextureRegionDrawable trd = new TextureRegionDrawable(atlas.findRegion("farms2"));
        this.setBackground(trd);
        this.setFillParent(true);
        hudToolTipActors.add(hudTooltip);
        team = new Team();
        this.add(new TopBar()).expand().fillX().colspan(2).top();
        this.row();
        this.add(team).expand().bottom().left().pad(40);
        this.row();
        this.add(abilitySelectPanel);
        this.pack();
    }

    public Array<Actor> getHudToolTipActors() {
        return hudToolTipActors;
    }



    public void populateAbilities(Character activeCharacter, DragAndDrop abilitySelectDragAndDrop, EnemyTeam enemyTeam) {
        abilitySelectPanel.populateAbilities(activeCharacter,abilitySelectDragAndDrop, enemyTeam, team);
    }

    public void displayAbilitySelectPanel(){
        System.out.println("Displaying ability panel.");
        abilitySelectPanel.setVisible(true);
        abilitySelectPanel.pack();
    }

    public void hideAbilitySelectPanel(){
        abilitySelectPanel.setVisible(false);
    }



    public Array<Actor> getHudTooltipActors() {
        return hudToolTipActors;
    }

}
