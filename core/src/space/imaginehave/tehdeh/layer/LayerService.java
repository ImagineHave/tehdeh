package space.imaginehave.tehdeh.layer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.agent.TowerMapObject;
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
		
		TowerMapObject dta = new TowerMapObject(new Vector3(vector), state);
		layer.getObjects().add(dta);
	}
	
	public void removeFromTiledMapTileLayer(TowerMapObject dta, TiledMapTileLayer layer) {
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
	public TowerMapObject getDtaFromTiledMapTileLayer(Vector3 vector, TiledMapTileLayer layer) {
		
		Array<TowerMapObject> agents = layer.getObjects().getByType(TowerMapObject.class);
		TowerMapObject dta = null;
		for (TowerMapObject agent : agents) {
			if(agent.getPosition().x == vector.x && agent.getPosition().y == vector.y) {
				dta = (TowerMapObject) agent;
			}
		}
		return dta;
	}
	
	public TiledMapTileLayer fetchTowerLayer(TiledMap tiledMap) {
		if (tiledMap.getLayers().get(Constant.LAYER_TOWER) instanceof TiledMapTileLayer) {
			return (TiledMapTileLayer) tiledMap.getLayers().get(Constant.LAYER_TOWER);
		} else {
			throw new RuntimeException("layer is not a TiledMapTileLayer.");
		}
		
	}
	
	public MapLayer fetchAgentLayer(TiledMap tiledMap) {
		if (tiledMap.getLayers().get(Constant.LAYER_AGENT) instanceof MapLayer) {
			return (MapLayer) tiledMap.getLayers().get(Constant.LAYER_AGENT);
		} else {
			throw new RuntimeException("layer is not a MapLayer.");
		}
	}

	public TiledMapTileLayer fetchOverlay(TiledMap tiledMap) {
		if (tiledMap.getLayers().get(Constant.OVERLAY) instanceof TiledMapTileLayer) {
			return (TiledMapTileLayer) tiledMap.getLayers().get(Constant.OVERLAY);
		} else {
			throw new RuntimeException("layer is not a TiledMapTileLayer.");
		}
	}

}
