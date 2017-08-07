package space.imaginehave.tehdeh.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.gui.HUD;
import space.imaginehave.tehdeh.inputprocessor.InputProcessorTehDeh;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.wave.Population;

public class GameScreenTehDeh implements Screen {

	private SpriteBatch batch;
	private Viewport viewport;
	private Stage stage;
	private Skin skin;
	private InputProcessor processor;
	
	private Population population;
	private OrthographicCamera camera;

	public GameScreenTehDeh() {
		
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new StretchViewport(Constant.GAME_WIDTH, Constant.GAME_HEIGHT, camera);
		viewport.apply(true);
		
		population = new Population(batch, camera);

		HUD hud = new HUD(viewport, population);
		stage = new Stage();
		stage.addActor(hud.getHud());
		stage.setViewport(viewport);

		InputMultiplexer im = new InputMultiplexer();
		processor = new InputProcessorTehDeh(this, camera, population);
		im.addProcessor(processor);
		im.addProcessor(stage);
		Gdx.input.setInputProcessor(im);
		
		population.createAgents();
		
		population.createWalls();
		
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		population.render();
		
		stage.act(Math.min(delta, 1 / 30f));
		stage.draw();
		
		batch.begin();
		GameStateTehDeh.getInstance().getMouse().render(batch);
		batch.end();
	    
	}
	
	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void resize(int width, int height) {

		stage.getViewport().update(width, height, true);

	}

	@Override
	public void pause() {

		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {

		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {

		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {

		stage.dispose();
		skin.dispose();

	}
	
}
