package space.imaginehave.tehdeh;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.objects.TiledMapTileMapObject;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import space.imaginehave.tehdeh.screen.GameScreenTehDeh;
import space.imaginehave.tehdeh.state.Entity;
import space.imaginehave.tehdeh.state.TehDehGameState;
import space.imaginehave.tehdeh.tiledmaprenderer.OrthogonalTiledMapRendererTehDeh;

public class TehDehGame extends Game {

	private SpriteBatch	batch;
	private TehDehGameState state;
	private BitmapFont	font;
	private Texture bucketImage;
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;

	@Override
	public void create() {
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));
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
		return bucketImage;
	}

	public void addEntity(int screenX, int screenY, Texture texture) {
		TiledMapTileMapObject tmo = new TiledMapTileMapObject(new StaticTiledMapTile(new TextureRegion(bucketImage)), false, false);
		TiledMapTileLayer entityLayer = (TiledMapTileLayer) tiledMap.getLayers().get("entityLayer");
		entityLayer.getCell(screenX, screenY).setTile(new StaticTiledMapTile(new TextureRegion(bucketImage)));
//		((TiledMapTileLayer) tiledMap).setCell(x, y, cell); .getTileSets().("entityLayer").get().add(tmo);
	}
}
