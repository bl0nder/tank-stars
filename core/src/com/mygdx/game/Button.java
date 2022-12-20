package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    public Sprite sprite;

    public Button(Texture t) {
        sprite = new Sprite(t);
    }
    public void drawButton(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
