package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import space.imaginehave.tehdeh.TehDehGame;


public class OrthogonalTiledMapRendererTehDeh extends OrthogonalTiledMapRenderer {

	private final TehDehGame game;
	
	public OrthogonalTiledMapRendererTehDeh(TiledMap map, final TehDehGame game) {
		super(map);
		super.batch = game.getBatch();
		this.game = game;
	}
	
	
	@Override
	public void render() {
		
		super.render();
		
	}


}
