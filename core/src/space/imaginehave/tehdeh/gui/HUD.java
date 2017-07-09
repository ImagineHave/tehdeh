package space.imaginehave.tehdeh.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.Constant;
import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.Util;
import space.imaginehave.tehdeh.button.HUDButton;

public class HUD {

	private Table hud;

	public HUD (Skin skin, Viewport viewport, final TehDehGame game) {
		skin = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

		hud = new Table();
		hud.setBounds(viewport.getWorldWidth()*0.75f, 0, viewport.getWorldWidth()*0.25f, viewport.getWorldHeight());
		hud.setDebug(true);

		Label towerButtonLabel = new Label(Constant.TEST_TOWER, skin);
		final HUDButton towerButton = new HUDButton(skin, 
				(Texture) game.getState().getAssetManager().get(Constant.TEST_TOWER));
		hud.add(towerButtonLabel);
		hud.add(towerButton);
		hud.row();
		
		towerButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!game.getState().getPlacementTexture().isPresent()) {
					game.getState().setPlacementTexture(towerButton.getTexture());
					game.getState().setMouseCoords(game.getState().getMouseCoords());	
				} 
			}
		});
		
		Label goalChangeButtonLabel = new Label(Constant.OVERLAY_GOAL, skin);
		final HUDButton goalChangeButton = new HUDButton(skin, (Texture) game.getState().getAssetManager().get(Constant.OVERLAY_GOAL));
		hud.add(goalChangeButtonLabel);
		hud.add(goalChangeButton);
		hud.row();
		
		goalChangeButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				Vector3 vector = game.getState().getGoal();
				
				game.getState().getLayerService().removeFromTiledMapTileLayer(
						game.getState().getLayerService().getDtaFromTiledMapTileLayer(vector, game.getState().getOverlay()),
						game.getState().getOverlay());
				
				game.getState().setGoal(Util.getRandomPosition());
				
				game.getState().getLayerService().addToTiledMapTileLayer(
						game.getState().getGoal(), 
						(Texture) game.getState().getAssetManager().get(Constant.OVERLAY_GOAL), 
						game.getState().getOverlay(), 
						game.getState());
				
			}
		});

		Label resetLabel = new Label(Constant.BUTTON_RESET, skin);
		final HUDButton resetButton = new HUDButton(skin, (Texture) game.getState().getAssetManager().get(Constant.BUTTON_RESET));
		hud.add(resetLabel);
		hud.add(resetButton);
		hud.row();
		
		resetButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				game.getState().reset();
			}
		});
		
		
		Label moreLabel = new Label(Constant.BUTTON_MORE, skin);
		final HUDButton moreButton = new HUDButton(skin, (Texture) game.getState().getAssetManager().get(Constant.BUTTON_MORE));
		hud.add(moreLabel);
		hud.add(moreButton);
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
