package space.imaginehave.tehdeh.search;
import java.util.*;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class ThetaStarLazySearch extends Search {
	
	TiledMapTileLayer tiledMapTileLayer;
	private PriorityList<AStarNode> openList;
	private LinkedList<AStarNode> closedList;
	
	
	public ThetaStarLazySearch (GameStateTehDeh state) {
		super(state);
	}
	

	/**
    Find the path from the start node to the end node. A list
    of AStarNodes is returned, or null if the path is not
    found. 
  */
  public List<Vector3> findPath(AStarNode startNode, AStarNode goalNode) {

    openList = new PriorityList<AStarNode>();
    closedList = new LinkedList<AStarNode>();

    startNode.costFromStart = 0;
    startNode.estimatedCostToGoal = startNode.getEstimatedCost(goalNode);
    startNode.pathParent = null;
    openList.add(startNode);

    while (!openList.isEmpty()) {
      AStarNode node = (AStarNode)openList.removeFirst();
      
      if (  node.equals(goalNode)) {  
        // construct the path from start to goal
        return constructPath(node);
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
    return constructPath(nodeWithSmallestCost);
  }

	
  private void setVertex(AStarNode node) {
	  if(node.pathParent==null) {
		  return;
	  }
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
			openList.remove(neighborNode); //will only remove *if* it is in open
			
			openList.add(neighborNode);
		}
	}


private void computeCost(AStarNode node, AStarNode neighborNode) {
	if(node.pathParent == null) {
		return;
	}
	float calculatedCostForGrandParent = node.pathParent.costFromStart + node.pathParent.getCost(neighborNode);
	if (calculatedCostForGrandParent < neighborNode.costFromStart) {
		neighborNode.pathParent = node.pathParent;
		neighborNode.costFromStart = calculatedCostForGrandParent;
	}
}


/**
    A simple priority list, also called a priority queue.
    Objects in the list are ordered by their priority,
    determined by the object's Comparable interface.
    The highest priority item is first in the list.
  */
  public static class PriorityList<AStarNode> extends LinkedList {

    public void add(Comparable<AStarNode> object) {
      for (int i=0; i<size(); i++) {
        if (object.compareTo((AStarNode) get(i)) <= 0) {
          add(i, (AStarNode)object);
          return;
        }
      }
      addLast(object);
    }
  }


  /**
    Construct the path, not including the start node.
  */
  protected List<Vector3> constructPath(AStarNode node) {
    LinkedList<Vector3> path = new LinkedList<Vector3>();
    while (node.pathParent != null) {
      path.addFirst(new Vector3(node.x*tiledMapTileLayer.getTileWidth(), node.y*tiledMapTileLayer.getTileHeight(), 0));
      node = node.pathParent;
    }
    return path;
  }


  @Override
  public void calculatePathsForRegisteredAgents() {
	for(Agent agent: agents) {
		AStarNode aStarStartNode = new AStarNode(agent.getPosition(), tiledMapTileLayer);
		AStarNode aStarGoalNode = new AStarNode(agent.getGoal(), tiledMapTileLayer);
		List<Vector3> aStarPath = findPath(aStarStartNode, aStarGoalNode); 
		agent.getPostionPath().clear();
		agent.getPostionPath().addAll(aStarPath);
	}
  }

  private boolean lineOfSight(AStarNode s, AStarNode s1) {
		int x0 = s.x;
		int y0 = s.y;
		int x1 = s1.x;
		int y1 = s1.y;
		int dy = y1 - y0;
		int dx = x1 - x0;
		int f = 0;
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
  
}