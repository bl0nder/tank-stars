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

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GameScreen implements Screen, Serializable {
    final transient TankStars game;
    final static float PPM = 100f;
    public final int screenWidth = 1280;
    public final int screenHeight = 720;
    final float maxFuel = 1;

    //Aditya hello
    final int uniqueID = 1;

    public int s1;
    public int s2;

    transient Texture tankImage1;
    transient Texture tankImage2;
    transient Texture terrainImage;
    transient Texture hpBarImage;
    transient Texture bgImage;
    transient Texture pauseButtonImage;
    transient Texture tree1Image;
    transient Texture tree2Image;
    transient Texture tree3Image;
    transient Texture tree4Image;
    transient Texture tower1Image;
    transient Texture tower2Image;
    transient Texture dotsImage;
    transient Texture shooterImage;
    transient Texture fuelImage;
    transient Texture hpImage1;
    transient Texture hpImage2;
    transient Texture powerTexture;

    transient Sprite bg;
    transient Sprite power1Sprite;
    transient Sprite power2Sprite;
    transient Sprite tank1;
    transient Sprite tank2;
    transient Sprite terrain;
    transient Sprite tree11;
    transient Sprite tree12;
    transient Sprite tree21;
    transient Sprite tree22;
    transient Sprite tree3;
    transient Sprite tree4;
    transient Sprite dots;
    transient Sprite tower2;
    transient Sprite tower1;
    transient Sprite hpBar;
    transient Sprite pauseButton;
    transient Sprite shooter;
    transient Sprite fuel;
    transient Sprite hp1;
    transient Sprite hp2;

    transient Projectile projectile;
    transient Vector3 touchPoint = new Vector3();


    private transient World world;
    private transient Box2DDebugRenderer debugRenderer;
    public transient Body tank1Body;
    public transient Body tank2Body;
    private transient Body groundBody;
    transient OrthographicCamera camera;
    transient Viewport viewport;

    private transient SpriteBatch batch;
    public transient Texture atomicLower;
    public transient Texture atomicTurret;
    public transient Texture dubstepLower;
    public transient Texture dubstepTurret;
    public transient Texture spectreLower;
    public transient Texture spectreTurret;
    public transient Texture atomicLowerMirror;
    public transient Texture atomicTurretMirror;
    public transient Texture dubstepLowerMirror;
    public transient Texture dubstepTurretMirror;
    public transient Texture spectreLowerMirror;
    public transient Texture spectreTurretMirror;
    private transient Texture tank1LowerTexture;
    private transient Texture tank1TurretTexture;
    private transient Texture tank2LowerTexture;
    private transient Texture tank2TurretTexture;
    private transient Texture groundTexture;
    private transient Texture fuel1Texture;
    private transient Texture fuel2Texture;

    private transient Sprite tank1LowerSprite;
    private transient Sprite tank1TurretSprite;
    private transient Sprite tank2LowerSprite;
    private transient Sprite tank2TurretSprite;
    private transient Sprite groundSprite;
    public transient Sprite fuel1Sprite;
    public transient Sprite fuel2Sprite;
    public User u1;
    public User u2;


    int turn = 1;
    float maxHp = 1000f;

    transient Animation<TextureRegion> explosionAnimation;
    transient Texture explosionSheet;
    float stateTime;
    int cols = 8;
    int rows = 6;
    boolean animation = false;
    public GameScreen(final TankStars game, int s1, int s2) {
        this.s1 = s1;
        this.s2 = s2;
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
        powerTexture = new Texture("power.png");

        System.out.printf("s1 = %d & s2 = %d\n", this.s1, this.s2);

        //Player 1 tanks
        if (this.s1 == 1) {
            this.tank1LowerTexture = new Texture("atomic-lower.png");
            tank1TurretTexture = new Texture("atomic-turret.png");
        }
        else if (s1 == 2) {
            this.tank1LowerTexture = new Texture("dubstep-lower.png");
            tank1TurretTexture = new Texture("dubstep-turret.png");
        }
        else {
            this.tank1LowerTexture= new Texture("spectre-lower.png");
            tank1TurretTexture = new Texture("spectre-turret.png");
        }

        //Player 2 tanks
        if (this.s2 == 1) {
            this.tank2LowerTexture = new Texture("atomic-mirror-lower.png");
            tank2TurretTexture = new Texture("atomic-mirror-turret.png");
        }
        else if (s2 == 2) {
            this.tank2LowerTexture = new Texture("dubstep-mirror-lower.png");
            tank2TurretTexture = new Texture("dubstep-mirror-turret.png");
        }
        else {
            this.tank2LowerTexture= new Texture("spectre-mirror-lower.png");
            tank2TurretTexture = new Texture("spectre-mirror-turret.png");
        }

        //Power
        power1Sprite = new Sprite(powerTexture);
        power2Sprite = new Sprite(powerTexture);

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
        tree11.setPosition(50, 50);
        tree12 = new Sprite(tree1Image);
        tree12.setOrigin(0, 0);
        tree12.setScale(0.25f, 0.25f);
        tree12.setPosition(1280 - 50 - 50, 50);
        tree21 = new Sprite(tree2Image);
        tree21.setOrigin(0, 0);
        tree21.setScale(0.25f, 0.25f);
        tree21.setPosition(500, 50);
        tree22 = new Sprite(tree2Image);
        tree22.setOrigin(0, 0);
        tree22.setScale(0.25f, 0.25f);
        tree22.setPosition(300, 50);
        tree3 = new Sprite(tree3Image);
        tree3.setOrigin(0, 0);
        tree3.setScale(0.25f, 0.25f);
        tree3.setPosition(750, 50);
        tree4 = new Sprite(tree4Image);
        tree4.setOrigin(0, 0);
        tree4.setScale(0.25f, 0.25f);
        tree4.setPosition(900, 50);

        tower1 = new Sprite(tower1Image);
        tower1.setOrigin(0, 0);
        tower1.setScale(0.25f);
        tower1.setPosition(825, 50);
        tower2 = new Sprite(tower2Image);
        tower2.setOrigin(0, 0);
        tower2.setScale(0.25f);
        tower2.setPosition(200, 50);

        pauseButton = new Sprite(pauseButtonImage);
        pauseButton.setOrigin(0, 0);
        pauseButton.setScale(0.75f, 0.75f);
        pauseButton.setPosition(0, screenHeight - 30 - 50);

        //Left tank

        //Lower
//        this.tank1LowerTexture = new Texture("atomic-lower.png");
        this.tank1LowerSprite = new Sprite(tank1LowerTexture);
        this.tank1LowerSprite.setOrigin(0,0);
        this.tank1LowerSprite.setScale(1f);
        this.tank1LowerSprite.setPosition(0,500);

        //Turret
//        this.tank1TurretTexture = new Texture("atomic-turret.png");
        this.tank1TurretSprite = new Sprite(tank1TurretTexture);
        this.tank1TurretSprite.setOrigin(0,0);
        this.tank1TurretSprite.setPosition(0,500);
        this.tank1TurretSprite.setScale(1f);

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



        //Right tank

        //Lower
//        this.tank2LowerTexture = new Texture("dubstep-mirror-lower.png");
        this.tank2LowerSprite = new Sprite(tank2LowerTexture);
        this.tank2LowerSprite.setOrigin(0,0);
        this.tank2LowerSprite.setScale(1f);
        this.tank2LowerSprite.setPosition(100,500);

        //Turret
//        this.tank2TurretTexture = new Texture("dubstep-mirror-turret.png");
        this.tank2TurretSprite = new Sprite(tank2TurretTexture);
        this.tank2TurretSprite.setOrigin(this.tank1TurretSprite.getWidth() - 5,0);
        this.tank2TurretSprite.setScale(1f);
        this.tank2TurretSprite.setPosition(100,500);

        //CreateTank for bodies and fixtures
        this.tank1Body = createTank(100,0);
        this.tank2Body = createTank(1180,0);

        this.u1 = new User(tank1Body, tank1LowerSprite, tank1TurretSprite, fuel1Sprite, power1Sprite);
        this.u2 = new User(tank2Body, tank2LowerSprite, tank2TurretSprite, fuel2Sprite, power2Sprite);

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

//        ArrayList<Integer> arr1 = new ArrayList<Integer>();
//        ArrayList<Integer> arr2 = new ArrayList<Integer>();
//
//        int k;
//        for (k=0; k<rows; k++) {
//            arr1.add(k);
//        }
//        for (k=0; k<cols; k++) {
//            arr2.add(k);
//        }
        int index = 0;
//
//        Iterator<Integer> it1 = arr1.iterator();
//        Iterator<Integer> it2 = arr2.iterator();
//
//        int i1;
//        int i2;
//        while (it1.hasNext()) {
//            i1 = it1.next();
//            while(it2.hasNext()) {
//                i2 = it2.next();
//                frames[index++] = tmp[i1][i2];
//            }
//        }
//
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                frames[index++] = tmp[i][j];
            }
        }

        explosionAnimation = new Animation<TextureRegion>(0.025f, frames);
        stateTime = 0f;

        //collision detection and setting for animation


