package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class TankStars extends ApplicationAdapter {
	SpriteBatch batch;
	OrthographicCamera camera;

	//Class Instances
	Tank dubstepTank;
	Tank atomicTank;
	Ground ground;
	Button button;

	//Textures
	Texture dubstepTexture;
	Texture atomicTexture;
	Texture groundTexture;
//	TextureRegion groundRegion;
	Texture buttonTexture;

	//Design
	private int paddingX = 10;
	private int paddingY = 50;
	public static final Color BACKGROUND = new Color(0x1A2969ff);
	@Override
	public void create() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);

		//Textures
		dubstepTexture = new Texture("DubstepTank.png");
		atomicTexture = new Texture("AtomicTank.png");
		groundTexture = new Texture("ground_purple.png");
		groundTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
		buttonTexture = new Texture("MainMenuButton.png");
//		groundRegion = new TextureRegion(groundTexture,0,0,Gdx.graphics.getWidth(),groundTexture.getHeight());


		dubstepTank = new Tank(dubstepTexture);
		atomicTank = new Tank(atomicTexture);
		ground = new Ground(groundTexture);
		button = new Button(buttonTexture);

		ground.sprite.setOrigin(0,0);
		ground.sprite.setScale(0.5f);
//		groundRegion.setRegion(0,0,1280, 0);
		ground.sprite.setPosition(0,paddingY-ground.sprite.getHeight()*ground.sprite.getScaleY());

		dubstepTank.sprite.setOrigin(0,0);
		dubstepTank.sprite.setScale(1);
		dubstepTank.sprite.setPosition(paddingX,paddingY);

		atomicTank.sprite.setOrigin(0,0);
		atomicTank.sprite.setScale(-1,1);
		atomicTank.sprite.setPosition(Gdx.graphics.getWidth()-paddingX,paddingY);

		button.sprite.setOrigin(0,0);
		button.sprite.setScale(0.5f);
		float shiftX = (button.sprite.getScaleX()*button.sprite.getWidth())/2;
		float shiftY = (button.sprite.getScaleY()*button.sprite.getHeight())/2;
		button.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - shiftX, (float) Gdx.graphics.getHeight()/2 - shiftY);

	}
	@Override
	public void render() {
		ScreenUtils.clear(BACKGROUND);
		batch.begin();
		//Draw stuff here
		dubstepTank.drawTank(batch);
		atomicTank.drawTank(batch);
		ground.drawGround(batch);
		button.drawButton(batch);
//		batch.draw(tank,0,0);
		batch.end();
	}
	@Override
	public void dispose() {
		batch.dispose();
		dubstepTexture.dispose();
		atomicTexture.dispose();
	}
}
