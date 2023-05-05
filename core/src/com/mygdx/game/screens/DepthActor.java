package com.mygdx.game.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class DepthActor extends Actor implements Comparable<DepthActor>
{
    public int depth;

    @Override
    public int compareTo(DepthActor o)
    {
        return Integer.compare(depth, o.depth);
    }
}
