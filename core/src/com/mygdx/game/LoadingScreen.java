package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class LoadingScreen implements Screen {
    public OrthographicCamera camera;
    public TankStars game;
    public Texture bgTexture;
    public Texture loaderTexture;
    public MiscUIElement bg;
    public MiscUIElement loader;

    public LoadingScreen(TankStars game) {
        this.game = game;
        camera = new OrthographicCamera((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);
        bgTexture = new Texture("LoadingScreenBg.png");
        bg = new MiscUIElement(bgTexture);
        loaderTexture = new Texture ("Loader.png");
        loader = new MiscUIElement(loaderTexture);


        bg.sprite.setOrigin(0,0);
        bg.sprite.setScale(1f);

        loader.sprite.setOrigin(0,0);
        loader.sprite.setScale(1f);
        loader.sprite.setPosition((float) Gdx.graphics.getWidth()/2 - (loader.sprite.getWidth()*loader.sprite.getScaleX()/2), 20f);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        bg.drawElement(game.batch);
        loader.drawElement(game.batch);
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
