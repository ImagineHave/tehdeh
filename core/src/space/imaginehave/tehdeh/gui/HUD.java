package space.imaginehave.tehdeh.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.AssetManager;
import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.Util;
import space.imaginehave.tehdeh.button.HUDButton;
import space.imaginehave.tehdeh.inputprocessor.Mouse;
import space.imaginehave.tehdeh.overlay.GoalOverlayMapObject;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.tower.TowerType;
import space.imaginehave.tehdeh.wave.Population;

public class HUD {

	private Table hud;
	private Mouse mouse;

	public HUD (Viewport viewport, Population population, Mouse mouse) {

		this.mouse = mouse;
		
		Drawable towerButtonDrawable = new TextureRegionDrawable(new TextureRegion((AssetManager.getInstance().getTexture(Constant.TEST_TOWER_2_PNG))));
		final HUDButton towerButton = new HUDButton(towerButtonDrawable);
		TowerType towerType = new TowerType();
		towerType.setTexture((Texture) AssetManager.getInstance().getTexture(Constant.TEST_TOWER_2_PNG));
		towerButton.setTowerType(towerType);

		Drawable goalDrawable = new TextureRegionDrawable(new TextureRegion(AssetManager.getInstance().getTexture(Constant.OVERLAY_GOAL_PNG)));
		final HUDButton goalChangeButton = new HUDButton(goalDrawable);

		Drawable resetDrawable = new TextureRegionDrawable(new TextureRegion(AssetManager.getInstance().getTexture(Constant.BUTTON_RESET_PNG)));
		final HUDButton resetButton = new HUDButton(resetDrawable);

		Drawable moreDrawable = new TextureRegionDrawable(new TextureRegion(AssetManager.getInstance().getTexture(Constant.BUTTON_MORE_PNG)));
		final HUDButton moreButton = new HUDButton(moreDrawable);

		hud = new Table();
		hud.defaults().size(Constant.BUTTON_SIZE);
		hud.defaults().space(Constant.BUTTON_SPACE);
		hud.pad(50);
		hud.setDebug(true);

		hud.add(towerButton);

		hud.add(resetButton); 
		
		hud.add(moreButton); 
		hud.row();
		
		hud.pack();
		hud.setPosition(Constant.VIEWPORT_WIDTH-hud.getWidth(), Constant.VIEWPORT_HEIGHT-hud.getHeight());

		addListenersToButtons(towerButton, goalChangeButton, resetButton, moreButton, population);
	}

	private void addListenersToButtons(final HUDButton towerButton, final HUDButton goalChangeButton,
			final HUDButton resetButton, final HUDButton moreButton, final Population population) {
		
		resetButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				population.reset();
			}
		});
		
		towerButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!mouse.getPlacementTowerType().isPresent()) {
					mouse.setPlacementTexture(towerButton.getTowerType());
					mouse.setMouseCoords(mouse.getMouseCoords());	
				} 
			}
		});
		
		moreButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				population.createAgents();
			}
		});
	}
	
	public Table getHud(){
		return hud;
	}
	
}
