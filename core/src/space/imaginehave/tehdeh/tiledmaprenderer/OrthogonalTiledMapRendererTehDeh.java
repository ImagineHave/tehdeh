package space.imaginehave.tehdeh.tiledmaprenderer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import space.imaginehave.tehdeh.TehDehGame;


public class OrthogonalTiledMapRendererTehDeh extends OrthogonalTiledMapRenderer {

	private final TehDehGame game;
	
	public OrthogonalTiledMapRendererTehDeh(TiledMap map, final TehDehGame game) {
		super(map);
		this.game = game;
	}
	
	
	@Override
	public void render() {
		
		super.render();
		
		game.getBatch().begin();
		if (game.getState().getPlacementTexture().isPresent()) {
			Texture placementTexture = game.getState().getPlacementTexture().get();
			game.getBatch().draw(
					placementTexture, 
					game.getState().getMouseCoords().x - placementTexture.getWidth()/2, 
					game.getState().getMouseCoords().y - placementTexture.getHeight()/2
					);
		}
		game.getBatch().end();
	}


}
