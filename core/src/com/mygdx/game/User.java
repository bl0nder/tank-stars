package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class User {
    private String name;
    private ArrayList<Tank> unlockedTanks = new ArrayList<Tank>();
    private int creds;
    private int numWins;
    private int numLosses;

    public Sprite sprite;
    public User(Texture t) {
        sprite = new Sprite(t);
        sprite.setPosition(0,0);
        sprite.setScale(0.5f);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Tank> getUnlockedTanks() {
        return unlockedTanks;
    }

    public void setUnlockedTanks(ArrayList<Tank> unlockedTanks) {
        this.unlockedTanks = unlockedTanks;
    }

    public int getCreds() {
        return creds;
    }

    public void setCreds(int creds) {
        this.creds = creds;
    }

    public int getNumWins() {
        return numWins;
    }

    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    public int getNumLosses() {
        return numLosses;
    }

    public void setNumLosses(int numLosses) {
        this.numLosses = numLosses;
    }

    public void unlockTank() {

    }

    public void drawUser(SpriteBatch batch) {
        sprite.draw(batch);
    }

}
