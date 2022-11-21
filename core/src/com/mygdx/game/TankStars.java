package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TankStars extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	User user;
	FitViewport view;
	OrthographicCamera camera;

	static final int windowWidth = 800;
	static final int windowHeight = 400;

	@Override
	public void create () {
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		float aspectRatio = (float)height/(float)width;

		float viewportWidth = aspectRatio*windowWidth;
		float viewportHeight = aspectRatio*windowHeight;

		batch = new SpriteBatch();
		img = new Texture("Abrams.png");
		user = new User(img);
		camera = new OrthographicCamera();
		view = new FitViewport(1280, 720, camera);
		camera.position.set(1280/2, 720/2, 0);
		view.apply();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0.3f, 0.2f, 0.8f, 1);
		batch.begin();
		user.drawUser(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void resize(int width, int height) {
		view.update(width, height);
		camera.position.set(width/2, height/2, 0);
	}
}
