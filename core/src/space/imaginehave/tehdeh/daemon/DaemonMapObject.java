package space.imaginehave.tehdeh.daemon;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.agent.AgentMob;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class DaemonMapObject extends MapObject {

	List<MapObject> theDead;

	public DaemonMapObject() {
		theDead = new ArrayList<MapObject>();
	}

	public void update() {
		Array<AgentMob> agents = GameStateTehDeh.getInstance().getAgentLayer().getObjects().getByType(AgentMob.class);
		Array<HurtyThingBullet> projectiles = GameStateTehDeh.getInstance().getAgentLayer().getObjects().getByType(HurtyThingBullet.class);
		
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
				GameStateTehDeh.getInstance().getAgentLayer().getObjects().remove(projectile);
			}
		}
	}

	private void killAgent(AgentMob agent) {
		theDead.add(agent);
		GameStateTehDeh.getInstance().getAgentLayer().getObjects().remove(agent);
	}

}