//        projectile = new Projectile("projectile.png", 100 + 55, 200 + 15 + 40, 1f, world, 0.5f, 0f, 20f, 500);
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

    public void update(float delta) throws InterruptedException {
        camera.update();
        updateInput(delta);
        world.step(1/60f, 6, 2);
        game.batch.setProjectionMatrix(camera.combined);
        u1.tankLowerSprite.setPosition(u1.tankBody.getPosition().x*PPM - (tank1LowerSprite.getWidth()*tank1LowerSprite.getScaleX())/2, u1.tankBody.getPosition().y*PPM - (tank1LowerSprite.getHeight()*tank1LowerSprite.getScaleY())/2);
        u1.tankTurretSprite.setPosition(u1.tankBody.getPosition().x*PPM - (tank1TurretSprite.getWidth()*tank1TurretSprite.getScaleX())/2 + 3, u1.tankBody.getPosition().y*PPM + 9);
        u2.tankLowerSprite.setPosition(this.tank2Body.getPosition().x*PPM - (tank2LowerSprite.getWidth()*tank2LowerSprite.getScaleX())/2, this.tank2Body.getPosition().y*PPM - (tank2LowerSprite.getHeight()*tank2LowerSprite.getScaleY())/2);
        u2.tankTurretSprite.setPosition(this.tank2Body.getPosition().x*PPM - (tank2TurretSprite.getWidth()*tank2TurretSprite.getScaleX())/2, this.tank2Body.getPosition().y*PPM + 9);
//
        //Fuelbar
        u1.fuelSprite.setPosition(u1.tankBody.getPosition().x*PPM - (u1.tankLowerSprite.getWidth()*u1.tankLowerSprite.getScaleX())/2 - 13, u1.tankBody.getPosition().y*PPM - (u1.tankLowerSprite.getHeight()*u1.tankLowerSprite.getScaleY())/2 - 15);
        u2.fuelSprite.setPosition(u2.tankBody.getPosition().x*PPM - (u2.tankLowerSprite.getWidth()*u2.tankLowerSprite.getScaleX())/2 - 13, u2.tankBody.getPosition().y*PPM - (u2.tankLowerSprite.getHeight()*u2.tankLowerSprite.getScaleY())/2 - 15);

        //power
        u1.powerSprite.setPosition(u1.tankBody.getPosition().x*PPM - (u1.tankLowerSprite.getWidth()*u1.tankLowerSprite.getScaleX())/2 - 35, u1.tankBody.getPosition().y*PPM - (u1.tankLowerSprite.getHeight()*u1.tankLowerSprite.getScaleY())/2 + 45);
        u2.powerSprite.setPosition(u2.tankBody.getPosition().x*PPM - (u2.tankLowerSprite.getWidth()*u2.tankLowerSprite.getScaleX())/2 - 35, u2.tankBody.getPosition().y*PPM - (u2.tankLowerSprite.getHeight()*u2.tankLowerSprite.getScaleY())/2 + 45);
//        this.groundSprite.setPosition(this.groundBody.getPosition().x*PPM - (groundSprite.getWidth()*groundSprite.getScaleX())/2, this.groundBody.getPosition().y*PPM - (groundSprite.getHeight()*tank2LowerSprite.getScaleY())/2);
    }

    public void fireProjectile() {
        if (turn == 1) {
            //add slider code (probably in the render loop itself since it would always need to be present)
            //the slider could then be refreshed
            //add enter to fire in updateInput and call this function
            //power would need to be taken from the slider
            projectile = new Projectile("projectile.png", u1.tankBody.getPosition().x*PPM + 40, u1.tankBody.getPosition().y*PPM + 40, 1f, world, 0.5f, 0f, u1.angle, u1.power, turn);
            //damage is dealt through the contact fn itself
            //need to add User class code there to subtract hp from their attribute and have the users as an attribute in the gameScreen class itself
            //just add the tanks as an attribute for the user too and maybe the projectile as an attribute for the tank itself
            //for the animation, when isAnimationFinished(float stateTime) returns true in the render loop (when drawing it) set stateTime to zero and animation to false
            //then try and see if the animation works with more than one projectile
            //if not we'll try and debug it
            turn = 2;

        }
        else if (turn == 2) {
            projectile = new Projectile("projectile.png", tank2Body.getPosition().x*PPM - 40, tank2Body.getPosition().y*PPM + 40, 1f, world, 0.5f, 0f, u2.angle, u2.power, turn);
            turn = 1;
        }

    }

    public void collision() {
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture f1 = contact.getFixtureA();
                Fixture f2 = contact.getFixtureB();

                if ((f1.getBody() != null && f2.getBody() != null)) {
                    animation = true;
                }

                if (turn == 1) {
                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == groundBody || f2.getBody() == projectile.projectileBody && f1.getBody() == groundBody) {
                        //animation for explosion
//                        animation = true;
                        //check if the radius is enough to hit second tank
                        float distance = Math.abs(projectile.projectileBody.getPosition().x - u2.tankLowerSprite.getX());
                        float damage = projectile.calculateDamage(false, distance);

                        if (u2.hp > 0) {
                            u2.hp -= damage*u2.damageRate;
                        }
                        else if (damage*u2.damageRate > u2.hp) {
                            u2.hp = 0;
                        }

                        hp2.setScale(u2.hp/1000, 1);
                    }
                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == u2.tankBody || f2.getBody() == projectile.projectileBody && f1.getBody() == u2.tankBody) {
                        //animation for explosion
//                        animation = true;
                        //deal max amount of damage
                        float damage = projectile.calculateDamage(true, 0);

                        if (u2.hp > 0) {
                            u2.hp -= damage*u2.damageRate;
                        }
                        else if (damage*u2.damageRate > u2.hp) {
                            u2.hp = 0;
                        }

                        hp2.setScale(u2.hp/1000, 1);
                    }
                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == u1.tankBody || f2.getBody() == projectile.projectileBody && f1.getBody() == u1.tankBody) {
                        //animation for explosion
//                        animation = true;
                        //again deal max amount of damage
                        float damage = projectile.calculateDamage(true, 0);

                        if (u1.hp > 0) {
                            u1.hp -= damage*u1.damageRate;
                        }
                        else if (damage*u1.damageRate > u1.hp) {
                            u1.hp = 0;
                        }

                        hp1.setScale(u1.hp/1000, 1);
                    }
                }
                else if (turn == 2) {
                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == groundBody || f2.getBody() == projectile.projectileBody && f1.getBody() == groundBody) {
                        //animation for explosion
                        //check if the radius is enough to hit second tank
                        float distance = Math.abs(projectile.projectileBody.getPosition().x - u1.tankLowerSprite.getX());
                        float damage = projectile.calculateDamage(false, distance);

                        if (u1.hp > 0) {
                            u1.hp -= damage*u1.damageRate;
                        }
                        else if (damage*u1.damageRate > u1.hp) {
                            u1.hp = 0;
                        }

                        hp1.setScale(u1.hp/1000, 1);
                    }
                    if (f1.getBody() == projectile.projectileBody && f2.getBody() == u1.tankBody || f2.getBody() == projectile.projectileBody && f1.getBody() == u1.tankBody) {
                        //animation for explosion
                        animation = true;
                        //deal max amount of damage
                        float damage = projectile.calculateDamage(true, 0);

                        if (u1.hp > 0) {
                            u1.hp -= damage*u1.damageRate;
                        }
                        else if (damage*u1.damageRate > u1.hp) {
                            u1.hp = 0;
                        }
                        hp1.setScale(u1.hp/1000, 1);
                    }

                }

