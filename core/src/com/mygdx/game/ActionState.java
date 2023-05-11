package com.mygdx.game;

public class ActionState {

    public static StateValue playerStateValue = StateValue.ALLOWED_TO_ACT;



    public enum StateValue {
        ALLOWED_TO_ACT,
        NOT_ALLOWED_TO_ACT
    }
}

