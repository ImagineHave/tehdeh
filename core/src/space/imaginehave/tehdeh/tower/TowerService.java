package space.imaginehave.tehdeh.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.wave.Population;

public class TowerService {
	
	public TowerMapObject createTower(Vector2 position, TowerType type, Population population) {
		return new TowerMapObject(position.cpy(), type, population);
	}
	
	public TowerMapObject createWall(Vector2 position, Texture texture, Population population) {
		TowerType type = new TowerType(0,0,0,0,texture);
		return new TowerMapObject(position.cpy(), type, population);
	}
	
}
