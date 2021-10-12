package eventschar;

import com.badlogic.gdx.math.Rectangle;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.leschars.Char;

public interface CollisionChar {

    public boolean collisionDuChar(Terrain w, ElementDeJeu ele, Char c, Rectangle r);
    
}
