package eventsbullet;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.lescases.Drapeau;
import modele.leselementsdejeu.leschars.Char;
import modele.lesprojectiles.Projectile;

public class CollisionBulletCORDrapeau extends CollisionBulletCOR{

    public CollisionBulletCORDrapeau(CollisionBulletCOR s) {
        super(s);
    }

    @Override
    protected boolean CollisionBulletCORContre(Terrain w, Projectile p, ElementDeJeu ele, Char c) {

        if((p.getPosition().x <= 0 || p.getPosition().x >= 28 - Projectile.SIZE) ||
                        (p.getPosition().y <= 0 || p.getPosition().y >= 28 - Projectile.SIZE)) {
            
            c.getBulletTires().removeValue(p, true);
            return true;
        }

        if(ele instanceof Drapeau) {
            if(p.getBounds().overlaps(ele.getBounds())) {
                c.getBulletTires().removeValue(p, true);
                ((Drapeau)(ele)).setAlive(false);
            }
            return true;
        }
 
        
        return false;
    }
    
}
