package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.graphics.Texture;

import space.imaginehave.tehdeh.search.Search;

class AgentType {
	
	public Search search;
	Texture texture;
	int stepsToFollowPath;
	int patience;
	int sight;
	int maxHealth;
	int score;
	int money;
	int damage;

	public AgentType(Search search, Texture texture){
		this.search = search;
		this.texture = texture;
		stepsToFollowPath = 5;
		patience = 3;
		maxHealth = 4;
	}

}
