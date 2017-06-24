package space.imaginehave.tehdeh.agent;

import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector3;


public abstract class MapObjectAgent extends MapObject implements Agent {
	
	Vector3 position;
	Vector3 velocity;
	Vector3 goal;
	List<Vector3> velocityPath;
	List<Vector3> positionPath;
	Vector3 maxPosition;
	Vector3 minPosition;
	
	@Override
	public Vector3 getPosition() {
		return position;
	}

	@Override
	public Vector3 getVelocity() {
		return velocity;
	}

	@Override
	public Vector3 getGoal() {
		return goal;
	}

	@Override
	public List<Vector3> getPostionPath() {
		return positionPath;
	}

	@Override
	public List<Vector3> getVelocityPath() {
		return velocityPath;
	}

	@Override
	public Vector3 getMaxPosition() {
		return maxPosition;
	}

	@Override
	public Vector3 getMinPosition() {
		return minPosition;
	}
	
	
	public abstract void update();

}
