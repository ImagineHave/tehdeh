package space.imaginehave.tehdeh.state;

import java.util.Optional;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.agent.AgentService;
import space.imaginehave.tehdeh.agent.AgentBullet;
import space.imaginehave.tehdeh.layer.LayerService;
import space.imaginehave.tehdeh.search.AStarSearch;
import space.imaginehave.tehdeh.search.BoidSearch;
import space.imaginehave.tehdeh.search.ThetaStarLazySearch;

public class GameStateTehDeh implements State {
	
	private AssetManager assetManager;
	private Optional<Texture> placementTexture;
	private Vector3 mouseVector;
	private final TiledMapTileLayer towerLayer;
	private final TiledMapTileLayer overlay;
	private final MapLayer agentLayer;
	private final TiledMap tiledMap;
	private final AgentService agentService;
	private final LayerService layerService;
	private final BoidSearch boidSearch;
	private final AStarSearch aStarSearch;
	private final ThetaStarLazySearch thetaStarSearch;
	private Viewport viewport;
	private final TehDehGame game;
	private Vector3 goal;
	
	public GameStateTehDeh (TehDehGame tehDehGame) {
		this.game = tehDehGame;
		
		setAssetManager();
		getAssetManager().load(Constant.TEST_TOWER_PNG, Texture.class);
		getAssetManager().load(Constant.TEST_AGENT_PNG, Texture.class);
		getAssetManager().load(Constant.OVERLAY_GOAL_PNG, Texture.class);
		getAssetManager().load(Constant.BUTTON_RESET_PNG, Texture.class);
		getAssetManager().load(Constant.BUTTON_MORE_PNG, Texture.class);
		getAssetManager().load(Constant.TEST_BULLET_PNG, Texture.class);
		getAssetManager().finishLoading();
		
		placementTexture = Optional.empty();
		mouseVector = new Vector3(0,0,0);
		agentService = new AgentService();
		
		tiledMap = new TmxMapLoader().load(Constant.TMX);
		
		layerService = new LayerService();
		towerLayer = layerService.fetchTowerLayer(tiledMap);
		agentLayer = layerService.fetchAgentLayer(tiledMap);
		overlay = layerService.fetchOverlay(tiledMap);
		
		boidSearch = new BoidSearch(this);
		aStarSearch = new AStarSearch(this);
		thetaStarSearch = new ThetaStarLazySearch(this);
		
		goal = new Vector3(Constant.GAME_WIDTH/2,32,0);
		
		layerService.addToTiledMapTileLayer(goal, 
				(Texture) getAssetManager().get(Constant.OVERLAY_GOAL_PNG), 
				overlay, 
				this);
		
	}

	private AssetManager setAssetManager() {
		return assetManager = new AssetManager();
	}
	
	public Optional<Texture> getPlacementTexture(){
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
		return tiledMap;
	}

	public TiledMapTileLayer getTowerLayer() {
		return towerLayer;
	}
	
	public MapLayer getAgentLayer() {
		return agentLayer;
	}
	
	public TiledMapTileLayer getOverlay() {
		return overlay;
	}
	
	public void createWalls() {
		
		for (int i = 0; i < viewport.getWorldWidth(); i += 16) {
			if( i % 128 != 0)
				layerService.addToTiledMapTileLayer(new Vector3(i,400,0), 
						(Texture) getAssetManager().get(Constant.TEST_TOWER_PNG), 
						towerLayer, 
						this);
		}
		
	}
	
	public void createAgents() {
		agentService.placeholderAgentStarter(this);
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

	public void redefineGoals() {
		this.agentService.resetGoals(this);
		
	}

	public void reset() {
		
		agentService.reset(this);
		agentService.placeholderAgentStarter(this);
		
	}

	public void addBullet(AgentBullet bullet) {
		getAgentLayer().getObjects().add(bullet);
	}

	public LayerService getLayerService() {
		return layerService;
	}

	public TehDehGame getGame() {
		return game;
	}

}
