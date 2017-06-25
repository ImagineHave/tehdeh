package space.imaginehave.tehdeh.search;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.agent.Agent;

public class BoidSearch implements SearchInterface {

	private static BoidSearch boidSearch;
	private final ArrayList<Agent> boids;
	

	@Override
	public List<Vector3> calculatePath(Agent agent) {
		moveBoids();
		return null;
	}

	public static BoidSearch getInstance() {
		if (BoidSearch.boidSearch == null) {
			BoidSearch.boidSearch = new BoidSearch();
		}
		return BoidSearch.boidSearch;
	}

	private BoidSearch() {
		boids = new ArrayList<Agent>();
	}

	public List<Agent> getAgents() {
		return boids;
	}


	public void moveBoids() {
		
		Vector3 cohesion;
		Vector3 separation;
		Vector3 alignment;
		Vector3 goal;
		Vector3 bounds;
		
		for(Agent boid : boids) {
			
			cohesion = getCohesion(boid);
			separation = getSeparation(boid);
			alignment = getAlignment(boid);
			goal = getGoal(boid);
			bounds = getBounds(boid);
			
			Vector3 velocity = new Vector3(boid.getVelocity()
					.add(cohesion)
					.add(separation)
					.add(alignment)
					.add(goal)
					.add(bounds));
			
			velocity = velocity.clamp(0, 3);
			boid.getVelocityPath().add(velocity);
			Vector3 newPosition = new Vector3(boid.getPosition());
			newPosition.add(velocity);
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
		for (Agent notBoid : boids) {
			if (notBoid != boid) {
				cohesion.add(notBoid.getPosition());
			}
		}
		
		cohesion.scl(1/(float)(boids.size()-1));
		cohesion.sub(boid.getPosition());
		cohesion.scl(1/(float)100);
		return cohesion;
	}
	
	
	/**
	 * 
	 * Don't move too close to everyone else.
	 */
	private Vector3 getSeparation(Agent boid) {
		Vector3 separation = new Vector3(0,0,0);
		for (Agent notBoid : boids) {
			if (notBoid != boid) {
				if (notBoid.getPosition().dst(boid.getPosition()) < 16) {
					Vector3 position = new Vector3(boid.getPosition());
					separation.sub(position.sub(notBoid.getPosition()));
				}
			}
		}
		return separation.scl(16);
	}
	
	
	/**
	 * Keep swimming the same direction
	 */
	private Vector3 getAlignment(Agent boid) {
		Vector3 alignment = new Vector3(0,0,0);
		for (Agent notBoid : boids) {
			if (notBoid != boid) {
				alignment.add(notBoid.getVelocity());
			}
		}
		alignment.scl(1/(float)(boids.size()-1));
		alignment.scl(1/(float) 8);
		return alignment;
	}
	
	
	/**
	 * head to the finish
	 */
	private Vector3 getGoal(Agent boid) {
		Vector3 goal = new Vector3(boid.getGoal());
		goal = goal.sub(boid.getPosition()).scl(1/100);
		return goal;
	}
	
	
	/**
	 * bound me up
	 */
	private Vector3 getBounds(Agent boid) {
		Vector3 bound = new Vector3(0,0,0);
		
		if (boid.getPosition().x < boid.getMinPosition().x) {
			bound.x = 1;
		} else if (boid.getPosition().x > boid.getMaxPosition().x) {
			bound.x = -1;
		}
		
		if (boid.getPosition().y < boid.getMinPosition().y) {
			bound.y = 1;
		} else if (boid.getPosition().y > boid.getMaxPosition().y) {
			bound.y = -1;
		}

		return bound;
	}
}
