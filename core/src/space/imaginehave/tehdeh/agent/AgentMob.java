package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentMob extends AgentMapObject {

	public AgentType type;
	
	public AgentMob(Vector2 postion, Vector2 velocity, GameStateTehDeh state, AgentType type) {
		super(postion, velocity, state.getGoal());
		this.type = type;
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(
				type.texture, 
				position.x,
				position.y);
		
	}
	
	public void update() {
		
		type.search.calculatePathsForAgent(this);
		
		if(!positionPath.isEmpty()) {
			position = new Vector2(positionPath.get(positionPath.size()-1));
			positionPath.remove(positionPath.size()-1);
		}			
		if(!velocityPath.isEmpty()) {
			velocity = new Vector2(velocityPath.get(velocityPath.size()-1));
			velocityPath.clear();
		}
		polygon.setPosition(position.x, position.y);
	}

}
