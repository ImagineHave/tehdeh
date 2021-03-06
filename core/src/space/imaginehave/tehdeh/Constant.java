package space.imaginehave.tehdeh;

import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.overlay.GoalOverlayMapObject;

public class Constant {

	public static final int GAME_HEIGHT = 800;
	public static final int GAME_WIDTH = 800;

	public static final int VIEWPORT_HEIGHT = 800;
	public static final int VIEWPORT_WIDTH = 800;

	public static final String TEST_TOWER_PNG = "testTower.png";
	public static final String TEST_TOWER_2_PNG = "testTower.png";
	
	public static final String TEST_AGENT_PNG = "testAgent.png";
	public static final String BOID_AGENT_PNG = "boidAgent.png";
	public static final String ASTAR_AGENT_PNG = "aStarAgent.png";
	public static final String TSTAR_AGENT_PNG = "tStarAgent.png";
	
	public static final String TEST_HURTYTHING_PNG = "testBullet.png";
	
	public static final String BUTTON_RESET_PNG = "reset.png";
	public static final String BUTTON_MORE_PNG = "more.png";
	public static final int BUTTON_SIZE = 50;
	public static final int BUTTON_SPACE = 10;
	
	public static final String OVERLAY_GOAL_PNG = "goal.png";
	public static final Vector2 ORIGINAL_GOAL_VECTOR = new Vector2(Constant.GAME_WIDTH/2,32);;
	public static final GoalOverlayMapObject ORIGINAL_GOAL_MAP_OBJECT = new GoalOverlayMapObject(ORIGINAL_GOAL_VECTOR);

	public static final String TMX = "tehdeh.tmx";
	public static final String LAYER_TOWER = "towerLayer";
	public static final String LAYER_AGENT = "agentLayer";
	public static final String OVERLAY = "overlay";
	
	public static final Vector2 VIEWPORT_BOTTOM_LEFT = new Vector2(0,0);
	public static final Vector2 VIEWPORT_TOP_RIGHT = new Vector2(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
	
}
