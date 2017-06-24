package space.imaginehave.tehdeh.search;

import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

public interface SearchInterface {
	
	List<Cell> calculatePath(Cell startNode, Cell goalNode);
	

}
