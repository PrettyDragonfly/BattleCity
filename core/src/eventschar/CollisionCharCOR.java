package eventschar;

import com.badlogic.gdx.math.Rectangle;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.leschars.Char;

public abstract class CollisionCharCOR implements CollisionChar {
    
    private CollisionCharCOR suivant;


    /**
     * Chaine de responsabilite
     * @param s
     */
    public CollisionCharCOR(CollisionCharCOR s) {
        this.suivant = s;
    }

    protected abstract boolean CollisionCharCORContre(Terrain w, ElementDeJeu ele, Char c, Rectangle r);

    @Override
    public boolean collisionDuChar(Terrain w, ElementDeJeu ele, Char c, Rectangle r) {
        if(!(this.CollisionCharCORContre(w, ele, c, r))) {
            if(suivant != null) {
                return suivant.collisionDuChar(w, ele, c, r);
            }
            else
                return false;
        }
        else
            return true;
    }

    /**
     * initialisation de la chaine
     * @return
     */
    public static CollisionCharCOR initCOR() {

        CollisionCharCORMurRenforce murRenforce = new CollisionCharCORMurRenforce(null);
        //CollisionCharCORChar chars = new CollisionCharCORChar(murRenforce);
        CollisionCharCORMurBrique murBrique = new CollisionCharCORMurBrique(murRenforce);
        CollisionCharCORMurIncassable murIncassable = new CollisionCharCORMurIncassable(murBrique);
        CollisionCharCORArbre arbre = new CollisionCharCORArbre(murIncassable);

        
        return arbre;

    }
}
