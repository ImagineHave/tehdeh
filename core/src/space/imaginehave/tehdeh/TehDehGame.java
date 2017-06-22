package space.imaginehave.tehdeh;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import space.imaginehave.tehdeh.screen.TehDehGameScreen;
import state.TehDehGameState;

public class TehDehGame extends Game {

	private SpriteBatch	batch;
	private TehDehGameState state;
	private BitmapFont	font;
	private Texture bucketImage;

	@Override
	public void create() {
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
		state = new TehDehGameState();
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new TehDehGameScreen(this));
	}

	@Override
	public void render() {

		super.render();
	}

	@Override
	public void dispose() {

		batch.dispose();
		font.dispose();
	}
	
	public TehDehGameState getState() {
		return this.state;
	}
	
	public Batch getBatch() {
		return this.batch;
	}
	
	public Texture getBucket(){
		return bucketImage;
	}
}
