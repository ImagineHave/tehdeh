package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.agent.AgentBullet;
import space.imaginehave.tehdeh.agent.AgentCore;
import space.imaginehave.tehdeh.agent.AgentMapObject;
import space.imaginehave.tehdeh.tower.TowerMapObject;


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
		
		if(object instanceof AgentBullet) {
			AgentBullet agent = ((AgentBullet) object);
			agent.update();
			float rotation = new Vector2(agent.getOrigin().x, agent.getOrigin().y).angle(new Vector2(agent.getGoal().x, agent.getGoal().y));
			
			batch.draw(
					new TextureRegion((Texture) game.getState().getAssetManager().get(Constant.TEST_BULLET_PNG)), 
					agent.getPosition().x,
					agent.getPosition().y,
					0,0,
					8f,8f,8f,8f,
					rotation-210);
		}	
		else if(object instanceof TowerMapObject) {
			TowerMapObject agent = ((TowerMapObject) object);
			agent.update();
//			batch.draw(
//					(Texture) game.getState().getAssetManager().get(Constant.TEST_TOWER), 
//					agent.getPosition().x,
//					agent.getPosition().y);
		} else if(object instanceof AgentCore) {
			AgentMapObject agent = ((AgentMapObject) object);
			agent.update();
			batch.draw(
					(Texture) game.getState().getAssetManager().get(Constant.TEST_AGENT_PNG), 
					agent.getPosition().x,
					agent.getPosition().y);
		}	
		
	}
}
