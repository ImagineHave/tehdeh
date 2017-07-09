package space.imaginehave.tehdeh;

import java.util.Random;

import com.badlogic.gdx.math.Vector3;

public class Util {
	
	private static final Random random = new Random();
	
	public static Vector3 getRandomPosition(Integer x, Integer y) {
		Vector3 position = new Vector3(
				(x != null) ? x : random.nextInt((int) Constant.VIEWPORT_WIDTH),
				(y != null) ? y : random.nextInt((int) Constant.VIEWPORT_HEIGHT),
				0f);
		return position;
	}
	
	public static Vector3 getRandomPosition(){
		return Util.getRandomPosition(null, null);
	}

}
