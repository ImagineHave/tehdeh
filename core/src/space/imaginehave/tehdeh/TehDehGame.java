package space.imaginehave.tehdeh;

import com.badlogic.gdx.Game;

import space.imaginehave.tehdeh.screen.GameScreenTehDeh;

public class TehDehGame extends Game {

	@Override
	public void create() {
		this.setScreen(new GameScreenTehDeh());
	}
	
	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {

	}

}
