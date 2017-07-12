package space.imaginehave.tehdeh.agent;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;


public abstract class AgentMapObject extends MapObject {
	
	Texture texture;
	Vector2 position;
	Vector2 velocity;
	Vector2 goal;
	List<Vector2> velocityPath;
	List<Vector2> positionPath;
	
	public AgentMapObject(Vector2 position, Vector2 velocity, Vector2 goal) {
		
		this.position = position;
		this.velocity = velocity;
		this.goal = goal;
		
		this.positionPath = new ArrayList<Vector2>();
		this.velocityPath = new ArrayList<Vector2>();
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public Vector2 getGoal() {
		return goal;
	}

	public List<Vector2> getPostionPath() {
		return positionPath;
	}

	public List<Vector2> getVelocityPath() {
		return velocityPath;
	}
	
	public abstract void update();

	public Texture getTexture() {
		return texture;
	}

	public boolean atGoal() {
		return goal.dst(position) < 15;
	}
	
	@Override
	public String toString() {
		return ""+this.getClass();
	}
}
