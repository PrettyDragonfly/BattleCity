package vue;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.Avion;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.ElementDeJeuDynamique;
import modele.leselementsdejeu.ElementDeJeuStatique;
import modele.leselementsdejeu.lescases.Drapeau;
import modele.leselementsdejeu.lescases.MurBrique;
import modele.leselementsdejeu.lescases.MurRenforce;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.CharJoueur;
import modele.lesprojectiles.Projectile;
 
public class WorldRenderer {

	private static final float CAMERA_WIDTH = 28f;
	private static final float CAMERA_HEIGHT = 28f;
 
	SpriteBatch batch;
	HashMap<Integer, Texture> images;
	Texture charJoueur, charJoueurPos, charJoueurGauche, charJoueurDroite, charJoueurHaut, charJoueurBas,
			chasseurChar, chasseurDrapeau, drapeau, fond, murBrique, murRenforce, projectile, arbre, vide;
	OrthographicCamera camera;

	private boolean debug = false;
	private int width;
	private int height;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis

	private Terrain world;
	
	/** for debug rendering **/
	ShapeRenderer debugRenderer = new ShapeRenderer();
	
	public WorldRenderer(Terrain world, boolean debug) {
		this.world = world;
		this.camera = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.camera.position.set(CAMERA_WIDTH/2, CAMERA_HEIGHT/2, 0);
		this.camera.update();
		this.debug = debug;
		batch = new SpriteBatch();
		loadTextures();
	}

	public void setSize (int w, int h) {
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH;
		ppuY = (float)height / CAMERA_HEIGHT;
	}

	private void loadTextures() {
		TextureFactory.getInstance();
	}

	private void drawStaticElement() {

		for (ElementDeJeuStatique block : world.getGameElementStatic()) {
			switch(block.getClass().getSimpleName()) {
				case "Arbre":
					batch.draw(TextureFactory.getInstance().getArbre(), block.getPosition().x * ppuX, block.getPosition().y  * ppuY, ElementDeJeu.SIZE * ppuX, ElementDeJeu.SIZE * ppuY);
					break;
				case "Drapeau":
					if(((Drapeau)(block)).isAlive())
						batch.draw(TextureFactory.getInstance().getDrapeau(),block.getPosition().x * ppuX, block.getPosition().y  * ppuY, Drapeau.SIZE * ppuX, Drapeau.SIZE * ppuY);
					else      
						batch.draw(TextureFactory.getInstance().getDrapeauCasse(),block.getPosition().x * ppuX, block.getPosition().y  * ppuY, Drapeau.SIZE * ppuX, Drapeau.SIZE * ppuY);			
					break; 
				case "MurIncassable" :
					batch.draw(TextureFactory.getInstance().getMurIncassable(),block.getPosition().x * ppuX, block.getPosition().y  * ppuY, ElementDeJeu.SIZE * ppuX, ElementDeJeu.SIZE * ppuY);
					break; 
			}  
		}

	}

	private void drawDynamicElement() {

		for (ElementDeJeuDynamique block : world.getGameElementDynamic()) {
			switch(block.getClass().getSimpleName()) {
				case"MurBrique":
					batch.draw(TextureFactory.getInstance().getMurBrique(), block.getPosition().x * ppuX, block.getPosition().y  * ppuY, ElementDeJeu.SIZE * ppuX, ElementDeJeu.SIZE * ppuY);
					break;
				case "MurRenforce":
					batch.draw(TextureFactory.getInstance().getMurRenforce(), block.getPosition().x * ppuX, block.getPosition().y  * ppuY, ElementDeJeu.SIZE * ppuX, ElementDeJeu.SIZE * ppuY);
					break;
				case "Avion":
					batch.draw(TextureFactory.getInstance().getAvion(), block.getPosition().x * ppuX, block.getPosition().y  * ppuY, Avion.SIZE * ppuX, Avion.SIZE * ppuY);
					break;
					//	batch.draw(vide, block.getPosition().x * ppuX, block.getPosition().y  * ppuY, Case.SIZE * ppuX, Case.SIZE * ppuY);
			}
		}

	}

