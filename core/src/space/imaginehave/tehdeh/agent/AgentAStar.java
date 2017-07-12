package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.search.AStarSearch;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentAStar extends AgentCore {

	public AgentAStar(Vector2 postion, Vector2 velocity, Vector2 goal, GameStateTehDeh state) {
		super(postion, velocity, goal);
		search = new AStarSearch(state);
		texture = (Texture) state.getAssetManager().get(Constant.ASTAR_AGENT_PNG);
	}

}
