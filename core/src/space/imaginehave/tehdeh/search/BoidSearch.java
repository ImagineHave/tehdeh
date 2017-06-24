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
			
			Vector3 velocity = boid.getVelocity()
					.add(cohesion)
					.add(separation)
					.add(alignment)
					.add(goal)
					.add(bounds)
					;
			
			velocity = velocity.clamp(0, 1);
			boid.getVelocityPath().add(velocity);
			boid.getPostionPath().add(boid.getPosition().add(velocity));
		}
		
	}
	

	/**
	 * 
	 * Find the centre of everyone else.
	 * 
	 */
	private Vector3 getCohesion(Agent boid) {
		Vector3 cohesion = Vector3.Zero;
		for (Agent notBoid : boids) {
			if (notBoid != boid) {
				cohesion = cohesion.add(boid.getPosition());
			}
		}
		
		cohesion = cohesion.scl(1/(float)(boids.size()-1));
		cohesion = cohesion.scl(1/(float)100);
		cohesion = cohesion.sub(boid.getPosition());
		return cohesion;
	}
	
	
	/**
	 * 
	 * Don't move too close to everyone else.
	 */
	private Vector3 getSeparation(Agent boid) {
		Vector3 separation = Vector3.Zero;
		for (Agent notBoid : boids) {
			if (notBoid != boid) {
				if (notBoid.getPosition().dst(boid.getPosition()) < 100) {
					separation = separation.sub(boid.getPosition().sub(notBoid.getPosition()));
				}
			}
		}
		return separation;
	}
	
	
	/**
	 * Keep swimming the same direction
	 */
	private Vector3 getAlignment(Agent boid) {
		Vector3 alignment = Vector3.Zero;
		for (Agent notBoid : boids) {
			if (notBoid != boid) {
				alignment = alignment.add(notBoid.getVelocity());
			}
		}
		alignment = alignment.scl(1/(float)(boids.size()-1));
		alignment = alignment.scl(1/(float)8);
		return alignment;
	}
	
	
	/**
	 * head to the finish
	 */
	private Vector3 getGoal(Agent boid) {
		Vector3 goal = boid.getGoal();
		goal = goal.sub(boid.getPosition());
		return goal;
	}
	
	
	/**
	 * bound me up
	 */
	private Vector3 getBounds(Agent boid) {
		Vector3 bound = Vector3.Zero;
		
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
