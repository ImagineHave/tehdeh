package space.imaginehave.tehdeh.state;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * Just got tired of writing out TehDehGameState
 * @author Christopher Williams
 *
 */
public interface State {

	public TiledMapTileLayer getTowerLayer();
	
}
