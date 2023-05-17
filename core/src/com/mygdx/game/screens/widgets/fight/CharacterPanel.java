package com.mygdx.game.screens.widgets.fight;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.StringBuilder;
import com.mygdx.game.Assets;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.render.AnimatedActor;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.*;
import com.mygdx.game.screens.widgets.Team.TeamSlotIndex;
import com.mygdx.game.screens.widgets.inventory.EquipSlots;
import com.mygdx.game.screens.widgets.inventory.InventoryItem;
import com.mygdx.game.screens.widgets.inventory.InventoryUi;
import com.mygdx.game.state.Character;
import com.mygdx.game.state.Stats;

public class CharacterPanel extends Table implements Comparable<CharacterPanel> {

    private Character character;
    private Image characterSprite;
    private Label label;
    private BuffsBar buffsBar = new BuffsBar();
    private HudTooltip hudTooltip;
    private TeamSlotIndex teamSlotIndex;
    private AnimatedActor animatedActor;
    private HudTooltipListener hudToolTipListener;
    private String hudToolTipDesc = "";
    private Stats combatStatModifiers = new Stats(); //TODO have buffs register with this and clear as part of end of combat rituals


    public void update() {
        buffsBar.update();
        label.setText("hp: " + character.getHp() + "/" + character.getMaxHp());
        hudToolTipDesc = buildCharacterTooltipDesc();
        if(animatedActor != null){
            animatedActor.removeListener(hudToolTipListener);
            hudToolTipListener = new HudTooltipListener(hudToolTipDesc);
            animatedActor.addListener(hudToolTipListener);
            this.pack();
        }
    }

    public CharacterPanel(Character character){
        super(Assets.skin());
        this.character = character;
        label = new Label("hp: " + character.getHp() + "/" + character.getMaxHp(), Assets.skin(), "title");
        this.add(label).expand().fill().pad(20).align(Align.center);

        if(character.getCharacterType() == Character.CharacterType.KNIGHT){
            this.row();
            TextureAtlas atlas = Assets.getAssetManager().get("overthrow.atlas", TextureAtlas.class);
                    Animation<TextureRegion> idleKnight = new Animation<TextureRegion>(0.1f, atlas.findRegions("KNIGHT"), Animation.PlayMode.LOOP);
                    animatedActor = new AnimatedActor(idleKnight);
                    animatedActor.setScaling(Scaling.fit);
                    hudToolTipDesc = buildCharacterTooltipDesc();
                    hudToolTipListener = new HudTooltipListener(hudToolTipDesc);
                    animatedActor.addListener(hudToolTipListener);
                    this.add(animatedActor).width(200).height(250);
        }else {
            this.row();
            Image sprite = new CharacterSprite(character.getCharacterType());
            sprite.setScaling(Scaling.fit);
            this.characterSprite = sprite;
            this.add(sprite).width(200).height(250);
        }
        this.row();
        this.add(buffsBar).expandX().height(32).left();
        this.defaults().expand().fill();
        this.setName(character.getCharacterType().toString());
        this.pack();
    }



    private String buildCharacterTooltipDesc() {
        StringBuilder sb = new StringBuilder();
        sb.append(character.getName());
        sb.append(System.getProperty("line.separator"));
        sb.append("hp: " + character.getHp() + "/" + character.getMaxHp());
        sb.append(System.getProperty("line.separator"));
        sb.append("Armor: " + getFullyModifiedStats().getArmor());
        sb.append(System.getProperty("line.separator"));
        sb.append("Magic Resistance: " + getFullyModifiedStats().getMagicResistance());
        sb.append(System.getProperty("line.separator"));
        sb.append("Physical Damage: " + getFullyModifiedStats().getPhysicalDamage());
        sb.append(System.getProperty("line.separator"));
        sb.append("Magical Damage: " + getFullyModifiedStats().getMagicalDamage());
        sb.append(System.getProperty("line.separator"));
        sb.append("Speed: " + getFullyModifiedStats().getSpeed());
        return sb.toString();
    }

    public Stats getFullyModifiedStats(){
        Stats finalStats = character.getStats();
        finalStats.applyModifiers(combatStatModifiers);
        finalStats.applyModifiers(getEquippedGearStatModifiers());
        return finalStats;
    }

