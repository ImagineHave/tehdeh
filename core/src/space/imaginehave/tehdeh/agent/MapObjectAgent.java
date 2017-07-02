package space.imaginehave.tehdeh.agent;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector3;


public abstract class MapObjectAgent extends MapObject implements Agent {
	
	Vector3 position;
	Vector3 velocity;
	Vector3 goal;
	List<Vector3> velocityPath;
	List<Vector3> positionPath;
	
	public MapObjectAgent(Vector3 position, Vector3 velocity, Vector3 goal) {
		
		this.position = position;
		this.velocity = velocity;
		this.goal = goal;
		
		this.positionPath = new ArrayList<Vector3>();
		this.velocityPath = new ArrayList<Vector3>();
	}
	
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
	
	public abstract void update();
	
	@Override
	public String toString() {
		return String.format("p: %f,%f", position.x, position.y);
		
	}

}
