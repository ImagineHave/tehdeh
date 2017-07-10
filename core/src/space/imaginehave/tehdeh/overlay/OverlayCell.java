package space.imaginehave.tehdeh.overlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class OverlayCell extends Cell {
	
	public OverlayCell(Texture texture) {
		super();
		TextureRegion textureRegion = new TextureRegion(texture);
		TiledMapTile tmt = new StaticTiledMapTile(textureRegion);
		setTile(tmt);
	}

}
