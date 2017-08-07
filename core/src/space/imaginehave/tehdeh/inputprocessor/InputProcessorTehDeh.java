package space.imaginehave.tehdeh.inputprocessor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import space.imaginehave.tehdeh.TehDehGame;
import space.imaginehave.tehdeh.Util;
import space.imaginehave.tehdeh.screen.GameScreenTehDeh;
import space.imaginehave.tehdeh.state.GameStateTehDeh;
import space.imaginehave.tehdeh.tower.TowerMapObject;
import space.imaginehave.tehdeh.tower.TowerService;
import space.imaginehave.tehdeh.tower.TowerType;
import space.imaginehave.tehdeh.wave.Population;

public class InputProcessorTehDeh implements InputProcessor {
	
	private final Camera camera;
	private final TowerService towerService;
	private final GameScreenTehDeh screen;
	private final Population population;
	private final Mouse mouse;
	
	public InputProcessorTehDeh(final GameScreenTehDeh screen, final Camera camera, final Population population, final Mouse mouse) {
		this.screen = screen;
		this.camera = camera;
		this.population = population;
		this.mouse = mouse;
		towerService = new TowerService();
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
//		if(!GameStateTehDeh.getInstance().getPlacementTexture().isPresent()) {
//			Vector2 vector = camera.unproject(new Vector2(screenX, screenY, 0));
//			for(MapObject a : GameStateTehDeh.getInstance().getAgentLayer().getObjects()) {
//				((Agent)a).setGoal(vector);
//			}
//		}
			
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(mouse.getPlacementTowerType().isPresent()) {
			if (screen instanceof GameScreenTehDeh) {
				Vector2 vector = Util.convert(camera.unproject(new Vector3(screenX, screenY, 0)));
				TowerType towerType = mouse.getPlacementTowerType().get();
				TowerMapObject towerMapObject = towerService.createTower(vector, towerType, population);
				
				population.getLayerService().addTower(
						towerMapObject, 
						population.getTowerLayer(), 
						population.getAgentLayer());
				
				mouse.setPlacementTexture(null);
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
		mouse.setMouseCoords(vector);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
