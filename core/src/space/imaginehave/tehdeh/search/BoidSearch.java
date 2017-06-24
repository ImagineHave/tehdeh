package space.imaginehave.tehdeh.search;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import space.imaginehave.tehdeh.agent.Agent;

public class BoidSearch implements SearchInterface {
	
	private static BoidSearch boidSearch;
	private final ArrayList<Agent> boids;
	
	private BoidSearch() {
		boids = new ArrayList<Agent>();
	}

	@Override
	public List<Cell> calculatePath(Cell startNode, Cell goalNode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static BoidSearch getInstance() {
		if(BoidSearch.boidSearch == null) {
			BoidSearch.boidSearch = new BoidSearch();
		}
		return BoidSearch.boidSearch;
	}

}