//                world.destroyBody(projectile.projectileBody);
//                projectile.projectileSprite.getTexture().dispose();

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
    }

    public void gameOver() throws InterruptedException {
        System.out.println("Game Over");
        TimeUnit.SECONDS.sleep(1);
        game.setScreen(game.mainMenuScreen);
    }


    public void updateInput(float delta) throws InterruptedException {
        float fx1 = 0;
        float rx1 = tank1TurretSprite.getRotation();
        float fx2 = 0;
        float rx2 = tank2TurretSprite.getRotation();
        final float speedFactor = 1f;

        if (u1.hp == 0 || u2.hp == 0) {
            gameOver();
        }

        if (Gdx.input.isKeyPressed(Keys.D) && this.turn == 1) {
            if (((this.tank1LowerSprite.getX() + (this.tank1LowerSprite.getWidth()*this.tank1LowerSprite.getScaleX())) < Gdx.graphics.getWidth()) && u1.fuel >= 0) {
                fx1 += 1;
                u1.fuel -= u1.fuelDepletionSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.A) && this.turn == 1) {
            if (this.tank1LowerSprite.getX() > 0 && u1.fuel >= 0) {
                fx1 -= 1;
                u1.fuel -= u1.fuelDepletionSpeed;
            }
        }
        if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT) && turn == 2) {
            if ((this.tank2LowerSprite.getX() + (this.tank2LowerSprite.getWidth()*this.tank2LowerSprite.getScaleX())) < Gdx.graphics.getWidth() && u2.fuel >= 0) {
                fx2 += 1;
                u2.fuel -= u2.fuelDepletionSpeed;
            }
        }

        if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT) && turn == 2) {
            if (this.tank2LowerSprite.getX() > 0 && u2.fuel >= 0) {
                fx2 -= 1;
                u2.fuel -= u2.fuelDepletionSpeed;
            }
        }

        if (Gdx.input.isKeyPressed(Keys.W) && this.turn == 1) {
            rx1 += 1;
            u1.angle = rx1;
        }

        if (Gdx.input.isKeyPressed(Keys.S) && this.turn == 1) {
            rx1 -= 1;
            u1.angle = rx1;
        }

        if (Gdx.input.isKeyPressed(Keys.DPAD_UP) && this.turn == 2) {
            rx2 -= 1;
            u2.angle = (float) Math.PI - rx2;
        }

        if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN) && this.turn == 2) {
            rx2 += 1;
            u2.angle = (float) Math.PI - rx2;
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

        if (Gdx.input.isKeyPressed(Keys.Q)) {
            if (this.turn == 1 && u1.power > 0) {
                u1.power -= 2;
            }
            if (this.turn == 2 && u2.power > 0) {
                u2.power -= 2;
            }
        }

        if (Gdx.input.isKeyPressed(Keys.E)) {
            if (this.turn == 1 && u1.power < 1001) {
                u1.power += 2;
            }
            if (this.turn == 2 && u2.power < 1001) {
                u2.power += 2;
            }
        }

        if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
            fireProjectile();
            collision();
        }

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        if (projectile != null) {
            projectile.projectileSprite.setPosition(projectile.projectileBody.getPosition().x*PPM, projectile.projectileBody.getPosition().y*PPM);
            projectile.projectileSprite.setRotation((float)Math.atan(projectile.projectileBody.getLinearVelocity().y/projectile.projectileBody.getLinearVelocity().x) * 180/(float)Math.PI);
        }

        u1.tankBody.setLinearVelocity(new Vector2(fx1*speedFactor, u1.tankBody.getLinearVelocity().y));
        u1.x = u1.tankBody.getPosition().x*PPM;
        u1.y = u1.tankBody.getPosition().y*PPM;

        u2.tankBody.setLinearVelocity(new Vector2(fx2*speedFactor, tank2Body.getLinearVelocity().y));
        u2.x = u2.tankBody.getPosition().x*PPM;
        u2.y = u2.tankBody.getPosition().y*PPM;

        tank1TurretSprite.setRotation(rx1);
        tank2TurretSprite.setRotation(rx2);

        u1.fuelSprite.setScale(u1.fuel, u1.fuelSprite.getScaleY());
        u2.fuelSprite.setScale(u2.fuel, u2.fuelSprite.getScaleY());

        u1.powerSprite.setScale((float) u1.power/1000, 1);
        u2.powerSprite.setScale((float) u2.power/1000, 1);

