package space.imaginehave.tehdeh.overlay;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.state.GameStateTehDeh;

public class OverlayMapObject extends MapObject {
	
	private GameStateTehDeh state;
	private Vector3 position;
	private OverlayCell cell;

	public OverlayMapObject (Vector3 position, GameStateTehDeh state, Texture texture) {
		this.position = position;
		this.cell = new OverlayCell(texture);
		this.state = state;
	}
	
	public Vector3 getPosition() {
		return this.position;
	}
	
	public OverlayCell getCell() {
		return this.cell;
	}

}
