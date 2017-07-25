package space.imaginehave.tehdeh.tower;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.agent.AgentMob;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class TowerMapObject extends MapObject {
	
	int range = 50;
	int firingDelay = 3;
	float delayTimer=3;
	int directionalInaccuracyInDegrees = 15;
	private GameStateTehDeh state;
	private List<HurtyThingBullet> bullets = new ArrayList<HurtyThingBullet>();
	private Vector2 position;
	private TowerCell towerCell;

	public TowerMapObject (Vector2 position, GameStateTehDeh state, Texture texture) {
		this.position = position;
		this.state = state;
		this.towerCell = new TowerCell(texture);
	}

	public void update() {
		delayTimer += Gdx.graphics.getDeltaTime();
		if (delayTimer > firingDelay) {
			Optional<AgentMob> agent = getClosestAgentInRange();
			if (agent.isPresent()) {
				fireBullet(agent.get());
				delayTimer = 0;
			}
		}
		for (int i = 0; i < bullets.size(); i++) {
			HurtyThingBullet bullet = bullets.get(i);
			if (bullet.isToRemove()) {
				bullets.remove(bullet);
				state.getAgentLayer().getObjects().remove(bullet);
			}
		} 
		
	}

	private Optional<AgentMob> getClosestAgentInRange() {
		AgentMob agentToReturn = null;
		Array<AgentMob> agents = state.getAgentLayer().getObjects().getByType(AgentMob.class);
		for(int i = 0; i < agents.size; i++) {
			MapObject mapObject = agents.get(i);
			if(mapObject instanceof AgentMob && !(mapObject instanceof HurtyThingBullet)) { //TODO: refactor. Need better way to handle type of mapobject
				AgentMob agent = (AgentMob) mapObject;
				float nearestAgentDistance = Integer.MAX_VALUE;
				Circle circle = new Circle(position.x, position.y, range);
				if(circle.contains(new Vector2(agent.getPosition().x, agent.getPosition().y)) &&
						position.dst2(agent.getPosition()) < nearestAgentDistance) {
					nearestAgentDistance = position.dst2(agent.getPosition());
					agentToReturn = agent;
				}
			}
		}
		return Optional.ofNullable(agentToReturn);
	}

	private void fireBullet(AgentMob agent) {
		Vector2 targetVector = agent.getPosition().cpy().sub(position.cpy());
		int inaccuracy = MathUtils.random(-directionalInaccuracyInDegrees, directionalInaccuracyInDegrees);
		Vector2 targetVector2 = new Vector2(targetVector.x, targetVector.y);
		targetVector2.rotate(inaccuracy);
		//TODO: magic number 8 = tilewidth/2
		HurtyThingBullet bullet = new HurtyThingBullet(position.cpy().add(8, 8), new Vector2(), new Vector2(targetVector2.x, targetVector2.y), state);
		state.addBullet(bullet);
		bullets.add(bullet);
	}
	
	public int getRange() {
		return range;
	}
	
	public Vector2 getPosition() {
		return this.position;
	}

	public TowerCell getCell() {
		return towerCell;
	}

	@Override
	public String toString() {
		return this.getClass() + "p" + position.x + ":" + position.y;
	}
}
