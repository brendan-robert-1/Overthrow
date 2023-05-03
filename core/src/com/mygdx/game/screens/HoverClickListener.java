package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.screens.widgets.HoverBox;

public class HoverClickListener extends ClickListener {

    private InputListener mouseMoved = mouseMoved();
    private Stage stage;
    private HoverBox hoverBox;

    public HoverClickListener(Stage stage, HoverBox toDisplay){
        this.stage = stage;
        this.hoverBox = toDisplay;
    }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            stage.addActor(hoverBox);
            stage.addListener(mouseMoved);
            System.out.println("Entering object..");
            super.enter(event, x, y, pointer, fromActor);
        }



        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            System.out.println("Leaving object");
            hoverBox.setVisible(false);
            stage.removeListener(mouseMoved);
            super.exit(event, x, y, pointer, toActor);
        }

        private InputListener mouseMoved(){
            return new InputListener(){
                @Override
                public boolean mouseMoved(InputEvent event, float x, float y) {
                    hoverBox.setVisible(true);
                    hoverBox.setPosition(event.getStageX()+ 10, event.getStageY() + 10);
                    return super.mouseMoved(event, x, y);
                }
            };
        }

}
