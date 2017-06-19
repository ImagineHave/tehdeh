package space.imaginehave.tehdeh;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import space.imaginehave.tehdeh.screen.TehDehGameScreen;

public class TehDehGame extends Game {

	public SpriteBatch	batch;
	public BitmapFont	font;

	@Override
	public void create() {

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
}
