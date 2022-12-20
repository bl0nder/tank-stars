package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    final TankStars game;
    final static float PPM = 100f;
    public final int screenWidth = 1280;
    public final int screenHeight = 720;
    final float maxFuel = 1;
    final float fuelDepletionSpeed = 0.005f;
    private float fuel1 = maxFuel;
    private float fuel2 = maxFuel;

    //Aditya hello
    final int uniqueID = 1;

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
    Texture hpImage1;
    Texture hpImage2;

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
    Sprite hp1;
    Sprite hp2;

    Projectile projectile;
    Vector3 touchPoint = new Vector3();


    private World world;
    private Box2DDebugRenderer debugRenderer;
    private Body tank1Body;
    private Body tank2Body;
    private Body groundBody;
    OrthographicCamera camera;
    Viewport viewport;

    private SpriteBatch batch;
    private Texture tank1LowerTexture;
    private Texture tank1TurretTexture;
    private Texture tank2LowerTexture;
    private Texture tank2TurretTexture;
    private Texture groundTexture;
    private Texture fuel1Texture;
    private Texture fuel2Texture;

    private Sprite tank1LowerSprite;
    private Sprite tank1TurretSprite;
    private Sprite tank2LowerSprite;
    private Sprite tank2TurretSprite;
    private Sprite groundSprite;
    private Sprite fuel1Sprite;
    private Sprite fuel2Sprite;

    int turn = 1;
    float maxHp = 1000f;

    Animation<TextureRegion> explosionAnimation;
    Texture explosionSheet;
    float stateTime;
    int cols = 8;
    int rows = 6;
    boolean animation = true;
    public GameScreen(final TankStars game) {
        this.game = game;
        this.world = new World(new Vector2(0,-10), true);
        this.debugRenderer = new Box2DDebugRenderer();
        this.camera = new OrthographicCamera((float) Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);


        viewport = new FitViewport(1280,720, camera);
        camera.update();
        camera.setToOrtho(false, 1280, 720);

        //Textures
        bgImage = new Texture(Gdx.files.internal("bg.png"));
        hpBarImage = new Texture(Gdx.files.internal("hp-bar.png"));
        pauseButtonImage = new Texture(Gdx.files.internal("pause.png"));
        tree1Image = new Texture(Gdx.files.internal("tree1.png"));
        tree2Image = new Texture(Gdx.files.internal("tree2.png"));
        tree3Image = new Texture(Gdx.files.internal("tree3.png"));
        tree4Image = new Texture(Gdx.files.internal("tree4.png"));
        tower2Image = new Texture(Gdx.files.internal("tower2.png"));
        tower1Image = new Texture(Gdx.files.internal("tower1.png"));
        dotsImage = new Texture(Gdx.files.internal("dots.png"));
        hpImage1 = new Texture("hp-1.png");
        hpImage2 = new Texture("hp-2.png");
        explosionSheet = new Texture("explosion-spritesheet.png");

        //bg
        bg = new Sprite(bgImage);
        bg.setOrigin(0, 0);
        bg.setPosition(0, 0);

        //hp
        hpBar = new Sprite(hpBarImage);
        hpBar.setOrigin(0, 0);
        hpBar.setPosition((float) screenWidth/2 - hpBar.getWidth()/2, screenHeight - 100);
        hp1 = new Sprite(hpImage1);
        hp1.setOrigin(0, 0);
        hp1.setPosition((float) screenWidth/2 - (float) (880)/2, screenHeight - 100);
        hp2 = new Sprite(hpImage2);
        hp2.setOrigin(0, 0);
        hp2.setPosition((float) 2*hpBar.getX() - hp1.getX() - hp1.getWidth() + hpBar.getWidth(), screenHeight - 100);

        //trees and pause button
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



        //Left tank

        //Lower
        this.tank1LowerTexture = new Texture("atomic-lower.png");
        this.tank1LowerSprite = new Sprite(tank1LowerTexture);
        this.tank1LowerSprite.setOrigin(0,0);
        this.tank1LowerSprite.setScale(1f);
        this.tank1LowerSprite.setPosition(0,500);

        //Turret
        this.tank1TurretTexture = new Texture("atomic-turret.png");
        this.tank1TurretSprite = new Sprite(tank1TurretTexture);
        this.tank1TurretSprite.setOrigin(0,0);
        this.tank1TurretSprite.setPosition(0,500);
        this.tank1TurretSprite.setScale(1f);

        //Right tank

        //Lower
        this.tank2LowerTexture = new Texture("dubstep-mirror-lower.png");
        this.tank2LowerSprite = new Sprite(tank2LowerTexture);
        this.tank2LowerSprite.setOrigin(0,0);
        this.tank2LowerSprite.setScale(1f);
        this.tank2LowerSprite.setPosition(100,500);

        //Turret
        this.tank2TurretTexture = new Texture("dubstep-mirror-turret.png");
        this.tank2TurretSprite = new Sprite(tank2TurretTexture);
        this.tank2TurretSprite.setOrigin(this.tank1TurretSprite.getWidth() - 5,0);
        this.tank2TurretSprite.setScale(1f);
        this.tank2TurretSprite.setPosition(100,500);

        //CreateTank for bodies and fixtures
        this.tank1Body = createTank(0,500);
        this.tank2Body = createTank(100,500);

        //Fuel bar1
        this.fuel1Texture = new Texture("fuel.png");
        this.fuel1Sprite = new Sprite(fuel1Texture);
        this.fuel1Sprite.setOrigin(0,0);
        this.fuel1Sprite.setScale(1f, 1f);

        //Fuel bar 2
        this.fuel2Texture = new Texture("fuel.png");
        this.fuel2Sprite = new Sprite(fuel2Texture);
        this.fuel2Sprite.setOrigin(0,0);
        this.fuel2Sprite.setScale(1f, 1f);

        //Ground
        this.groundBody = createGround();
        this.groundTexture = new Texture("ground_purple.png");
        this.groundSprite = new Sprite(groundTexture);
        groundSprite.setOrigin(0,0);
        groundSprite.setScale(1.3f, 1.3f);
        groundSprite.setPosition(0,-596);

        //Heavy shit
        TextureRegion[][] tmp = TextureRegion.split(explosionSheet, explosionSheet.getWidth()/cols, explosionSheet.getHeight()/rows);
        TextureRegion[] frames = new TextureRegion[cols*rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }

        explosionAnimation = new Animation<TextureRegion>(0.025f, frames);
        stateTime = 0f;

        //collision detection and setting for animation
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture f1 = contact.getFixtureA();
                Fixture f2 = contact.getFixtureB();

                if (turn == 1) {
                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == groundBody || f2.getBody() == projectile.projectileBody && f1.getBody() == groundBody) {
                        //animation for explosion
                        animation = true;
                        //check if the radius is enough to hit second tank
                        float distance = Math.abs(projectile.projectileBody.getPosition().x - tank2LowerSprite.getX());
                        float damage = projectile.calculateDamage(false, distance);
                        hp1.setScale((maxHp - damage)/maxHp, 1);
                    }
                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == tank2Body || f2.getBody() == projectile.projectileBody && f1.getBody() == tank2Body) {
                        //animation for explosion
                        animation = true;
                        //deal max amount of damage
                        float damage = projectile.calculateDamage(true, 0);
                        hp1.setScale((maxHp - damage)/maxHp, 1);
                    }
