package space.imaginehave.tehdeh.hurtythings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

public abstract class HurtyThingMapObject extends MapObject {
	
	protected static final float height = 16f;
	protected static final float width = 16;
	
	protected Texture texture;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 goal;
	protected Polygon polygon;
	
	public HurtyThingMapObject(Vector2 position, Vector2 velocity, Vector2 goal) {
		this.position = position;
		this.velocity = velocity;
		this.goal = goal;
		polygon = new Polygon(new float[]{0,0,width,0,width,height,0,height});
	}

	public Texture getTexture() {
		return texture;
	}
	
	public TextureRegion getTextureRegion() {
		return new TextureRegion(texture);
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Vector2 getGoal() {
		return goal;
	}

	public void setGoal(Vector2 goal) {
		this.goal = goal;
	}
	public Polygon getBoundingBox() {
		return polygon;
	}
}
