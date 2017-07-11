package space.imaginehave.tehdeh.search;

import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

public class AStarNode implements Comparable<AStarNode> {
	
	int x, y;

	public AStarNode pathParent;
	
	public float costFromStart = Integer.MAX_VALUE;
	
	public int estimatedCostToGoal = Integer.MAX_VALUE;

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

	public int calculateEstimatedCostToGoal(AStarNode goalNode) {
		return (int) ( Math.pow(x - goalNode.x, 2) +
				Math.pow(y - goalNode.y,2));
	}

	public List<AStarNode> getNeighbors() {
		List<AStarNode> neighbors = new ArrayList<AStarNode>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if(i == j) {
					continue;
				}
				int xToCheck = (int) (x+i);
				int yToCheck = (int) (y+j);
				if(xToCheck < 0 || xToCheck> tiledMapTileLayer.getWidth() ||
					yToCheck < 0 || yToCheck > tiledMapTileLayer.getHeight()+2 //TODO this allows neighbors outside of sceen in direction of agent start area.
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
	
	public float getCost() {
	    return costFromStart + estimatedCostToGoal;
	  }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AStarNode other = (AStarNode) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public int compareTo(AStarNode o) {
		float thisValue = this.getCost();
	    float otherValue = ((AStarNode)o).getCost();

	    float v = thisValue - otherValue;
	    return (v>0)?1:(v<0)?-1:0; // sign function(obj == null) {
	}
}
