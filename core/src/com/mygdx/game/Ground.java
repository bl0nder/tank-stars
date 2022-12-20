package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ground {
    public Sprite sprite;

    Ground(Texture t) {
        sprite = new Sprite(t);
    }

    public void drawGround(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