//        u1.tankBody.setLinearVelocity(new Vector2(fx1*speedFactor, u1.tankBody.getLinearVelocity().y));
    }


    public void render(float delta) {
        try {
            update(delta);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ScreenUtils.clear(0, 0, 0.2f, 1);
        game.batch.setProjectionMatrix(camera.combined);


        TextureRegion currentFrame = explosionAnimation.getKeyFrame(stateTime, false);;
        if (animation) {
            stateTime += Gdx.graphics.getDeltaTime();
            currentFrame = explosionAnimation.getKeyFrame(stateTime, false);
            if (explosionAnimation.isAnimationFinished(stateTime)) {
                stateTime = 0;
                animation = false;
                world.destroyBody(projectile.projectileBody);
            }
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
        if (projectile != null) {
            projectile.projectileSprite.draw(game.batch);
            if (animation) game.batch.draw(currentFrame, projectile.projectileSprite.getX()-60, projectile.projectileSprite.getY()-50);
        }
        groundSprite.draw(game.batch);
        fuel1Sprite.draw(game.batch);
        fuel2Sprite.draw(game.batch);
        hpBar.draw(game.batch);
        hp1.draw(game.batch);
        hp2.draw(game.batch);
        pauseButton.draw(game.batch);
        power1Sprite.draw(game.batch);
        power2Sprite.draw(game.batch);
        game.batch.end();

    }

    public void serialize() throws IOException {
        ObjectOutputStream savedGames = null;
//        Scanner scan = null;
//        Writer wr = null;
//        FileReader numberGamesIn = null;

        try {
//            numberGamesIn = new FileReader("number.txt");
//            wr = new FileWriter("number.txt");
//            scan = new Scanner(numberGamesIn);

//            int num = scan.nextInt();
            int num = TankStars.numSavedGames;
//            if (num == 0) {
//                savedGames = new ObjectOutputStream(new FileOutputStream("file1.txt"));
////                wr.write(1);
//            }
            if (num == 1){
                savedGames = new ObjectOutputStream(new FileOutputStream("file1.txt"));
//                wr.write(2);
            }
            else if (num == 2) {
                savedGames = new ObjectOutputStream(new FileOutputStream("file2.txt"));
//                wr.write(3);
            }
            else {
                savedGames = new ObjectOutputStream(new FileOutputStream("file3.txt"));
//                wr.write(3);
            }
            savedGames.writeObject(this);
        }
        finally {
            if (savedGames != null) {
                savedGames.close();
            }
//            if (wr != null) {
//                wr.close();
//            }
//
//            if (numberGamesIn != null) {
//                numberGamesIn.close();
//            }

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
        world.dispose();
        batch.dispose();
        debugRenderer.dispose();
    }
}
