package com.battlecity.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import vue.BattleCity;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "JeuNul";
		config.width = 750;// 900;
		config.height = 750;// 900;
		new LwjglApplication(new BattleCity(), config);
	}
}
