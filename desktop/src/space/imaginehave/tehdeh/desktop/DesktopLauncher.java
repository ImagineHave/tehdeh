package space.imaginehave.tehdeh.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import space.imaginehave.tehdeh.TehDehGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Teh Deh";
		config.width = 800;
		config.height = 800;
		new LwjglApplication(new TehDehGame(), config);
	}
}
