package modele.lesprojectiles;

import com.badlogic.gdx.math.Vector2;

public class TirNormal extends Projectile {

    public TirNormal(Vector2 pos, Direction d) {
        super(pos, d);
    }

    public TirNormal(Vector2 pos, Direction d, float size) {
        super(pos, d, size);
    }

    @Override
    public void update(float delta) {   
        getPosition().x = getPosition().x + velocity.x * delta;
        getPosition().y = getPosition().y + velocity.y * delta;  

        getBounds().x = getPosition().x; 
        getBounds().y = getPosition().y;      
    }
    
}
