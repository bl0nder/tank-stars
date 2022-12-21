package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class User {
    public Sprite sprite;
    public Body tankBody;
    public Sprite tankLowerSprite;
    public Sprite tankTurretSprite;
    public Sprite fuelSprite;
    public Sprite powerSprite;
    public float hp;
    public float fuel;
    public float fuelDepletionSpeed;
    public float damageRate;
    public int power;
    public float angle;
    public int angleFactor;

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
    }
}