package space.imaginehave.tehdeh.hurtythings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class HurtyThingBullet extends MapObject {

	protected static final float height = 16f;
	protected static final float width = 16f;
	
	private int lifeTime = 2;
	private int speed = 3;
	private float lifeTimer;
	private boolean remove;
	private Vector2 origin;
	private int damage = 3;
	
	protected Texture texture;
	protected Vector2 position;
	protected Vector2 velocity;
	protected Vector2 goal;
	protected Polygon polygon;
	
	public HurtyThingBullet(Vector2 position, Vector2 velocity, Vector2 goal, GameStateTehDeh state) {
		this.position = position;
		this.velocity = velocity;
		this.goal = goal;
		polygon = new Polygon(new float[]{0,0,width,0,width,height,0,height});
		this.origin = position;
		texture = state.getAssetManager().get(Constant.TEST_HURTYTHING_PNG);
	}
	

	public float getSpeed() {
		return speed;
	}


	public void update() {
		lifeTimer += Gdx.graphics.getDeltaTime();
		if(lifeTimer > lifeTime){
			remove = true;
		}
		
		position.add(goal.cpy().setLength(speed));
		polygon.setPosition(position.x, position.y);
	}
	
	public boolean isToRemove() {
		return remove;
	}
	
	public Vector2 getOrigin() {
		return origin;
	}


	public void draw(SpriteBatch batch) {
		float rotation = new Vector2(this.getOrigin().x, this.getOrigin().y).angle(new Vector2(this.getGoal().x, this.getGoal().y));
		batch.draw(
				this.getTextureRegion(), 
				this.getPosition().x,
				this.getPosition().y,
				0,0,
				width,height,1f,1f,
				rotation-210);
	}


	public int getDamage() {
		return damage;
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
