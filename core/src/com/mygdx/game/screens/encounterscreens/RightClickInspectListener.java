package com.mygdx.game.screens.encounterscreens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.screens.widgets.InspectBox;

public class RightClickInspectListener extends ClickListener {

    private InputListener mouseMoved = mouseMoved();
    private Stage stage;
    private InspectBox inspectBox;

    public RightClickInspectListener(Stage stage, InspectBox inspectBox){
        super(Input.Buttons.RIGHT);
        this.stage = stage;
        this.inspectBox = inspectBox;
    }



    @Override
    public void clicked(InputEvent event, float x, float y) {
        stage.addActor(inspectBox);
        setVisibleAtMouse(event);
        stage.addListener(mouseMoved);
        System.out.println("Entering object..");
        super.clicked(event, x, y);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        System.out.println("Leaving object");
        if(pointer == -1){
            inspectBox.setVisible(false);
            stage.removeListener(mouseMoved);
        }

        super.exit(event, x, y, pointer, toActor);
    }

    private InputListener mouseMoved(){
        return new InputListener(){
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                System.out.println("mouse moved to: "+ x +", " +  y);
                setVisibleAtMouse(event);
                return super.mouseMoved(event, x, y);
            }
        };
    }

    private void setVisibleAtMouse(InputEvent event){
        inspectBox.setVisible(true);
        inspectBox.setPosition(event.getStageX()+ 10, event.getStageY() + 10);
    }

}
