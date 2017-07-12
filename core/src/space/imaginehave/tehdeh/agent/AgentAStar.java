package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.search.AStarSearch;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentAStar extends AgentCore {

	public AgentAStar(Vector2 postion, Vector2 velocity, GameStateTehDeh state) {
		super(postion, velocity, state.getGoal());
		search = new AStarSearch(state);
		texture = (Texture) state.getAssetManager().get(Constant.ASTAR_AGENT_PNG);
		this.batch = state.getBatch();
	}

	@Override
	public void draw() {
		batch.draw(
				texture, 
				position.x,
				position.y);
		
	}

}
