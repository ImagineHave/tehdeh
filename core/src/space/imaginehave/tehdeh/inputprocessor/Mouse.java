package space.imaginehave.tehdeh.inputprocessor;

import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Mouse {

	private Optional<Texture> placementTextureOptional;
	private Vector2 mouseVector;
	
	public Mouse(){
		this.placementTextureOptional = Optional.empty();
		this.mouseVector = Vector2.Zero.cpy();
	}
	
	public void render(SpriteBatch batch){
		if (placementTextureOptional.isPresent()) {
			Texture placementTexture = placementTextureOptional.get();
			batch.draw(
					placementTexture, 
					mouseVector.x - placementTexture.getWidth()/2, 
					mouseVector.y - placementTexture.getHeight()/2
					);
		}
	}
	
	public void setPlacementTexture(Texture texture){
		this.placementTextureOptional = Optional.ofNullable(texture);
	}
	
	public void setMouseCoords(Vector2 vector){
		this.mouseVector = vector;
	}

	public Vector2 getMouseCoords(){
		return mouseVector;
	}

	public Optional<Texture> getPlacementTexture() {
		return placementTextureOptional;
	}
	
}
