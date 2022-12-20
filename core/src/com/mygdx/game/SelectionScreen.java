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

public class SelectionScreen implements Screen {
    final TankStars game;

    Texture bgImage;
    Texture tank1Image;
    Texture tank2Image;
    Texture tank3Image;
    Texture headingImage;
    Texture descImage1;
    Texture descImage2;
    Texture descImage3;
    Texture boxImage1;
    Texture boxImage2;
    Texture boxImage3;
    Texture boxImage;
    Texture selectButtonImage;
    Texture heading2Image;
    Texture playImage;

    Sprite bg;
    Sprite tank1;
    Sprite tank2;
    Sprite tank3;
    Sprite heading;
    Sprite desc1;
    Sprite desc2;
    Sprite desc3;
    Sprite box1;
    Sprite box2;
    Sprite box3;
    Sprite box;
    Sprite selectButton;
    Sprite heading2;
    Sprite playButton;

    OrthographicCamera camera;
    Viewport viewport;
    Vector3 touchPoint = new Vector3();
    boolean b1 = false, b2 = false, b3 = false, h1 = true, h2 = false;
    int s1 = 0, s2 = 0;
    public final int screenWidth = 1280;
    public final int screenHeight = 720;
    public SelectionScreen(final TankStars game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        bgImage = new Texture("selection-bg.png");
        tank1Image = new Texture("atomic-bigger.png");
        tank2Image = new Texture("dubstep-bigger.png");
        tank3Image = new Texture("spectre-bigger.png");
        headingImage = new Texture("select-heading.png");
        descImage1 = new Texture("aheading.png");
        descImage2 = new Texture("dheading.png");
        descImage3 = new Texture("sheading.png");
        boxImage1 = new Texture("bg-boxa.png");
        boxImage2 = new Texture("bg-boxd.png");
        boxImage3 = new Texture("bg-boxs.png");
        selectButtonImage = new Texture("yellow-btn.png");
        boxImage = new Texture("bgbox.png");
        heading2Image = new Texture("select-heading-2.png");
        playImage = new Texture("play.png");

        bg = new Sprite(bgImage);
        bg.setOrigin(0, 0);
        bg.setPosition(0, 0);

        playButton = new Sprite(playImage);
        playButton.setOrigin(0, 0);
        playButton.setScale(0.25f);
        playButton.setPosition((float) 9*screenWidth/10,600);

        tank1 = new Sprite(tank1Image);
        tank1.setOrigin(0, 0);
        tank1.setScale(0.75f);
        tank1.setPosition(100, 225);
        tank2 = new Sprite(tank2Image);
        tank2.setOrigin(0, 0);
        tank2.setScale(0.75f);
        tank2.setPosition(100 + 400, 225);
        tank3 = new Sprite(tank3Image);
        tank3.setOrigin(0, 0);
        tank3.setScale(0.75f);
        tank3.setPosition(100 + 400 + 400, 225);

        heading = new Sprite(headingImage);
        heading.setOrigin(0, 0);
        heading.setScale(0.5f);
        heading.setPosition((float) screenWidth/2 - (float) (690*0.5)/2, 600);
        heading2 = new Sprite(heading2Image);
        heading2.setOrigin(0, 0);
        heading2.setScale(0.5f);
        heading2.setPosition(heading.getX(), heading.getY());

        desc1 = new Sprite(descImage1);
        desc1.setOrigin(0, 0);
        desc1.setScale(0.35f);
        desc1.setPosition(95, 450);
        desc2 = new Sprite(descImage2);
        desc2.setOrigin(0, 0);
        desc2.setScale(0.35f);
        desc2.setPosition(95 + 400, 450);
        desc3 = new Sprite(descImage3);
        desc3.setOrigin(0, 0);
        desc3.setScale(0.35f);
        desc3.setPosition(95 + 400 + 400, 450);

        box1 = new Sprite(boxImage1);
        box1.setOrigin(0, 0);
        box1.setScale(0.75f);
        box1.setPosition(80, 160);
        box2 = new Sprite(boxImage2);
        box2.setOrigin(0, 0);
        box2.setScale(0.75f);
        box2.setPosition(80 + 400, 160);
        box3 = new Sprite(boxImage3);
        box3.setOrigin(0, 0);
        box3.setScale(0.9f, 0.75f);
        box3.setPosition(80 + 400 + 400, 160);
        box = new Sprite(boxImage);
        box.setOrigin(0, 0);
        box.setScale(0.75f);

        selectButton = new Sprite(selectButtonImage);
        selectButton.setOrigin(0, 0);
        selectButton.setScale(0.5f);
        selectButton.setPosition((float) screenWidth/2 - (float) (720/2)/2, 50);
    }

    void update() {
        if(Gdx.input.justTouched()) {
            //unprojects the camera
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if(box1.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                b1 = true;
                b2 = false;
                b3 = false;
            }
            if (box2.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                b1 = false;
                b2 = true;
                b3 = false;
            }
            if (box3.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                b1 = false;
                b2 = false;
                b3 = true;
            }
            if (h1 && heading.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                h1 = false;
                h2 = true;
                b1 = false;
                b2 = false;
                b3 = false;
            }
            else if (h2 && heading2.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                h1 = true;
                h2 = false;
                b1 = false;
                b2 = false;
                b3 = false;
            }
            if ((b1||b2||b3) && selectButton.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                if (h1) {
                    if (b1) s1 = 1;
                    if (b2) s1 = 2;
                    if (b3) s1 = 3;
                }
                else if (h2) {
                    if (b1) s2 = 1;
                    if (b2) s2 = 2;
                    if (b3) s2 = 3;
                }
            }
            if (playButton.getBoundingRectangle().contains(touchPoint.x, touchPoint.y)) {
                GameScreen newScreen = new GameScreen(game);
                game.gameScreen = newScreen;
                game.setScreen(game.gameScreen);
            }
        }
    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        bg.draw(game.batch);
        box1.draw(game.batch);
        box2.draw(game.batch);
        box3.draw(game.batch);
        if (b1 || b2 || b3) box.draw(game.batch);
        tank1.draw(game.batch);
        tank2.draw(game.batch);
        tank3.draw(game.batch);
        if (h1 && !h2) heading.draw(game.batch);
        if (h2 && !h1) heading2.draw(game.batch);
        if (s1>0 &&s2>0) playButton.draw(game.batch);
        desc1.draw(game.batch);
        desc2.draw(game.batch);
        desc3.draw(game.batch);
        selectButton.draw(game.batch);
        game.batch.end();
        update();
        if (b1) {
            box.setScale(0.75f);
            box.setPosition(box1.getX(), box1.getY());
        }
        if (b2) {
            box.setScale(0.75f);
            box.setPosition(box2.getX(), box2.getY());
        }
        if (b3) {
            box.setScale(0.9f, 0.75f);
            box.setPosition(box3.getX(), box3.getY());
        }
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

    }
}
