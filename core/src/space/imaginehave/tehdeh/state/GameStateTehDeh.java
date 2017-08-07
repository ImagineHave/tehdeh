package space.imaginehave.tehdeh.state;

import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.player.Player;

public class GameStateTehDeh {
	

	private final Player player;
	
	private static GameStateTehDeh state;
	
	public static GameStateTehDeh getInstance() {
		if (state == null) {
			state = new GameStateTehDeh();
		}
		return state;
	}
	
	private GameStateTehDeh () {
		player = new Player();
	}

	public Player getPlayer() {
		return player;
	}

}
