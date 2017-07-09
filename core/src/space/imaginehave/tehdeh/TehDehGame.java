package space.imaginehave.tehdeh;

import com.badlogic.gdx.Game;
import space.imaginehave.tehdeh.screen.GameScreenTehDeh;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class TehDehGame extends Game {

	
	private GameStateTehDeh state;
	

	@Override
	public void create() {
		
		state = new GameStateTehDeh(this);
		this.setScreen(new GameScreenTehDeh(this));
	}
	

	@Override
	public void render() {

		super.render();
	}

	@Override
	public void dispose() {

	}
	
	public GameStateTehDeh getState() {
		return this.state;
	}

}
