package space.imaginehave.tehdeh.state;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.AssetManager;
import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
import space.imaginehave.tehdeh.inputprocessor.Mouse;
import space.imaginehave.tehdeh.layer.LayerService;
import space.imaginehave.tehdeh.player.Player;
import space.imaginehave.tehdeh.tower.TowerMapObject;
import space.imaginehave.tehdeh.wave.Population;

public class GameStateTehDeh implements State {
	

	private Viewport viewport;
	private final Player player;
	private Vector2 goal;
	private Mouse mouse;
	
	private static GameStateTehDeh state;
	
	public static GameStateTehDeh getInstance() {
		if (state == null) {
			state = new GameStateTehDeh();
		}
		return state;
	}
	
	private GameStateTehDeh () {
		
		mouse = new Mouse();
		
		goal = new Vector2(Constant.GAME_WIDTH/2,32);
		
		player = new Player();
	}

	public TiledMap getTiledMap() {
		return Population.getInstance().getTiledMap();
	}

	public TiledMapTileLayer getTowerLayer() {
		return Population.getInstance().getTowerLayer();
	}
	
	public MapLayer getAgentLayer() {
		return Population.getInstance().getAgentLayer();
	}
	
	public TiledMapTileLayer getOverlay() {
		return Population.getInstance().getOverlay();
	}
	
	public void createWalls() {
		Population.getInstance().createWalls();
	}
	
	public void createAgents() {
		Population.getInstance().createAgents();;
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

	public void redefineGoals() {
		Population.getInstance().resetGoals();
		
	}

	public void reset() {
		Population.getInstance().reset();
		
	}

	public void addBullet(HurtyThingBullet bullet) {
		getAgentLayer().getObjects().add(bullet);
	}
	
	public Mouse getMouse(){
		return this.mouse;
	}

	public Player getPlayer() {
		return player;
	}

	public LayerService getLayerService() {
		return Population.getInstance().getLayerService();
	}

}
