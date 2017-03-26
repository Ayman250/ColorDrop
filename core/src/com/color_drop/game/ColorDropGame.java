package com.color_drop.game;

import com.badlogic.gdx.Game;
import helper.AssetLoader;
import screens.GameScreen;


public class ColorDropGame extends Game {
    @Override
    public void create() {
        AssetLoader.load();
        setScreen(new GameScreen());

    }

    @Override
    public void dispose() {
        super.dispose();
        //AssetLoader.dispose();
    }
}
