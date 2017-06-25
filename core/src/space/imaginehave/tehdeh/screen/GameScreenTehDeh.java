package space.imaginehave.tehdeh.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.agent.DummyAgent;
import space.imaginehave.tehdeh.agent.MapObjectAgent;
import space.imaginehave.tehdeh.button.TowerButton;
import space.imaginehave.tehdeh.entity.TowerCell;
import space.imaginehave.tehdeh.inputprocessor.InputProcessorTehDeh;
import space.imaginehave.tehdeh.search.BoidSearch;
import space.imaginehave.tehdeh.search.SearchInterface;
import space.imaginehave.tehdeh.tiledmaprenderer.OrthogonalTiledMapRendererTehDeh;

public class GameScreenTehDeh implements Screen {

	private final TehDehGame game;
	private Stage stage;
	private Skin skin;
	private InputProcessor processor;
	private Viewport viewport;
	private TiledMapRenderer tiledMapRenderer;
	private SpriteBatch	batch;

	public GameScreenTehDeh(final TehDehGame tehDehGame) {
		this.game = tehDehGame;
		
		batch = new SpriteBatch();
		
		tiledMapRenderer = new OrthogonalTiledMapRendererTehDeh(game, batch);
		
		OrthographicCamera camera = new OrthographicCamera();
		
		viewport = new StretchViewport(800, 800, camera);

		stage = new Stage();
		stage.setViewport(viewport);

		InputMultiplexer im = new InputMultiplexer();
		processor = new InputProcessorTehDeh(tehDehGame, camera);
		im.addProcessor(processor);
		im.addProcessor(stage);

		Gdx.input.setInputProcessor(im);
		createUI();
		viewport.apply();
		
		createAgents();
	}
	
	private void createAgents() {
		
		SearchInterface search = BoidSearch.getInstance();
		
		for (int i = 0; i < 3; i++) {
			
			MapObjectAgent moa = new DummyAgent(viewport, search);
			BoidSearch.getInstance().getAgents().add(moa);
			game.getState().getAgentLayer().getObjects().add(moa);
		}
	}

	private void createUI() {
				skin = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

				Table hud = new Table();
				hud.setBounds(viewport.getWorldWidth()*0.75f, 0, viewport.getWorldWidth()*0.25f, viewport.getWorldHeight());
				hud.setDebug(true);
				stage.addActor(hud);

				final TowerButton button = new TowerButton(skin, (Texture) game.getAssetManager().get("testTower.png"));
				hud.add(button).top().left().expand();
				
				button.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						if(!game.getState().getPlacementTexture().isPresent()) {
							game.getState().setPlacementTexture(button.getTexture());
							game.getState().setMouseCoords(game.getState().getMouseCoords());	
						} else {
							game.getState().removeMouseFollow();
						}
					}
				});

	}

	@Override
	public void show() {

		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		tiledMapRenderer.setView((OrthographicCamera) viewport.getCamera());
        tiledMapRenderer.render();
		
		stage.act(Math.min(delta, 1 / 30f));
		stage.draw();
		
		batch.begin();
		if (game.getState().getPlacementTexture().isPresent()) {
			Texture placementTexture = game.getState().getPlacementTexture().get();
			batch.draw(
					placementTexture, 
					game.getState().getMouseCoords().x - placementTexture.getWidth()/2, 
					game.getState().getMouseCoords().y - placementTexture.getHeight()/2
					);
		}
		batch.end();

        
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
	
	public void addEntity(Vector3 vector, Texture texture) {
		
		TowerCell cell = new TowerCell(texture);
		TiledMapTileLayer towerLayer = game.getState().getTowerLayer();
		towerLayer.setCell((int) (vector.x/towerLayer.getTileWidth()), (int) (vector.y/towerLayer.getTileHeight()), cell);
	}

}
