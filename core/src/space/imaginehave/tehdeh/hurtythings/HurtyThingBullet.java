package space.imaginehave.tehdeh.hurtythings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.agent.AgentCore;
import space.imaginehave.tehdeh.overlay.OverlayMapObject;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class HurtyThingBullet extends HurtyThingCore {

	private int lifeTime = 2;
	private int speed = 3;
	private float lifeTimer;
	private boolean remove;
	private Vector2 origin;
	
	public HurtyThingBullet(Vector3 position, Vector3 velocity, Vector3 goal, GameStateTehDeh state) {
		super(position, velocity, goal);
		this.origin = new Vector2(position.cpy().x, position.cpy().y);
		texture = state.getAssetManager().get(Constant.TEST_HURTYTHING_PNG);
	}
	
	@Override
	public float getSpeed() {
		return speed;
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
