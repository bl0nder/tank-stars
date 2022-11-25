package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MiscUIElement {
    public Sprite sprite;

    public MiscUIElement(Texture t) {
        sprite = new Sprite(t);
    }
    public void drawElement(SpriteBatch batch) {
        sprite.draw(batch);
    }

}
