package space.imaginehave.tehdeh.hurtythings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;

public abstract class HurtyThingMapObject extends MapObject {
	
	protected Texture texture;
	
	public Texture getTexture() {
		return texture;
	}
	
	public TextureRegion getTextureRegion() {
		return new TextureRegion(texture);
	}
}
