package space.imaginehave.tehdeh.wave;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.AssetManager;
import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.agent.AgentService;
import space.imaginehave.tehdeh.daemon.DaemonMapObject;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
import space.imaginehave.tehdeh.layer.LayerService;
import space.imaginehave.tehdeh.overlay.GoalOverlayMapObject;
import space.imaginehave.tehdeh.tiledmaprenderer.OrthogonalTiledMapRendererTehDeh;
import space.imaginehave.tehdeh.tower.TowerMapObject;
import space.imaginehave.tehdeh.tower.TowerService;

public class Population {
	
	private static Population population;
	
	private final TiledMapTileLayer towerLayer;
	private final TiledMapTileLayer overlay;
	private final MapLayer agentLayer;
	
	private final AgentService agentService;
	private final LayerService layerService;
	private final TowerService towerService;
	
	private final TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	
	public Population (SpriteBatch batch, OrthographicCamera camera) {
		
		tiledMap = new TmxMapLoader().load(Constant.TMX);
		
		tiledMapRenderer = new OrthogonalTiledMapRendererTehDeh(tiledMap, batch);
		
		agentService = new AgentService();
		layerService = new LayerService();
		towerService = new TowerService();
		
		towerLayer = layerService.fetchTowerLayer(tiledMap);
		agentLayer = layerService.fetchAgentLayer(tiledMap);
		overlay = layerService.fetchOverlay(tiledMap);
		
		layerService.addToOverlay(Constant.ORIGINAL_GOAL_MAP_OBJECT, overlay);
		agentLayer.getObjects().add(new DaemonMapObject(this));
		
		tiledMapRenderer.setView(camera);
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
	
	public void createAgents() {
		agentService.placeholderAgentStarter(this);
	}
	
	public TowerService getTowerService() {
		return towerService;
	}
	
	public LayerService getLayerService() {
		return layerService;
	}

	public AgentService getAgentService() {
		return agentService;
	}

	public void resetGoals() {
		agentService.resetGoals(this);
		
	}

	public void reset() {
		agentService.reset(this);
		
	}
	
	public void createWalls() {
		
		for (int i = 0; i < Constant.GAME_WIDTH; i += 16) {
			if( i % 128 != 0){
				TowerMapObject tmo = towerService.createWall(
						new Vector2(i,
									Constant.GAME_HEIGHT/2), 
									AssetManager.getInstance().getTexture(Constant.TEST_TOWER_PNG),
									this);
				
				layerService.addTower(tmo,
						towerLayer,
						agentLayer);
			}
		}
		
	}
	
	public void addBullet(HurtyThingBullet bullet) {
		getAgentLayer().getObjects().add(bullet);
	}
	
	public void render() {
		tiledMapRenderer.render();
	}
}
