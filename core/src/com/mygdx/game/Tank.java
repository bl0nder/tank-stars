package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tank {
    public Sprite sprite;

    public Tank(Texture t) {
        sprite = new Sprite(t);
    }
    public void drawTank(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
