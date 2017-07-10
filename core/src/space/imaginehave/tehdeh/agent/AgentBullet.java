package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class AgentBullet extends AgentCore {

	private int lifeTime = 2;
	private int speed = 3;
	private float lifeTimer;
	private boolean remove;
	private Vector2 origin;
	
	public AgentBullet(Vector3 position, Vector3 velocity, Vector3 goal) {
		super(position, velocity, goal);
		this.origin = new Vector2(position.cpy().x, position.cpy().y);
	}
	
	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return speed;
	}

	@Override
	public void setGoal(Vector3 goalVector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		lifeTimer += Gdx.graphics.getDeltaTime();
		if(lifeTimer > lifeTime){
			remove = true;
		}
		
		position.add(goal.cpy().setLength(speed));
	}
	
	public boolean isToRemove() {
		return remove;
	}
	
	public Vector2 getOrigin() {
		return origin;
	}

}
