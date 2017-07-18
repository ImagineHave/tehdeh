package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.search.ThetaStarLazySearch;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentThetaStar extends AgentMapObject {

	public AgentThetaStar(Vector2 postion, Vector2 velocity, GameStateTehDeh state) {
		super(postion, velocity, state.getGoal());
		search = new ThetaStarLazySearch(state);
		texture = (Texture) state.getAssetManager().get(Constant.TSTAR_AGENT_PNG);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(
				texture, 
				position.x,
				position.y);
		
	}

}
