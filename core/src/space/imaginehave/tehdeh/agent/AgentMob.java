package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentMob extends AgentMapObject {

	public AgentType type;
	private int stepsMoved;
	private int stepsStuck;
	private int currentHealth;
	public AgentMob(Vector2 postion, Vector2 velocity, GameStateTehDeh state, AgentType type) {
		super(postion, velocity, state.getGoal());
		this.type = type;
		this.currentHealth = type.maxHealth;
	}

	@Override
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
	
	@Override
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

}
