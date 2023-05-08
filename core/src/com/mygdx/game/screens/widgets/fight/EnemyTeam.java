package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.screens.widgets.CharacterSprite;
import com.mygdx.game.screens.widgets.InspectBox;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.EnemySlots;

public class EnemyTeam extends Table {

    private EnemySlots enemySlots;

    public EnemyTeam(EnemySlots enemySlots){
      super(Assets.skin());
        this.enemySlots = enemySlots;
        update();

        this.pack();
    }


    public void update(){
        this.clearChildren();
        Character firstEnemy = enemySlots.getFirstCharacter();
        Character secondEnemy = enemySlots.getSecondCharacter();
        Character thirdEnemy = enemySlots.getThirdCharacter();
        Character fourthEnemy = enemySlots.getFourthCharacter();

        if(firstEnemy == null || firstEnemy.getHp() <= 0 ){
            enemySlots.setFirstCharacter(null);
        } else {
            this.add(addEnemyPanel(firstEnemy)).expand();
        }

        if(secondEnemy == null || secondEnemy.getHp() <= 0 ){
            enemySlots.setSecondCharacter(null);
        } else {
            this.add(addEnemyPanel(secondEnemy)).expand();
        }

        if(thirdEnemy == null || thirdEnemy.getHp()  <= 0 ){
            enemySlots.setThirdCharacter(null);
        } else {
            this.add(addEnemyPanel(thirdEnemy)).expand();
        }

        if(fourthEnemy == null || fourthEnemy.getHp() <= 0 ){
            enemySlots.setFourthCharacter(null);
        } else {
            this.add(addEnemyPanel(fourthEnemy)).expand();
        }

        this.pack();
    }

    private CharacterPanel addEnemyPanel(Character character) {
        CharacterPanel characterPanel = new CharacterPanel(character, Assets.skin());
        characterPanel.add(new Label("hp: " + character.getHp(), Assets.skin(), "title")).expand().fill().pad(20).align(Align.center);

        characterPanel.row();
        Image sprite = new CharacterSprite(character.getCharacterType());
        sprite.setScaling(Scaling.fit);
        characterPanel.add(sprite).width(200).height(250);
        InspectBox characterInspectBox = new InspectBox(character.getName(),
                "hp: " + character.getHp() + "\n" +
                        "armor: " + character.getHp() + "\n"+
                        "magic resistance: " + character.getHp() + "\n"+
                        "physical damage: " + character.getHp() + "\n"+
                        "magic damage: " + character.getHp() + "\n"+
                        "speed: " + character.getHp() + "\n"
        );
        // characterPanel.addListener(new RightClickInspectListener(stage, characterInspectBox));
        characterPanel.row();

        characterPanel.defaults().expand().fill();
        characterPanel.setName(character.getCharacterType().toString());
        return characterPanel;
    }



    public EnemySlots getEnemySlots() {
        return enemySlots;
    }
}
