package space.imaginehave.tehdeh.hurtythings;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class HurtyThingCore extends HurtyThingMapObject {

	public HurtyThingCore(Vector2 position, Vector2 velocity, Vector2 goal) {
		super(position,velocity,goal);
	}

	public abstract float getSpeed();
	
	public abstract void update();

	public abstract void draw(SpriteBatch batch);
}
