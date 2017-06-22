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

		// A skin can be loaded via JSON or defined programmatically, either is
		// fine. Using a skin is optional but strongly
		// recommended solely for the convenience of getting a texture, region,
		// etc as a drawable, tinted drawable, etc.
		skin = new Skin();

		// Generate a 1x1 white texture and store it in the skin named "white".
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));

		Pixmap pixmap2 = new Pixmap(1, 1, Format.RGBA8888);
		pixmap2.setColor(Color.FOREST);
		pixmap2.fill();
		skin.add("wiggle", new Texture(pixmap));

		Rectangle bucket = new Rectangle();
		bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
		bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
						// the bottom screen edge
		bucket.width = 64;
		bucket.height = 64;

		// Store the default libgdx font under the name "default".
		skin.add("default", new BitmapFont());

		// Configure a TextButtonStyle and name it "default". Skin resources are
		// stored by type, so this doesn't overwrite the font.
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);

		// Create a table that fills the screen. Everything else will go inside
		// this table.
		Table hud = new Table();
		hud.setBounds(600, 0, 200, 600);
		hud.setDebug(true);
		stage.addActor(hud);

		// Create a button with the "default" TextButtonStyle. A 3rd parameter
		// can be used to specify a name other than "default".
		final TextButton button = new TextButton("Click me!", skin);
		hud.add(button);

		// Add a listener to the button. ChangeListener is fired when the
		// button's checked state changes, eg when clicked,
		// Button#setChecked() is called, via a key press, etc. If the
		// event.cancel() is called, the checked state will be reverted.
		// ClickListener could have been used, but would only fire when clicked.
		// Also, canceling a ClickListener event won't
		// revert the checked state.
		button.addListener(new ChangeListener() {

			public void changed(ChangeEvent event, Actor actor) {

				System.out.println("Clicked! Is checked: " + button.isChecked());
				button.setText("Good job!");
				
				game.getState().setMouseFollow(game.getBucket());
				game.getState().setMouseCoords(game.getState().getMouseCoords());
			}
		});

		// Add an image actor. Have to set the size, else it would be the size
		// of the drawable (which is the 1x1 texture).
		hud.add(new Image(skin.newDrawable("white", Color.RED))).size(64);

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
