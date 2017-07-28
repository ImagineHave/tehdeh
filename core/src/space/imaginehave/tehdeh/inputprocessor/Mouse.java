package space.imaginehave.tehdeh.inputprocessor;

import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.tower.TowerType;

public class Mouse {

	private Optional<TowerType> placementTowerTypeOptional;
	private Vector2 mouseVector;
	
	public Mouse(){
		this.placementTowerTypeOptional = Optional.empty();
		this.mouseVector = Vector2.Zero.cpy();
	}
	
	public void render(SpriteBatch batch){
		if (placementTowerTypeOptional.isPresent()) {
			Texture placementTexture = placementTowerTypeOptional.get().texture;
			batch.draw(
					placementTexture, 
					mouseVector.x - placementTexture.getWidth()/2, 
					mouseVector.y - placementTexture.getHeight()/2
					);
		}
	}
	
	public void setPlacementTexture(TowerType towerType){
		this.placementTowerTypeOptional = Optional.ofNullable(towerType);
	}
	
	public void setMouseCoords(Vector2 vector){
		this.mouseVector = vector;
	}

	public Vector2 getMouseCoords(){
		return mouseVector;
	}

	public Optional<TowerType> getPlacementTowerType() {
		return placementTowerTypeOptional;
	}
	
}
