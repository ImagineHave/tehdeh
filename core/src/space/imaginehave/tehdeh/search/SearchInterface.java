package space.imaginehave.tehdeh.search;

import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import space.imaginehave.tehdeh.agent.Agent;

public interface SearchInterface {
	
	List<Cell> calculatePath(Agent startNode, Cell goalNode);
	

}
