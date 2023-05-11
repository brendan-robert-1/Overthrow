package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.mygdx.game.Assets;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.screens.widgets.BuffsBar;
import com.mygdx.game.screens.widgets.CharacterSprite;
import com.mygdx.game.state.Character;

public class CharacterPanel extends Table implements Comparable<CharacterPanel> {

    private Character character;
    private Image characterSprite;
    private Label label;
    private BuffsBar buffsBar = new BuffsBar();


    public void update() {
        buffsBar.update();
        label.setText("hp: " + character.getHp());
        this.pack();
    }

    public CharacterPanel(Character character){
        super(Assets.skin());
        this.character = character;
        label = new Label("hp: " + character.getHp(), Assets.skin(), "title");
        this.add(label).expand().fill().pad(20).align(Align.center);

        this.row();
        Image sprite = new CharacterSprite(character.getCharacterType());
        sprite.setScaling(Scaling.fit);
        this.characterSprite = sprite;
        this.add(sprite).width(200).height(250);

        this.row();
        this.add(buffsBar).expandX().height(32).left();
        this.defaults().expand().fill();
        this.setName(character.getCharacterType().toString());
        this.pack();
    }


    public static CharacterPanel from(CharacterPanel c) {
        return new CharacterPanel(
                new Character(c.getCharacter())
        );
    }





    public Character getCharacter() {
        return character;
    }



    public Image getCharacterSprite() {
        return characterSprite;
    }



    public void animateDebuff(Buff debuff) {
        buffsBar.animateDebuff(debuff);
    }



    public Label getLabel() {
        return label;
    }



    public BuffsBar getBuffsBar() {
        return buffsBar;
    }


    @Override
    public int compareTo(CharacterPanel o) {
        return Integer.compare(character.getChargeTime(), o.getCharacter().getChargeTime());
    }

    public int getChargeTime(){
        return character.getChargeTime();
    }

    public int getHp(){
        return character.getHp();
    }

    public void decreaseHpBy(int decreaseBy){
        this.getCharacter().setHp(this.character.getHp() - decreaseBy);
        if(this.getCharacter().getHp() <=0){
            this.remove();
        }
    }
}