//                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == tank2LowerBody || f1.getBody() == projectile.projectileBody && f2.getBody() == tank2LowerBody) {
//                        //animation for explosion
//                        animation = true;
//                        //again deal max amount of damage
//                        float damage = projectile.calculateDamage(true, 0);
//                        hp1.setScale((maxHp - damage)/maxHp, 1);
//                    }
                }
                else if (turn == 2) {
                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == groundBody || f2.getBody() == projectile.projectileBody && f1.getBody() == groundBody) {
                        //animation for explosion
                        //check if the radius is enough to hit second tank
                        float distance = Math.abs(projectile.projectileBody.getPosition().x - tank1LowerSprite.getX());
                        float damage = projectile.calculateDamage(false, distance);
                        hp2.setScale((maxHp - damage)/maxHp, 1);
                    }
                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == tank1Body || f2.getBody() == projectile.projectileBody && f1.getBody() == tank1Body) {
                        //animation for explosion
                        animation = true;
                        //deal max amount of damage
                        float damage = projectile.calculateDamage(true, 0);
                        hp2.setScale((maxHp - damage)/maxHp, 1);
                    }
                }

            }

            @Override
            public void endContact(Contact contact) {
                animation = false;
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        projectile = new Projectile("projectile.png", 100 + 55, 200 + 15 + 40, 1f, world, 0.5f, 0.5f, 45f, 100);
//        groundSprite.setPosition(0,-550);

    }

    private Body createTank(float x, float y) {

        Body tankBody1;
        BodyDef tankDef1 = new BodyDef();
        tankDef1.position.set(new Vector2(x/PPM, y/PPM));
        tankDef1.type = BodyDef.BodyType.DynamicBody;
        tankBody1 = world.createBody(tankDef1);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((float) this.tank1LowerSprite.getWidth()/4/PPM,(float) this.tank1LowerSprite.getHeight()/4/PPM);

        PolygonShape turret = new PolygonShape();
        turret.setAsBox(this.tank1TurretSprite.getWidth()/4/PPM, this.tank1TurretSprite.getHeight()/4/PPM);

        tankBody1.createFixture(shape, 1f);
        tankBody1.createFixture(turret, 1f);
        shape.dispose();

        return tankBody1;
    }

    public Body createGround() {
        Body groundBody;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0,0);
        groundBody = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1280f/PPM, 70f/PPM);

        groundBody.createFixture(shape, 0f);
        shape.dispose();

        return groundBody;
    }

    public void update(float delta) {
        camera.update();
        updateInput(delta);
        world.step(1/60f, 6, 2);
        game.batch.setProjectionMatrix(camera.combined);
        this.tank1LowerSprite.setPosition(this.tank1Body.getPosition().x*PPM - (tank1LowerSprite.getWidth()*tank1LowerSprite.getScaleX())/2, this.tank1Body.getPosition().y*PPM - (tank1LowerSprite.getHeight()*tank1LowerSprite.getScaleY())/2);
        this.tank1TurretSprite.setPosition(this.tank1Body.getPosition().x*PPM - (tank1TurretSprite.getWidth()*tank1TurretSprite.getScaleX())/2 + 3, this.tank1Body.getPosition().y*PPM + 9);
        this.tank2LowerSprite.setPosition(this.tank2Body.getPosition().x*PPM - (tank2LowerSprite.getWidth()*tank2LowerSprite.getScaleX())/2, this.tank2Body.getPosition().y*PPM - (tank2LowerSprite.getHeight()*tank2LowerSprite.getScaleY())/2);
        this.tank2TurretSprite.setPosition(this.tank2Body.getPosition().x*PPM - (tank2TurretSprite.getWidth()*tank2TurretSprite.getScaleX())/2, this.tank2Body.getPosition().y*PPM + 9);
//
        //Fuelbar
        this.fuel1Sprite.setPosition(this.tank1Body.getPosition().x*PPM - (tank1LowerSprite.getWidth()*tank1LowerSprite.getScaleX())/2 - 13, this.tank1Body.getPosition().y*PPM - (tank1LowerSprite.getHeight()*tank1LowerSprite.getScaleY())/2 - 15);
        this.fuel2Sprite.setPosition(this.tank2Body.getPosition().x*PPM - (tank2LowerSprite.getWidth()*tank2LowerSprite.getScaleX())/2 - 13, this.tank2Body.getPosition().y*PPM - (tank2LowerSprite.getHeight()*tank2LowerSprite.getScaleY())/2 - 15);
//        this.groundSprite.setPosition(this.groundBody.getPosition().x*PPM - (groundSprite.getWidth()*groundSprite.getScaleX())/2, this.groundBody.getPosition().y*PPM - (groundSprite.getHeight()*tank2LowerSprite.getScaleY())/2);
    }

    public void updateInput(float delta) {
        float fx1 = 0;
        float rx1 = tank1TurretSprite.getRotation();
        float fx2 = 0;
        float rx2 = tank2TurretSprite.getRotation();
        final float speedFactor = 1f;

        if (Gdx.input.isKeyPressed(Keys.D) && this.turn == 1) {
            if (((this.tank1LowerSprite.getX() + (this.tank1LowerSprite.getWidth()*this.tank1LowerSprite.getScaleX())) < Gdx.graphics.getWidth()) && fuel1 >= 0) {
                fx1 += 1;
                fuel1 -= fuelDepletionSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.A) && this.turn == 1) {
            if (this.tank1LowerSprite.getX() > 0 && fuel1 >= 0) {
                fx1 -= 1;
                fuel1 -= fuelDepletionSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && turn == 2) {
            if ((this.tank2LowerSprite.getX() + (this.tank2LowerSprite.getWidth()*this.tank2LowerSprite.getScaleX())) < Gdx.graphics.getWidth() && fuel2 >= 0) {
                fx2 += 1;
                fuel2 -= fuelDepletionSpeed;
            }
        }

        if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && turn == 2) {
            if (this.tank2LowerSprite.getX() > 0 && fuel2 >= 0) {
                fx2 -= 1;
                fuel2 -= fuelDepletionSpeed;
            }
        }

        if (Gdx.input.isKeyPressed(Keys.W) && this.turn == 1) {
            rx1 += 1;
        }

        if (Gdx.input.isKeyPressed(Keys.S) && this.turn == 1) {
            rx1 -= 1;
        }

        if (Gdx.input.isKeyPressed(Keys.DPAD_UP) && this.turn == 2) {
            rx2 -= 1;
        }

        if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) && this.turn == 2) {
            rx2 += 1;
        }

        if(Gdx.input.justTouched()) {
            //unprojects the camera
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if(pauseButton.getBoundingRectangle().contains(touchPoint.x,touchPoint.y)) {
                if (game.pauseScreen == null) {
                    PauseScreen newScreen = new PauseScreen(game);
                    game.pauseScreen = newScreen;
                }
                game.setScreen(game.pauseScreen);
            }
        }

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        tank1Body.setLinearVelocity(new Vector2(fx1*speedFactor, tank1Body.getLinearVelocity().y));
        tank2Body.setLinearVelocity(new Vector2(fx2*speedFactor, tank2Body.getLinearVelocity().y));
        tank1TurretSprite.setRotation(rx1);
        tank2TurretSprite.setRotation(rx2);

        fuel1Sprite.setScale(fuel1, fuel1Sprite.getScaleY());
        fuel2Sprite.setScale(fuel2, fuel2Sprite.getScaleY());
        tank1Body.setLinearVelocity(new Vector2(fx1*speedFactor, tank1Body.getLinearVelocity().y));

        projectile.projectileSprite.setPosition(projectile.projectileBody.getPosition().x, projectile.projectileBody.getPosition().y);
        projectile.projectileSprite.setRotation((float)Math.atan(projectile.projectileBody.getLinearVelocity().y/projectile.projectileBody.getLinearVelocity().x) * 180/(float)Math.PI);
    }


    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(world, camera.combined.scl(PPM));

        TextureRegion currentFrame = explosionAnimation.getKeyFrame(stateTime, false);;
        if (animation) {
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = explosionAnimation.getKeyFrame(stateTime, true);
        }

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
        tank1LowerSprite.draw(game.batch);
        tank1TurretSprite.draw(game.batch);
        tank2LowerSprite.draw(game.batch);
        tank2TurretSprite.draw(game.batch);
        groundSprite.draw(game.batch);
        fuel1Sprite.draw(game.batch);
        fuel2Sprite.draw(game.batch);
        projectile.projectileSprite.draw(game.batch);
        hpBar.draw(game.batch);
        hp1.draw(game.batch);
        hp2.draw(game.batch);
        if (animation) game.batch.draw(currentFrame, projectile.projectileSprite.getX(), projectile.projectileSprite.getY());
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
        world.dispose();
        batch.dispose();
        debugRenderer.dispose();
    }
}
