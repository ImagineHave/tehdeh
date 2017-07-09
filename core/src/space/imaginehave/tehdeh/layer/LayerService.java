package space.imaginehave.tehdeh.layer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.agent.Agent;
import space.imaginehave.tehdeh.agent.DummyTowerAgent;
import space.imaginehave.tehdeh.entity.TowerCell;
import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class LayerService {
	
	/**
	 * For Overlay and Towerlayer
	 * @param vector
	 * @param texture
	 * @param layer
	 */
	public void addToTiledMapTileLayer(Vector3 vector, Texture texture, TiledMapTileLayer layer, GameStateTehDeh state) {
		TowerCell cell = new TowerCell(texture);
		layer.setCell((int) (vector.x/layer.getTileWidth()), (int) (vector.y/layer.getTileHeight()), cell);
		
		DummyTowerAgent dta = new DummyTowerAgent(new Vector3(vector), state);
		layer.getObjects().add(dta);
	}
	
	public void removeFromTiledMapTileLayer(DummyTowerAgent dta, TiledMapTileLayer layer) {
		if (dta != null) {
			layer.setCell((int) (dta.getPosition().x/layer.getTileWidth()), (int) (dta.getPosition().y/layer.getTileHeight()), null);
			layer.getObjects().remove(dta);
		}
	}
	
	/**
	 * returns null if not found
	 * @param vector
	 * @return
	 */
	public DummyTowerAgent getDtaFromTiledMapTileLayer(Vector3 vector, TiledMapTileLayer layer) {
		
		Array<DummyTowerAgent> agents = layer.getObjects().getByType(DummyTowerAgent.class);
		DummyTowerAgent dta = null;
		for (Agent agent : agents) {
			if(agent.getPosition().x == vector.x && agent.getPosition().y == vector.y) {
				dta = (DummyTowerAgent) agent;
			}
		}
		return dta;
	}

}
