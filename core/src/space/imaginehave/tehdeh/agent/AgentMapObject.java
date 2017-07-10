package space.imaginehave.tehdeh.agent;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector3;


public abstract class AgentMapObject extends MapObject {
	
	Vector3 position;
	Vector3 velocity;
	Vector3 goal;
	List<Vector3> velocityPath;
	List<Vector3> positionPath;
	
	public AgentMapObject(Vector3 position, Vector3 velocity, Vector3 goal) {
		
		this.position = position;
		this.velocity = velocity;
		this.goal = goal;
		
		this.positionPath = new ArrayList<Vector3>();
		this.velocityPath = new ArrayList<Vector3>();
	}
	
	public Vector3 getPosition() {
		return position;
	}

	public Vector3 getVelocity() {
		return velocity;
	}

	public Vector3 getGoal() {
		return goal;
	}

	public List<Vector3> getPostionPath() {
		return positionPath;
	}

	public List<Vector3> getVelocityPath() {
		return velocityPath;
	}
	
	public abstract void update();
	

}
