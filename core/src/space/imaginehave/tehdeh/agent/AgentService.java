package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.AssetManager;
import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.Util;
import space.imaginehave.tehdeh.search.AStarSearch;
import space.imaginehave.tehdeh.search.BoidSearch;
import space.imaginehave.tehdeh.search.Search;
import space.imaginehave.tehdeh.search.SearchType;
import space.imaginehave.tehdeh.search.ThetaStarLazySearch;
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
	
	
	public void createAgentsOfType(GameStateTehDeh state, 
			int count, 
			SearchType searchType) {
		for(int i = 0; i < count; i++) {
			createAddAgentToPopulation(state, searchType);
		}
	}
	
	public void createAddAgentToPopulation(GameStateTehDeh state, SearchType searchType) {
		
		switch (searchType) {
			case BOID : 
				state.getAgentLayer().getObjects().add(createAgent(state, new BoidSearch(state), AssetManager.getInstance().getTexture("boidAgent.png")));
				break;
			case ASTAR : 
				state.getAgentLayer().getObjects().add(createAgent(state, new AStarSearch(state), AssetManager.getInstance().getTexture("aStarAgent.png")));
				break;
			case THETASTAR : 
				state.getAgentLayer().getObjects().add(createAgent(state,new ThetaStarLazySearch(state), AssetManager.getInstance().getTexture("tStarAgent.png")));
				break;
			default :
				throw new RuntimeException("Search type not known: " + searchType);
		}
	}
	
	public void reset(GameStateTehDeh state){
		
		for(AgentMob moa : state.getAgentLayer().getObjects().getByType(AgentMob.class)){
			state.getAgentLayer().getObjects().remove(moa);
		}
		
		this.placeholderAgentStarter(state);
		
	}
	
	public void resetGoals(GameStateTehDeh state) {
		
		Array<AgentMob> agents = state.getAgentLayer().getObjects().getByType(AgentMob.class);
		
		for(AgentMob agent : agents ) {
			agent.setGoal(state.getGoal());
		}
	}
	
	private AgentMob createAgent(GameStateTehDeh state, Search search, Texture texture) {
		AgentType type = new AgentType(search, texture);
		return new AgentMob(Util.getRandomPosition(null, (int) Constant.GAME_HEIGHT + 16), new Vector2(0,0), state, type);
	}
}
	
