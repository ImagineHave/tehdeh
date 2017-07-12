package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.search.Search;

public abstract class AgentCore extends AgentMapObject {
	
	Search search;
	
	public AgentCore (Vector2 postion, Vector2 velocity, Vector2 goal) {
		super(postion, velocity, goal);
	}

	public void setGoal(Vector2 goalVector) {
		this.goal = goalVector;
	}

	@Override
	public void update() {
		
		search.calculatePathsForAgent(this);
		
		if(!positionPath.isEmpty()) {
			position = new Vector2(positionPath.get(positionPath.size()-1));
			positionPath.clear();
		}			
		if(!velocityPath.isEmpty()) {
			velocity = new Vector2(velocityPath.get(velocityPath.size()-1));
			velocityPath.clear();
		}
	}

	public float getSpeed() {
		return 1;
	}
}
