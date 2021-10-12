package eventsbullet;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.ElementDeJeuDynamique;
import modele.leselementsdejeu.lescases.MurBrique;
import modele.leselementsdejeu.leschars.Char;
import modele.lesprojectiles.Projectile;

public class CollisionBulletCORMurBrique extends CollisionBulletCOR{

    public CollisionBulletCORMurBrique(CollisionBulletCOR s) {
        super(s);
    }

    @Override
    protected boolean CollisionBulletCORContre(Terrain w, Projectile p, ElementDeJeu ele, Char c) {

        if((p.getPosition().x <= 0 || p.getPosition().x >= 28 - Projectile.SIZE) ||
                        (p.getPosition().y <= 0 || p.getPosition().y >= 28 - Projectile.SIZE)) {
            
            c.getBulletTires().removeValue(p, true);
            return true;
        }

        if(ele instanceof MurBrique) {
            if(p.getBounds().overlaps(ele.getBounds())) {
                c.getBulletTires().removeValue(p, true);
                w.getAllGameElement().removeValue(ele, true);
                w.getAllGameElementWithoutChars().removeValue(ele, true);
                w.getGameElementDynamic().removeValue((ElementDeJeuDynamique)ele, true);
            }
            return true;
        }
 
        
        return false;
    }
    
}
