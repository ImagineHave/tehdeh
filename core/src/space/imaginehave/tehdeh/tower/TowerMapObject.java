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
import space.imaginehave.tehdeh.wave.Population;

public class TowerMapObject extends MapObject {
	
	private Population population;
	private TowerType type;
	private List<HurtyThingBullet> bullets = new ArrayList<HurtyThingBullet>();
	private Vector2 position;
	private TowerCell towerCell;
	private float delayTimer = 3;

	public TowerMapObject (Vector2 position, TowerType type, Population population) {
		this.position = position;
		this.towerCell = new TowerCell(type.texture);
		this.type = type;
		this.population = population;
	}

	public void update() {
		if(type.range == 0) {
			return; //FIXME
		}
		delayTimer += Gdx.graphics.getDeltaTime();
		if (delayTimer > type.firingDelay) {
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
				population.getAgentLayer().getObjects().remove(bullet);
			}
		} 
		
	}

	private Optional<AgentMob> getClosestAgentInRange() {
		AgentMob agentToReturn = null;
		Array<AgentMob> agents = population.getAgentLayer().getObjects().getByType(AgentMob.class);
		float nearestAgentDistance = Integer.MAX_VALUE;
		for(int i = 0; i < agents.size; i++) {
			AgentMob agent = agents.get(i);
			Circle circle = new Circle(position.x, position.y, type.range);
			if(circle.contains(new Vector2(agent.getPosition().x, agent.getPosition().y)) &&
					position.dst2(agent.getPosition()) < nearestAgentDistance) {
				nearestAgentDistance = position.dst2(agent.getPosition());
				agentToReturn = agent;
			}
		}
 		return Optional.ofNullable(agentToReturn);
	}

	private void fireBullet(AgentMob agent) {
		Vector2 targetVector = agent.getPosition().cpy().sub(position.cpy());
		int inaccuracy = MathUtils.random(-type.directionalInaccuracyInDegrees, type.directionalInaccuracyInDegrees);
		targetVector.rotate(inaccuracy);
		//TODO: magic number 8 = tilewidth/2
		HurtyThingBullet bullet = new HurtyThingBullet(position.cpy(), new Vector2(), targetVector);
		population.addBullet(bullet);
		bullets.add(bullet);
	}
	
	public int getRange() {
		return type.range;
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