	private void drawChar() {

		for (Char chars : world.getChars()) {
			switch(chars.getClass().getSimpleName()) {
				case "CharJoueur":
				switch(chars.getDirection()) {
					case GAUCHE :
						batch.draw(TextureFactory.getInstance().getCharJoueurGauche(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					case DROITE :
						batch.draw(TextureFactory.getInstance().getCharJoueurDroite(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					case HAUT :
						batch.draw(TextureFactory.getInstance().getCharJoueurHaut(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					case BAS :
						batch.draw(TextureFactory.getInstance().getCharJoueurBas(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					}
					break;
				case "ChasseurCharFollow":
				switch(chars.getDirection()) {
					case GAUCHE :
						batch.draw(TextureFactory.getInstance().getChasseurCharGauche(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					case DROITE :
						batch.draw(TextureFactory.getInstance().getChasseurCharDroite(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					case HAUT :
						batch.draw(TextureFactory.getInstance().getChasseurCharHaut(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					case BAS :
						batch.draw(TextureFactory.getInstance().getChasseurCharBas(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					}
					break;
				case "ChasseurCharAlea":
				switch(chars.getDirection()) {
					case GAUCHE :
						batch.draw(TextureFactory.getInstance().getChasseurCharGauche(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					case DROITE :
						batch.draw(TextureFactory.getInstance().getChasseurCharDroite(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					case HAUT :
						batch.draw(TextureFactory.getInstance().getChasseurCharHaut(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					case BAS :
						batch.draw(TextureFactory.getInstance().getChasseurCharBas(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
						break;
					}
					break;
				case"ChasseurDrapeau":
					switch(chars.getDirection()) {
						case GAUCHE :
							batch.draw(TextureFactory.getInstance().getChasseurDrapeauGauche(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
							break;
						case DROITE :
							batch.draw(TextureFactory.getInstance().getChasseurDrapeauDroite(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
							break;
						case HAUT :
							batch.draw(TextureFactory.getInstance().getChasseurDrapeauHaut(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
							break;
						case BAS :
							batch.draw(TextureFactory.getInstance().getChasseurDrapeauBas(), chars.getPosition().x * ppuX, chars.getPosition().y  * ppuY, Char.SIZE * ppuX, Char.SIZE * ppuY);
							break;
						}
					break;
			}
		}

	}

	private void drawBullet() {
		for (Char allChar : world.getChars()) {
			for (Projectile projectile : allChar.getBulletTires()) {
				switch(projectile.getDirection()) {
					case GAUCHE	:
						batch.draw(TextureFactory.getInstance().getProjectileGauche(), projectile.getPosition().x * ppuX, projectile.getPosition().y  * ppuY, Projectile.SIZE * ppuX, Projectile.SIZE * ppuY);
						break;
					case BAS:
						batch.draw(TextureFactory.getInstance().getProjectileBas(), projectile.getPosition().x * ppuX, projectile.getPosition().y  * ppuY, Projectile.SIZE * ppuX, Projectile.SIZE * ppuY);
						break;
					case DROITE:
						batch.draw(TextureFactory.getInstance().getProjectileDroite(), projectile.getPosition().x * ppuX, projectile.getPosition().y  * ppuY, Projectile.SIZE * ppuX, Projectile.SIZE * ppuY);
						break;
					case HAUT:
						batch.draw(TextureFactory.getInstance().getProjectileHaut(), projectile.getPosition().x * ppuX, projectile.getPosition().y  * ppuY, Projectile.SIZE * ppuX, Projectile.SIZE * ppuY);
						break;
					default:
						break;
	
				}
			}
		}

	}
	
	public void render() {
		batch.begin();
		drawStaticElement();
		drawDynamicElement();
		drawChar();
		drawBullet();

	   	batch.end();

		if (debug) //GameScreen.java ligne 37
		   drawDebug();
	   	
	}

	private void drawDebug() { 
		// render blocks
		debugRenderer.setProjectionMatrix(camera.combined);
		debugRenderer.begin(ShapeType.Line);

		for (ElementDeJeuStatique block : world.getGameElementStatic()) {
			Rectangle rect = block.getBounds();
			float x1 = /*block.getPosition().x +*/ rect.x;
			float y1 = /*block.getPosition().y + */rect.y;
			if(block instanceof Drapeau) {
				debugRenderer.setColor(new Color(0, 0, 1, 1));
			}
			else
				debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		for (ElementDeJeuDynamique block : world.getGameElementDynamic()) {
			Rectangle rect = block.getBounds();
			float x1 = /*block.getPosition().x +*/ rect.x;
			float y1 = /*block.getPosition().y + */rect.y;
			debugRenderer.setColor(new Color(1, 0, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);
		}

		for (Char chars : world.getChars()) {
			for (Projectile p : chars.getBulletTires()) {
				Rectangle rect1 = p.getBounds();
				//System.out.println("rectangle projectile : " + rect1.height + "  " + rect1.width);
				float x1 = rect1.x;
				float y1 = rect1.y;
				debugRenderer.setColor(new Color(1, 1, 0, 1));
				debugRenderer.rect(x1, y1, rect1.width, rect1.height);
			}
			Rectangle rect = chars.getBounds();
			Rectangle rectG = chars.getBoundsGauche();
			Rectangle rectD = chars.getBoundsDroite();
			Rectangle rectB = chars.getBoundsBas();
			Rectangle rectH = chars.getBoundsHaut();
			float x1 = /*chars.getPosition().x + */rect.x;
			float y1 = /*chars.getPosition().y + */rect.y;

			float xG = rectG.x;
			float yG = rectG.y;

			float xD = rectD.x;
			float yD = rectD.y;

			float xB = rectB.x;
			float yB = rectB.y;

			float xH = rectH.x;
			float yH = rectH.y;

			debugRenderer.setColor(new Color(0, 1, 0, 1));
			debugRenderer.rect(x1, y1, rect.width, rect.height);

			debugRenderer.setColor(new Color(1, 1, 0, 1));
			debugRenderer.rect(xG, yG, rectG.width, rectG.height);
			debugRenderer.rect(xD, yD, rectD.width, rectD.height);
			debugRenderer.rect(xB, yB, rectB.width, rectB.height);
			debugRenderer.rect(xH, yH, rectH.width, rectH.height);
		}

		/*for (ElementDeJeuDynamique block : world.getGameElementDynamic()) {
			if(block instanceof MurBrique) {
				for (Rectangle rect : ((MurBrique) block).getBoundsMorceaux()) {
					float x1 = rect.x;
					float y1 = rect.y;
					debugRenderer.setColor(new Color(0, 1, 1, 1));
					debugRenderer.rect(x1, y1, rect.width, rect.height);
				}

			} else if(block instanceof MurRenforce) {
				for (Rectangle rect : ((MurRenforce) block).getBoundsMorceaux()) {
					float x1 = rect.x;
					float y1 = rect.y;
					debugRenderer.setColor(new Color(0, 1, 1, 1));
					debugRenderer.rect(x1, y1, rect.width, rect.height);
				}
			}

		}*/
		debugRenderer.end();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Terrain getWorld() {
		return world;
	}

	public void setWorld(Terrain world) {
		this.world = world;
	}
}
