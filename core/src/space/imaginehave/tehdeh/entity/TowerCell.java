package space.imaginehave.tehdeh.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class TowerCell extends Cell {

	public TowerCell(Texture towerTexture) {
		super();
		TextureRegion entityTextureRegion = new TextureRegion(towerTexture);
		TiledMapTile tmt = new StaticTiledMapTile(entityTextureRegion);
		setTile(tmt);
	}
}
