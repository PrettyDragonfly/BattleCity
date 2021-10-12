package vue;
 
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import modele.lemonde.Terrain;

public class BattleCity extends Game {

    BitmapFont scoreFont, livesFont;
	Batch scoreBatch;
 
    @Override
    public void create() {
        BattleCityScreen bcs = new BattleCityScreen();
        setScreen(bcs);
    }

    @Override
	public void dispose() {
		getScreen().dispose();
	}

	@Override
	public void render() {
		super.render();
		GameScreen currentScreen = (GameScreen) getScreen();

        if(currentScreen instanceof BattleCityScreen) {
            Terrain world = currentScreen.getWorld();

            if (world.getCharsEnnemis().isEmpty()) {
                currentScreen.dispose();
                setScreen(new GameWonScreen());
            }
    
            if ( !(Terrain.drapeau.isAlive) || world.getCharJoueur().getVie() <= 0) {
                currentScreen.dispose();
                setScreen(new GameOverScreen());
            }
        }





		//drawTimeAndLives(world);
	}

    /*private void drawTimeAndLives(Terrain world) {
		scoreBatch.begin();
		CharSequence score = "Temps : " + ((int) world.getTime());
		CharSequence time = "Vies : " + (world.getPacMan().getLives());
		scoreFont.draw(scoreBatch, score, (float) (world.getWidth() / 10) * 16, (float) (world.getHeight() + 2) * 16);
		livesFont.draw(scoreBatch, time, (float) (world.getWidth() * 0.7) * 16, (float) (world.getHeight() + 2) * 16);
		scoreBatch.end();
	}*/
}