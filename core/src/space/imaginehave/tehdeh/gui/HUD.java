package space.imaginehave.tehdeh.gui;

import java.util.Optional;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.button.TowerButton;

public class HUD {

	private Table hud;

	public HUD (Skin skin, Viewport viewport, final TehDehGame game) {
		skin = new Skin(Gdx.files.internal("skin/plain-james-ui.json"));

		hud = new Table();
		hud.setBounds(viewport.getWorldWidth()*0.75f, 0, viewport.getWorldWidth()*0.25f, viewport.getWorldHeight());
		hud.setDebug(true);

		final TowerButton towerButton = new TowerButton(skin, (Texture) game.getState().getAssetManager().get("testTower.png"));
		hud.add(towerButton).top().left().expand();
		
		towerButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				if(!game.getState().getPlacementTexture().isPresent()) {
					game.getState().setPlacementTexture(towerButton.getTexture());
					game.getState().setMouseCoords(game.getState().getMouseCoords());	
				} 
			}
		});
		
		final TowerButton goalChangeButton = new TowerButton(skin, (Texture) game.getState().getAssetManager().get("goalChange.png"));
		hud.add(goalChangeButton).top().left().expand();
		
		goalChangeButton.addListener(new ChangeListener() {
			public void changed(ChangeEvent event, Actor actor) {
				Vector3 vector = game.getState().getGoal();
				game.getState().removeTower(game.getState().getTower(vector));
				game.getState().setGoal(game.getState().getRandomPosition());
				game.getState().addTower(game.getState().getGoal(), (Texture) game.getState().getAssetManager().get("goalChange.png"));
			}
		});

	}
	
	public Table getHud(){
		return hud;
	}
	
}
