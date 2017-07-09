package space.imaginehave.tehdeh;

import com.badlogic.gdx.math.Vector3;

public class Constant {

	public static final int GAME_HEIGHT = 800;
	public static final int GAME_WIDTH = 800;

	public static final int VIEWPORT_HEIGHT = 800;
	public static final int VIEWPORT_WIDTH = 800;

	public static final String TEST_TOWER = "testTower.png";
	public static final String TEST_AGENT = "testAgent.png";
	public static final String TEST_BULLET = "testBullet.png";
	
	public static final String BUTTON_RESET = "reset.png";
	public static final String BUTTON_MORE = "more.png";
	
	public static final String OVERLAY_GOAL = "goal.png";

	public static final String TMX = "tehdeh.tmx";
	public static final String LAYER_TOWER = "towerLayer";
	public static final String LAYER_AGENT = "agentLayer";
	public static final String OVERLAY = "overlay";
	
	
	public static final Vector3 VIEWPORT_BOTTOM_LEFT = new Vector3(0,0,0);
	public static final Vector3 VIEWPORT_TOP_RIGHT = new Vector3(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, 0);
	
}
