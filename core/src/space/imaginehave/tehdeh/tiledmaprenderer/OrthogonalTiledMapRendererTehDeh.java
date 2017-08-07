package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import space.imaginehave.tehdeh.agent.AgentMob;
import space.imaginehave.tehdeh.agent.AgentMob;
import space.imaginehave.tehdeh.daemon.DaemonMapObject;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
import space.imaginehave.tehdeh.screen.GameScreenTehDeh;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.tower.TowerMapObject;


public class OrthogonalTiledMapRendererTehDeh extends OrthogonalTiledMapRenderer {


	public OrthogonalTiledMapRendererTehDeh(final GameScreenTehDeh gameScreenTehDeh) {
		super(GameStateTehDeh.getInstance().getTiledMap(), gameScreenTehDeh.getBatch());
	}
	
	
	@Override
	public void render() {
		super.render();
	}
	
	
	@Override
	public void renderObject(MapObject object) {
		
		if(object instanceof HurtyThingBullet) {
			HurtyThingBullet hurtyThing = ((HurtyThingBullet) object);
			hurtyThing.update();
			hurtyThing.draw((SpriteBatch) batch);
		}	
		else if (object instanceof TowerMapObject) {
			TowerMapObject agent = ((TowerMapObject) object);
			agent.update();
		} 
		else if (object instanceof AgentMob) {
			AgentMob agent = ((AgentMob) object);
			agent.update();
			agent.draw((SpriteBatch) batch);
		}	
		else if (object instanceof DaemonMapObject) {
			DaemonMapObject daemon = ((DaemonMapObject) object);
			daemon.update();
		}
		
	}
}
