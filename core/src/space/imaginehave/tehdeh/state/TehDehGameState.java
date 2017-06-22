package space.imaginehave.tehdeh.state;

import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;

public class TehDehGameState {
	
	private Optional<Entity> placementEntity;
	private Vector3 mouseVector;
	
	public TehDehGameState () {
		this.placementEntity = Optional.empty();
		this.mouseVector = Vector3.Zero;
	}
	
	public Optional<Entity> getPlacementEntity(){
		return placementEntity;
	}
	
	public void setPlacementEntity(Entity texture){
		this.placementEntity = Optional.of(texture);
	}
	
	public void setMouseCoords(Vector3 vector){
		this.mouseVector = vector;
	}

	public Vector3 getMouseCoords(){
		return mouseVector;
	}

	public void removeMouseFollow() {
		placementEntity = Optional.empty();
	}
}
