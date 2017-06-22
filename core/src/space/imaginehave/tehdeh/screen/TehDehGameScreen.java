package space.imaginehave.tehdeh.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.inputprocessor.TehDehInputProcessor;

public class TehDehGameScreen implements Screen {

	private final TehDehGame game;
	private OrthographicCamera camera;
	private Stage stage;
	private Skin skin;
	private InputProcessor processor;

	public TehDehGameScreen(final TehDehGame tehDehGame) {
		this.game = tehDehGame;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);

		stage = new Stage();

		InputMultiplexer im = new InputMultiplexer();
		processor = new TehDehInputProcessor(tehDehGame, camera);
		im.addProcessor(processor);
		im.addProcessor(stage);

		Gdx.input.setInputProcessor(im);
		createUI();
	}

	private void createUI() {
				skin = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

				Table hud = new Table();
				hud.setBounds(600, 0, 200, 600);
				hud.setDebug(true);
				stage.addActor(hud);

				final ImageButton button = new ImageButton(skin);
				button.getStyle().imageUp = new Image(game.getBucket()).getDrawable();
				hud.add(button).top().left().expand();

				button.addListener(new ChangeListener() {
					public void changed(ChangeEvent event, Actor actor) {
						System.out.println("Clicked! Is checked: " + button.isChecked());
						game.getState().setMouseFollow(game.getBucket());
						game.getState().setMouseCoords(game.getState().getMouseCoords());
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
		stage.act(Math.min(delta, 1 / 30f));
		stage.draw();
		
		game.getBatch().begin();
		if (game.getState().getMouseFollow().isPresent()) {
			Texture mouseFollowTexture = game.getState().getMouseFollow().get();
			game.getBatch().draw(
					mouseFollowTexture, 
					game.getState().getMouseCoords().x - mouseFollowTexture.getWidth()/2, 
					game.getState().getMouseCoords().y - mouseFollowTexture.getHeight()/2
					);
		}
		game.getBatch().end();

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
