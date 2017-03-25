package com.color_drop.game;

import com.badlogic.gdx.Game;

/**
 * Created by Ayman on 3/25/2017.
 */

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
