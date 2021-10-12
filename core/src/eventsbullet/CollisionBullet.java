package eventsbullet;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.leschars.Char;
import modele.lesprojectiles.*;

public interface CollisionBullet {

    public boolean collisionDuProjectile(Terrain w, Projectile p, ElementDeJeu ele, Char c);
}
