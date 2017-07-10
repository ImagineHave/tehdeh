package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.search.Search;

public class AgentCore extends AgentMapObject {
	
	Search search;
	
	public AgentCore (Vector3 postion, Vector3 velocity, Vector3 goal) {
		super(postion, velocity, goal);
	}

	public void setGoal(Vector3 goalVector) {
		this.goal = goalVector;
	}

	@Override
	public void update() {
		
		search.calculatePathsForAgent(this);
		
		if(!positionPath.isEmpty()) {
			position = new Vector3(positionPath.get(positionPath.size()-1));
			positionPath.clear();
		}			
		if(!velocityPath.isEmpty()) {
			velocity = new Vector3(velocityPath.get(velocityPath.size()-1));
			velocityPath.clear();
		}
	}

	public float getSpeed() {
		return 1;
	}

	@Override
	public void suicide() {
		// TODO Auto-generated method stub
		
	}
	
	

}
