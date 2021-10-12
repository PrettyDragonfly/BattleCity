package modele.leselementsdejeu;

import com.badlogic.gdx.math.Vector2;

public abstract class ElementDeJeuStatique extends ElementDeJeu {

    public ElementDeJeuStatique(Vector2 pos) {
        super(pos);
    }

    public ElementDeJeuStatique(Vector2 pos, float size) {
        super(pos, size);
    }
    
}
