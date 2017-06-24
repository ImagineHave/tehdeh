package space.imaginehave.tehdeh;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import space.imaginehave.tehdeh.screen.GameScreenTehDeh;
import space.imaginehave.tehdeh.state.TehDehGameState;

public class TehDehGame extends Game {

	
	private TehDehGameState state;
	private static AssetManager assetManager;

	@Override
	public void create() {
		
		assetManager = new AssetManager();
		assetManager.load("testTower.png", Texture.class);
		assetManager.finishLoading();
		
		state = new TehDehGameState();
		this.setScreen(new GameScreenTehDeh(this));
	}
	

	@Override
	public void render() {

		super.render();
	}

	@Override
	public void dispose() {

	}
	
	public TehDehGameState getState() {
		return this.state;
	}


	public AssetManager getAssetManager() {
		return assetManager;
	}
}
