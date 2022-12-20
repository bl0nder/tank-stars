package com.mygdx.game;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.physics.box2d.*;

public class Drop {
    Sprite dropSprite;
    Body dropBody;
    public Drop(String path, float x, World world) {
        Texture dropImage = new Texture("drop.png");
        Sprite drop = new Sprite(dropImage);
        drop.setOrigin(0, 0);
        drop.setScale(0.25f);
        drop.setPosition(x, Gdx.graphics.getHeight() - drop.getHeight()/2);
        this.dropSprite = drop;

        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.position.set(drop.getX(), drop.getY());

        Body body = world.createBody(bodydef);

        CircleShape shape = new CircleShape();
        shape.setRadius(this.dropSprite.getWidth()/2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.8f;
//        fixtureDef.friction = friction;
        fixtureDef.restitution = 0f;

        Fixture fixture = body.createFixture(fixtureDef);

        body.setLinearVelocity(0, -100f);
    }
}
