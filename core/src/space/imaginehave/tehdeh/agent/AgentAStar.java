package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.search.AStarSearch;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentAStar extends AgentCore {

	public AgentAStar(Vector3 postion, Vector3 velocity, Vector3 goal, GameStateTehDeh state) {
		super(postion, velocity, goal);
		search = new AStarSearch(state);
		texture = (Texture) state.getAssetManager().get(Constant.ASTAR_AGENT_PNG);
	}

}
