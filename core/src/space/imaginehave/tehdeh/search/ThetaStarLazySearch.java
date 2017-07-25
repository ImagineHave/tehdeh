package space.imaginehave.tehdeh.search;
import java.util.*;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.agent.AgentMob;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class ThetaStarLazySearch extends Search {
	
	TiledMapTileLayer tiledMapTileLayer;
	private PriorityQueue<AStarNode> openList;
	private HashSet<AStarNode> closedList;
	
	
	public ThetaStarLazySearch (GameStateTehDeh state) {
		super(state);
		this.tiledMapTileLayer = state.getTowerLayer();
	}
	

	/**
    Find the path from the start node to the end node. A list
    of AStarNodes is returned, or null if the path is not
    found. 
	 * @param agent 
  */
  public List<Vector2> findPath(AStarNode startNode, AStarNode goalNode, AgentMob agent) {
 
	openList = new PriorityQueue<AStarNode>();
    closedList = new HashSet<AStarNode>();
    startNode.costFromStart = 0;
    startNode.pathParent = startNode;
    startNode.estimatedCostToGoal = startNode.calculateEstimatedCostToGoal(goalNode);

    openList.add(startNode);

    while (!openList.isEmpty()) {
      AStarNode node = (AStarNode)openList.poll();
      
      if (  node.equals(goalNode)) {  
        // construct the path from start to goal
        return constructPath(node, agent);
      }
      setVertex(node);
      closedList.add(node);
      for(AStarNode neighborNode : node.getNeighbors()) {
        boolean isOpen = openList.contains(neighborNode);
        boolean isClosed = closedList.contains(neighborNode);

        if(!isClosed) {
        	if(!isOpen) {
        			neighborNode.costFromStart = Integer.MAX_VALUE;
        			neighborNode.pathParent = null;
        			neighborNode.estimatedCostToGoal = neighborNode.calculateEstimatedCostToGoal(goalNode);
        	}
        	updateVertex(node, neighborNode);
        }
      }
    }

    // no path found. return path to the node with the smallest estimated cost
    AStarNode nodeWithSmallestCost = null;
    for(AStarNode node : closedList) {
    	if(nodeWithSmallestCost == null || (node.getCost() < nodeWithSmallestCost.getCost())) {
    		nodeWithSmallestCost = node;
    	}
    }
    return constructPath(nodeWithSmallestCost, agent);
  }

	
  private void setVertex(AStarNode node) {
		if(!lineOfSight(node.pathParent, node)) {
			Set<AStarNode> neighborInClosedSet = new HashSet<AStarNode>(node.getNeighbors());
			neighborInClosedSet.retainAll(closedList);
			float minCostFromStart = Integer.MAX_VALUE;
			
			for(AStarNode neighborNode: neighborInClosedSet) {
				float neighborCostFromStart = neighborNode.costFromStart + neighborNode.getCost(node);
				if(neighborCostFromStart < minCostFromStart) {
					node.pathParent = neighborNode;
					node.costFromStart = neighborCostFromStart; 
				}
			}
		}
	}

  	private void updateVertex(AStarNode node, AStarNode neighborNode) {
		float oldCost = neighborNode.costFromStart;
		computeCost(node, neighborNode);
		if(neighborNode.costFromStart < oldCost) {
			openList.add(neighborNode);
		}
	}


	private void computeCost(AStarNode node, AStarNode neighborNode) {
		float calculatedCostForGrandParent = node.pathParent.costFromStart + node.pathParent.getCost(neighborNode);
		if (calculatedCostForGrandParent < neighborNode.costFromStart) {
			neighborNode.pathParent = node.pathParent;
			neighborNode.costFromStart = calculatedCostForGrandParent;
		}
	}



  /**
    Construct the path, not including the start node.
  */
  protected List<Vector2> constructPath(AStarNode node, AgentMob agent) {
    LinkedList<Vector2> path = new LinkedList<Vector2>();
    while (node.pathParent != node) {
    	float nodeXPixels = node.x*state.getTowerLayer().getTileWidth();
		float nodeYPixels = node.y*state.getTowerLayer().getTileHeight();
		float nodeParentXPixels = node.pathParent.x*state.getTowerLayer().getTileWidth();
		float nodeParentYPixels = node.pathParent.y*state.getTowerLayer().getTileHeight();
		path.addLast(new Vector2(nodeXPixels, nodeYPixels));
		
		float difX = nodeXPixels - nodeParentXPixels;
		float difY = nodeYPixels - nodeParentYPixels;
		double distanceBetweenNodes = Math.hypot(difX, difY);  

    	if(node.pathParent.pathParent == node.pathParent) {
    		float firstX = agent.getPosition().x;
    		float firstY = agent.getPosition().y;
    		difX = nodeXPixels-firstX;
    		difY = nodeYPixels-firstY;
    		distanceBetweenNodes = Math.hypot(difX, difY);
    	}
      Vector2 diffVector = new Vector2(difX, difY);
      diffVector.setLength(agent.getSpeed());
      while(distanceBetweenNodes - agent.getSpeed() > agent.getSpeed()) {
    	  path.addLast(path.get(path.size()-1).cpy().sub(diffVector.cpy()));
    	  distanceBetweenNodes-=agent.getSpeed();
      }
      node = node.pathParent;
    }
    return path;
  }

  @Override
  public void calculatePathsForAgent(AgentMob agent) {
		AStarNode aStarStartNode = new AStarNode(agent.getPosition(), tiledMapTileLayer);
		AStarNode aStarGoalNode = new AStarNode(agent.getGoal(), tiledMapTileLayer);
		List<Vector2> aStarPath = findPath(aStarStartNode, aStarGoalNode, agent); 
		agent.getPostionPath().clear();
		agent.getPostionPath().addAll(aStarPath);
  }

  private boolean lineOfSight(AStarNode s, AStarNode s1) {
		int x0 = s.x;
		int y0 = s.y;
		int x1 = s1.x;
		int y1 = s1.y;
		int dy = y1 - y0;
		int dx = x1 - x0;
		float f = 0;
		int sy = 1;
		int sx = 1;

		if (dy < 0) {
			dy = -dy;
			sy = -1;
		}
		if (dx < 0) {
			dx = -dx;
			sx = -1;
		}

		if (dx >= dy) {
			while (x0 != x1) {
				f += dy;
				int cellx = x0 + ((sx - 1) / 2);
				int celly = y0 + ((sy - 1) / 2);
				if (f >= dx) {

					if (tiledMapTileLayer.getCell(cellx, celly) != null) {
						return false;
					}
					y0 += sy;
					f -= dx;
				}
				if (f != 0 && tiledMapTileLayer.getCell(cellx, celly) != null) {
					return false;
				}
				if (dy == 0 && tiledMapTileLayer.getCell(cellx, y0) != null && tiledMapTileLayer.getCell(cellx, y0 - 1) != null) {
					return false;
				}
				x0 += sx;
			}
		} else {
			while (y0 != y1) {
				f += dx;
				int cellx = x0 + ((sx - 1) / 2);
				int celly = y0 + ((sy - 1) / 2);
				if (f >= dy) {

					if (tiledMapTileLayer.getCell(cellx, celly) != null) {
						return false;
					}
					x0 += sx;
					f -= dy;
				}
				if (f != 0 && tiledMapTileLayer.getCell(cellx, celly) != null) {
					return false;
				}
				if (dx == 0 && tiledMapTileLayer.getCell(x0, celly) != null && tiledMapTileLayer.getCell(x0 - 1, celly) != null) {
					return false;
				}
				y0 += sy;
			}
		}
		return true;
	}
  
  @Override
public boolean isPathFollowing() {
	return true;
}

}