package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.math.Vector3;

public class DummyTowerAgent extends MapObjectAgent {
	
	public DummyTowerAgent (Vector3 position) {
		super(position, new Vector3(0,0,0), new Vector3(0,0,0));
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
	public float getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}


}
