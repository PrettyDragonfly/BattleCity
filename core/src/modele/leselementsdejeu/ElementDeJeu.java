package modele.leselementsdejeu;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class ElementDeJeu {

    public static final float SIZE = 1f;

    Vector2  position = new Vector2();
    Rectangle  bounds = new Rectangle();

    public ElementDeJeu(Vector2 pos) {
        this.setPosition(pos);
        this.setBounds(pos.x, pos.y, SIZE, SIZE);
    }

    public ElementDeJeu(Vector2 pos, float size) {
        this.setPosition(pos);
        this.setBounds(pos.x, pos.y, size, size);
    }

    public abstract void update(float delta);


    public static float getSize() {
        return SIZE;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(float x, float y, float width, float height) {
        this.bounds.x = x;
        this.bounds.y = y;
        this.bounds.width = width;
        this.bounds.height = height;
    }

}
