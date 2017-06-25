package search;
import java.util.*;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.search.SearchInterface;

/**
  The AStarSearch class, along with the AStarNode class,
  implements a generic A* search algorithm. The AStarNode
  class should be subclassed to provide searching capability.
*/
public class AStarSearch implements SearchInterface{
	
	TiledMapTileLayer tiledMapTileLayer;
	
	public AStarSearch (TiledMapTileLayer tiledMapTileLayer) {
		this.tiledMapTileLayer = tiledMapTileLayer;
	}
	
	/**
    Find the path from the start node to the end node. A list
    of AStarNodes is returned, or null if the path is not
    found. 
  */
  public List<Vector3> findPath(AStarNode startNode, AStarNode goalNode) {

    PriorityList<AStarNode> openList = new PriorityList<AStarNode>();
    LinkedList<AStarNode> closedList = new LinkedList<AStarNode>();

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

      List<AStarNode> neighbors = node.getNeighbors();
      for (int i=0; i<neighbors.size(); i++) {
        AStarNode neighborNode =
          (AStarNode)neighbors.get(i);
        boolean isOpen = openList.contains(neighborNode);
        boolean isClosed =
          closedList.contains(neighborNode);
        float costFromStart = node.costFromStart +
          node.getCost(neighborNode);

        // check if the neighbor node has not been
        // traversed or if a shorter path to this
        // neighbor node is found.
        if ((!isOpen && !isClosed) ||
          costFromStart < neighborNode.costFromStart)
        {
          neighborNode.pathParent = node;
          neighborNode.costFromStart = costFromStart;
          neighborNode.estimatedCostToGoal =
            neighborNode.getEstimatedCost(goalNode);
          if (isClosed) {
            closedList.remove(neighborNode);
          }
          if (!isOpen) {
            openList.add(neighborNode);
          }
        }
      }
      closedList.add(node);
    }

    // no path found
    return null;
  }

	
  /**
    A simple priority list, also called a priority queue.
    Objects in the list are ordered by their priority,
    determined by the object's Comparable interface.
    The highest priority item is first in the list.
  */
  public static class PriorityList<T> extends LinkedList {

    public void add(Comparable object) {
      for (int i=0; i<size(); i++) {
        if (object.compareTo(get(i)) <= 0) {
          add(i, object);
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
      path.addFirst(new Vector3(node.x, node.y, 0));
      node = node.pathParent;
    }
    return path;
  }

@Override
public List<Vector3> calculatePath(Agent agent) {
	AStarNode aStarStartNode = new AStarNode(agent.getPosition(), tiledMapTileLayer);
	AStarNode aStarGoalNode = new AStarNode(agent.getGoal(), tiledMapTileLayer);// new AStarNode(goalNode, tiledMapTileLayer);
	List<Vector3> aStarPath = findPath(aStarStartNode, aStarGoalNode); 
	return aStarPath;
}


  
}