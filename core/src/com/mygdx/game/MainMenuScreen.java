package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    OrthographicCamera camera;
    final TankStars game;
    Tank dubstepTank;
    Tank atomicTank;
    Ground ground;
    Button buttonNewGame;
    Button buttonLoadGame;
    Button buttonMisc;
    Button buttonExit;

    //Textures
    Texture dubstepTexture;
    Texture atomicTexture;
    Texture groundTexture;
    TextureRegion groundRegion;
    Texture buttonYellowTexture;
    Texture buttonRedTexture;

    FreeTypeFontGenerator generator;
    FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    BitmapFont menuText;

    //Design
    private int paddingX = 10;
    private int paddingY = 50;
    public static final Color BACKGROUND = new Color(0x1A2969ff);

    public MainMenuScreen(final TankStars game) {
        this.game = game;
        this.camera = new OrthographicCamera((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("Arial.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        menuText = generator.generateFont(parameter);

        //Textures
        dubstepTexture = new Texture("DubstepTank.png");
        atomicTexture = new Texture("AtomicTank.png");
        groundTexture = new Texture("ground_purple.png");
        groundTexture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat);
        buttonYellowTexture = new Texture("MainMenuButton.png");
        buttonRedTexture = new Texture("ExitButton.png");
        groundRegion = new TextureRegion(groundTexture);



        dubstepTank = new Tank(dubstepTexture);
        atomicTank = new Tank(atomicTexture);
        ground = new Ground(groundTexture);
        buttonNewGame = new Button(buttonYellowTexture);
        buttonLoadGame = new Button (buttonYellowTexture);
        buttonMisc = new Button (buttonYellowTexture);
        buttonExit = new Button(buttonRedTexture);


        ground.sprite.setOrigin(0,0);
        ground.sprite.setScale(1.25f, 1.25f);
//		groundRegion.setRegion(0,0,Gdx.graphics.getWidth(),ground.sprite.getHeight()*ground.sprite.getScaleY());
//		groundRegion.setRegion(0,0,1280, 0);
        ground.sprite.setPosition(0,paddingY-ground.sprite.getHeight()*ground.sprite.getScaleY());

        dubstepTank.sprite.setOrigin(0,0);
        dubstepTank.sprite.setScale(1);
        dubstepTank.sprite.setPosition(paddingX,paddingY);

        atomicTank.sprite.setOrigin(0,0);
        atomicTank.sprite.setScale(-1,1);
        atomicTank.sprite.setPosition(Gdx.graphics.getWidth()-paddingX,paddingY);


        //Buttons
        buttonNewGame.sprite.setOrigin(0,0);
        buttonNewGame.sprite.setScale(0.5f);
        float shiftX = (buttonNewGame.sprite.getScaleX()*buttonNewGame.sprite.getWidth())/2;
        float shiftY = (buttonNewGame.sprite.getScaleY()*buttonNewGame.sprite.getHeight())/2;
        buttonNewGame.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - shiftX, (float) 5*Gdx.graphics.getHeight()/7 - shiftY);


        buttonLoadGame.sprite.setOrigin(0,0);
        buttonLoadGame.sprite.setScale(0.5f);
        buttonLoadGame.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - shiftX, (float) 4*Gdx.graphics.getHeight()/7 - shiftY);

        buttonMisc.sprite.setOrigin(0,0);
        buttonMisc.sprite.setScale(0.5f);
        buttonMisc.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - shiftX, (float) 3*Gdx.graphics.getHeight()/7 - shiftY);

        buttonExit.sprite.setOrigin(0,0);
        buttonExit.sprite.setScale(0.5f);
        float shiftRedX = (buttonExit.sprite.getScaleX()*buttonExit.sprite.getWidth())/2;
        float shiftRedY = (buttonExit.sprite.getScaleY()*buttonExit.sprite.getHeight())/2;
        buttonExit.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - shiftRedX, (float) 2*Gdx.graphics.getHeight()/7 - shiftRedY);
    }
    @Override
    public void render(float delta) {
        ScreenUtils.clear(BACKGROUND);
        game.batch.begin();
        //Draw stuff here
        dubstepTank.drawTank(game.batch);
        atomicTank.drawTank(game.batch);
        ground.drawGround(game.batch);
        buttonNewGame.drawButton(game.batch);
        buttonLoadGame.drawButton(game.batch);
        buttonMisc.drawButton(game.batch);
        buttonExit.drawButton(game.batch);

        menuText.setColor(Color.WHITE);
        menuText.draw(game.batch, "Hello...", 100,100);

//		batch.draw(tank,0,0);
        game.batch.end();
    }
    @Override
    public void dispose() {
        game.batch.dispose();
        dubstepTexture.dispose();
        atomicTexture.dispose();
        generator.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
