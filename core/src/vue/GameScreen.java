package vue;

import com.badlogic.gdx.Screen;

import modele.lemonde.Terrain;

public abstract class GameScreen implements Screen {
    protected static WorldRenderer worldRenderer;

	public GameScreen() {
	}

	public Terrain getWorld() {
		return worldRenderer.getWorld();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}
