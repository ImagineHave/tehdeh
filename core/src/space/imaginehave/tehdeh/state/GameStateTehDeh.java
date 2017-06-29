package space.imaginehave.tehdeh.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.agent.DummyAgent;
import space.imaginehave.tehdeh.agent.MapObjectAgent;
import space.imaginehave.tehdeh.entity.TowerCell;
import space.imaginehave.tehdeh.search.AStarSearch;
import space.imaginehave.tehdeh.search.BoidSearch;
import space.imaginehave.tehdeh.search.SearchInterface;

public class GameStateTehDeh implements State {
	
	private Optional<Texture> placementTexture;
	private Vector3 mouseVector;
	private List<Agent> agents;
	private final TiledMapTileLayer towerLayer;
	private final MapLayer agentLayer;
	private final TiledMap tiledMap;
	private final BoidSearch boidSearch;
	private final AStarSearch aStarSearch;
	
	public GameStateTehDeh () {
		this.placementTexture = Optional.empty();
		this.mouseVector = new Vector3(0,0,0);
		this.setAgents(new ArrayList<Agent>());
		tiledMap = new TmxMapLoader().load("tehdeh.tmx");
		towerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("towerLayer");
		agentLayer = (MapLayer) tiledMap.getLayers().get("agentLayer");
		
		boidSearch = new BoidSearch(this);
		aStarSearch = new AStarSearch(this);
	}
	
	public Optional<Texture> getPlacementTexture (){
		return placementTexture;
	}
	
	public void setPlacementTexture(Texture texture){
		this.placementTexture = Optional.of(texture);
	}
	
	public void setMouseCoords(Vector3 vector){
		this.mouseVector = vector;
	}

	public Vector3 getMouseCoords(){
		return mouseVector;
	}

	public void removeMouseFollow() {
		placementTexture = Optional.empty();
	}

	public List<Agent> getEntities() {
		return agents;
	}

	public void setAgents(List<Agent> entities) {
		this.agents = entities;
	}

	public TiledMap getTiledMap() {
		return this.tiledMap;
	}

	public TiledMapTileLayer getTowerLayer() {
		return towerLayer;
	}
	
	public MapLayer getAgentLayer() {
		return agentLayer;
	}
	
	public void addEntity(Vector3 vector, Texture texture) {
		TowerCell cell = new TowerCell(texture);
		towerLayer.setCell((int) (vector.x/towerLayer.getTileWidth()), (int) (vector.y/towerLayer.getTileHeight()), cell);
	}
	
	public void createAgents(Viewport viewport) {
		
		for (int i = 0; i < 100; i++) {
			MapObjectAgent moa = new DummyAgent(viewport, boidSearch, i);
			boidSearch.getAgents().add(moa);
			this.getAgentLayer().getObjects().add(moa);
		}
		
		for (int i = 0; i < 1; i++) {
			MapObjectAgent moa = new DummyAgent(viewport, aStarSearch, i+100);
			aStarSearch.getAgents().add(moa);
			this.getAgentLayer().getObjects().add(moa);
		}
	}
	
	
	public void calculatePaths() {
		aStarSearch.calculatePathsForRegisteredAgents();
		boidSearch.calculatePathsForRegisteredAgents();
	}
	
	public void createWalls() {
		
	}
}
