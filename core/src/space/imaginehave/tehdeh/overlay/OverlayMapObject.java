package space.imaginehave.tehdeh.overlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector2;

import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class OverlayMapObject extends MapObject {
	
	private GameStateTehDeh state;
	private Vector2 position;
	private OverlayCell cell;

	public OverlayMapObject (Vector2 position, GameStateTehDeh state, Texture texture) {
		this.position = position;
		this.cell = new OverlayCell(texture);
		this.state = state;
	}
	
	public Vector2 getPosition() {
		return this.position;
	}
	
	public OverlayCell getCell() {
		return this.cell;
	}

}
