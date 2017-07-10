package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.agent.AgentBullet;
import space.imaginehave.tehdeh.agent.AgentCore;
import space.imaginehave.tehdeh.agent.TowerMapObject;
import space.imaginehave.tehdeh.agent.AgentMapObject;


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
	}
	
	
	@Override
	public void renderObject(MapObject object) {
		
		if(object instanceof AgentCore) {
			AgentMapObject agent = ((AgentMapObject) object);
			agent.update();
			batch.draw(
					(Texture) game.getState().getAssetManager().get(Constant.TEST_AGENT_PNG), 
					agent.getPosition().x,
					agent.getPosition().y);
		}
		
		else if(object instanceof AgentBullet) {
			AgentMapObject agent = ((AgentMapObject) object);
			agent.update();
			batch.draw(
					(Texture) game.getState().getAssetManager().get(Constant.TEST_BULLET_PNG), 
					agent.getPosition().x,
					agent.getPosition().y);
		}	
		else if(object instanceof TowerMapObject) {
			AgentMapObject agent = ((AgentMapObject) object);
			agent.update();
//			batch.draw(
//					(Texture) game.getState().getAssetManager().get(Constant.TEST_TOWER), 
//					agent.getPosition().x,
//					agent.getPosition().y);
		}	
	}
}
