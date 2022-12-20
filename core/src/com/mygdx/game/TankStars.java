package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TankStars extends Game {

	public SpriteBatch batch;
	public PauseScreen pauseScreen;
	public GameScreen gameScreen;
	public SelectionScreen selectionScreen;
	public MainMenuScreen mainMenuScreen;

	@Override
	public void create() {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen (this));
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
