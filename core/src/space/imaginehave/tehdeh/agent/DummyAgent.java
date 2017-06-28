package space.imaginehave.tehdeh.agent;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.search.SearchInterface;

public class DummyAgent extends MapObjectAgent {
	
	Random random = new Random();
	SearchInterface search;
	private int name;
	
	public DummyAgent (Viewport viewport, SearchInterface search, int name) {
		super.position = new Vector3(
				random.nextInt((int) viewport.getWorldWidth()),
				viewport.getWorldHeight() + 64,
				0f);
		
		super.velocity = new Vector3(0,0,0);
		
		super.goal = new Vector3(
				25*16,
				-64,
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
		
		this.name = name;
	}

	@Override
	public void setGoal(Vector3 goalVector) {
		this.goal = goalVector;
	}

	@Override
	public void update() {
		if(!positionPath.isEmpty()) {
			position = new Vector3(positionPath.get(positionPath.size()-1));
			positionPath.clear();
		}			
		if(!velocityPath.isEmpty()) {
			velocity = new Vector3(velocityPath.get(velocityPath.size()-1));
			velocityPath.clear();
		}
	}
	
	@Override
	public String toString() {
		return String.format("id: %d p: %f,%f v: %f,%f", name, position.x, position.y, velocity.x, velocity.y);
		
	}
}
