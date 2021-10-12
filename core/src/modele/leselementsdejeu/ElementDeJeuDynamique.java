package modele.leselementsdejeu;

import com.badlogic.gdx.math.Vector2;

public abstract class ElementDeJeuDynamique extends ElementDeJeu{

    public ElementDeJeuDynamique(Vector2 pos) {
        super(pos);
    }

    public ElementDeJeuDynamique(Vector2 pos, float size) {
        super(pos, size);
    }
    
}
