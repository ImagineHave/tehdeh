package space.imaginehave.tehdeh.overlay;

import space.imaginehave.tehdeh.AssetManager;
import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class GoalOverlayMapObject extends OverlayMapObject {

	public GoalOverlayMapObject() {
		super(GameStateTehDeh.getInstance().getGoal(), AssetManager.getInstance().getTexture(Constant.OVERLAY_GOAL_PNG));
	}

}
