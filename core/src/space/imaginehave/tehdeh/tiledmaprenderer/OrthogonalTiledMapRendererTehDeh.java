package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import space.imaginehave.tehdeh.TehDehGame;


public class OrthogonalTiledMapRendererTehDeh extends OrthogonalTiledMapRenderer {

	private final TehDehGame game;
	
	public OrthogonalTiledMapRendererTehDeh(final TehDehGame game, final Batch batch) {
		super(game.getState().getTiledMap());
		super.batch = batch;
		this.game = game;
	}
	
	
	@Override
	public void render() {
		
		super.render();
		
	}


}
