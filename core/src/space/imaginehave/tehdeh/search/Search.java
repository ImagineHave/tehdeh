package space.imaginehave.tehdeh.search;

import space.imaginehave.tehdeh.agent.AgentMob;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.wave.Population;

public abstract class Search {

	Population population;
	
	public Search(Population population) {
		this.population = population;
	}
	
	public abstract void calculatePathsForAgent(AgentMob boid);
	
	public abstract boolean isPathFollowing();
}
