package com.mygdx.game;

import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreenTest implements Screen {
    final TankStars game;
    final static float PPM = 100f;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Body tank1;
    private Body ground;
    OrthographicCamera camera;
    Viewport viewport;

    private SpriteBatch batch;
    private Texture atomicTank;
    private Sprite atomic;
    public GameScreenTest(final TankStars game) {
        this.game = game;
        this.world = new World(new Vector2(0,-10), true);
        this.debugRenderer = new Box2DDebugRenderer();
        this.camera = new OrthographicCamera((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);


        viewport = new FitViewport(1280,720, camera);
        camera.update();
        camera.setToOrtho(false, 1280, 720);

        this.atomicTank = new Texture("AtomicTank.png");
        this.atomic = new Sprite(atomicTank);
        this.atomic.setOrigin(0,0);
        this.atomic.setScale(0.5f);
        this.atomic.setPosition(0,500);

        this.tank1 = createTank1();
        this.ground = renderGround();
    }

    private Body createTank1() {

        Body tankBody1;
        BodyDef tankDef1 = new BodyDef();
        tankDef1.position.set(new Vector2(0, 500/PPM));
        tankDef1.type = BodyDef.BodyType.DynamicBody;
        tankBody1 = world.createBody(tankDef1);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((float) this.atomic.getWidth()/4/PPM,(float) this.atomic.getHeight()/4/PPM);

        tankBody1.createFixture(shape, 1f);
        shape.dispose();

        return tankBody1;
    }

    public Body renderGround() {
        Body groundBody;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);
        groundBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(camera.viewportWidth/PPM, 15f/PPM);

        groundBody.createFixture(shape, 0f);
        shape.dispose();

        return groundBody;
    }

    public void update(float delta) {
        camera.update();
        updateInput(delta);
        world.step(1/60f, 6, 2);
        game.batch.setProjectionMatrix(camera.combined);
        this.atomic.setPosition(this.tank1.getPosition().x*PPM - (atomic.getWidth()*atomic.getScaleX())/2, this.tank1.getPosition().y*PPM - (atomic.getHeight()*atomic.getScaleY())/2);
    }

    public void updateInput(float delta) {
        float fx = 0;
        final float speedFactor = 5f;

        if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {

            if ((this.atomic.getX() + (this.atomic.getWidth()*this.atomic.getScaleX())) < Gdx.graphics.getWidth()) {
                fx += 1;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
            if (this.atomic.getX() > 0) {
                fx -= 1;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        tank1.setLinearVelocity(new Vector2(fx*speedFactor, tank1.getLinearVelocity().y));
    }

    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(world, camera.combined.scl(PPM));

        game.batch.begin();
        atomic.draw(game.batch);
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
        world.dispose();
        batch.dispose();
        debugRenderer.dispose();
    }
}
