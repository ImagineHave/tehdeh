package space.imaginehave.tehdeh.state;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.agent.AgentService;
import space.imaginehave.tehdeh.daemon.DaemonMapObject;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
import space.imaginehave.tehdeh.inputprocessor.Mouse;
import space.imaginehave.tehdeh.layer.LayerService;
import space.imaginehave.tehdeh.overlay.GoalOverlayMapObject;
import space.imaginehave.tehdeh.player.Player;
import space.imaginehave.tehdeh.tower.TowerMapObject;
import space.imaginehave.tehdeh.tower.TowerService;

public class GameStateTehDeh implements State {
	
	private AssetManager assetManager;
	private final TiledMapTileLayer towerLayer;
	private final TiledMapTileLayer overlay;
	private final MapLayer agentLayer;
	private final TiledMap tiledMap;
	private final AgentService agentService;
	private final LayerService layerService;
	private final TowerService towerService;
	private Viewport viewport;
	private final TehDehGame game;
	private final Player player;
	private Vector2 goal;
	private Mouse mouse;
	
	public GameStateTehDeh (TehDehGame tehDehGame) {
		this.game = tehDehGame;
		
		createAssetManager();
		getAssetManager().load(Constant.TEST_TOWER_PNG, Texture.class);
		getAssetManager().load(Constant.TEST_TOWER_2_PNG, Texture.class);
		getAssetManager().load(Constant.TEST_AGENT_PNG, Texture.class);
		getAssetManager().load(Constant.BOID_AGENT_PNG, Texture.class);
		getAssetManager().load(Constant.ASTAR_AGENT_PNG, Texture.class);
		getAssetManager().load(Constant.TSTAR_AGENT_PNG, Texture.class);
		getAssetManager().load(Constant.OVERLAY_GOAL_PNG, Texture.class);
		getAssetManager().load(Constant.BUTTON_RESET_PNG, Texture.class);
		getAssetManager().load(Constant.BUTTON_MORE_PNG, Texture.class);
		getAssetManager().load(Constant.TEST_HURTYTHING_PNG, Texture.class);
		getAssetManager().finishLoading();
		
		mouse = new Mouse();
		
		agentService = new AgentService();
		
		tiledMap = new TmxMapLoader().load(Constant.TMX);
		
		layerService = new LayerService();
		towerLayer = layerService.fetchTowerLayer(tiledMap);
		agentLayer = layerService.fetchAgentLayer(tiledMap);
		overlay = layerService.fetchOverlay(tiledMap);
		towerService = new TowerService();
		
		goal = new Vector2(Constant.GAME_WIDTH/2,32);
		
		
		layerService.addToOverlay(new GoalOverlayMapObject(this), 
				overlay, 
				this);
		
		agentLayer.getObjects().add(new DaemonMapObject(this));
		
		player = new Player();
	}

	private AssetManager createAssetManager() {
		return assetManager = new AssetManager();
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
			if( i % 128 != 0){
				TowerMapObject tmo = towerService.createWall(new Vector2(i,Constant.GAME_HEIGHT/2), this, (Texture) getAssetManager().get(Constant.TEST_TOWER_PNG));
				layerService.addTower(tmo,
						towerLayer,
						agentLayer,
						this);
			}
		}
		
	}
	
	public void createAgents() {
		agentService.placeholderAgentStarter(this);
	}
	
	public Vector2 getGoal() {
		return goal;
	}
	
	public void setGoal(Vector2 position) {
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
		
	}

	public void addBullet(HurtyThingBullet bullet) {
		getAgentLayer().getObjects().add(bullet);
	}

	public LayerService getLayerService() {
		return layerService;
	}

	public TehDehGame getGame() {
		return game;
	}
	
	public Mouse getMouse(){
		return this.mouse;
	}

	public Player getPlayer() {
		return player;
	}

}
