package space.imaginehave.tehdeh.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AgentTower extends AgentMapObject {
	
	int range = 50;
	int firingDelay = 3;
	float delayTimer=3;
	int directionalInaccuracyInDegrees = 15;
	private MapObjects agents;
	private GameStateTehDeh state;
	private List<AgentBullet> bullets = new ArrayList<AgentBullet>();

	public AgentTower (Vector3 position, GameStateTehDeh state) {
		super(position, new Vector3(0,0,0), new Vector3(0,0,0));
		this.state = state;
	}

	@Override
	public void update() {
		delayTimer += Gdx.graphics.getDeltaTime();
		if(delayTimer > firingDelay) {
			Optional<AgentCore> agent = getClosestAgentInRange();
			if(agent.isPresent()) {
				fireBullet(agent.get());
				delayTimer = 0;
			}
		}
		for(int i = 0; i < bullets.size(); i++) {
			AgentBullet bullet = bullets.get(i);
			if (bullet.isToRemove()) {
				bullets.remove(bullet);
				state.getAgentLayer().getObjects().remove(bullet);
			}
		}
		
	}

	private Optional<AgentCore> getClosestAgentInRange() {
		AgentCore agentToReturn = null;
		for(int i = 0; i<agents.getCount(); i++) {
			MapObject mapObject = agents.get(i);
			if(mapObject instanceof AgentCore) {
				AgentCore agent = (AgentCore) mapObject;
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

	private void fireBullet(AgentCore agent) {
		Vector3 targetVector = agent.getPosition().cpy().sub(position.cpy());
		int inaccuracy = MathUtils.random(-directionalInaccuracyInDegrees, directionalInaccuracyInDegrees);
		Vector2 targetVector2 = new Vector2(targetVector.x, targetVector.y);
		targetVector2.rotate(inaccuracy);
		AgentBullet bullet = new AgentBullet(position, new Vector3(), new Vector3(targetVector2.x, targetVector2.y , 0));
		state.addBullet(bullet);
		bullets.add(bullet);
	}
	
	public int getRange() {
		return range;
	}
	
	public void setAgents(MapObjects mapObjects) {
		this.agents = mapObjects;
	}

	@Override
	public void suicide() {
		// TODO Auto-generated method stub
		
	}

}
