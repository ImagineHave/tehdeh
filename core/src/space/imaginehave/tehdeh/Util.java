package space.imaginehave.tehdeh;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Util {
	
	private static final Random random = new Random();
	
	public static Vector2 getRandomPosition(Integer x, Integer y) {
		Vector2 position = new Vector2(
				(x != null) ? x : random.nextInt((int) Constant.VIEWPORT_WIDTH),
				(y != null) ? y : random.nextInt((int) Constant.VIEWPORT_HEIGHT));
		return position;
	}
	
	public static Vector2 getRandomPosition(){
		return Util.getRandomPosition(null, null);
	}
	
	public static Vector2 convert(Vector3 vector) {
		return new Vector2(vector.x, vector.y);
	}

}
