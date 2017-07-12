package space.imaginehave.tehdeh.inputprocessor;

import java.util.Optional;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class Mouse {

	private GameStateTehDeh state;
	private Optional<Texture> placementTextureOptional;
	private Vector2 mouseVector;
	
	public Mouse(GameStateTehDeh state){
		this.state = state;
		this.placementTextureOptional = Optional.empty();
		this.mouseVector = Vector2.Zero.cpy();
	}
	
	public void render(){
		state.getBatch().begin();
		if (placementTextureOptional.isPresent()) {
			Texture placementTexture = placementTextureOptional.get();
			state.getBatch().draw(
					placementTexture, 
					mouseVector.x - placementTexture.getWidth()/2, 
					mouseVector.y - placementTexture.getHeight()/2
					);
		}
		state.getBatch().end();
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
