package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.agent.Bullet;
import space.imaginehave.tehdeh.agent.DummyAgent;
import space.imaginehave.tehdeh.agent.DummyTowerAgent;
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
		
		if(object instanceof DummyAgent) {
			MapObjectAgent agent = ((MapObjectAgent) object);
			agent.update();
			batch.draw(
					(Texture) game.getState().getAssetManager().get(Constant.TEST_AGENT), 
					agent.getPosition().x,
					agent.getPosition().y);
		}
		
		else if(object instanceof Bullet) {
			MapObjectAgent agent = ((MapObjectAgent) object);
			agent.update();
			batch.draw(
					(Texture) game.getState().getAssetManager().get(Constant.TEST_BULLET), 
					agent.getPosition().x,
					agent.getPosition().y);
		}	
		else if(object instanceof DummyTowerAgent) {
			MapObjectAgent agent = ((MapObjectAgent) object);
			agent.update();
//			batch.draw(
//					(Texture) game.getState().getAssetManager().get(Constant.TEST_TOWER), 
//					agent.getPosition().x,
//					agent.getPosition().y);
		}	
	}
}
