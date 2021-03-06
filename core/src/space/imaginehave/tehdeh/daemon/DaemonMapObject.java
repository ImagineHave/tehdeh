package space.imaginehave.tehdeh.daemon;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.agent.AgentMob;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.wave.Population;

public class DaemonMapObject extends MapObject {

	List<MapObject> theDead;
	Population population;

	public DaemonMapObject(Population population) {
		theDead = new ArrayList<MapObject>();
		this.population = population;
	}

	public void update() {
		Array<AgentMob> agents = population.getAgentLayer().getObjects().getByType(AgentMob.class);
		Array<HurtyThingBullet> projectiles = population.getAgentLayer().getObjects().getByType(HurtyThingBullet.class);
		
		for (AgentMob agent : agents) {
			if (agent.atGoal()){
				GameStateTehDeh.getInstance().getPlayer().hurt(agent);
				killAgent(agent);
			}
			
			checkAgentProjectileCollisions(projectiles, agent);
		}
	}

	private void checkAgentProjectileCollisions(Array<HurtyThingBullet> projectiles, AgentMob agent) {
		for(HurtyThingBullet projectile: projectiles) {
			if(Intersector.overlapConvexPolygons(projectile.getBoundingBox(), agent.getBoundingBox())) {
				agent.doDamage(projectile.getDamage());
				if(agent.isDead()) {
					GameStateTehDeh.getInstance().getPlayer().agentProfitted(agent);
					killAgent(agent);
				}
				population.getAgentLayer().getObjects().remove(projectile);
			}
		}
	}

	private void killAgent(AgentMob agent) {
		theDead.add(agent);
		population.getAgentLayer().getObjects().remove(agent);
	}

}
