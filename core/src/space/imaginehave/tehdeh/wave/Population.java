package space.imaginehave.tehdeh.wave;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
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
import space.imaginehave.tehdeh.tower.TowerMapObject;
import space.imaginehave.tehdeh.tower.TowerService;

public class Population {
	
	private static Population population;
	
	private final TiledMapTileLayer towerLayer;
	private final TiledMapTileLayer overlay;
	private final MapLayer agentLayer;
	private final TiledMap tiledMap;
	private final AgentService agentService;
	private final LayerService layerService;
	private final TowerService towerService;
	
	public static Population getInstance() {
		if (population == null) {
			population = new Population();
		}
		return population;
	}
	
	private Population () {
		
		
		tiledMap = new TmxMapLoader().load(Constant.TMX);
		
		agentService = new AgentService();
		layerService = new LayerService();
		towerService = new TowerService();
		
		towerLayer = layerService.fetchTowerLayer(tiledMap);
		agentLayer = layerService.fetchAgentLayer(tiledMap);
		overlay = layerService.fetchOverlay(tiledMap);
		
		layerService.addToOverlay(new GoalOverlayMapObject(), overlay);
		agentLayer.getObjects().add(new DaemonMapObject());
		
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
		agentService.placeholderAgentStarter();
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
		agentService.resetGoals();
		
	}

	public void reset() {
		agentService.reset();
		
	}
	
	public void createWalls() {
		
		for (int i = 0; i < Constant.GAME_WIDTH; i += 16) {
			if( i % 128 != 0){
				TowerMapObject tmo = Population.getInstance().getTowerService().createWall(
						new Vector2(i,
									Constant.GAME_HEIGHT/2), 
									AssetManager.getInstance().getTexture(Constant.TEST_TOWER_PNG));
				Population.getInstance().getLayerService().addTower(tmo,
						Population.getInstance().getTowerLayer(),
						Population.getInstance().getAgentLayer());
			}
		}
		
	}
	
	public void addBullet(HurtyThingBullet bullet) {
		getAgentLayer().getObjects().add(bullet);
	}
}
