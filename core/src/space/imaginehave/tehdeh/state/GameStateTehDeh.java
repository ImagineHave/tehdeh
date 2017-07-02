package space.imaginehave.tehdeh.state;

import java.util.Optional;
import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.agent.AgentService;
import space.imaginehave.tehdeh.agent.DummyTowerAgent;
import space.imaginehave.tehdeh.entity.TowerCell;
import space.imaginehave.tehdeh.search.AStarSearch;
import space.imaginehave.tehdeh.search.BoidSearch;

public class GameStateTehDeh implements State {
	
	private AssetManager assetManager;
	private final Random random = new Random();
	private Optional<Texture> placementTexture;
	private Vector3 mouseVector;
	private final TiledMapTileLayer towerLayer;
	private final MapLayer agentLayer;
	private final TiledMap tiledMap;
	private final AgentService agentService;
	private final BoidSearch boidSearch;
	private final AStarSearch aStarSearch;
	private Viewport viewport;
	private final TehDehGame game;
	private Vector3 goal;
	private final int width;
	private final int height;
	
	public GameStateTehDeh (TehDehGame tehDehGame, int width, int height) {
		this.game = tehDehGame;
		this.width = width;
		this.height = height;
		
		assetManager = new AssetManager();
		assetManager.load("testTower.png", Texture.class);
		assetManager.load("testAgent.png", Texture.class);
		assetManager.load("goalChange.png", Texture.class);
		assetManager.load("reset.png", Texture.class);
		assetManager.finishLoading();
		
		this.placementTexture = Optional.empty();
		this.mouseVector = new Vector3(0,0,0);
		this.agentService = new AgentService();
		tiledMap = new TmxMapLoader().load("tehdeh.tmx");
		towerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("towerLayer");
		agentLayer = (MapLayer) tiledMap.getLayers().get("agentLayer");
		
		boidSearch = new BoidSearch(this);
		aStarSearch = new AStarSearch(this);
		
		goal = new Vector3(width/2,32,0);
		
		addTower(goal, (Texture) getAssetManager().get("goalChange.png"));
	}
	
	public Optional<Texture> getPlacementTexture (){
		return placementTexture;
	}
	
	public void setPlacementTexture(Texture texture){
		this.placementTexture = Optional.ofNullable(texture);
	}
	
	public void setMouseCoords(Vector3 vector){
		this.mouseVector = vector;
	}

	public Vector3 getMouseCoords(){
		return mouseVector;
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
	
	public void addTower(Vector3 vector, Texture texture) {
		TowerCell cell = new TowerCell(texture);
		towerLayer.setCell((int) (vector.x/towerLayer.getTileWidth()), (int) (vector.y/towerLayer.getTileHeight()), cell);
		
		DummyTowerAgent dta = new DummyTowerAgent(new Vector3(vector));
		towerLayer.getObjects().add(dta);
	}
	
	public void removeTower(DummyTowerAgent dta) {
		towerLayer.setCell((int) (dta.getPosition().x/towerLayer.getTileWidth()), (int) (dta.getPosition().y/towerLayer.getTileHeight()), null);
		towerLayer.getObjects().remove(dta);
	}
	
	/**
	 * returns null if not found
	 * @param vector
	 * @return
	 */
	public DummyTowerAgent getTower(Vector3 vector) {
		
		Array<DummyTowerAgent> agents = towerLayer.getObjects().getByType(DummyTowerAgent.class);
		DummyTowerAgent dta = null;
		for (Agent agent : agents) {
			if(agent.getPosition().x == vector.x && agent.getPosition().y == vector.y) {
				dta = (DummyTowerAgent) agent;
			}
		}
		return dta;
	}
	
	public void calculatePaths() {
		aStarSearch.calculatePathsForRegisteredAgents();
		boidSearch.calculatePathsForRegisteredAgents();
	}
	
	public void createWalls() {
		
		for (int i = 0; i < viewport.getWorldWidth(); i += 16) {
			if( i % 128 != 0)
				this.addTower(new Vector3(i,400,0), (Texture) assetManager.get("testTower.png"));
		}
		
	}
	
	public void createAgents() {
		agentService.createAgents(this, boidSearch, 100);
//		agentService.createAgents(this, aStarSearch, 1);
	}
	
	public Vector3 getBottomLeft() {
		return new Vector3(
				viewport.getLeftGutterWidth(), 
				viewport.getBottomGutterHeight(),
				0f);
	}

	public Vector3 getTopRight() {
		return new Vector3(
				viewport.getWorldWidth(), 
				viewport.getWorldHeight(),
				0f);
	}
	
	public Vector3 getRandomPosition(){
		return this.getRandomPosition(null, null);
	}
	
	public Vector3 getRandomPosition(Integer x, Integer y) {
		Vector3 position = new Vector3(
				(x != null) ? x : random.nextInt((int) viewport.getWorldWidth()),
				(y != null) ? y : random.nextInt((int) viewport.getWorldWidth()),
				0f);
		return position;
	}
	
	public Vector3 getGoal() {
		return goal;
	}
	
	public void setGoal(Vector3 position) {
		goal = position;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public AssetManager getAssetManager() {
		return this.assetManager;
	}

	public float getWidth() {
		return this.width;
	}

	public float getHeight() {
		return this.height;
	}

	public void redefineGoals() {
		this.agentService.resetGoals(this);
		
	}

}
