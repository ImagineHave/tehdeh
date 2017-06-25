package search;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector3;

public class AStarNode {
	
	int x, y;

	public AStarNode pathParent;
	
	public float costFromStart;
	
	public int estimatedCostToGoal;

	private TiledMapTileLayer tiledMapTileLayer;
	
	public AStarNode(int x, int y, TiledMapTileLayer tiledMapTileLayer) {
		this.tiledMapTileLayer = tiledMapTileLayer;
		this.x = x;
		this.y = y;
	}
	
	public AStarNode(Vector3 position, TiledMapTileLayer tiledMapTileLayer) {
		this.tiledMapTileLayer = tiledMapTileLayer;
		this.x = (int) position.x;
		this.y = (int) position.y;
	}

	public int getEstimatedCost(AStarNode goalNode) {
		return Math.abs(goalNode.x + x) + Math.abs(goalNode.y + y);
	}

	public List<AStarNode> getNeighbors() {
		List<AStarNode> neighbors = new ArrayList<AStarNode>();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j<2; j++) {
				if(i == x && j == y) {
					continue;
				}
				if(tiledMapTileLayer.getCell(i, j) == null) {
					neighbors.add(new AStarNode(i,j, tiledMapTileLayer));
				}
			}
		}
		return neighbors;
	}

	public int getCost(AStarNode neighborNode) {
		return 1;
	}

}
