package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.math.Vector3;

public class DummyTowerAgent extends MapObjectAgent {
	
	public DummyTowerAgent (Vector3 position) {
		this.position = position;
	}

	@Override
	public void setGoal(Vector3 goalVector) {
		// no-op
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String toString() {
		return String.format("p: %f,%f", position.x, position.y);
		
	}

}
