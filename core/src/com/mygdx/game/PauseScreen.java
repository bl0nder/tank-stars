package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import org.w3c.dom.Text;

public class PauseScreen implements Screen {

    public TankStars game;
    OrthographicCamera camera;
    public MiscUIElement gameScreenBlurred;
    public MiscUIElement pauseMenuBg;
    public Button resumeButton;
    public Button saveButton;
    public Button exitToMenuButton;
    public Texture gameScreenBlurredTexture;
    public Texture buttonYellowTexture;
    public Texture buttonRedTexture;
    public Texture pauseMenuBgTexture;

    public PauseScreen(TankStars game) {
        this.game = game;
        camera = new OrthographicCamera((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);

        gameScreenBlurredTexture = new Texture("GameScreenBgBlurred.png");
        pauseMenuBgTexture = new Texture("PauseMenuBg.png");
        buttonYellowTexture = new Texture("MainMenuButton.png");
        buttonRedTexture = new Texture("ExitButton.png");

        gameScreenBlurred = new MiscUIElement(gameScreenBlurredTexture);
        pauseMenuBg = new MiscUIElement(pauseMenuBgTexture);
        resumeButton = new Button(buttonYellowTexture);
        saveButton = new Button(buttonYellowTexture);
        exitToMenuButton = new Button(buttonRedTexture);

        pauseMenuBg.sprite.setOrigin(0,0);
        pauseMenuBg.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - pauseMenuBg.sprite.getWidth()/2 - 10, (float) Gdx.graphics.getHeight()/2 - pauseMenuBg.sprite.getHeight()/2 - 10);

        resumeButton.sprite.setOrigin(0,0);
        resumeButton.sprite.setScale(0.5f);
        resumeButton.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - (resumeButton.sprite.getWidth()*resumeButton.sprite.getScaleX())/2, (float) Gdx.graphics.getHeight()/2 - (resumeButton.sprite.getHeight() * resumeButton.sprite.getScaleY())/2);

        saveButton.sprite.setOrigin(0,0);
        saveButton.sprite.setScale(0.5f);
        saveButton.sprite.setPosition((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);

        exitToMenuButton.sprite.setOrigin(0,0);
        exitToMenuButton.sprite.setScale(0.5f);
        exitToMenuButton.sprite.setPosition((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        //Draw stuff here
        gameScreenBlurred.drawElement(game.batch);
        pauseMenuBg.drawElement(game.batch);
        resumeButton.drawButton(game.batch);
        saveButton.drawButton(game.batch);
        exitToMenuButton.drawButton(game.batch);
        game.batch.end();
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

    @Override
    public void dispose() {

    }
}
