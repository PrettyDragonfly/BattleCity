package eventsbullet;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.ElementDeJeuDynamique;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.CharEnnemi;
import modele.leselementsdejeu.leschars.CharJoueur;
import modele.lesprojectiles.Projectile;

public class CollisionBulletCORChar extends CollisionBulletCOR{

    public CollisionBulletCORChar(CollisionBulletCOR s) {
        super(s);
    }

    @Override
    protected boolean CollisionBulletCORContre(Terrain w, Projectile p, ElementDeJeu ele, Char c) {
        if(ele instanceof Char) {
            if(ele instanceof CharJoueur && c instanceof CharEnnemi) {
                if(p.getBounds().overlaps(ele.getBounds())) {
                    c.getBulletTires().removeValue(p, true);
                    ((CharJoueur)(ele)).setVie(((CharJoueur)(ele)).getVie()-1);
                }
            }
            else if(ele instanceof CharEnnemi && c instanceof CharJoueur) {
                if(p.getBounds().overlaps(ele.getBounds())) {
                    c.getBulletTires().removeValue(p, true);
                    w.getAllGameElement().removeValue(ele, true);
                    w.getChars().removeValue((Char)ele, true);
                    w.getGameElementDynamic().removeValue((ElementDeJeuDynamique)ele, true);
                    w.getCharsEnnemis().removeValue((Char)ele, true);
                }
            }



            return true;
        }
 
        
        return false;
    }
    
}
