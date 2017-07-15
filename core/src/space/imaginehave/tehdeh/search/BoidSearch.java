package space.imaginehave.tehdeh.search;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.agent.AgentBoid;
import space.imaginehave.tehdeh.agent.AgentCore;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.tower.TowerMapObject;

public class BoidSearch extends Search {

	public BoidSearch(final GameStateTehDeh state) {
		super(state);
		
	}

	@Override
	public void calculatePathsForAgent(AgentCore boid) {
		
		Vector2 cohesion;
		Vector2 separation;
		Vector2 alignment;
		Vector2 goal;
		Vector2 bounds;
		Vector2 avoids;
	
		cohesion = getCohesion(boid);
		separation = getSeparation(boid);
		alignment = getAlignment(boid);
		goal = getGoal(boid);
		bounds = getBounds(boid);
		avoids = getAvoids(boid);
		
		Vector2 newVelocity = new Vector2(0,0)
				.add(cohesion)
				.add(separation)
				.add(alignment)
				.add(goal)
				.add(bounds)
				.add(avoids);
		
		newVelocity.add(boid.getVelocity());
		newVelocity.scl(1/(float)5);
		newVelocity.add(boid.getVelocity());
		newVelocity.clamp(0, 2);
		
		Vector2 newPosition = new Vector2(boid.getPosition());
		newPosition = newPosition.add(newVelocity);
		boid.getPostionPath().clear();
		boid.getVelocityPath().clear();
		boid.getVelocityPath().add(newVelocity);
		boid.getPostionPath().add(newPosition);
			
		
	}
	

	/**
	 * 
	 * Find the centre of everyone else.
	 * 
	 */
	private Vector2 getCohesion(AgentCore boid) {
		Vector2 cohesion = new Vector2(0,0);
		
		Array<AgentBoid> agents = state.getAgentLayer().getObjects().getByType(AgentBoid.class);
		
		for (AgentBoid notBoid : agents) {
			if (notBoid != boid) {
				cohesion.add(notBoid.getPosition());
			}
		}
		
		cohesion.scl(1/(float)(agents.size-1));
		cohesion.sub(boid.getPosition());
		cohesion.scl(1/(float)150);
		return cohesion;
	}
	
	
	/**
	 * 
	 * Don't move too close to everyone else.
	 */
	private Vector2 getSeparation(AgentCore boid) {
		Vector2 separation = new Vector2(0,0);
		
		Array<AgentBoid> agents = state.getAgentLayer().getObjects().getByType(AgentBoid.class);
		
		for (AgentBoid notBoid : agents) {
			if (notBoid != boid) {
				if (notBoid.getPosition().dst(boid.getPosition()) < 32) {
					separation = new Vector2(boid.getPosition());
					separation.sub(notBoid.getPosition());
				}
			}
		}
		return separation.scl(1/(float)20);
	}
	
	private Vector2 getAvoids(AgentCore boid) {
		Vector2 avoid = new Vector2(0,0);
		Array<TowerMapObject> dtas = state.getAgentLayer().getObjects().getByType(TowerMapObject.class);
		for( TowerMapObject dta : dtas){
			if (dta.getPosition().dst(boid.getPosition()) < 16) {
				avoid = new Vector2(boid.getPosition());
				avoid.sub(dta.getPosition());
			}
		}
		return avoid;
	}
	
	
	/**
	 * Keep swimming the same direction
	 */
	private Vector2 getAlignment(AgentCore boid) {
		Vector2 alignment = new Vector2(0,0);
		
		Array<AgentBoid> agents = state.getAgentLayer().getObjects().getByType(AgentBoid.class);
		
		for (AgentBoid notBoid : agents) {
			if (notBoid != boid) {
				alignment.add(notBoid.getVelocity());
			}
		}
		alignment.scl(1/(float)(agents.size-1));
		alignment.scl(1/(float) 10);
		return alignment;
	}
	
	
	/**
	 * head to the finish
	 */
	private Vector2 getGoal(AgentCore boid) {
		Vector2 goal = new Vector2(boid.getGoal());
		goal = goal.sub(boid.getPosition()).scl(1/(float)1000);
		return goal;
	}
	
	
	/**
	 * bound me up
	 */
	private Vector2 getBounds(AgentCore boid) {
		Vector2 bound = new Vector2(0,0);
		
		if (boid.getPosition().x < Constant.VIEWPORT_BOTTOM_LEFT.x) {
			bound.x = 1;
		} else if (boid.getPosition().x > Constant.VIEWPORT_TOP_RIGHT.x) {
			bound.x = -1;
		}
		
		if (boid.getPosition().y < Constant.VIEWPORT_BOTTOM_LEFT.y) {
			bound.y = 1;
		} else if (boid.getPosition().y > Constant.VIEWPORT_TOP_RIGHT.y) {
			bound.y = -1;
		}

		return bound;
	}
}
