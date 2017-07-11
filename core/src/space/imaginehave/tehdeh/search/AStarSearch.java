package space.imaginehave.tehdeh.search;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.agent.AgentBoid;
import space.imaginehave.tehdeh.agent.AgentCore;
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
  public List<Vector3> findPath(AStarNode startNode, AStarNode goalNode, AgentCore agent) {

    PriorityQueue<AStarNode> openList = new PriorityQueue<AStarNode>();
    LinkedList<AStarNode> closedList = new LinkedList<AStarNode>();

    startNode.costFromStart = 0;
    startNode.estimatedCostToGoal = startNode.calculateEstimatedCostToGoal(goalNode);
    startNode.pathParent = null;
    openList.add(startNode);

    while (!openList.isEmpty()) {
      AStarNode node = (AStarNode)openList.poll();
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
          if (isClosed) { //NEEDED?
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
    Construct the path, not including the start node.
  */
  protected List<Vector3> constructPath(AStarNode node, AgentCore agent) {
	  AStarNode a = node;
	  //Smooth Path
	  while(a.pathParent!=null){
	      //Set pathParent to furthest visible node
	      setLastVisibleNode(a);
	                                     
	      //Continue with parent
	      a=a.pathParent;
	
	}
	  
    LinkedList<Vector3> path = new LinkedList<Vector3>();
    while (node.pathParent != null) {
    	double distanceBetweenNodes = Math.hypot(node.pathParent.x*tiledMapTileLayer.getTileWidth()-node.x*tiledMapTileLayer.getTileWidth(), node.pathParent.y*tiledMapTileLayer.getTileHeight()-node.y*tiledMapTileLayer.getTileHeight());  
    	float nodeXPixels = node.x*state.getTowerLayer().getTileWidth();
		float nodeYPixels = node.y*state.getTowerLayer().getTileHeight();
		
		path.addLast(new Vector3(nodeXPixels, nodeYPixels, 0));
    	float difX = nodeXPixels - node.pathParent.x*tiledMapTileLayer.getTileWidth();
    	float difY = nodeYPixels - node.pathParent.y*tiledMapTileLayer.getTileHeight();

    	if(node.pathParent.pathParent == null ) {
    		float firstX = agent.getPosition().x;
    		float firstY = agent.getPosition().y;
    		difX = nodeXPixels-firstX;
    		difY = nodeYPixels-firstY;
    		distanceBetweenNodes = Math.hypot(difX, difY);
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
  
  
  private void setLastVisibleNode(AStarNode a) {
	while(a.pathParent.pathParent != null) {
      if(!lineOfSight(a, a.pathParent.pathParent)) {
          break;
      }             
      a.pathParent = a.pathParent.pathParent;                           
	}
}   


  @Override
  public void calculatePathsForAgent(AgentCore agent) {
	
	AStarNode aStarStartNode = new AStarNode(agent.getPosition(), state.getTowerLayer());
	AStarNode aStarGoalNode = new AStarNode(agent.getGoal(), state.getTowerLayer());
	List<Vector3> aStarPath = findPath(aStarStartNode, aStarGoalNode, agent); 
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

					if (state.getTowerLayer().getCell(cellx, celly) != null) {
						return false;
					}
					y0 += sy;
					f -= dx;
				}
				if (f != 0 && state.getTowerLayer().getCell(cellx, celly) != null) {
					return false;
				}
				if (dy == 0 && state.getTowerLayer().getCell(cellx, y0) != null && state.getTowerLayer().getCell(cellx, y0 - 1) != null) {
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

					if (state.getTowerLayer().getCell(cellx, celly) != null) {
						return false;
					}
					x0 += sx;
					f -= dy;
				}
				if (f != 0 && state.getTowerLayer().getCell(cellx, celly) != null) {
					return false;
				}
				if (dx == 0 && state.getTowerLayer().getCell(x0, celly) != null && state.getTowerLayer().getCell(x0 - 1, celly) != null) {
					return false;
				}
				y0 += sy;
			}
		}
		return true;
	}

}