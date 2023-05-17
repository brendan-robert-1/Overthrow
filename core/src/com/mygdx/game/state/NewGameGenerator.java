package com.mygdx.game.state;

import com.mygdx.game.encounters.GameNode;
import com.mygdx.game.encounters.OverthrowActs.ActType;
import com.mygdx.game.screens.widgets.Team;
import com.mygdx.game.screens.widgets.TopBar;
import com.mygdx.game.screens.widgets.fight.CharacterPanel;

import java.util.UUID;

public class NewGameGenerator {
    public static void generateNewGame(Character.CharacterType characterType){
        UUID seed = UUID.randomUUID();
        MapGraph graph = MapGraph.buildNormalGameMap(seed, ActType.FARMS);
        GameNode currentNode = graph.getNodesForFloor(0).get(0);
        graph.printMap(currentNode);
        GameState state = GameState.getInstance();
        state.setRunSeed(seed);
        Team.getInstance().init( new CharacterPanel(Character.generateNewCharacter(characterType)), null, null, null);
        TopBar.getInstance().setCoin(15);
        state.setMapGraph(graph);
        state.setCurrentNode(currentNode);
        state.setCurrentFloor(0);
    }
}


