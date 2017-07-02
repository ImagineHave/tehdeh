package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.agent.MapObjectAgent;


public class OrthogonalTiledMapRendererTehDeh extends OrthogonalTiledMapRenderer {

	private final TehDehGame game;
	
	public OrthogonalTiledMapRendererTehDeh(
			final TehDehGame game, 
			final Batch batch) {
		super(game.getState().getTiledMap());
		super.batch = batch;
		this.game = game;
	}
	
	
	@Override
	public void render() {
		super.render();
		game.getState().redefineGoals();
		game.getState().calculatePaths();
	}
	
	
	@Override
	public void renderObject(MapObject object) {
		MapObjectAgent agent = ((MapObjectAgent) object);
		agent.update();
		batch.draw(
				(Texture) game.getState().getAssetManager().get("testAgent.png"), 
				agent.getPosition().x,
				agent.getPosition().y);
	}


}
