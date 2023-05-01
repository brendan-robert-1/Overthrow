package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.mygdx.game.screens.MainMenuScreen;

public class OverthrowGame extends Game {

	@Override
	public void create () {
		setScreen(new MainMenuScreen());
	}
}