package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.math.Vector3;

public class DummyAgent extends MapObjectAgent {
	
	public DummyAgent (Vector3 postion, Vector3 velocity, Vector3 goal) {
		super(postion, velocity, goal);
	}

	@Override
	public void setGoal(Vector3 goalVector) {
		this.goal = goalVector;
	}

	@Override
	public void update() {
		
		if(!positionPath.isEmpty()) {
			position = new Vector3(positionPath.get(positionPath.size()-1));
			positionPath.clear();
		}			
		if(!velocityPath.isEmpty()) {
			velocity = new Vector3(velocityPath.get(velocityPath.size()-1));
			velocityPath.clear();
		}
	}

	@Override
	public float getSpeed() {
		return 1;
	}

}
