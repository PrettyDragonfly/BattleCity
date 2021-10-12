package modele.leselementsdejeu.leschars;

import com.badlogic.gdx.math.Vector2;

import modele.lesprojectiles.Projectile;

public class CharJoueur extends CharAllie {

    private int vie = 3;
    
    public CharJoueur(Vector2 pos) {
        super(pos);
       }

    @Override
    public void update(float delta) {
        if(this.getState().equals(State.ROULE)) {
            getPosition().x = getPosition().x + velocity.x * delta;
            getPosition().y = getPosition().y + velocity.y * delta;  
    
        }

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

        //System.out.println("pos : " + getPosition());
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

}
