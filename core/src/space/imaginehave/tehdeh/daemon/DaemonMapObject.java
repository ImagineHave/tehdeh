package space.imaginehave.tehdeh.daemon;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.agent.AgentMapObject;
import space.imaginehave.tehdeh.hurtythings.HurtyThingMapObject;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class DaemonMapObject extends MapObject {

	List<MapObject> theDead;
	GameStateTehDeh state;

	public DaemonMapObject(GameStateTehDeh state) {
		this.state = state;
		theDead = new ArrayList<MapObject>();
	}

	public void update() {
		Array<AgentMapObject> agents = state.getAgentLayer().getObjects().getByType(AgentMapObject.class);
		Array<HurtyThingMapObject> projectiles = state.getAgentLayer().getObjects().getByType(HurtyThingMapObject.class);
		
		for (AgentMapObject agent : agents) {
			if (agent.atGoal()){
				theDead.add(agent);
				state.getAgentLayer().getObjects().remove(agent);
			}
			
			for(HurtyThingMapObject projectile: projectiles) {
				if(Intersector.overlapConvexPolygons(projectile.getBoundingBox(), agent.getBoundingBox())) {
					state.getAgentLayer().getObjects().remove(agent);
					state.getAgentLayer().getObjects().remove(projectile);
				}
			}
		}
	}

}
