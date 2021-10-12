package modele.lesprojectiles;

import com.badlogic.gdx.math.Vector2;

import modele.leselementsdejeu.ElementDeJeuDynamique;

public abstract class Projectile extends ElementDeJeuDynamique {

    public static final float SIZE = 0.3f;
    public static final float SPEED = 15f; // unit per second

    Vector2  acceleration = new Vector2();
    Vector2  velocity = new Vector2();

    private Direction direction;


    public enum Direction {
        GAUCHE, DROITE, HAUT, BAS
    }

    public Projectile(Vector2 pos, Direction d) {
        super(pos);
        direction = d;
    }

    public Projectile(Vector2 pos, Direction d, float size) {
        super(pos, size);
        direction = d;
    }

    public void update (float delta) {
        /*getPosition().x = getPosition().x + velocity.x * delta;
        getPosition().y = getPosition().y + velocity.y * delta;  

        getBounds().x = getPosition().x; 
        getBounds().y = getPosition().y; */
        
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    
}
