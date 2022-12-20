package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TankStars extends Game {

	public SpriteBatch batch;
	public PauseScreen pauseScreen;
	public GameScreen gameScreen;
	public SelectionScreen selectionScreen;
	public MainMenuScreen mainMenuScreen;

	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new GameScreen (this));
		this.pauseScreen = new PauseScreen(this);
		this.gameScreen = new GameScreen(this);
		this.mainMenuScreen = new MainMenuScreen(this);
		this.selectionScreen = new SelectionScreen(this);
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
