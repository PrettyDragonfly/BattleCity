package modele.leselementsdejeu.lescases;

import com.badlogic.gdx.math.Vector2;

import modele.leselementsdejeu.ElementDeJeuStatique;

public class Drapeau extends ElementDeJeuStatique {

    public static final float SIZE = 2f;

    public boolean isAlive = true;

    public Drapeau(Vector2 pos) {
        super(pos);
        setBounds(pos.x, pos.y, SIZE, SIZE);
    }

    @Override
    public void update(float delta) {
        
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    
}
