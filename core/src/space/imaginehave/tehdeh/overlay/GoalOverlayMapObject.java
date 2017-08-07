package space.imaginehave.tehdeh.overlay;

import com.badlogic.gdx.graphics.Texture;

import space.imaginehave.tehdeh.AssetManager;
import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class GoalOverlayMapObject extends OverlayMapObject {

	public GoalOverlayMapObject(GameStateTehDeh state) {
		super(state.getGoal(), state, AssetManager.getInstance().getTexture(Constant.OVERLAY_GOAL_PNG));
	}

}
