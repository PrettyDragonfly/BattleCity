package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen extends GameScreen {
	SpriteBatch batch;
	BitmapFont message;

	public GameOverScreen() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		batch = new SpriteBatch();
		message = new BitmapFont();
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float v) {
		batch.begin();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		CharSequence msg = "bg c'est perdu";
		message.draw(batch, msg, (int) (Gdx.app.getGraphics().getWidth() * 0.4), Gdx.app.getGraphics().getHeight() / 2);
		batch.end();
	}

	@Override
	public void resize(int i, int i1) {

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
