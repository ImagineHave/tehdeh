package space.imaginehave.tehdeh.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class TowerService {
	
	public TowerMapObject createTower(Vector2 position, GameStateTehDeh state, Texture texture) {
		TowerType type = new TowerType();
		return new TowerMapObject(position.cpy(), state, texture, type);
	}
	
	public TowerMapObject createWall(Vector2 position, GameStateTehDeh state, Texture texture) {
		TowerType type = new TowerType();
		return new TowerMapObject(position.cpy(), state, texture, type);
	}
	
}