    public Stats getEquippedGearStatModifiers(){
        Stats finalStatModifiers = new Stats();
        EquipSlots equipSlots = getEquipSlots();
        if(equipSlots != null){
            InventoryItem head = equipSlots.getHeadSlot().getTopInventoryItem();
            InventoryItem cape = equipSlots.getCapeSlot().getTopInventoryItem();
            InventoryItem necklace = equipSlots.getNecklaceSlot().getTopInventoryItem();
            InventoryItem earring = equipSlots.getEarringSlot().getTopInventoryItem();
            InventoryItem chest = equipSlots.getChestSlot().getTopInventoryItem();
            InventoryItem legs = equipSlots.getLegSlot().getTopInventoryItem();
            InventoryItem gloves = equipSlots.getGloveSlot().getTopInventoryItem();
            InventoryItem feet = equipSlots.getFeetSlot().getTopInventoryItem();
            InventoryItem weapon = equipSlots.getWeapon1Slot().getTopInventoryItem();
            InventoryItem shield = equipSlots.getWeapon2Slot().getTopInventoryItem();

            if(head != null){
                finalStatModifiers.applyModifiers(head.getStatModifiers());
            }
            if(cape != null){
                finalStatModifiers.applyModifiers(cape.getStatModifiers());
            }
            if(necklace != null){
                finalStatModifiers.applyModifiers(necklace.getStatModifiers());
            }
            if(earring != null){
                finalStatModifiers.applyModifiers(earring.getStatModifiers());
            }
            if(chest != null){
                finalStatModifiers.applyModifiers(chest.getStatModifiers());
            }
            if(legs != null){
                finalStatModifiers.applyModifiers(legs.getStatModifiers());
            }
            if(gloves != null){
                finalStatModifiers.applyModifiers(gloves.getStatModifiers());
            }
            if(feet != null){
                finalStatModifiers.applyModifiers(feet.getStatModifiers());
            }
            if(weapon != null){
                finalStatModifiers.applyModifiers(weapon.getStatModifiers());
            }
            if(shield != null){
                finalStatModifiers.applyModifiers(shield.getStatModifiers());
            }
        }

       return finalStatModifiers;
    }





    private EquipSlots getEquipSlots() {
        if(teamSlotIndex == null){return null;}
        switch(teamSlotIndex){
            case FIRST -> {return InventoryUi.getInstance().getCharacter1EquipSlots();}
            case SECOND -> {return InventoryUi.getInstance().getCharacter2EquipSlots();}
            case THIRD -> {return InventoryUi.getInstance().getCharacter3EquipSlots();}
            case FOURTH -> {return InventoryUi.getInstance().getCharacter4EquipSlots();}

            default -> throw new IllegalStateException("Unexpected value: " + teamSlotIndex);
        }
    }



    public static CharacterPanel from(CharacterPanel c) {
        return new CharacterPanel(
                new Character(c.getCharacter()));
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
        if(this.getCharacter().getHp() <= 0){
            this.remove();
        }
        this.update();
    }

    //bounds percent to have it make sense
    public void decreaseHpByPercent(int decreaseByPercent){
        if(decreaseByPercent > 100){
            decreaseByPercent = 100;
        }
        if(decreaseByPercent < 1){
            decreaseByPercent = 0;
        }
        //Rounded down
        int amountToDamage = this.getCharacter().getHp() / decreaseByPercent;
       decreaseHpBy(amountToDamage);
    }

    public void increaseHpBy(int increaseBy){
        Character character  = this.getCharacter();
        character.setHp(character.getHp() + increaseBy);
        if(character.getHp() <=0){
            this.remove();
        }
        if(character.getHp() > character.getMaxHp()){
            character.setHp(character.getMaxHp());
        }
        this.update();
    }



    public void increaseMaxHpBy(int partyMaxHpIncrease) {
        Character character = this.getCharacter();
        character.setMaxHp(character.getMaxHp() + partyMaxHpIncrease);
        character.increaseHpBy(partyMaxHpIncrease);
        this.update();
    }



    public TeamSlotIndex getTeamSlotIndex() {
        return teamSlotIndex;
    }



    public CharacterPanel setTeamSlotIndex(TeamSlotIndex teamSlotIndex) {
        this.teamSlotIndex = teamSlotIndex;
        return this;
    }



    public Stats getCombatStatModifiers() {
        return combatStatModifiers;
    }



    public CharacterPanel setCombatStatModifiers(Stats combatStatModifiers) {
        this.combatStatModifiers = combatStatModifiers;
        return this;
    }
}
