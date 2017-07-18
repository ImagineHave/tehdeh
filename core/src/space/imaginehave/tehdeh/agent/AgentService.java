package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.Util;
import space.imaginehave.tehdeh.search.AStarSearch;
import space.imaginehave.tehdeh.search.BoidSearch;
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
		
		this.placeholderAgentStarter(state);
		
	}
	
	public void resetGoals(GameStateTehDeh state) {
		
		Array<AgentMapObject> agents = state.getAgentLayer().getObjects().getByType(AgentMapObject.class);
		
		for(AgentMapObject agent : agents ) {
			agent.setGoal(state.getGoal());
		}
	}
	
	private AgentMob createAgentBoid(GameStateTehDeh state) {
		AgentType type = new AgentType(new BoidSearch(state), state.getAssetManager().get("boidAgent.png", Texture.class));
		return new AgentMob(Util.getRandomPosition(null, (int) Constant.GAME_HEIGHT + 16), new Vector2(0,0), state, type);
	}
	
	private AgentMob createAgentAStar(GameStateTehDeh state) {
		AgentType type = new AgentType(new AStarSearch(state), state.getAssetManager().get("aStarAgent.png", Texture.class));
		return new AgentMob(Util.getRandomPosition(null, (int) Constant.GAME_HEIGHT + 16), new Vector2(0,0), state, type);
	}
	
	private AgentMob createAgentThetaStar(GameStateTehDeh state) {
		AgentType type = new AgentType(new ThetaStarLazySearch(state), state.getAssetManager().get("tStarAgent.png", Texture.class));
		return new AgentMob(Util.getRandomPosition(null, (int) Constant.GAME_HEIGHT + 16), new Vector2(0,0), state, type);
	}
	
}
