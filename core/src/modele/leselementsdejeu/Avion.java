package modele.leselementsdejeu;

import com.badlogic.gdx.math.Vector2;

import modele.lemonde.Terrain;

public class Avion extends ElementDeJeuDynamique{

    public static final float SIZE = 4f; // a unit

    Vector2  velocity = new Vector2();

    public Avion(Vector2 pos, float size) {
        super(pos, size);
    }

    @Override
    public void update(float delta) {

        getPosition().x = getPosition().x + getVelocity().x * delta;
        getPosition().y = getPosition().y + getVelocity().y * delta;  

        getBounds().x = getPosition().x; 
        getBounds().y = getPosition().y; 
        
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }
    
}
