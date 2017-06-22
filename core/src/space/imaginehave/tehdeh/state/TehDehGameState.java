package space.imaginehave.tehdeh.state;

import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class TehDehGameState {
	
	private Entity[][] tileMap;
	private Optional<Texture> mouseFollow;
	private Vector3 mouseVector;
	
	public TehDehGameState () {
		this.mouseFollow = Optional.empty();
		this.mouseVector = Vector3.Zero;
	}
	
	public Optional<Texture> getMouseFollow(){
		return mouseFollow;
	}
	
	public void setMouseFollow(Texture texture){
		this.mouseFollow = Optional.of(texture);
	}
	
	public void setMouseCoords(Vector3 vector){
		this.mouseVector = vector;
	}

	public Vector3 getMouseCoords(){
		return mouseVector;
	}

	public Entity[][] getTileMap() {
		return tileMap;
	}

	public void setTileMap(Entity[][] tileMap) {
		this.tileMap = tileMap;
	}

	public void removeMouseFollow() {
		mouseFollow = Optional.empty();
	}
}
