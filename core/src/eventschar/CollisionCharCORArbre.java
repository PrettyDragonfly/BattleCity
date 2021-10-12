package eventschar;

import com.badlogic.gdx.math.Rectangle;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.lescases.Arbre;
import modele.leselementsdejeu.leschars.Char;

public class CollisionCharCORArbre extends CollisionCharCOR{

    
    public CollisionCharCORArbre(CollisionCharCOR s) {
        super(s);
    }

    @Override
    protected boolean CollisionCharCORContre(Terrain w, ElementDeJeu ele, Char c, Rectangle r) {
        if(ele instanceof Arbre) {
            return true;
        }
        else 
            return false;
    }
}
