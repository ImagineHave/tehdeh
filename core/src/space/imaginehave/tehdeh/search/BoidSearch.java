package space.imaginehave.tehdeh.search;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.agent.DummyTowerAgent;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class BoidSearch extends Search {

	public BoidSearch(final GameStateTehDeh state) {
		super(state);
	}

	@Override
	public void calculatePathsForRegisteredAgents() {
		
		Vector3 cohesion;
		Vector3 separation;
		Vector3 alignment;
		Vector3 goal;
		Vector3 bounds;
		Vector3 avoids;
	
		
		for(Agent boid : getAgents()) {
			
			cohesion = getCohesion(boid);
			separation = getSeparation(boid);
			alignment = getAlignment(boid);
			goal = getGoal(boid);
			bounds = getBounds(boid);
			avoids = getAvoids(boid);
			
			Vector3 newVelocity = new Vector3(0,0,0)
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
			
			boid.getVelocityPath().add(newVelocity);
			Vector3 newPosition = new Vector3(boid.getPosition());
			newPosition = newPosition.add(newVelocity);
			boid.getPostionPath().add(newPosition);
			
		}
		
	}
	

	/**
	 * 
	 * Find the centre of everyone else.
	 * 
	 */
	private Vector3 getCohesion(Agent boid) {
		Vector3 cohesion = new Vector3(0,0,0);
		for (Agent notBoid : getAgents()) {
			if (notBoid != boid) {
				cohesion.add(notBoid.getPosition());
			}
		}
		
		cohesion.scl(1/(float)(getAgents().size()-1));
		cohesion.sub(boid.getPosition());
		cohesion.scl(1/(float)150);
		return cohesion;
	}
	
	
	/**
	 * 
	 * Don't move too close to everyone else.
	 */
	private Vector3 getSeparation(Agent boid) {
		Vector3 separation = new Vector3(0,0,0);
		for (Agent notBoid : getAgents()) {
			if (notBoid != boid) {
				if (notBoid.getPosition().dst(boid.getPosition()) < 32) {
					separation = new Vector3(boid.getPosition());
					separation.sub(notBoid.getPosition());
				}
			}
		}
		return separation.scl(1/(float)20);
	}
	
	private Vector3 getAvoids(Agent boid) {
		Vector3 avoid = new Vector3(0,0,0);
		Array<DummyTowerAgent> dtas = state.getTowerLayer().getObjects().getByType(DummyTowerAgent.class);
		for( DummyTowerAgent dta : dtas){
			if (dta.getPosition().dst(boid.getPosition()) < 16) {
				avoid = new Vector3(boid.getPosition());
				avoid.sub(dta.getPosition());
			}
		}
		return avoid;
	}
	
	
	/**
	 * Keep swimming the same direction
	 */
	private Vector3 getAlignment(Agent boid) {
		Vector3 alignment = new Vector3(0,0,0);
		for (Agent notBoid : getAgents()) {
			if (notBoid != boid) {
				alignment.add(notBoid.getVelocity());
			}
		}
		alignment.scl(1/(float)(getAgents().size()-1));
		alignment.scl(1/(float) 10);
		return alignment;
	}
	
	
	/**
	 * head to the finish
	 */
	private Vector3 getGoal(Agent boid) {
		Vector3 goal = new Vector3(boid.getGoal());
		goal = goal.sub(boid.getPosition()).scl(1/(float)1000);
		return goal;
	}
	
	
	/**
	 * bound me up
	 */
	private Vector3 getBounds(Agent boid) {
		Vector3 bound = new Vector3(0,0,0);
		
		if (boid.getPosition().x < state.getBottomLeft().x) {
			bound.x = 1;
		} else if (boid.getPosition().x > state.getTopRight().x) {
			bound.x = -1;
		}
		
		if (boid.getPosition().y < state.getBottomLeft().y) {
			bound.y = 1;
		} else if (boid.getPosition().y > state.getTopRight().y) {
			bound.y = -1;
		}

		return bound;
	}
}
