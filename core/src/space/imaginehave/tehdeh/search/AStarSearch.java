package space.imaginehave.tehdeh.search;
import java.util.*;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.state.State;

public class AStarSearch implements SearchInterface {
	
	private final State state;
	private ArrayList<Agent> agents;
	
	public AStarSearch (State state) {
		this.state = state;
		agents = new ArrayList<Agent>();
	}

	public List<Agent> getAgents() {
		return agents;
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
          neighbors.get(i);
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

    // no path found. return path to the node with the smallest estimated cost
    AStarNode nodeWithSmallestCost = null;
    for(AStarNode node : closedList) {
    	if(nodeWithSmallestCost == null || (node.getCost() < nodeWithSmallestCost.getCost())) {
    		nodeWithSmallestCost = node;
    	}
    }
    return constructPath(nodeWithSmallestCost);
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
      path.addFirst(new Vector3(node.x*state.getTowerLayer().getTileWidth(), node.y*state.getTowerLayer().getTileHeight(), 0));
      node = node.pathParent;
    }
    return path;
  }


  @Override
  public void calculatePathsForRegisteredAgents() {
	for(Agent agent: agents) {
		AStarNode aStarStartNode = new AStarNode(agent.getPosition(), state.getTowerLayer());
		AStarNode aStarGoalNode = new AStarNode(agent.getGoal(), state.getTowerLayer());
		List<Vector3> aStarPath = findPath(aStarStartNode, aStarGoalNode); 
		agent.getPostionPath().clear();
		agent.getPostionPath().addAll(aStarPath);
	}
	
}


  
}