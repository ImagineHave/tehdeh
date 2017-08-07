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
	
	
	public void placeholderAgentStarter(Population population){
		createAgentsOfType(100, SearchType.BOID, population);
		createAgentsOfType(1, SearchType.ASTAR, population);
		createAgentsOfType(1, SearchType.THETASTAR, population);
	}
	
	
	public void createAgentsOfType(int count, 
			SearchType searchType,
			Population population) {
		for(int i = 0; i < count; i++) {
			createAddAgentToPopulation(searchType, population);
		}
	}
	
	public void createAddAgentToPopulation(SearchType searchType, Population population) {
		
		switch (searchType) {
			case BOID : 
				population.getAgentLayer().getObjects().add(createAgent(new BoidSearch(population), AssetManager.getInstance().getTexture("boidAgent.png")));
				break;
			case ASTAR : 
				population.getAgentLayer().getObjects().add(createAgent(new AStarSearch(population), AssetManager.getInstance().getTexture("aStarAgent.png")));
				break;
			case THETASTAR : 
				population.getAgentLayer().getObjects().add(createAgent(new ThetaStarLazySearch(population), AssetManager.getInstance().getTexture("tStarAgent.png")));
				break;
			default :
				throw new RuntimeException("Search type not known: " + searchType);
		}
	}
	
	public void reset(Population population){
		
		for(AgentMob moa : population.getAgentLayer().getObjects().getByType(AgentMob.class)){
			population.getAgentLayer().getObjects().remove(moa);
		}
		
		this.placeholderAgentStarter(population);
		
	}
	
	public void resetGoals(Population population) {
		
		Array<AgentMob> agents = population.getAgentLayer().getObjects().getByType(AgentMob.class);
		
		for(AgentMob agent : agents ) {
			agent.setGoal(GameStateTehDeh.getInstance().getGoal());
		}
	}
	
	private AgentMob createAgent(Search search, Texture texture) {
		AgentType type = new AgentType(search, texture);
		return new AgentMob(Util.getRandomPosition(null, (int) Constant.GAME_HEIGHT + 16), new Vector2(0,0), GameStateTehDeh.getInstance(), type);
	}
}
	
