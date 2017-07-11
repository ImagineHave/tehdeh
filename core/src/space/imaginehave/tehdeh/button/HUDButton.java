package space.imaginehave.tehdeh.button;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import space.imaginehave.tehdeh.Constant;


public class HUDButton extends ImageButton {

	private Texture texture;
	
	public HUDButton(Drawable drawable) {
		super(drawable);
	}

	public void setMouseFollowTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return texture;
	}
	
}
