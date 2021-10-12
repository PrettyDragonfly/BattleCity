package eventsbullet;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.lescases.MurIncassable;
import modele.leselementsdejeu.leschars.Char;
import modele.lesprojectiles.Projectile;

public class CollisionBulletCORMurIncassable extends CollisionBulletCOR {

    public CollisionBulletCORMurIncassable(CollisionBulletCOR s) {
        super(s);
    }

    @Override
    protected boolean CollisionBulletCORContre(Terrain w, Projectile p, ElementDeJeu ele, Char c) {
        if(ele instanceof MurIncassable) {
            if(p.getBounds().overlaps(ele.getBounds())) {
                c.getBulletTires().removeValue(p, true);
            }
            return true;
        }
 
        
        return false;
    }
}
