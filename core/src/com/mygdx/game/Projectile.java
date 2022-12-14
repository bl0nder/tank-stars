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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.physics.box2d.*;

public class Projectile {
    Sprite projectileSprite;
    final static float PPM = 100f;
    Body projectileBody;
    float damageRadius = 100;
    float maxDamage = (float) 1000/3 + 1;

    public Projectile(String path, float x, float y, float scale, World world, float density, float restitution, float turretAngle, int power, int turn) {
        Texture projectileImage = new Texture(path);
        Sprite projectile = new Sprite(projectileImage);
        projectile.setOrigin(0, 0);
        projectile.setPosition(x, y);
        projectile.setScale(scale);
        this.projectileSprite = projectile;

        BodyDef bodydef = new BodyDef();
        bodydef.type = BodyDef.BodyType.DynamicBody;
        bodydef.position.set(projectile.getX()/PPM, projectile.getY()/PPM);

        Body body = world.createBody(bodydef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(this.projectileSprite.getWidth()/2/PPM, this.projectileSprite.getHeight()/2/PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.friction = 10000;
        fixtureDef.restitution = restitution;

        Fixture fixture = body.createFixture(fixtureDef);

        if (turn == 1) {
            body.setLinearVelocity(new Vector2((float) Math.cos(turretAngle*Math.PI/180)*power/PPM, (float)Math.sin(turretAngle*Math.PI/180)*power/PPM));
        }
        if (turn == 2) {
            body.setLinearVelocity(new Vector2((float) -Math.cos(turretAngle*Math.PI/180)*power/PPM, (float)Math.sin(turretAngle*Math.PI/180)*power/PPM));
        }

        this.projectileBody = body;

        shape.dispose();
    }

    public int getDamageRadius() {
        return 200;
    }

    public float calculateDamage(boolean contact, float distance) {
        if (distance >= damageRadius) return 0f;
        else {
            if (contact) {
                return (float) maxDamage;
            }
            else {
                return maxDamage - (maxDamage/damageRadius)*distance;
            }
        }
    }
}
