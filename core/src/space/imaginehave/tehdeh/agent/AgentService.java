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
import space.imaginehave.tehdeh.wave.Population;

/**
 * create and manage agent populations
 * @author Christopher Williams
 *
 */
public class AgentService {
	
	
	public void placeholderAgentStarter(){
		createAgentsOfType(100, SearchType.BOID);
		createAgentsOfType(1, SearchType.ASTAR);
		createAgentsOfType(1, SearchType.THETASTAR);
	}
	
	
	public void createAgentsOfType(int count, 
			SearchType searchType) {
		for(int i = 0; i < count; i++) {
			createAddAgentToPopulation(searchType);
		}
	}
	
	public void createAddAgentToPopulation(SearchType searchType) {
		
		switch (searchType) {
			case BOID : 
				Population.getInstance().getAgentLayer().getObjects().add(createAgent(new BoidSearch(), AssetManager.getInstance().getTexture("boidAgent.png")));
				break;
			case ASTAR : 
				Population.getInstance().getAgentLayer().getObjects().add(createAgent(new AStarSearch(), AssetManager.getInstance().getTexture("aStarAgent.png")));
				break;
			case THETASTAR : 
				Population.getInstance().getAgentLayer().getObjects().add(createAgent(new ThetaStarLazySearch(), AssetManager.getInstance().getTexture("tStarAgent.png")));
				break;
			default :
				throw new RuntimeException("Search type not known: " + searchType);
		}
	}
	
	public void reset(){
		
		for(AgentMob moa : Population.getInstance().getAgentLayer().getObjects().getByType(AgentMob.class)){
			Population.getInstance().getAgentLayer().getObjects().remove(moa);
		}
		
		this.placeholderAgentStarter();
		
	}
	
	public void resetGoals() {
		
		Array<AgentMob> agents = Population.getInstance().getAgentLayer().getObjects().getByType(AgentMob.class);
		
		for(AgentMob agent : agents ) {
			agent.setGoal(GameStateTehDeh.getInstance().getGoal());
		}
	}
	
	private AgentMob createAgent(Search search, Texture texture) {
		AgentType type = new AgentType(search, texture);
		return new AgentMob(Util.getRandomPosition(null, (int) Constant.GAME_HEIGHT + 16), new Vector2(0,0), GameStateTehDeh.getInstance(), type);
	}
}
	
