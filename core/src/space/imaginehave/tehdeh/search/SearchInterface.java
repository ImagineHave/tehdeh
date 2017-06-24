package space.imaginehave.tehdeh.search;

import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.agent.Agent;

public interface SearchInterface {
	
	List<Vector3> calculatePath(Agent agent);
	
}
