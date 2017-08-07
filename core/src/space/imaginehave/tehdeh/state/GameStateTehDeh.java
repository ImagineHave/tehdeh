package space.imaginehave.tehdeh.state;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.inputprocessor.Mouse;
import space.imaginehave.tehdeh.player.Player;

public class GameStateTehDeh {
	

	private final Player player;
	private Vector2 goal;
	private Mouse mouse;
	
	private static GameStateTehDeh state;
	
	public static GameStateTehDeh getInstance() {
		if (state == null) {
			state = new GameStateTehDeh();
		}
		return state;
	}
	
	private GameStateTehDeh () {
		
		mouse = new Mouse();
		
		goal = new Vector2(Constant.GAME_WIDTH/2,32);
		
		player = new Player();
	}
	
	public Vector2 getGoal() {
		return goal;
	}
	
	public void setGoal(Vector2 position) {
		goal = position;
	}
	
	public Mouse getMouse(){
		return this.mouse;
	}

	public Player getPlayer() {
		return player;
	}

}
