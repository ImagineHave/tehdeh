package space.imaginehave.tehdeh.agent;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.search.Search;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentMob extends MapObject {

	protected static final float height = 16f;
	protected static final float width = 16f;
	
	Texture texture;
	Vector2 position;
	Vector2 velocity;
	Vector2 goal;
	List<Vector2> velocityPath;
	List<Vector2> positionPath;
	Polygon polygon;
	boolean dead;
	
	public AgentType type;
	private int stepsMoved;
	private int stepsStuck;
	private int currentHealth;
	
	public AgentMob(Vector2 position, Vector2 velocity, AgentType type) {
		this.position = position;
		this.velocity = velocity;
		this.goal = Constant.ORIGINAL_GOAL_VECTOR;
		
		this.positionPath = new ArrayList<Vector2>();
		this.velocityPath = new ArrayList<Vector2>();
		polygon = new Polygon(new float[]{0,0,width,0,width,height,0,height});
		this.type = type;
		this.currentHealth = type.maxHealth;
	}


	public void draw(SpriteBatch batch) {
		batch.draw(
				type.texture, 
				position.x,
				position.y);
		
	}
	
	public void update() {
		
		if(type.search.isPathFollowing()) {
			if(stepsMoved % type.stepsToFollowPath == 0 ||
				stepsStuck > type.patience
					) {
				type.search.calculatePathsForAgent(this);
			}
		} else {
			type.search.calculatePathsForAgent(this);
		}
		
		if(!positionPath.isEmpty()) {
			if(position.equals(positionPath.get(positionPath.size()-1))) {
				stepsStuck++;
			} else {
				stepsStuck = 0;
			}
			
			position = new Vector2(positionPath.get(positionPath.size()-1));
			positionPath.remove(positionPath.size()-1);
			stepsMoved++;
		}			
		if(!velocityPath.isEmpty()) {
			velocity = new Vector2(velocityPath.get(velocityPath.size()-1));
			velocityPath.clear();
		}
		polygon.setPosition(position.x, position.y);
	}
	

	public void doDamage(int damage) {
		currentHealth -= damage;
		if(currentHealth <= 0) {
			dead = true;
		}
	}

	public int getScore() {
		return type.score;
	}
	
	public int getMoney() {
		return type.money;
	}
	
	public int getDamage() {
		return type.damage;
	}

	public Search getSearch() {
		return type.search;
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


	public void setGoal(Vector2 goalVector) {
		this.goal = goalVector;
	}


	public float getSpeed() {
		return 1;
	}

	
	public boolean isDead() {
		return dead;
	}

}
