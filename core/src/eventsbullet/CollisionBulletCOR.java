package eventsbullet;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.leschars.Char;
import modele.lesprojectiles.Projectile;

public abstract class CollisionBulletCOR implements CollisionBullet {

    private CollisionBulletCOR suivant;

    /**
     * Chaine de responsabilite
     * @param s
     */
    public CollisionBulletCOR(CollisionBulletCOR s) {
        this.suivant = s;
    }

    protected abstract boolean CollisionBulletCORContre(Terrain w, Projectile p, ElementDeJeu ele, Char c);

    @Override
    public boolean collisionDuProjectile(Terrain w, Projectile p, ElementDeJeu ele, Char c) {
        if(!(this.CollisionBulletCORContre(w, p, ele, c))) {
            if(suivant != null) {
                return suivant.collisionDuProjectile(w, p, ele, c);
            }
            else
                return false;
        }
        else
            return true;
    }

    /**
     * initialisation de la chaine
     * @return
     */
    public static CollisionBulletCOR initCOR() {

        CollisionBulletCORMurBrique murBrique = new CollisionBulletCORMurBrique(null);
        CollisionBulletCORMurRenforce murRenforce = new CollisionBulletCORMurRenforce(murBrique);
        CollisionBulletCORMurIncassable murIncassable = new CollisionBulletCORMurIncassable(murRenforce);
        CollisionBulletCORDrapeau drapeau = new CollisionBulletCORDrapeau(murIncassable);
        CollisionBulletCORChar chars = new CollisionBulletCORChar(drapeau);
        
        return chars;

    }

    
}
