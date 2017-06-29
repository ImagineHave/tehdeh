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

import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.entity.TowerCell;

public class TehDehGameState {
	
	private Optional<Texture> placementTexture;
	private Vector3 mouseVector;
	private List<Agent> agents;
	private final TiledMapTileLayer towerLayer;
	private final MapLayer agentLayer;
	private final TiledMap tiledMap;
	
	public TehDehGameState () {
		this.placementTexture = Optional.empty();
		this.mouseVector = new Vector3(0,0,0);
		this.setAgents(new ArrayList<Agent>());
		tiledMap = new TmxMapLoader().load("tehdeh.tmx");
		towerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("towerLayer");
		agentLayer = (MapLayer) tiledMap.getLayers().get("agentLayer");
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
}
