package space.imaginehave.tehdeh.hurtythings;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class HurtyThingCore extends HurtyThingMapObject {

	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 goal;
	
	public HurtyThingCore(Vector2 position, Vector2 velocity, Vector2 goal) {
		this.position = position;
		this.velocity = velocity;
		this.goal = goal;
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
	
	public abstract float getSpeed();
	
	public abstract void update();

	public abstract void draw(SpriteBatch batch);
}
