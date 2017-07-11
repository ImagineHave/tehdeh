package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.search.ThetaStarLazySearch;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentThetaStar extends AgentCore {

	public AgentThetaStar(Vector3 postion, Vector3 velocity, Vector3 goal, GameStateTehDeh state) {
		super(postion, velocity, goal);
		search = new ThetaStarLazySearch(state);
		texture = (Texture) state.getAssetManager().get(Constant.TSTAR_AGENT_PNG);
	}

}
