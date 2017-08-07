package space.imaginehave.tehdeh.overlay;

import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.AssetManager;
import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class GoalOverlayMapObject extends OverlayMapObject {

	public GoalOverlayMapObject(Vector2 position) {
		super(position, AssetManager.getInstance().getTexture(Constant.OVERLAY_GOAL_PNG));
	}

}
