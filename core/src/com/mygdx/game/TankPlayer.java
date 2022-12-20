package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class TankPlayer extends Sprite {
    public World world;
    public Body body;
    public float tankPosX;
    public float tankPosY;

//    private Texture texture;
    private Texture tankImage1;

    public TankPlayer(World world) {
        this.tankImage1 = new Texture("AtomicTank.png");
        this.tankPosX = 10;
        this.tankPosY = 10;
    }

    public void renderTank() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(100,100);
        Body body = world.createBody(bodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(50);

        FixtureDef fixDef = new FixtureDef();
        fixDef.shape = circle;

        Fixture fix = body.createFixture(fixDef);

        circle.dispose();
    }

    public void draw(SpriteBatch batch) {

    }
}
