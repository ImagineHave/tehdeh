package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.Util;
import space.imaginehave.tehdeh.search.Search;
import space.imaginehave.tehdeh.search.SearchType;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

/**
 * create and manage agent populations
 * @author Christopher Williams
 *
 */
public class AgentService {
	
	
	public void placeholderAgentStarter(GameStateTehDeh state){
		createAgentsOfType(state, 100, SearchType.BOID);
		createAgentsOfType(state, 1, SearchType.ASTAR);
		createAgentsOfType(state, 1, SearchType.THETASTAR);
	}
	
	
	public void createAgentsOfType(GameStateTehDeh state, int count, SearchType searchType) {
		for(int i = 0; i < count; i++) {
			createAddAgentToPopulation(state, searchType);
		}
	}
	
	public void createAddAgentToPopulation(GameStateTehDeh state,
			SearchType searchType
			) {
		
		switch (searchType) {
			case BOID : 
				state.getAgentLayer().getObjects().add(createAgentBoid(state));
				break;
			case ASTAR : 
				state.getAgentLayer().getObjects().add(createAgentAStar(state));
				break;
			case THETASTAR : 
				state.getAgentLayer().getObjects().add(createAgentThetaStar(state));
				break;
			default :
				throw new RuntimeException("Search type not known: " + searchType);
		}
	}
	
	public void reset(GameStateTehDeh state){
		
		for(AgentMapObject moa : state.getAgentLayer().getObjects().getByType(AgentMapObject.class)){
			state.getAgentLayer().getObjects().remove(moa);
		}
		
	}
	
	public void resetGoals(GameStateTehDeh state) {
		
		Array<AgentCore> agents = state.getAgentLayer().getObjects().getByType(AgentCore.class);
		
		for(AgentCore agent : agents ) {
			agent.setGoal(state.getGoal());
		}
	}
	
	private AgentBoid createAgentBoid(GameStateTehDeh state) {
		return new AgentBoid(Util.getRandomPosition(null, (int) Constant.GAME_HEIGHT + 16), new Vector2(0,0), state.getGoal(), state);
	}
	
	private AgentAStar createAgentAStar(GameStateTehDeh state) {
		return new AgentAStar(Util.getRandomPosition(null, (int) Constant.GAME_HEIGHT + 16), new Vector2(0,0), state.getGoal(), state);
	}
	
	private AgentThetaStar createAgentThetaStar(GameStateTehDeh state) {
		return new AgentThetaStar(Util.getRandomPosition(null, (int) Constant.GAME_HEIGHT + 16), new Vector2(0,0), state.getGoal(), state);
	}
	
}
