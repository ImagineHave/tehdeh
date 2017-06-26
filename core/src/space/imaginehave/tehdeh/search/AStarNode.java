package space.imaginehave.tehdeh.search;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

public class AStarNode implements Comparable<AStarNode> {
	
	int x, y;

	public AStarNode pathParent;
	
	public float costFromStart = Integer.MAX_VALUE;
	
	public int estimatedCostToGoal;

	private TiledMapTileLayer tiledMapTileLayer;
	
	public AStarNode(int x, int y, TiledMapTileLayer tiledMapTileLayer) {
		this.tiledMapTileLayer = tiledMapTileLayer;
		this.x = x;
		this.y = y;
	}
	
	public AStarNode(Vector3 position, TiledMapTileLayer tiledMapTileLayer) {
		this.tiledMapTileLayer = tiledMapTileLayer;
		this.x = (int) (position.x /tiledMapTileLayer.getTileWidth());
		this.y = (int) (position.y / tiledMapTileLayer.getTileHeight());
	}

	public int getEstimatedCost(AStarNode goalNode) {
		return (int) Math.hypot(goalNode.x - x, goalNode.y - y);
	}

	public List<AStarNode> getNeighbors() {
		List<AStarNode> neighbors = new ArrayList<AStarNode>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if(i == 0 && j == 0) {
					continue;
				}
				int xToCheck = (int) (x+i);
				int yToCheck = (int) (y+j);
				if(xToCheck < 0 || xToCheck> tiledMapTileLayer.getWidth() ||
					yToCheck < 0 || yToCheck > tiledMapTileLayer.getHeight()
						) {
					continue;
				}
				if(tiledMapTileLayer.getCell(xToCheck, yToCheck) == null) {
					neighbors.add(new AStarNode(xToCheck, yToCheck, tiledMapTileLayer));
				}
			}
		}
		return neighbors;
	}

	public int getCost(AStarNode neighborNode) {
		return (int) Math.hypot(neighborNode.x - x, neighborNode.y - y);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		AStarNode other = (AStarNode) obj;
		if(!(this.x == other.x)
				|| 
			!( this.y == other.y)) {
			return false;
		}
		return true;
	}
	
	public float getCost() {
	    return costFromStart + estimatedCostToGoal;
	  }

	@Override
	public int compareTo(AStarNode o) {
		float thisValue = this.getCost();
	    float otherValue = ((AStarNode)o).getCost();

	    float v = thisValue - otherValue;
	    return (v>0)?1:(v<0)?-1:0; // sign function(obj == null) {
	}
}
