package space.imaginehave.tehdeh.tower;

import com.badlogic.gdx.graphics.Texture;

public class TowerType {
	int range = 50;
	int firingDelay = 3;
	int directionalInaccuracyInDegrees = 15;
	public Texture texture;
	
	public TowerType() {
		
	}
	
	public TowerType(int range, int firingDelay, float delayTimer, int directionalInaccuracyInDegrees, Texture texture) {
		this.range = range;
		this.firingDelay = firingDelay;
		this.directionalInaccuracyInDegrees = directionalInaccuracyInDegrees;
		this.texture = texture;
		
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
}
