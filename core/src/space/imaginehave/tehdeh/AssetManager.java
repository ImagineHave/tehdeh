package space.imaginehave.tehdeh;

import com.badlogic.gdx.graphics.Texture;

public class AssetManager extends com.badlogic.gdx.assets.AssetManager {
	
	private static AssetManager assetManager;
	
	public static AssetManager getInstance() {
		if (assetManager == null) {
			assetManager = new AssetManager();
		}
		return assetManager;
	}
	
	private AssetManager() {
		load(Constant.TEST_TOWER_PNG, Texture.class);
		load(Constant.TEST_TOWER_2_PNG, Texture.class);
		load(Constant.TEST_AGENT_PNG, Texture.class);
		load(Constant.BOID_AGENT_PNG, Texture.class);
		load(Constant.ASTAR_AGENT_PNG, Texture.class);
		load(Constant.TSTAR_AGENT_PNG, Texture.class);
		load(Constant.OVERLAY_GOAL_PNG, Texture.class);
		load(Constant.BUTTON_RESET_PNG, Texture.class);
		load(Constant.BUTTON_MORE_PNG, Texture.class);
		load(Constant.TEST_HURTYTHING_PNG, Texture.class);
		finishLoading();
	}
	
	
	public Texture getTexture(String item) {
		return (Texture) get(item);
	}
	
}
