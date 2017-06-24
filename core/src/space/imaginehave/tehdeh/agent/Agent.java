package space.imaginehave.tehdeh.agent;

import java.util.List;

import com.badlogic.gdx.math.Vector3;

public interface Agent {

	public Vector3 getPosition();
	
	public Vector3 getVelocity();
	
	public Vector3 getGoal();
	
	public List<Vector3> getPostionPath();
	
	public List<Vector3> getVelocityPath();
	
	public Vector3 getMaxPosition();
	
	public Vector3 getMinPosition();
	
}
