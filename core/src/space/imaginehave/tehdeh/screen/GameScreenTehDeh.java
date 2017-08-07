package space.imaginehave.tehdeh.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.gui.HUD;
import space.imaginehave.tehdeh.inputprocessor.InputProcessorTehDeh;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.tiledmaprenderer.OrthogonalTiledMapRendererTehDeh;

public class GameScreenTehDeh implements Screen {

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private Viewport viewport;
	private Stage stage;
	private Skin skin;
	private InputProcessor processor;
	private TiledMapRenderer tiledMapRenderer;

	public GameScreenTehDeh(SpriteBatch batch, OrthographicCamera camera, Viewport viewport) {
		
		tiledMapRenderer = new OrthogonalTiledMapRendererTehDeh(GameStateTehDeh.getInstance().getTiledMap(), batch);
		
		this.batch = batch;
		this.camera = camera;
		this.viewport = viewport;

		stage = new Stage();

		stage.setViewport(viewport);

		InputMultiplexer im = new InputMultiplexer();
		processor = new InputProcessorTehDeh(this, camera);
		im.addProcessor(processor);
		im.addProcessor(stage);

		Gdx.input.setInputProcessor(im);
		createUI();
		viewport.apply();
		
		GameStateTehDeh.getInstance().setViewport(viewport);
		
		GameStateTehDeh.getInstance().createAgents();
		
		GameStateTehDeh.getInstance().createWalls();
		
	}

	private void createUI() {
		HUD hud = new HUD(viewport);
		stage.addActor(hud.getHud());
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
		
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
