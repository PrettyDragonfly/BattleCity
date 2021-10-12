package modele.leselementsdejeu.lescases;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import modele.leselementsdejeu.ElementDeJeuDynamique;
import modele.leselementsdejeu.ElementDeJeuStatique;

public class MurBrique extends ElementDeJeuDynamique {

    Array<Rectangle> boundsMorceaux = new Array<Rectangle>();

    public MurBrique(Vector2 pos) {
        super(pos);

        //Création Hitbox Morceaux de Brique
        boundsMorceaux.add(new Rectangle().set(getPosition().x, getPosition().y, SIZE/2, SIZE/2));
        boundsMorceaux.add(new Rectangle().set(((getPosition().x + SIZE/2)), getPosition().y, SIZE/2, SIZE/2));
        boundsMorceaux.add(new Rectangle().set(getPosition().x, ((getPosition().y + SIZE/2)), SIZE/2, SIZE/2));
        boundsMorceaux.add(new Rectangle().set(((getPosition().x + SIZE/2)), ((getPosition().y + SIZE/2)), SIZE/2, SIZE/2));

    }


    public Array<Rectangle> getBoundsMorceaux() {
        return boundsMorceaux;
    }

    public void setBoundsMorceaux(Array<Rectangle> boundsMorceaux) {
        this.boundsMorceaux = boundsMorceaux;
    }


    @Override
    public void update(float delta) {
        
    }

    
}
