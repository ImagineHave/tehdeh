package space.imaginehave.tehdeh;

import java.nio.channels.GatheringByteChannel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.screen.GameScreenTehDeh;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.wave.Population;

public class TehDehGame extends Game {

	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	
	@Override
	public void create() {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new StretchViewport(Constant.GAME_WIDTH, Constant.GAME_HEIGHT, camera);
		viewport.apply();
		
		Population population = Population.getInstance();
		GameStateTehDeh state = GameStateTehDeh.getInstance();
		
		this.setScreen(new GameScreenTehDeh(batch, camera, viewport));
	}
	

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {

	}

}
