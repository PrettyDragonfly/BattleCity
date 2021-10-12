package modele.leselementsdejeu.leschars.charennemi;

import com.badlogic.gdx.math.Vector2;

import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.ChasseurChar;
import modele.lesprojectiles.Projectile;

public class ChasseurCharFollow extends ChasseurChar{

    public ChasseurCharFollow(Vector2 pos) {
        super(pos);
    }

    @Override
    public void update(float delta) {
        getPosition().x = getPosition().x + getVelocity().x * delta;
        getPosition().y = getPosition().y + getVelocity().y * delta;  

        setBoundsBas(getPosition().x + 0.2f, getPosition().y - 0.15f, Char.SIZE - 0.4f, Char.SIZE - 1.4f);
        setBoundsDroite(getPosition().x + 1.55f, getPosition().y + 0.2f, Char.SIZE - 1.4f, Char.SIZE - 0.4f);
        setBoundsGauche(getPosition().x - 0.15f, getPosition().y + 0.2f, Char.SIZE - 1.4f, Char.SIZE - 0.4f);
        setBoundsHaut(getPosition().x + 0.2f, getPosition().y + 1.55f, Char.SIZE - 0.4f, Char.SIZE - 1.4f);

        /*if(getPosition().x <= 0) getPosition().x = 0;
        else if(getPosition().x >= 26 - Char.SIZE) getPosition().x = 26 - Char.SIZE;
        if(getPosition().y <= 0) getPosition().y = 0;
        else if(getPosition().y >= 26 - Char.SIZE) getPosition().y = 26 - Char.SIZE;*/

        //getBounds().x = getPosition().x; 
        //getBounds().y = getPosition().y; 

        for (Projectile projectile : getBulletTires()) {
            projectile.update(delta);
        }
        
    }
    
}
