package entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class TowerCell extends EntityCell {

	public TowerCell(Texture towerTexture) {
		super();
		TextureRegion entityTextureRegion = new TextureRegion(towerTexture);
		TiledMapTile tmt = new StaticTiledMapTile(entityTextureRegion);
		setTile(tmt);
	}
}
