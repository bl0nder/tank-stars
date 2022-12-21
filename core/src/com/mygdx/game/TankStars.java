package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.Serializable;

public class TankStars extends Game implements Serializable {

	public static int numSavedGames;
	public static TankStars instance;

	public SpriteBatch batch;
	public PauseScreen pauseScreen;
	public GameScreen gameScreen;
	public SelectionScreen selectionScreen;
	public MainMenuScreen mainMenuScreen;

	private TankStars() {

	}

	public static TankStars getInstance() {
		if (instance == null) {
			instance = new TankStars();
		}
		return instance;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		numSavedGames = 0;
		this.pauseScreen = new PauseScreen(this);
		this.gameScreen = new GameScreen(this, 0, 0);
		this.mainMenuScreen = new MainMenuScreen(this);
		this.selectionScreen = new SelectionScreen(this);
		this.setScreen(this.mainMenuScreen);
	}
	@Override
	public void render() {
		super.render();
	}
	@Override
	public void dispose() {
		batch.dispose();
	}
}
