package com.mygdx.game;

import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class User implements Serializable {
    public transient Sprite sprite;
    public transient Body tankBody;
    public transient Sprite tankLowerSprite;
    public transient Sprite tankTurretSprite;
    public transient Sprite fuelSprite;
    public transient Sprite powerSprite;
    public float hp;
    public float fuel;
    public float fuelDepletionSpeed;
    public float damageRate;
    public int power;
    public float angle;
    public int angleFactor;
    public float x;
    public float y;

    public User(Body tankBody, Sprite tankSprite1, Sprite tankSprite2, Sprite fuelSprite, Sprite powerSprite) {
        this.tankBody = tankBody;
        this.tankLowerSprite = tankSprite1;
        this.tankTurretSprite = tankSprite2;
        this.powerSprite = powerSprite;
        this.fuelSprite = fuelSprite;
        this.hp = 1000f;
        this.fuel = 1f;
        this.fuelDepletionSpeed = 0.005f;
        this.damageRate = 0.5f;
        this.power = 500;
        this.angle = 0;
        this.angleFactor = 10;
        this.x =0;
        this.y = 0;
    }
}