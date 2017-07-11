package space.imaginehave.tehdeh.button;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;



public class HUDButton extends ImageButton {

	private Texture texture;
	
	public HUDButton(Drawable drawable) {
		super(drawable);
		
		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		
		TextureRegionDrawable backGround = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap)));
		ImageButtonStyle style = new ImageButtonStyle();
		
		style.up = backGround.tint(Color.LIGHT_GRAY);
		style.down = backGround.tint(Color.DARK_GRAY);
		
		style.imageUp = drawable;
		style.imageDown = drawable;
		
		setStyle(style);
		
	}

	public void setMouseFollowTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return texture;
	}
	
}
