package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import controller.ChasseurCharAleaController;
import controller.ChasseurCharFollowController;
import controller.ChasseurDrapeauController;
import controller.WorldController;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;

import java.lang.Math;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.Avion;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.ElementDeJeuDynamique;
import modele.leselementsdejeu.lescases.Arbre;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.CharJoueur;
import modele.leselementsdejeu.leschars.charennemi.ChasseurDrapeau;
import modele.leselementsdejeu.leschars.charennemi.ChasseurCharFollow;
import modele.leselementsdejeu.leschars.charennemi.ChasseurCharAlea;
import modele.lesprojectiles.Projectile;
import modele.lesprojectiles.TirNormal;

import com.badlogic.gdx.utils.Pool;
 
public class BattleCityScreen extends GameScreen {

    private Terrain world;
    private WorldRenderer  renderer;

    private WorldController controller;
    private ChasseurCharAleaController chasseurAleaController;
    private ChasseurCharFollowController chasseurFollowController;
    private ChasseurDrapeauController chasseurDrapeauController;

    private Avion avion;
    private long lastAirplaneTime;
    private long lastBulletTime;


    
    private int width, height;

    private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject () {
            return new Rectangle();
        }
    };
   
 
    /** Rest of methods ommited **/
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        for (ElementDeJeu allGameElement : world.getAllGameElement()) {
            allGameElement.update(delta);
        }

        renderer.render();

        if(Gdx.input.isKeyJustPressed(Keys.SPACE))
            controller.spacePressed();

        if(Gdx.input.isKeyPressed(Keys.LEFT))
            controller.LeftPressed();
        else if(Gdx.input.isKeyPressed(Keys.DOWN))
            controller.DownPressed();
        else if(Gdx.input.isKeyPressed(Keys.RIGHT))
            controller.RightPressed();
        else if(Gdx.input.isKeyPressed(Keys.UP))
            controller.UpPressed();
        else if(!Gdx.input.isKeyPressed(Keys.DOWN) && !Gdx.input.isKeyPressed(Keys.UP) && 
                            !Gdx.input.isKeyPressed(Keys.RIGHT) && !Gdx.input.isKeyPressed(Keys.LEFT)) 
            controller.noPressed();

        boolean overlaps = false;


        /**
         * on parcours tout les chars
         */
        for (Char c : world.getChars()) {

            /**
             * on clear tout les voisins du char
             */
            c.clearGameElementsVoisins();

            Rectangle charRect = rectPool.obtain();
            charRect.set(c.getPosition().x, c.getPosition().y, c.getBounds().width, c.getBounds().height);
        
            charRect.x += c.getVelocity().x;
        
            charRect.y += c.getVelocity().y;
            
            /**
             * on parcours tout les elements du jeu
             */
            for (ElementDeJeu gameElement : world.getAllGameElement()) {
 
                /**
                 * si l'element est different du char et que ce n'est pas un arbre
                 */
                if(!(gameElement.equals(c)) && !(gameElement instanceof Arbre)) {  
                    
                    /**
                     * selon si l'element est un voisin du char, on l'ajoute à son array de voisin correspondant
                     */
                    
                    if(c.getBoundsGauche().overlaps(gameElement.getBounds())) {
                        c.getGameElementsGauche().add(gameElement);
                    }
                    else if(c.getBoundsDroite().overlaps(gameElement.getBounds())) {
                        c.getGameElementsDroite().add(gameElement);
                    }
                    else if(c.getBoundsBas().overlaps(gameElement.getBounds())) {
                        c.getGameElementsBas().add(gameElement);
                    }
                    if(c.getBoundsHaut().overlaps(gameElement.getBounds())) {
                        c.getGameElementsHaut().add(gameElement);
                    }
                    
                    overlaps = world.collisionChar(gameElement, c, charRect);


                    //world.collisionChar(gameElement, c);

                    charRect.x = c.getPosition().x;
                    charRect.y = c.getPosition().y;

                    /**
                     * on parcours tout les projectiles tirés par les chars et on appelle la COR
                     */
                    for (Projectile projectile : c.getBulletTires()) {
                        world.collisionProjectile(projectile, gameElement, c);
                    }
                }

                /**
                 * supprime les avions qui sortent du cadre
                 */
                if(gameElement instanceof Avion) {
                    if(gameElement.getPosition().x > 30 || gameElement.getPosition().y > 30) {
                        world.getGameElementDynamic().removeValue((ElementDeJeuDynamique)gameElement, false);
                        world.getAllGameElement().removeValue(gameElement, false);
                    }
                }

                    

            }
            /**
             * on appelle les controllers correspondants au char parcouru
             */
            if(c instanceof ChasseurCharFollow) {
                chasseurFollowController.update(delta, (ChasseurCharFollow) c);
            }
            else if(c instanceof ChasseurCharAlea) {
                chasseurAleaController.update(delta, (ChasseurCharAlea) c);
            }
            else if(c instanceof ChasseurDrapeau) {
                chasseurDrapeauController.update(delta, (ChasseurDrapeau) c);
            }

            if(!(c instanceof CharJoueur)) {
                //if(TimeUtils.nanoTime() - lastBulletTime > 1000000000) {
                    TirNormal t;
                    if(c.getBulletTires().size < Char.BULLETMAX) {
                        switch(c.getDirection()) {
                            case GAUCHE : 
                                t = new TirNormal(new Vector2(c.getPosition().x, c.getPosition().y + Char.SIZE/2 - Projectile.SIZE/2), 
                                                            modele.lesprojectiles.Projectile.Direction.GAUCHE, Projectile.SIZE);
                                t.getVelocity().x = -Projectile.SPEED;
                                c.getBulletTires().add(t);
                                break;
                            case BAS:
                                t = new TirNormal(new Vector2(c.getPosition().x + Char.SIZE/2 - Projectile.SIZE/2, c.getPosition().y), 
                                                            modele.lesprojectiles.Projectile.Direction.BAS, Projectile.SIZE);
                                t.getVelocity().y = -Projectile.SPEED;
                                c.getBulletTires().add(t);
                                break;
                            case DROITE:
                                t = new TirNormal(new Vector2(c.getPosition().x + Char.SIZE, c.getPosition().y + Char.SIZE/2 - Projectile.SIZE/2), 
                                                            modele.lesprojectiles.Projectile.Direction.DROITE, Projectile.SIZE);
                                t.getVelocity().x = Projectile.SPEED;
                                c.getBulletTires().add(t);
                                break;
                            case HAUT:
                                t = new TirNormal(new Vector2(c.getPosition().x + Char.SIZE/2 - Projectile.SIZE/2, c.getPosition().y + Char.SIZE), 
                                                            modele.lesprojectiles.Projectile.Direction.HAUT, Projectile.SIZE);
                                t.getVelocity().y = Projectile.SPEED;
                                c.getBulletTires().add(t);
                                break;
    
                        }
    
                        lastBulletTime = TimeUtils.nanoTime();
                        
                    }
                //}
                
            }


            /*if(c instanceof ChasseurCharFollow) {
                System.out.println("array : ");
                for (ElementDeJeu e : c.getGameElementsVoisins()) {
                    if(e != null)
                        System.out.println(e.getClass().getSimpleName());
                    else System.out.println("null");
                }
            }*/

            
        }

        // check si on peut créer un nouvel avion
      if(TimeUtils.nanoTime() - lastAirplaneTime > 2147483647) spawnAvion();

    }

    private void spawnAvion() {
        Vector2 dep = new Vector2(0, MathUtils.random(0, 26));
        Vector2 arr = new Vector2(26, MathUtils.random(0, 26));
        Vector2 vec = new Vector2(arr.x - dep.x, arr.y - dep.y);

        float t = 1f;

        float x = vec.x*t;
        float y = vec.y*t;

        Vector2 tqt = new Vector2(x, y);


        avion = new Avion(dep, Avion.SIZE);
        avion.setVelocity(tqt);
        
        world.getGameElementDynamic().add(avion);
        world.getAllGameElement().add(avion);

        lastAirplaneTime = TimeUtils.nanoTime();
    }
    
    @Override
    public void resize(int width, int height) {
        //renderer.setSize(width, height);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public void show() {
        world = new Terrain();
        System.out.println(world.getChars());
        renderer = new WorldRenderer(world, false); //boolean debug
        controller = new WorldController(world);
        chasseurAleaController = new ChasseurCharAleaController(world);
        chasseurFollowController = new ChasseurCharFollowController(world);
        chasseurDrapeauController = new ChasseurDrapeauController(world);
        renderer.setSize (Gdx.graphics.getWidth (), Gdx.graphics.getHeight ());
    }


    @Override
    public void hide() {
     Gdx.input.setInputProcessor(null);
    }
    
    @Override
    public void pause() {
    }
    
    @Override
    public void resume() {
    }
    
    @Override
    public void dispose() {
     Gdx.input.setInputProcessor(null);
    }

    public Terrain getWorld() {
        return world;
    }

    public void setWorld(Terrain world) {
        this.world = world;
    }

    public WorldRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(WorldRenderer renderer) {
        this.renderer = renderer;
    }

}