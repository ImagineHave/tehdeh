package space.imaginehave.tehdeh.agent;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.search.Search;


public abstract class AgentMapObject extends MapObject {
	
	protected static final float height = 16f;
	protected static final float width = 16;
	
	Texture texture;
	Vector2 position;
	Vector2 velocity;
	Vector2 goal;
	List<Vector2> velocityPath;
	List<Vector2> positionPath;
	Polygon polygon;
	protected Search search;
	
	public AgentMapObject(Vector2 position, Vector2 velocity, Vector2 goal) {
		
		this.position = position;
		this.velocity = velocity;
		this.goal = goal;
		
		this.positionPath = new ArrayList<Vector2>();
		this.velocityPath = new ArrayList<Vector2>();
		polygon = new Polygon(new float[]{0,0,width,0,width,height,0,height});
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

	public Texture getTexture() {
		return texture;
	}

	public boolean atGoal() {
		return goal.dst(position) < 15;
	}
	
	public Polygon getBoundingBox() {
		return polygon;
	}
	
	@Override
	public String toString() {
		return ""+this.getClass();
	}

	public abstract void draw(SpriteBatch batch);

	public void setGoal(Vector2 goalVector) {
		this.goal = goalVector;
	}

	public void update() {
		
		search.calculatePathsForAgent(this);
		
		if(!positionPath.isEmpty()) {
			position = new Vector2(positionPath.get(positionPath.size()-1));
			positionPath.remove(positionPath.size()-1);
		}			
		if(!velocityPath.isEmpty()) {
			velocity = new Vector2(velocityPath.get(velocityPath.size()-1));
			velocityPath.clear();
		}
		polygon.setPosition(position.x, position.y);
	}

	public float getSpeed() {
		return 1;
	}
}
