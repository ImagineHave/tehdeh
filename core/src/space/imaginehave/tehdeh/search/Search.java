package space.imaginehave.tehdeh.search;

import space.imaginehave.tehdeh.agent.AgentCore;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public abstract class Search {

	protected final GameStateTehDeh state;

	public Search(final GameStateTehDeh state) {
		this.state = state;
	}
	
	public abstract void calculatePathsForAgent(AgentCore boid);
}
