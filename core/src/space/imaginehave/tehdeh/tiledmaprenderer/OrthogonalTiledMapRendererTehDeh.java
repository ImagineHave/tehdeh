package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import space.imaginehave.tehdeh.agent.AgentCore;
import space.imaginehave.tehdeh.agent.AgentMapObject;
import space.imaginehave.tehdeh.daemon.DaemonMapObject;
import space.imaginehave.tehdeh.hurtythings.HurtyThingBullet;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.tower.TowerMapObject;


public class OrthogonalTiledMapRendererTehDeh extends OrthogonalTiledMapRenderer {

	public OrthogonalTiledMapRendererTehDeh(final GameStateTehDeh state) {
		super(state.getTiledMap(), state.getBatch());
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
			hurtyThing.draw();
		}	
		else if (object instanceof TowerMapObject) {
			TowerMapObject agent = ((TowerMapObject) object);
			agent.update();
		} 
		else if (object instanceof AgentCore) {
			AgentMapObject agent = ((AgentMapObject) object);
			agent.update();
			agent.draw();
		}	
		else if (object instanceof DaemonMapObject) {
			DaemonMapObject daemon = ((DaemonMapObject) object);
			daemon.update();
		}
		
	}
}
