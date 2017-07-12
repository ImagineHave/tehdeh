package space.imaginehave.tehdeh.hurtythings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector2;

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
	
	public HurtyThingBullet(Vector2 position, Vector2 velocity, Vector2 goal, GameStateTehDeh state) {
		super(position, velocity, goal);
		this.origin = new Vector2(position.cpy().x, position.cpy().y);
		texture = state.getAssetManager().get(Constant.TEST_HURTYTHING_PNG);
		batch = state.getBatch();
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

	@Override
	public void draw() {
		float rotation = new Vector2(this.getOrigin().x, this.getOrigin().y).angle(new Vector2(this.getGoal().x, this.getGoal().y));
		batch.draw(
				this.getTextureRegion(), 
				this.getPosition().x,
				this.getPosition().y,
				0,0,
				8f,8f,8f,8f,
				rotation-210);
		
	}

}
