package eventschar;

import com.badlogic.gdx.math.Rectangle;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.Char.State;

public class CollisionCharCORChar extends CollisionCharCOR{

    public CollisionCharCORChar(CollisionCharCOR s) {
        super(s);
    }

    @Override
    protected boolean CollisionCharCORContre(Terrain w, ElementDeJeu ele, Char c, Rectangle r) {
        if(ele instanceof Char) {
            if(c.getBounds().overlaps(ele.getBounds())) {
                c.setState(State.ARRET);
                c.getVelocity().x = 0;
                c.getVelocity().y = 0;
            }
            return true;
        }
        return false;
    }
    
}
