package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.search.Search;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

/**
 * create and manage agent populations
 * @author Christopher Williams
 *
 */
public class AgentService {
	
	public void createAgents(GameStateTehDeh state,
			Search search,
			int count
			) {
		
		for (int i = 0; i < count; i++) {
			MapObjectAgent moa = createDummyAgent(state);
			state.getAgentLayer().getObjects().add(moa);
			search.getAgents().add(moa);
		}
	
	}
	
	public void resetGoals(GameStateTehDeh state) {
		
		Array<MapObjectAgent> agents = state.getAgentLayer().getObjects().getByType(MapObjectAgent.class);
		
		for(MapObjectAgent agent : agents ) {
			agent.setGoal(state.getGoal());
		}
	}
	
	private DummyAgent createDummyAgent(GameStateTehDeh state) {
		return new DummyAgent(state.getRandomPosition(null, (int) state.getHeight() + 16), new Vector3(0,0,0), state.getGoal());
	}
	
}
