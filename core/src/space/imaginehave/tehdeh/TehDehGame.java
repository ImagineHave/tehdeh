package space.imaginehave.tehdeh;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.screen.GameScreenTehDeh;
import space.imaginehave.tehdeh.state.TehDehGameState;
import space.imaginehave.tehdeh.tiledmaprenderer.OrthogonalTiledMapRendererTehDeh;

public class TehDehGame extends Game {

	private SpriteBatch	batch;
	private TehDehGameState state;
	private BitmapFont	font;
	private Texture towerImage;
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;

	@Override
	public void create() {
		towerImage = new Texture(Gdx.files.internal("testTower.png"));
		state = new TehDehGameState();
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		tiledMap = new TmxMapLoader().load("tehdeh.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRendererTehDeh(tiledMap, this);
		this.setScreen(new GameScreenTehDeh(this));
	}
	
	public TiledMap getTiledMap() {
		return tiledMap;
	}

	public TiledMapRenderer getTiledMapRenderer() {
		return tiledMapRenderer;
	}

	@Override
	public void render() {

		super.render();
	}

	@Override
	public void dispose() {

		batch.dispose();
		font.dispose();
	}
	
	public TehDehGameState getState() {
		return this.state;
	}
	
	public Batch getBatch() {
		return this.batch;
	}
	
	public Texture getBucket(){
		return towerImage;
	}

	public void addEntity(Vector3 vector, Vector3 vector2, Texture texture) {
		TiledMapTileLayer entityLayer = (TiledMapTileLayer) tiledMap.getLayers().get("tileLayer1");
				
		Cell cell = new Cell();
		TextureRegion entityTextureRegion = new TextureRegion(towerImage);
		TiledMapTile tmt = new StaticTiledMapTile(entityTextureRegion);
		cell.setTile(tmt);
		
		entityLayer.setCell((int) (vector.x/entityLayer.getTileWidth()), (int) (vector.y/entityLayer.getTileHeight()), cell);
	}
}
