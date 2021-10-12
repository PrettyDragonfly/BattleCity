package eventschar;

import com.badlogic.gdx.math.Rectangle;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.lescases.MurRenforce;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.Char.Direction;
import modele.leselementsdejeu.leschars.Char.State;

public class CollisionCharCORMurRenforce extends CollisionCharCOR {

    
    public CollisionCharCORMurRenforce(CollisionCharCOR s) {
        super(s);
    }


    @Override
    protected boolean CollisionCharCORContre(Terrain w, ElementDeJeu ele, Char c, Rectangle r) {
    
        if(ele instanceof MurRenforce) {
            if (r.overlaps(ele.getBounds())) {
                c.getVelocity().x = 0;
                c.getVelocity().y = 0;
                c.setState(State.ARRET);

                float x = c.getPosition().x * 2;
                x = Math.round(x);
                c.getPosition().x = x / 2;

                float y = c.getPosition().y * 2;
                y = Math.round(y);
                c.getPosition().y = y / 2;
            }

            //r.x = c.getPosition().x;
            //r.y = c.getPosition().y;
            c.getBounds().x = c.getPosition().x;
            c.getBounds().y = c.getPosition().y;

            /*c.setBoundsBas(c.getPosition().x + 0.1f, c.getPosition().y - 0.1f, Char.SIZE - 0.2f, Char.SIZE - 1.5f);
            c.setBoundsDroite(c.getPosition().x + 1.6f, c.getPosition().y + 0.1f, Char.SIZE - 1.5f, Char.SIZE - 0.2f);
            c.setBoundsGauche(c.getPosition().x - 0.1f, c.getPosition().y + 0.1f, Char.SIZE - 1.5f, Char.SIZE - 0.2f);
            c.setBoundsHaut(c.getPosition().x + 0.1f, c.getPosition().y + 1.6f, Char.SIZE - 0.2f, Char.SIZE - 1.5f);*/
            

            return true;
        }
        return false;
    }

}




                                                        //CE QUIL FAUT FAIRE : Reprendre le code pour avoir les cases autours de soit, et l'appliquer dans GameScreen dans la boucle
                                                        //for ou il y a les chars pris et donc changer la COR pour ne plus prendre un char, mais un rectangle en paramètre

                                                        /*import com.badlogic.gdx.math.Rectangle;
                                                        import com.badlogic.gdx.utils.Pool; 

                                                        private Pool<Rectangle> rectPool = new Pool<Rectangle>() {
                                                            @Override
                                                            protected Rectangle newObject () {
                                                                return new Rectangle();
                                                            }
                                                        };*/
 
                                                        /*Rectangle playerRect = rectPool.obtain();
                                                        playerRect.set(c.c.getPosition().x, c.c.getPosition().y, c.getBounds().width, c.getBounds().height);
                                                    
                                                        playerRect.x += c.getVelocity().x;
                                                    
                                                        playerRect.y += c.getVelocity().y;
                                                        
                                                        if (playerRect.overlaps(ele.getBounds())) {
                                                            c.getVelocity().x = 0;
                                                            c.getVelocity().y = 0;
                                                            c.setState(State.ARRET);
                                                        }

                                                        playerRect.x = c.c.getPosition().x;
                                                        playerRect.y = c.c.getPosition().y;
                                                        c.getBounds().x = c.c.getPosition().x;
                                                        c.getBounds().y = c.c.getPosition().y;*/
