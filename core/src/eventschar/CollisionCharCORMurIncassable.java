package eventschar;

import modele.leselementsdejeu.lescases.MurIncassable;
import com.badlogic.gdx.math.Rectangle;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.Char.State;

public class CollisionCharCORMurIncassable extends CollisionCharCOR {

    
    public CollisionCharCORMurIncassable(CollisionCharCOR s) {
        super(s);
    }


    @Override
    protected boolean CollisionCharCORContre(Terrain w, ElementDeJeu ele, Char c, Rectangle r) {
    
        if(ele instanceof MurIncassable) {
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
