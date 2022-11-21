package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class User {
    public Sprite sprite;

    public User(Texture t) {
        sprite = new Sprite(t);
    }
    public void drawUser(SpriteBatch batch) {
        sprite.setOrigin(10,10);
        sprite.setScale(1f);
        sprite.setPosition(10,10);
        sprite.draw(batch);
    }
}