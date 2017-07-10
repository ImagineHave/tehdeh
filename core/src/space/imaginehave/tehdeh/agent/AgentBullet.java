package space.imaginehave.tehdeh.agent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

public class AgentBullet extends AgentCore {

	private int lifeTime = 4;
	private int speed = 3;
	private float lifeTimer;
	private boolean remove;
	
	public AgentBullet(Vector3 position, Vector3 velocity, Vector3 goal) {
		super(position, velocity, goal);
		// TODO Auto-generated constructor stub
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

	@Override
	public void suicide() {
		// TODO Auto-generated method stub
		
	}

}
