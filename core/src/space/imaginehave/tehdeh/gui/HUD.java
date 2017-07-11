package space.imaginehave.tehdeh.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

	public HUD (Viewport viewport, final TehDehGame game) {
		Skin skin = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

		Drawable towerButtonDrawable = new TextureRegionDrawable(new TextureRegion(((Texture) game.getState().getAssetManager().get(Constant.TEST_TOWER_PNG))));
		final HUDButton towerButton = new HUDButton(towerButtonDrawable);
		towerButton.setMouseFollowTexture((Texture) game.getState().getAssetManager().get(Constant.TEST_TOWER_PNG));

		Drawable goalDrawable = new TextureRegionDrawable(new TextureRegion((Texture) game.getState().getAssetManager().get(Constant.OVERLAY_GOAL_PNG)));
		final HUDButton goalChangeButton = new HUDButton(goalDrawable);

		Drawable resetDrawable = new TextureRegionDrawable(new TextureRegion((Texture) game.getState().getAssetManager().get(Constant.BUTTON_RESET_PNG)));
		final HUDButton resetButton = new HUDButton(resetDrawable);

		Drawable moreDrawable = new TextureRegionDrawable(new TextureRegion((Texture) game.getState().getAssetManager().get(Constant.BUTTON_MORE_PNG)));
		final HUDButton moreButton = new HUDButton(moreDrawable);

		hud = new Table(skin);
		hudWidth = 0.2f;
		hud.setBounds(Constant.VIEWPORT_WIDTH*(1-hudWidth), 0, Constant.VIEWPORT_HEIGHT*hudWidth, Constant.VIEWPORT_HEIGHT);
		hud.setDebug(true);

		hud.add(towerButton).size(Constant.BUTTON_SIZE).top().left().fill();
		
		hud.add(goalChangeButton).size(Constant.BUTTON_SIZE).top().left().fill();
		hud.row();

		hud.add(resetButton).size(Constant.BUTTON_SIZE).top().left().fill();
		
		hud.add(moreButton).size(Constant.BUTTON_SIZE).top().left().expand().fill();
		hud.row();
//		hud.pack();

		addListenersToButtons(game, towerButton, goalChangeButton, resetButton, moreButton);
	}

	private void addListenersToButtons(final TehDehGame game, final HUDButton towerButton, final HUDButton goalChangeButton,
			final HUDButton resetButton, final HUDButton moreButton) {
		
		resetButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.getState().reset();
			}
		});
		
		towerButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!game.getState().getPlacementTexture().isPresent()) {
					game.getState().setPlacementTexture(towerButton.getTexture());
					game.getState().setMouseCoords(game.getState().getMouseCoords());	
				} 
			}
		});
		
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
