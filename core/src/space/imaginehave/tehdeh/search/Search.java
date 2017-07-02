package space.imaginehave.tehdeh.search;

import java.util.ArrayList;
import java.util.List;

import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public abstract class Search {

	protected final GameStateTehDeh state;
	protected final ArrayList<Agent> agents;

	public Search(final GameStateTehDeh state) {
		agents = new ArrayList<Agent>();
		this.state = state;
	}
	
	public List<Agent> getAgents() {
		return agents;
	}
	
	public abstract void calculatePathsForRegisteredAgents();
}
