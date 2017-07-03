package space.imaginehave.tehdeh.agent;

import java.util.List;

import com.badlogic.gdx.math.Vector3;

public interface Agent {

	public Vector3 getPosition();
	
	public Vector3 getVelocity();
	
	public Vector3 getGoal();
	
	public float getSpeed();
	
	public void setGoal(Vector3 goalVector);
	
	public List<Vector3> getPostionPath();
	
	public List<Vector3> getVelocityPath();
	
}
