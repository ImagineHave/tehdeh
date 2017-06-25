package space.imaginehave.tehdeh.agent;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.search.SearchInterface;

public class DummyAgent extends MapObjectAgent {
	
	Random random = new Random();
	SearchInterface search;
	
	public DummyAgent (Viewport viewport, SearchInterface search) {
		super.position = new Vector3(
				random.nextInt((int) viewport.getWorldWidth()),
				random.nextInt((int) viewport.getWorldHeight()),
				0f);
		
		super.velocity = new Vector3(0,0,0);
		
		super.goal = new Vector3(
				-15,
				viewport.getWorldHeight()/2,
				0);
		
		super.maxPosition = new Vector3(
				viewport.getWorldWidth(), 
				viewport.getWorldHeight(),
				0f);
		
		super.minPosition = new Vector3(0,0,0);
		
		super.positionPath = new ArrayList<Vector3>();
		
		positionPath.add(position);
		
		super.velocityPath = new ArrayList<Vector3>();
		
		velocityPath.add(velocity);
		
		this.search = search;
	}
	

	@Override
	public void update() {
		position = positionPath.get(positionPath.size()-1);
		positionPath.clear();
		velocity = velocityPath.get(velocityPath.size()-1);
		velocityPath.clear();
		
	}

}