package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;

import space.imaginehave.tehdeh.search.Search;

public class AgentType {
	
	public Search search;
	Texture texture;

	public AgentType(Search search, Texture texture){
		this.search = search;
		this.texture = texture;
		
	}

}
