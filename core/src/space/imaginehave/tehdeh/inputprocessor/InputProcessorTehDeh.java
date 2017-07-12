package space.imaginehave.tehdeh.inputprocessor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.Util;
import space.imaginehave.tehdeh.screen.GameScreenTehDeh;
import space.imaginehave.tehdeh.tower.TowerMapObject;

public class InputProcessorTehDeh implements InputProcessor {
	
	private final TehDehGame game;
	private final Camera camera;
	
	public InputProcessorTehDeh(final TehDehGame game, final Camera camera) {
		this.game = game;
		this.camera = camera;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//		if(!game.getState().getPlacementTexture().isPresent()) {
//			Vector2 vector = camera.unproject(new Vector2(screenX, screenY, 0));
//			for(MapObject a : game.getState().getAgentLayer().getObjects()) {
//				((Agent)a).setGoal(vector);
//			}
//		}
			
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(game.getState().getPlacementTexture().isPresent()) {
			if (game.getScreen() instanceof GameScreenTehDeh) {
				Vector2 vector = Util.convert(camera.unproject(new Vector3(screenX, screenY, 0)));
				
				TowerMapObject towerMapObject = new TowerMapObject(vector, game.getState(), game.getState().getPlacementTexture().get());
				
				game.getState().getLayerService().addTower(
						towerMapObject, 
						game.getState().getTowerLayer(), 
						game.getState().getAgentLayer(), 
						game.getState());
				
				game.getState().setPlacementTexture(null);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector2 vector = Util.convert(camera.unproject(new Vector3(screenX, screenY, 0)));
		game.getState().setMouseCoords(vector);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
