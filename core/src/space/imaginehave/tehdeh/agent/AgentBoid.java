package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.search.BoidSearch;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentBoid extends AgentCore {

	public AgentBoid(Vector3 postion, Vector3 velocity, Vector3 goal, GameStateTehDeh state) {
		super(postion, velocity, goal);
		search = new BoidSearch(state);
		texture = (Texture) state.getAssetManager().get(Constant.BOID_AGENT_PNG);
	}


}
