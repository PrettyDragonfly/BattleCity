package controller;

import com.badlogic.gdx.math.Vector2;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.CharJoueur;
import modele.leselementsdejeu.leschars.Char.Direction;
import modele.leselementsdejeu.leschars.Char.State;
import modele.lesprojectiles.Projectile;
import modele.lesprojectiles.TirNormal;


                                                        

public class WorldController {
    
    private Terrain world;
    private CharJoueur charJoueur;
    
    
    public WorldController(Terrain world) {
        this.world = world;
        this.charJoueur = world.getCharJoueur();
    }
    
    /** The main update method **/
    public void update(float delta) {

    }

    public void spacePressed() {
        TirNormal t;
        if(charJoueur.getBulletTires().size < Char.BULLETMAX) {
            switch(charJoueur.getDirection()) {
                case GAUCHE : 
                    t = new TirNormal(new Vector2(charJoueur.getPosition().x, charJoueur.getPosition().y + Char.SIZE/2 - Projectile.SIZE/2), 
                                                modele.lesprojectiles.Projectile.Direction.GAUCHE, Projectile.SIZE);
                    t.getVelocity().x = -Projectile.SPEED;
                    charJoueur.getBulletTires().add(t);
                    break;
                case BAS:
                    t = new TirNormal(new Vector2(charJoueur.getPosition().x + Char.SIZE/2 - Projectile.SIZE/2, charJoueur.getPosition().y), 
                                                modele.lesprojectiles.Projectile.Direction.BAS, Projectile.SIZE);
                    t.getVelocity().y = -Projectile.SPEED;
                    charJoueur.getBulletTires().add(t);
                    break;
                case DROITE:
                    t = new TirNormal(new Vector2(charJoueur.getPosition().x + Char.SIZE, charJoueur.getPosition().y + Char.SIZE/2 - Projectile.SIZE/2), 
                                                modele.lesprojectiles.Projectile.Direction.DROITE, Projectile.SIZE);
                    t.getVelocity().x = Projectile.SPEED;
                    charJoueur.getBulletTires().add(t);
                    break;
                case HAUT:
                    t = new TirNormal(new Vector2(charJoueur.getPosition().x + Char.SIZE/2 - Projectile.SIZE/2, charJoueur.getPosition().y + Char.SIZE), 
                                                modele.lesprojectiles.Projectile.Direction.HAUT, Projectile.SIZE);
                    t.getVelocity().y = Projectile.SPEED;
                    charJoueur.getBulletTires().add(t);
                    break;

            }
            
        }
    }

    public void LeftPressed() {
        charJoueur.setDirection(Direction.GAUCHE);   
        charJoueur.setState(State.ROULE);
        charJoueur.getVelocity().x = -Char.SPEED;
        charJoueur.getVelocity().y = 0;

        float y = charJoueur.getPosition().y * 2;
        y = Math.round(y);
        charJoueur.getPosition().y = y / 2;
    }

    public void DownPressed() {
        charJoueur.setDirection(Direction.BAS);
        charJoueur.setState(State.ROULE);
        charJoueur.getVelocity().y = -Char.SPEED;
        charJoueur.getVelocity().x = 0;

        float x = charJoueur.getPosition().x * 2;
        x = Math.round(x);
        charJoueur.getPosition().x = x / 2;
    }

    public void RightPressed() {
        charJoueur.setDirection(Direction.DROITE);
        charJoueur.setState(State.ROULE);

        charJoueur.getVelocity().x = Char.SPEED;
        charJoueur.getVelocity().y = 0;


        float y = charJoueur.getPosition().y * 2;
        y = Math.round(y);
        charJoueur.getPosition().y = y / 2;
    }

    public void UpPressed() {
        charJoueur.setDirection(Direction.HAUT);
        charJoueur.setState(State.ROULE);
        charJoueur.getVelocity().y = Char.SPEED;
        charJoueur.getVelocity().x = 0;

        float x = charJoueur.getPosition().x * 2;
        x = Math.round(x);
        charJoueur.getPosition().x = x / 2;
    }

    public void noPressed() {
        charJoueur.setState(State.ARRET);

        charJoueur.getVelocity().x = 0;
        charJoueur.getVelocity().y = 0;

        float x = charJoueur.getPosition().x * 2;
        x = Math.round(x);
        charJoueur.getPosition().x = x / 2;

        float y = charJoueur.getPosition().y * 2;
        y = Math.round(y);
        charJoueur.getPosition().y = y / 2;
    }
}