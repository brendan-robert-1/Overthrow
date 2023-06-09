package com.mygdx.game.screens.encounterscreens.combat;

import com.badlogic.gdx.utils.Timer.Task;
import com.mygdx.game.character.buff.Buff;
import com.mygdx.game.screens.encounterscreens.MainGameScreen;
import com.mygdx.game.screens.widgets.Team;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;
import com.mygdx.game.screens.widgets.fight.FightNode;
import com.mygdx.game.screens.widgets.fight.FightObserver;
import com.mygdx.game.screens.widgets.fight.FightSubject;
import com.mygdx.game.state.Character;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EndOfTurnRunnable extends Task implements FightSubject {
    private static final Logger logger = LogManager.getLogger(EndOfTurnRunnable.class);
    private FightNode fight;
    private Team team = Team.getInstance();

    public EndOfTurnRunnable(FightNode fight) {
        this.fight = fight;
    }

    @Override
    public void run() {
        logger.info("Processing end of turn events");

        team.streamNonNull().forEach(characterPanel -> {
            processEndOfTurnDebuffFor(characterPanel);
            processEndOfTurnBuffFor(characterPanel);
            characterPanel.getBuffsBar().removeFinishedBuffs();
            characterPanel.update();
        });
        fight.getEnemyTeam().streamNonNull().forEach(enemyPanel -> {
            processEndOfTurnDebuffFor(enemyPanel);
            processEndOfTurnBuffFor(enemyPanel);
            enemyPanel.getBuffsBar().removeFinishedBuffs();
            enemyPanel.update();
        });
        fight.resolveDeaths();
        Team.getInstance().resolveDeaths();
        fight.update();
        logger.info("Ending turn.\n");
        notify("", FightObserver.FightEvent.TURN_OVER);
    }

    private void processEndOfTurnDebuffFor(CharacterPanel characterPanel) {
        if(characterPanel == null){return;}
        Character character = characterPanel.getCharacter();
        if(character == null) return;

        List<Buff> debuffs = characterPanel.getBuffsBar().getDebuffs();
        if(debuffs == null) {debuffs = new ArrayList<>();}
        debuffs.forEach(debuff -> {
            debuff.executeEndOfTurn(characterPanel);
            characterPanel.animateDebuff(debuff);
            debuff.reduceTurnsRemaining(1);
        });

    }

    private void processEndOfTurnBuffFor(CharacterPanel characterPanel) {
        if(characterPanel == null){return;}
        Character character = characterPanel.getCharacter();
        if(character == null) return;
        List<Buff> buffs = characterPanel.getBuffsBar().getBuffs();
        if(buffs == null) {buffs = new ArrayList<>();}
        buffs.forEach(buff -> {
            buff.executeEndOfTurn(characterPanel);
            buff.reduceTurnsRemaining(1);
        });
    }



    @Override
    public void notify(String eventd, FightObserver.FightEvent event) {
        MainGameScreen.getInstance().onNotify("", FightObserver.FightEvent.TURN_OVER);
    }
}
