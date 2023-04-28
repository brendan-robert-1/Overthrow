package com.mygdx.game.state;

import java.util.UUID;

public class NewGameGenerator {
    public static GameState generateNewGame(Character.CharacterType characterType){
        return new GameState(
                UUID.randomUUID(),
                characterSlotsGenerator(characterType),
                99,
                new Inventory()
        );
    }
    private static CharacterSlots characterSlotsGenerator(Character.CharacterType characterType){
        return new CharacterSlots(
                Character.generateNewCharacter(characterType),
                null,
                null,
                null
        );
    }
}


