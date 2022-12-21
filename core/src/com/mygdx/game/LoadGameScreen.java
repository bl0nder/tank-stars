package com.mygdx.game;

import java.io.*;
import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.physics.box2d.*;

import static java.lang.System.exit;


public class LoadGameScreen implements Screen, Serializable {
    final TankStars game;
    OrthographicCamera camera;

    Texture blankButtonImage;
    Texture loadButton1Image;
    Texture loadButton2Image;
    Texture loadButton3Image;
    Texture bgBoxImage;
    Texture backButtonImage;

    Sprite blankButton;
    Sprite loadButton1;
    Sprite loadButton2;
    Sprite loadButton3;
    Sprite bgBox;
    Sprite backButton;

    Vector3 touchPoint = new Vector3();

    public static final Color BACKGROUND = new Color(0x1A2969ff);

    public LoadGameScreen(final TankStars game) {
        this.game = game;
        camera = new OrthographicCamera();
//        viewport = new FitViewport(1280, 720, camera);
        camera.setToOrtho(false, 1280, 720);

        //Textures
        blankButtonImage = new Texture("blank-btn.png");
        loadButton1Image = new Texture("load-btn-1.png");
        loadButton2Image = new Texture("load-btn-2.png");
        loadButton3Image = new Texture("load-btn-3.png");
        bgBoxImage = new Texture("load-bg.png");
        backButtonImage = new Texture("back-btn.png");

        //Sprites
        blankButton = new Sprite(blankButtonImage);

        loadButton1 = new Sprite(loadButton1Image);
        loadButton1.setOrigin(0, 0);
        loadButton1.setScale(0.75f);
        loadButton1.setPosition((float) (Gdx.graphics.getWidth()/2 - loadButton1.getWidth()*loadButton1.getScaleX()/2), (float) (2*Gdx.graphics.getHeight()/3 - 2*loadButton1.getHeight()*loadButton1.getScaleY()/3) + 75);

        loadButton2 = new Sprite(loadButton2Image);
        loadButton2.setOrigin(0, 0);
        loadButton2.setScale(0.75f);
        loadButton2.setPosition((float) (Gdx.graphics.getWidth()/2 - loadButton2.getWidth()*loadButton2.getScaleX()/2), (float) (Gdx.graphics.getHeight()/2 - loadButton2.getHeight()*loadButton2.getScaleY()/2) - 20 + 75);

        loadButton3 = new Sprite(loadButton3Image);
        loadButton3.setOrigin(0, 0);
        loadButton3.setScale(0.75f);
        loadButton3.setPosition((float) (Gdx.graphics.getWidth()/2 - loadButton3.getWidth()*loadButton3.getScaleX()/2), (float) (Gdx.graphics.getHeight()/3 - loadButton3.getHeight()*loadButton3.getScaleY()/3) - 40 + 75);

        bgBox = new Sprite(bgBoxImage);
        bgBox.setOrigin(0, 0);
        bgBox.setScale(0.75f);
        bgBox.setPosition((float) (Gdx.graphics.getWidth()/2 - bgBox.getWidth()*bgBox.getScaleX()/2), (float) (Gdx.graphics.getHeight()/2 - bgBox.getHeight()*bgBox.getScaleY()/2));

        backButton = new Sprite(backButtonImage);
        backButton.setOrigin(0, 0);
        backButton.setScale(0.75f);
        backButton.setPosition((float) (Gdx.graphics.getWidth()/2 - backButton.getWidth()*backButton.getScaleX()/2), (float) (Gdx.graphics.getHeight()/4 - backButton.getHeight()*backButton.getScaleY()/4) - 60);
    }

    public void deserialize(int file) throws IOException, ClassNotFoundException{
        ObjectInputStream savedGames  = null;
        Scanner scan = null;
        Writer wr = null;
        FileInputStream numberGamesIn = null;

        try {
            numberGamesIn = new FileInputStream("number.txt");
            wr = new FileWriter("number.txt");
            scan = new Scanner(numberGamesIn);
            int num = scan.nextInt();
            if (file > num) {
                return;
            }
            else {
                if (file == 1) savedGames = new ObjectInputStream(new FileInputStream("file1.txt"));
                else if (file == 2) savedGames = new ObjectInputStream(new FileInputStream("file2.txt"));
                else if (file == 3) savedGames = new ObjectInputStream(new FileInputStream("file3.txt"));
            }
        }
        finally {
            GameScreen gameScreen = (GameScreen) savedGames.readObject();
            User user1 = gameScreen.u1;
            User user2 = gameScreen.u2;

            GameScreen newScreen = new GameScreen(game);
            newScreen.u1 = user1;
            newScreen.u2 = user2;
            newScreen.tank1.setPosition(1, 1);
            newScreen.tank2.setPosition(1, 2);
            newScreen. hp1.setScale(gameScreen.u1.hp/1000, 1);
            newScreen.hp2.setScale(gameScreen.u2.hp/1000, 1);
            //add the code for scaling fuel images here
            newScreen.u1.fuelSprite.setScale(newScreen.u1.fuel, newScreen.u1.fuelSprite.getScaleY());
            newScreen.u2.fuelSprite.setScale(newScreen.u2.fuel, newScreen.u2.fuelSprite.getScaleY());
            game.gameScreen = newScreen;
            game.setScreen(newScreen);
        }
    }

    public void updateScreen() throws IOException, ClassNotFoundException {
        if(Gdx.input.justTouched()) {
            //unprojects the camera
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if(loadButton1.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                deserialize(1);
            }
            if (loadButton2.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                deserialize(2);
            }
            if (loadButton2.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                deserialize(3);
            }
            if (backButton.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                game.setScreen(game.mainMenuScreen);
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
//        updateScreen();
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        bgBox.draw(game.batch);
        loadButton1.draw(game.batch);
        loadButton2.draw(game.batch);
        loadButton3.draw(game.batch);
        backButton.draw(game.batch);
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
