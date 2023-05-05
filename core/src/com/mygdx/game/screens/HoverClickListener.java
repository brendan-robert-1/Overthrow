package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.screens.encounterscreens.StageManager;
import com.mygdx.game.screens.widgets.InspectBox;

public class HoverClickListener extends ClickListener {

    private InputListener mouseMoved = mouseMoved();
    private Stage stage = StageManager.getInstance().getStage();
    private InspectBox inspectBox;

    public HoverClickListener(String title, String description){
        this( new InspectBox(title, description));
    }

    public HoverClickListener(InspectBox toDisplay){
        this.inspectBox = toDisplay;
    }

        @Override
        public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            inspectBox.setZIndex(stage.getActors().size);
            stage.addActor(inspectBox);
            stage.addListener(mouseMoved);
            System.out.println("Entering object..");
            super.enter(event, x, y, pointer, fromActor);
        }



        @Override
        public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
            if(pointer == -1){
                System.out.println("Leaving object");
                inspectBox.setVisible(false);
                stage.removeListener(mouseMoved);
            }
            super.exit(event, x, y, pointer, toActor);
        }

        private InputListener mouseMoved(){
            return new InputListener(){
                @Override
                public boolean mouseMoved(InputEvent event, float x, float y) {
                    inspectBox.setZIndex(stage.getActors().size);
                    inspectBox.setVisible(true);
                    inspectBox.setPosition(event.getStageX()+ 10, event.getStageY() + 10);
                    return super.mouseMoved(event, x, y);
                }
            };
        }

}
