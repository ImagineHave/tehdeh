package space.imaginehave.tehdeh.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.button.TowerButton;
import space.imaginehave.tehdeh.screen.GameScreenTehDeh;

public class HUD {

	private Table hud;

	public HUD (Skin skin, Viewport viewport, final TehDehGame game) {
		skin = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

		hud = new Table();
		hud.setBounds(viewport.getWorldWidth()*0.75f, 0, viewport.getWorldWidth()*0.25f, viewport.getWorldHeight());
		hud.setDebug(true);

		final TowerButton button = new TowerButton(skin, (Texture) game.getAssetManager().get("testTower.png"));
		hud.add(button).top().left().expand();
		
		button.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!game.getState().getPlacementTexture().isPresent()) {
					game.getState().setPlacementTexture(button.getTexture());
					game.getState().setMouseCoords(game.getState().getMouseCoords());	
				} else {
					game.getState().removeMouseFollow();
				}
			}
		});

	}
	
	public Table getHud(){
		return hud;
	}
	
}
