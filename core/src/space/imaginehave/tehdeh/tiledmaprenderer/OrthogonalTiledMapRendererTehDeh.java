package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.agent.AgentCore;
import space.imaginehave.tehdeh.agent.AgentMapObject;
import space.imaginehave.tehdeh.daemon.DaemonMapObject;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
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
		
		if(object instanceof HurtyThingBullet) {
			HurtyThingBullet hurtyThing = ((HurtyThingBullet) object);
			hurtyThing.update();
			float rotation = new Vector2(hurtyThing.getOrigin().x, hurtyThing.getOrigin().y).angle(new Vector2(hurtyThing.getGoal().x, hurtyThing.getGoal().y));
			
			batch.draw(
					hurtyThing.getTextureRegion(), 
					hurtyThing.getPosition().x,
					hurtyThing.getPosition().y,
					0,0,
					8f,8f,8f,8f,
					rotation-210);
		}	
		else if (object instanceof TowerMapObject) {
			TowerMapObject agent = ((TowerMapObject) object);
			agent.update();
//			batch.draw(
//					agent.getTexture(), 
//					agent.getPosition().x,
//					agent.getPosition().y);
		} 
		else if (object instanceof AgentCore) {
			AgentMapObject agent = ((AgentMapObject) object);
			agent.update();
			batch.draw(
					agent.getTexture(), 
					agent.getPosition().x,
					agent.getPosition().y);
		}	
		else if (object instanceof DaemonMapObject) {
			DaemonMapObject daemon = ((DaemonMapObject) object);
			daemon.update();
		}
		
	}
}
