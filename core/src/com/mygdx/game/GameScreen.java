package com.mygdx.game;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    final TankStars game;

    Texture tankImage1;
    Texture tankImage2;
    Texture terrainImage;
    Texture hpBarImage;
    Texture bgImage;
    Texture pauseButtonImage;
    Texture tree1Image;
    Texture tree2Image;
    Texture tree3Image;
    Texture tree4Image;
    Texture tower1Image;
    Texture tower2Image;
    Texture dotsImage;
    Texture shooterImage;
    Texture fuelImage;

    Sprite bg;
    Sprite tank1;
    Sprite tank2;
    Sprite terrain;
    Sprite tree11;
    Sprite tree12;
    Sprite tree21;
    Sprite tree22;
    Sprite tree3;
    Sprite tree4;
    Sprite dots;
    Sprite tower2;
    Sprite tower1;
    Sprite hpBar;
    Sprite pauseButton;
    Sprite shooter;
    Sprite fuel;
    OrthographicCamera camera;
    Viewport viewport;
    public final int screenWidth = 1280;
    public final int screenHeight = 720;
    public GameScreen(final TankStars game) {
        this.game = game;

        camera = new OrthographicCamera();
//        viewport = new FitViewport(1280, 720, camera);
        camera.setToOrtho(false, 1280, 720);

        bgImage = new Texture(Gdx.files.internal("bg.png"));
        tankImage1 = new Texture(Gdx.files.internal("atomic-lower.png"));
        tankImage2 = new Texture(Gdx.files.internal("dubstep-mirror.png"));
        terrainImage = new Texture(Gdx.files.internal("terrain-rugged.png"));
        hpBarImage = new Texture(Gdx.files.internal("hp-bar.png"));
        pauseButtonImage = new Texture(Gdx.files.internal("pause.png"));
        tree1Image = new Texture(Gdx.files.internal("tree1.png"));
        tree2Image = new Texture(Gdx.files.internal("tree2.png"));
        tree3Image = new Texture(Gdx.files.internal("tree3.png"));
        tree4Image = new Texture(Gdx.files.internal("tree4.png"));
        tower2Image = new Texture(Gdx.files.internal("tower2.png"));
        tower1Image = new Texture(Gdx.files.internal("tower1.png"));
        dotsImage = new Texture(Gdx.files.internal("dots.png"));
        shooterImage = new Texture(Gdx.files.internal("shooter.png"));
        fuelImage = new Texture("fuel.png");

        bg = new Sprite(bgImage);
        bg.setOrigin(0, 0);
        bg.setPosition(0, 0);

        tank1 = new Sprite(tankImage1);
        bg.setOrigin(0, 0);
        tank1.setPosition(100, 200);
        shooter = new Sprite(shooterImage);
        shooter.setOrigin(0, 0);
        shooter.setScale(0.125f, 0.125f);
        shooter.rotate(35);
        shooter.setPosition(100 + 5, 200 + 10);
        dots = new Sprite(dotsImage);
        dots.setOrigin(0, 0);
        dots.setScale(0.5f, 0.5f);
        dots.setPosition(100 + 55, 200 + 15 + 40);

        tank2 = new Sprite(tankImage2);
        bg.setOrigin(0, 0);
        tank2.setPosition(screenWidth - 200 - 55, 235);

        hpBar = new Sprite(hpBarImage);
        bg.setOrigin(0, 0);
        hpBar.setPosition((float) screenWidth/2 - (float) 880/2, screenHeight - 100);

        terrain = new Sprite(terrainImage);
        bg.setOrigin(0, 0);
        terrain.setPosition(0, 0);

        tree11 = new Sprite(tree1Image);
        tree11.setOrigin(0, 0);
        tree11.setScale(0.25f, 0.25f);
        tree11.setPosition(50, 200);
        tree12 = new Sprite(tree1Image);
        tree12.setOrigin(0, 0);
        tree12.setScale(0.25f, 0.25f);
        tree12.setPosition(1280 - 50 - 50, 275);
        tree21 = new Sprite(tree2Image);
        tree21.setOrigin(0, 0);
        tree21.setScale(0.25f, 0.25f);
        tree21.setPosition(500, 235);
        tree22 = new Sprite(tree2Image);
        tree22.setOrigin(0, 0);
        tree22.setScale(0.25f, 0.25f);
        tree22.setPosition(300, 230);
        tree3 = new Sprite(tree3Image);
        tree3.setOrigin(0, 0);
        tree3.setScale(0.25f, 0.25f);
        tree3.setPosition(750, 265);
        tree4 = new Sprite(tree4Image);
        tree4.setOrigin(0, 0);
        tree4.setScale(0.25f, 0.25f);
        tree4.setPosition(900, 235);

        tower1 = new Sprite(tower1Image);
        tower1.setOrigin(0, 0);
        tower1.setScale(0.25f);
        tower1.setPosition(825, 235);
        tower2 = new Sprite(tower2Image);
        tower2.setOrigin(0, 0);
        tower2.setScale(0.25f);
        tower2.setPosition(200, 200);

        pauseButton = new Sprite(pauseButtonImage);
        pauseButton.setOrigin(0, 0);
        pauseButton.setScale(0.75f, 0.75f);
        pauseButton.setPosition(0, screenHeight - 30 - 50);

        fuel = new Sprite(fuelImage);
        fuel.setOrigin(0, 0);
        fuel.setScale(0.5f);
        fuel.setPosition(100, 180);
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        bg.draw(game.batch);
        tree11.draw(game.batch);
        tree12.draw(game.batch);
        tree21.draw(game.batch);
        tree22.draw(game.batch);
        tree3.draw(game.batch);
        tree4.draw(game.batch);
        tower1.draw(game.batch);
        tower2.draw(game.batch);
        terrain.draw(game.batch);
        tank1.draw(game.batch);
        shooter.draw(game.batch);
        dots.draw(game.batch);
        fuel.draw(game.batch);
        tank2.draw(game.batch);
        hpBar.draw(game.batch);
        pauseButton.draw(game.batch);
        game.batch.end();
    }

    public void resize(int width, int height) {
//        viewport.update(width, height);
    }

    public void show() {

    }

    public void hide() {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {
        tankImage1.dispose();
        tankImage2.dispose();
        terrainImage.dispose();
    }
}
