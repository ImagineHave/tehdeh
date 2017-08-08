package space.imaginehave.tehdeh.wave;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.AssetManager;
import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.agent.AgentMob;
import space.imaginehave.tehdeh.agent.AgentService;
import space.imaginehave.tehdeh.daemon.DaemonMapObject;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
import space.imaginehave.tehdeh.layer.LayerService;
import space.imaginehave.tehdeh.overlay.GoalOverlayMapObject;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.tiledmaprenderer.OrthogonalTiledMapRendererTehDeh;
import space.imaginehave.tehdeh.tower.TowerMapObject;
import space.imaginehave.tehdeh.tower.TowerService;

public class Population {
	
	private final List<MapObject> theDead;
	
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
		
		//TODO remove this
//		agentLayer.getObjects().add(new DaemonMapObject(this));
		
		tiledMapRenderer.setView(camera);
		
		theDead = new ArrayList<MapObject>();
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
	
	public void update() {
		Array<AgentMob> agents = getAgentLayer().getObjects().getByType(AgentMob.class);
		Array<HurtyThingBullet> projectiles = getAgentLayer().getObjects().getByType(HurtyThingBullet.class);
		
		for (AgentMob agent : agents) {
			if (agent.atGoal()){
				GameStateTehDeh.getInstance().getPlayer().hurt(agent);
				killAgent(agent);
			}
			
			checkAgentProjectileCollisions(projectiles, agent);
		}
	}
	
	private void checkAgentProjectileCollisions(Array<HurtyThingBullet> projectiles, AgentMob agent) {
		for(HurtyThingBullet projectile: projectiles) {
			if(Intersector.overlapConvexPolygons(projectile.getBoundingBox(), agent.getBoundingBox())) {
				agent.doDamage(projectile.getDamage());
				if(agent.isDead()) {
					GameStateTehDeh.getInstance().getPlayer().agentProfitted(agent);
					killAgent(agent);
				}
				getAgentLayer().getObjects().remove(projectile);
			}
		}
	}

	private void killAgent(AgentMob agent) {
		theDead.add(agent);
		getAgentLayer().getObjects().remove(agent);
	}
}
