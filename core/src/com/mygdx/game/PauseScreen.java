package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import org.w3c.dom.Text;

public class PauseScreen implements Screen {

    public final TankStars game;
    OrthographicCamera camera;
    public MiscUIElement gameScreenBlurred;
    public MiscUIElement pauseMenuBg;
    public Button resumeButton;
    public Button saveButton;
    public Button exitToMenuButton;
    public Button settingsButton;

    public TextElement resumeGameText;
    public TextElement saveGameText;
    public TextElement exitToMenuText;
    public TextElement settingsText;

    public Texture gameScreenBlurredTexture;
    public Texture buttonYellowTexture;
    public Texture buttonRedTexture;
    public Texture pauseMenuBgTexture;
    public Texture resumeGameTextImage;
    public Texture saveGameTextImage;
    public Texture exitToMenuTextImage;
    public Texture settingsTextImage;
    Vector3 touchPoint = new Vector3();

    public PauseScreen(TankStars game) {
        this.game = game;
//        camera = new OrthographicCamera((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);
        camera = new OrthographicCamera();
//        viewport = new FitViewport(1280, 720, camera);
        camera.setToOrtho(false, 1280, 720);
        gameScreenBlurredTexture = new Texture("GameScreenBgBlurred.png");
        pauseMenuBgTexture = new Texture("PauseMenuBg.png");
        buttonYellowTexture = new Texture("MainMenuButton.png");
        buttonRedTexture = new Texture("ExitButton.png");
        resumeGameTextImage = new Texture("ResumeGameText.png");
        saveGameTextImage = new Texture("SaveGameText.png");
        exitToMenuTextImage = new Texture("ExitText.png");

        gameScreenBlurred = new MiscUIElement(gameScreenBlurredTexture);
        pauseMenuBg = new MiscUIElement(pauseMenuBgTexture);
        resumeButton = new Button(buttonYellowTexture);
        saveButton = new Button(buttonYellowTexture);
        exitToMenuButton = new Button(buttonRedTexture);
        resumeGameText = new TextElement(resumeGameTextImage);
        saveGameText = new TextElement(saveGameTextImage);
        exitToMenuText = new TextElement(exitToMenuTextImage);
        settingsButton = new Button(buttonYellowTexture);
        settingsTextImage = new Texture("Settings.png");
        settingsText = new TextElement(settingsTextImage);

        gameScreenBlurred.sprite.setScale(1.01f);

        pauseMenuBg.sprite.setOrigin(0,0);
        pauseMenuBg.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - pauseMenuBg.sprite.getWidth()/2 - 10, (float) Gdx.graphics.getHeight()/2 - pauseMenuBg.sprite.getHeight()/2 - 10);

        resumeButton.sprite.setOrigin(0,0);
        resumeButton.sprite.setScale(0.5f);
        resumeButton.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - (resumeButton.sprite.getWidth()*resumeButton.sprite.getScaleX())/2, (float) Gdx.graphics.getHeight()/2 + 110);

        saveButton.sprite.setOrigin(0,0);
        saveButton.sprite.setScale(0.5f);
        saveButton.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - (saveButton.sprite.getWidth()*saveButton.sprite.getScaleX())/2, (float) Gdx.graphics.getHeight()/2 - (saveButton.sprite.getHeight() * saveButton.sprite.getScaleY())/2 + 40);

        settingsButton.sprite.setOrigin(0,0);
        settingsButton.sprite.setScale(0.5f);
        settingsButton.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - (settingsButton.sprite.getWidth()*settingsButton.sprite.getScaleX())/2, (float) Gdx.graphics.getHeight()/2 - (settingsButton.sprite.getHeight() * settingsButton.sprite.getScaleY())/2 - 75);

        exitToMenuButton.sprite.setOrigin(0,0);
        exitToMenuButton.sprite.setScale(0.5f);
        exitToMenuButton.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - (exitToMenuButton.sprite.getWidth()*exitToMenuButton.sprite.getScaleX())/2, (float) Gdx.graphics.getHeight()/2 - (pauseMenuBg.sprite.getHeight()*pauseMenuBg.sprite.getScaleY()/3) -40 );

        resumeGameText.sprite.setOrigin(0,0);
        resumeGameText.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - resumeGameText.sprite.getWidth()/2, (float) Gdx.graphics.getHeight()/2 + (resumeButton.sprite.getHeight() * resumeButton.sprite.getScaleY()/2)+ 98);

        saveGameText.sprite.setOrigin(0,0);
        saveGameText.sprite.setPosition((float) Gdx.graphics.getWidth()/2- saveGameText.sprite.getWidth()/2, (float) Gdx.graphics.getHeight()/2+30);

        settingsText.sprite.setOrigin(0,0);
        settingsText.sprite.setPosition((float) Gdx.graphics.getWidth()/2- settingsText.sprite.getWidth()/2, (float) Gdx.graphics.getHeight()/2-90);

        exitToMenuText.sprite.setOrigin(0,0);
        exitToMenuText.sprite.setPosition((float) Gdx.graphics.getWidth()/2- exitToMenuText.sprite.getWidth()/2, (float) Gdx.graphics.getHeight()/2 - 222);
    }

    void update() {
        if(Gdx.input.justTouched()) {
            //unprojects the camera
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if(resumeButton.sprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                game.setScreen(game.gameScreen);
            }
            if (exitToMenuButton.sprite.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                game.setScreen(game.mainMenuScreen);
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();
        camera.update();
        game.batch.begin();
        //Draw stuff here
        gameScreenBlurred.drawElement(game.batch);
        pauseMenuBg.drawElement(game.batch);
        resumeButton.drawButton(game.batch);
        saveButton.drawButton(game.batch);
        exitToMenuButton.drawButton(game.batch);
        resumeGameText.drawElement(game.batch);
        saveGameText.drawElement(game.batch);
        exitToMenuText.drawElement(game.batch);
        settingsButton.drawButton(game.batch);
        settingsText.drawElement(game.batch);
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
        gameScreenBlurredTexture.dispose();
        pauseMenuBgTexture.dispose();
        buttonRedTexture.dispose();
        exitToMenuTextImage.dispose();
        saveGameTextImage.dispose();
        resumeGameTextImage.dispose();
        buttonYellowTexture.dispose();
    }
}
