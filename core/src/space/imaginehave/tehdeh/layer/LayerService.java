package space.imaginehave.tehdeh.layer;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.overlay.OverlayMapObject;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.tower.TowerMapObject;

public class LayerService {
	
	public void addToOverlay(OverlayMapObject overlayMapObject, TiledMapTileLayer layer) {
		layer.setCell(
				(int) (overlayMapObject.getPosition().x/layer.getTileWidth()), 
				(int) (overlayMapObject.getPosition().y/layer.getTileHeight()), 
				overlayMapObject.getCell());
		
		layer.getObjects().add(overlayMapObject);
	}
	
	public void removeFromOverlay(OverlayMapObject omo, TiledMapTileLayer layer) {
		if (omo != null) {
			layer.setCell((int) (omo.getPosition().x/layer.getTileWidth()), (int) (omo.getPosition().y/layer.getTileHeight()), null);
			layer.getObjects().remove(omo);
		}
	}
	
	/**
	 * returns null if not found
	 * @param vector
	 * @return
	 */
	public OverlayMapObject getFromOverlay(Vector2 vector, TiledMapTileLayer layer) {
		
		Array<OverlayMapObject> overlayObjects = layer.getObjects().getByType(OverlayMapObject.class);
		OverlayMapObject omo = null;
		for (OverlayMapObject overlayObject : overlayObjects) {
			if(overlayObject.getPosition().x == vector.x && overlayObject.getPosition().y == vector.y) {
				omo = overlayObject;
			}
		}
		return omo;
	}
	
	public void addTower(
			TowerMapObject towerMapObject, 
			TiledMapTileLayer tileLayer,
			MapLayer agentLayer) {
		
		tileLayer.setCell(
				(int) (towerMapObject.getPosition().x/tileLayer.getTileWidth()), 
				(int) (towerMapObject.getPosition().y/tileLayer.getTileHeight()), 
				towerMapObject.getCell());
		
		agentLayer.getObjects().add(towerMapObject);
		
	}
	
	public void removeTower(TowerMapObject towerMapObject, TiledMapTileLayer tileLayer, MapLayer agentLayer) {
		if (towerMapObject != null) {
			tileLayer.setCell((int) (towerMapObject.getPosition().x/tileLayer.getTileWidth()), (int) (towerMapObject.getPosition().y/tileLayer.getTileHeight()), null);
			agentLayer.getObjects().remove(towerMapObject);
		}
	}
	
	public TowerMapObject getTower(Vector2 vector, MapLayer layer) {
		
		Array<TowerMapObject> towers = layer.getObjects().getByType(TowerMapObject.class);
		TowerMapObject tmo = null;
		for (TowerMapObject towerMapObject : towers) {
			if(towerMapObject.getPosition().x == vector.x && towerMapObject.getPosition().y == vector.y) {
				tmo = towerMapObject;
			}
		}
		return tmo;
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
