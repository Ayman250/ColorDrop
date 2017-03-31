package com.color_drop.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.color_drop.game.ColorDrop;
import com.color_drop.game.ColorDropGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.samples = 3;
		new LwjglApplication(new ColorDropGame(), config);
	}
}
