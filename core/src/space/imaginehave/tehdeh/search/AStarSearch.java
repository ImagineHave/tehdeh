package space.imaginehave.tehdeh.search;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class AStarSearch extends Search {
	
	private TiledMapTileLayer tiledMapTileLayer;


	public AStarSearch (GameStateTehDeh state) {
		super(state);
		this.tiledMapTileLayer = state.getTowerLayer();
	}

	/**
    Find the path from the start node to the end node. A list
    of AStarNodes is returned, or null if the path is not
    found. 
  */
  public List<Vector3> findPath(AStarNode startNode, AStarNode goalNode, Agent agent) {

    PriorityList<AStarNode> openList = new PriorityList<AStarNode>();
    LinkedList<AStarNode> closedList = new LinkedList<AStarNode>();

    startNode.costFromStart = 0;
    startNode.estimatedCostToGoal = startNode.calculateEstimatedCostToGoal(goalNode);
    startNode.pathParent = null;
    openList.add(startNode);

    while (!openList.isEmpty()) {
      AStarNode node = (AStarNode)openList.removeFirst();
      if (  node.equals(goalNode)) {  
        // construct the path from start to goal
        return constructPath(node, agent);
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
          neighborNode.estimatedCostToGoal = neighborNode.calculateEstimatedCostToGoal(goalNode);
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
    return constructPath(nodeWithSmallestCost, agent);
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
  protected List<Vector3> constructPath(AStarNode node, Agent agent) {
    LinkedList<Vector3> path = new LinkedList<Vector3>();
    while (node.pathParent != null) {
    	double distanceBetweenNodes = Math.hypot(node.pathParent.x*tiledMapTileLayer.getTileWidth()-node.x*tiledMapTileLayer.getTileWidth(), node.pathParent.y*tiledMapTileLayer.getTileHeight()-node.y*tiledMapTileLayer.getTileHeight());  
    	path.addLast(new Vector3(node.x*state.getTowerLayer().getTileWidth(), node.y*state.getTowerLayer().getTileHeight(), 0));
    	float difX = node.x*tiledMapTileLayer.getTileWidth() - node.pathParent.x*tiledMapTileLayer.getTileWidth();
    	float difY = node.y*tiledMapTileLayer.getTileHeight() - node.pathParent.y*tiledMapTileLayer.getTileHeight();

    	if(node.pathParent.pathParent == null ) {
    		node.pathParent.x = (int) agent.getPosition().x;
    		node.pathParent.y = (int) agent.getPosition().y;
    		distanceBetweenNodes = Math.hypot(node.pathParent.x-node.x*tiledMapTileLayer.getTileWidth(), node.pathParent.y-node.y*tiledMapTileLayer.getTileHeight());
    		difX = node.x*tiledMapTileLayer.getTileWidth()-node.pathParent.x;
    		difY = node.y*tiledMapTileLayer.getTileHeight()-node.pathParent.y;
    	}
      Vector3 diffVector = new Vector3(difX, difY,0);
      diffVector.setLength(agent.getSpeed());
      while(distanceBetweenNodes - agent.getSpeed() > agent.getSpeed()) {
    	  path.addLast(path.get(path.size()-1).cpy().sub(diffVector));
    	  distanceBetweenNodes-=agent.getSpeed();
      }
      node = node.pathParent;
    }
    return path;
  }


  @Override
  public void calculatePathsForRegisteredAgents() {
	for(Agent agent: agents) {
		AStarNode aStarStartNode = new AStarNode(agent.getPosition(), state.getTowerLayer());
		AStarNode aStarGoalNode = new AStarNode(agent.getGoal(), state.getTowerLayer());
		List<Vector3> aStarPath = findPath(aStarStartNode, aStarGoalNode, agent); 
		agent.getPostionPath().clear();
		agent.getPostionPath().addAll(aStarPath);
	}
	
}


  
}