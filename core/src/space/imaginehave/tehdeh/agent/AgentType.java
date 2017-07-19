package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;

import space.imaginehave.tehdeh.search.Search;

public class AgentType {
	
	public Search search;
	Texture texture;
	int stepsToFollowPath;
	int patience;

	public AgentType(Search search, Texture texture){
		this.search = search;
		this.texture = texture;
		stepsToFollowPath = 5;
		patience = 3;
	}

}
