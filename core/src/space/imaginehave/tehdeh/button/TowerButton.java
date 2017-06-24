package space.imaginehave.tehdeh.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class TowerButton extends ImageButton {

	private Texture texture;

	public TowerButton(Skin skin, Texture texture) {
		super(skin);
		this.texture = texture;
		super.getStyle().imageUp = new Image(texture).getDrawable();
	}
	
	public Texture getTexture() {
		return texture;
	}
	
}
