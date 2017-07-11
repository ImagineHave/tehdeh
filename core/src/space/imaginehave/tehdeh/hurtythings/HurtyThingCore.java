package space.imaginehave.tehdeh.hurtythings;

import com.badlogic.gdx.math.Vector3;

public abstract class HurtyThingCore extends HurtyThingMapObject {

	protected Vector3 position;
	protected Vector3 velocity;
	protected Vector3 goal;
	
	public HurtyThingCore(Vector3 position, Vector3 velocity, Vector3 goal) {
		this.position = position;
		this.velocity = velocity;
		this.goal = goal;
	}

	public Vector3 getPosition() {
		return position;
	}

	public void setPosition(Vector3 position) {
		this.position = position;
	}

	public Vector3 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3 velocity) {
		this.velocity = velocity;
	}

	public Vector3 getGoal() {
		return goal;
	}

	public void setGoal(Vector3 goal) {
		this.goal = goal;
	}
	
	public abstract float getSpeed();
	
	public abstract void update();

}
