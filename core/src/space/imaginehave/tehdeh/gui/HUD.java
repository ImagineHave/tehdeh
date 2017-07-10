package space.imaginehave.tehdeh.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.Util;
import space.imaginehave.tehdeh.button.HUDButton;
import space.imaginehave.tehdeh.overlay.GoalOverlayMapObject;

public class HUD {

	private Table hud;
	private final float hudWidth;

	public HUD (Skin skin, Viewport viewport, final TehDehGame game) {
		skin = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

		hud = new Table();
		hudWidth = 0.2f;
		hud.setBounds(Constant.VIEWPORT_WIDTH*(1-hudWidth), 0, Constant.VIEWPORT_HEIGHT*hudWidth, Constant.VIEWPORT_HEIGHT);
		hud.setDebug(true);

		Drawable towerButtonDrawable = new TextureRegionDrawable(new TextureRegion((Texture) game.getState().getAssetManager().get(Constant.TEST_TOWER_PNG)));
		final HUDButton towerButton = new HUDButton(towerButtonDrawable);
		towerButton.setTexture((Texture) game.getState().getAssetManager().get(Constant.TEST_TOWER_PNG));
		hud.add(towerButton).left();
		hud.row();
		
		towerButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!game.getState().getPlacementTexture().isPresent()) {
					game.getState().setPlacementTexture(towerButton.getTexture());
					game.getState().setMouseCoords(game.getState().getMouseCoords());	
				} 
			}
		});
		
		Drawable goalDrawable = new TextureRegionDrawable(new TextureRegion((Texture) game.getState().getAssetManager().get(Constant.OVERLAY_GOAL_PNG)));
		final HUDButton goalChangeButton = new HUDButton(goalDrawable);
		hud.add(goalChangeButton).left();
		hud.row();
		
		goalChangeButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				Vector3 vector = game.getState().getGoal();
				
				game.getState().getLayerService().removeFromOverlay(
						game.getState().getLayerService().getFromOverlay(vector, game.getState().getOverlay()),
						game.getState().getOverlay());
				
				game.getState().setGoal(Util.getRandomPosition());
				
				game.getState().getLayerService().addToOverlay(
						new GoalOverlayMapObject(game.getState()),
						game.getState().getOverlay(), 
						game.getState());
				
			}
		});

		Drawable resetDrawable = new TextureRegionDrawable(new TextureRegion((Texture) game.getState().getAssetManager().get(Constant.BUTTON_RESET_PNG)));
		final HUDButton resetButton = new HUDButton(resetDrawable);
		hud.add(resetButton).left();
		hud.row();
		
		resetButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.getState().reset();
			}
		});
		
		
		Drawable moreDrawable = new TextureRegionDrawable(new TextureRegion((Texture) game.getState().getAssetManager().get(Constant.BUTTON_MORE_PNG)));
		final HUDButton moreButton = new HUDButton(moreDrawable);
		hud.add(moreButton).left();
		hud.row();
		
		moreButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.getState().createAgents();
			}
		});
	}
	
	public Table getHud(){
		return hud;
	}
	
}
