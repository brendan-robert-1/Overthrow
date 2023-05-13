package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.Assets;
import com.mygdx.game.state.Character;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class EnemyTeam extends Table {

    private CharacterPanel firstCharacterPanel;
    private CharacterPanel secondCharacterPanel;
    private CharacterPanel thirdCharacterPanel;
    private CharacterPanel fourthCharacterPanel;

    public EnemyTeam(CharacterPanel firstCharacterPanel, CharacterPanel secondCharacterPanel, CharacterPanel thirdCharacterPanel, CharacterPanel fourthCharacterPanel){
      super(Assets.skin());
        this.firstCharacterPanel = firstCharacterPanel;
        this.secondCharacterPanel = secondCharacterPanel;
        this.thirdCharacterPanel = thirdCharacterPanel;
        this.fourthCharacterPanel  = fourthCharacterPanel;
        init();
        this.pack();
    }



    public void init(){
        this.clearChildren();

        if(firstCharacterPanel != null && firstCharacterPanel.getCharacter() != null){
            this.firstCharacterPanel = new CharacterPanel(firstCharacterPanel.getCharacter());
            this.add(firstCharacterPanel).expand();
        }
        if(secondCharacterPanel != null && secondCharacterPanel.getCharacter() != null){
            this.secondCharacterPanel = new CharacterPanel(secondCharacterPanel.getCharacter());
            this.add(secondCharacterPanel).expand();
        }
        if(thirdCharacterPanel != null && thirdCharacterPanel.getCharacter() != null){
            this.thirdCharacterPanel = new CharacterPanel(thirdCharacterPanel.getCharacter());
            this.add(thirdCharacterPanel).expand();
        }
        if(fourthCharacterPanel != null && fourthCharacterPanel.getCharacter() != null){
            this.fourthCharacterPanel = new CharacterPanel(fourthCharacterPanel.getCharacter());
            this.add(fourthCharacterPanel).expand();
        }


        this.pack();
    }


    public void animateAttack(CharacterPanel characterPanel) {
     //   characterPanel.setDebug(true);
        characterPanel.setClip(true);
        characterPanel.getCharacterSprite().addAction(Actions.sequence(
                Actions.moveBy(-50, 0, 0.5f),
                Actions.moveBy(50, 0, 0.5f)
        ));
        this.pack();
    }

    public Stream<CharacterPanel> streamNonNull(){
        return Arrays.asList(
                firstCharacterPanel,
                secondCharacterPanel,
                thirdCharacterPanel,
                fourthCharacterPanel
        ).stream().filter(Objects::nonNull);
    }



    public void removeDead(CharacterPanel enemyPanel) {
        if(firstCharacterPanel == enemyPanel){
            firstCharacterPanel = null;
        }
        if(secondCharacterPanel == enemyPanel){
            secondCharacterPanel = null;
        }
        if(thirdCharacterPanel == enemyPanel){
            thirdCharacterPanel = null;
        }
        if(fourthCharacterPanel == enemyPanel){
            fourthCharacterPanel = null;
        }
        this.pack();
    }
}
