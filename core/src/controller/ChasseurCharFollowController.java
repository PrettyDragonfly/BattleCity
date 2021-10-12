package controller;

import javax.lang.model.util.ElementScanner6;

import com.badlogic.gdx.math.Rectangle;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.ElementDeJeuDynamique;
import modele.leselementsdejeu.ElementDeJeuStatique;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.ChasseurChar;
import modele.leselementsdejeu.leschars.Char.Direction;
import modele.leselementsdejeu.leschars.Char.State;
import modele.leselementsdejeu.leschars.charennemi.ChasseurCharFollow;

public class ChasseurCharFollowController {
    private Terrain world;
    
    public ChasseurCharFollowController(Terrain world) {
        this.world = world;
    }

    public void dirGauche(ChasseurCharFollow charFollow) {
        charFollow.setDirection(Direction.GAUCHE);   
        charFollow.setState(State.ROULE);

        charFollow.getVelocity().x = -ChasseurChar.SPEED;
        charFollow.getVelocity().y = 0;

        float y = charFollow.getPosition().y * 2;
        y = Math.round(y);
        charFollow.getPosition().y = y / 2;
    }

    public void dirDroite(ChasseurCharFollow charFollow) {
        charFollow.setDirection(Direction.DROITE);   
        charFollow.setState(State.ROULE);
        
        charFollow.getVelocity().x = ChasseurChar.SPEED;
        charFollow.getVelocity().y = 0;

        float y = charFollow.getPosition().y * 2;
        y = Math.round(y);
        charFollow.getPosition().y = y / 2;
    }

    public void dirBas(ChasseurCharFollow charFollow) {
        charFollow.setDirection(Direction.BAS);   
        charFollow.setState(State.ROULE);
        
        charFollow.getVelocity().x = 0;
        charFollow.getVelocity().y = -ChasseurChar.SPEED;

        float x = charFollow.getPosition().x * 2;
        x = Math.round(x);
        charFollow.getPosition().x = x / 2;
    }

    public void dirHaut(ChasseurCharFollow charFollow) {
        charFollow.setDirection(Direction.HAUT);   
        charFollow.setState(State.ROULE);
        
        charFollow.getVelocity().x = 0;
        charFollow.getVelocity().y = ChasseurChar.SPEED;

        float x = charFollow.getPosition().x * 2;
        x = Math.round(x);
        charFollow.getPosition().x = x / 2;
    }

    public void update(float delta, ChasseurCharFollow charFollow) {
        
        if(charFollow.getPosition().x < 0) {
            charFollow.getPosition().x = 0;
            charFollow.setDirection(Direction.HAUT);
        } 
        else if(charFollow.getPosition().x > 27 - Char.SIZE) {
            charFollow.setDirection(Direction.BAS);
            charFollow.getPosition().x = 27 - Char.SIZE;
            
        } 
        else if(charFollow.getPosition().y < 0) {
            charFollow.getPosition().y = 0;
            charFollow.setDirection(Direction.GAUCHE);
        }
        else if(charFollow.getPosition().y > 27 - Char.SIZE) {
            charFollow.getPosition().y = 27 - Char.SIZE;
            charFollow.setDirection(Direction.GAUCHE);
        }

        if(charFollow.getPosition().x < world.getCharJoueur().getPosition().x - 0.5f) {
            //System.out.println("char follow a gauche du char joueur");
            if(charFollow.getGameElementsDroite().notEmpty()) {
                switch(charFollow.getDirection()) {
                case BAS:
                    if(charFollow.getGameElementsBas().notEmpty())
                        dirHaut(charFollow);
                    else     
                        dirBas(charFollow);
                    break;
                case DROITE:
                    if(charFollow.getGameElementsDroite().notEmpty())
                        dirBas(charFollow);
                    else      
                        dirDroite(charFollow);
                    break;
                case GAUCHE:
                    if(charFollow.getGameElementsGauche().notEmpty())
                        dirDroite(charFollow);
                    else      
                        dirGauche(charFollow);
                    break;
                case HAUT:
                    if(charFollow.getGameElementsHaut().notEmpty())
                        dirGauche(charFollow);
                    else    
                        dirHaut(charFollow);
                    break;
                    
                }
            }
            else
                dirDroite(charFollow);
        } 
        else if(charFollow.getPosition().x > world.getCharJoueur().getPosition().x + 0.5f) {
            //System.out.println("char follow a droite du char joueur");
            if(charFollow.getGameElementsGauche().notEmpty()) {
                switch(charFollow.getDirection()) {
                case BAS:
                    if(charFollow.getGameElementsBas().notEmpty())
                        dirHaut(charFollow);
                    else    
                        dirBas(charFollow);
                    break;
                case DROITE:
                    if(charFollow.getGameElementsDroite().notEmpty())
                        dirGauche(charFollow);
                    else 
                        dirDroite(charFollow);
                    break;
                case GAUCHE:
                    if(charFollow.getGameElementsGauche().notEmpty())
                        dirBas(charFollow);
                    break;
                case HAUT:
                    if(charFollow.getGameElementsHaut().notEmpty())
                        dirDroite(charFollow);
                    else 
                    dirHaut(charFollow);
                    break;
                    
                }
            }
            else
                dirGauche(charFollow);
        }
        else if(charFollow.getPosition().y < world.getCharJoueur().getPosition().y - 0.5f) {
            //System.out.println("char follow en-dessous du char joueur");
            if(charFollow.getGameElementsHaut().notEmpty()) {
                switch(charFollow.getDirection()) {
                case BAS:
                    if(charFollow.getGameElementsBas().notEmpty())
                        dirHaut(charFollow);
                    else      
                        dirBas(charFollow);
                    break;
                case DROITE:
                    if(charFollow.getGameElementsDroite().notEmpty())
                        dirBas(charFollow);
                    else        
                        dirDroite(charFollow);
                    break;
                case GAUCHE:
                    if(charFollow.getGameElementsGauche().notEmpty())
                        dirDroite(charFollow);
                    else        
                        dirGauche(charFollow);
                    break;
                case HAUT:
                    if(charFollow.getGameElementsHaut().notEmpty())
                        dirGauche(charFollow);
                    break;
                    
                }
            }
            else
                dirHaut(charFollow);

        }
        else if(charFollow.getPosition().y > world.getCharJoueur().getPosition().y + 0.5f) {
            //System.out.println("char follow au-dessus du char joueur");
            if(charFollow.getGameElementsBas().notEmpty()) {                //Comme après avoir changer de direction il est plus overlaps, sa direction redevient directement BAS
                switch(charFollow.getDirection()) {                         // -> boucle infinie de changement de direction
                case BAS:
                    if(charFollow.getGameElementsBas().notEmpty())
                        dirGauche(charFollow);
                    break;
                case DROITE:
                    if(charFollow.getGameElementsDroite().notEmpty())
                        dirHaut(charFollow);
                    else      
                        dirDroite(charFollow);
                    break;
                case GAUCHE:
                    if(charFollow.getGameElementsGauche().notEmpty())
                        dirDroite(charFollow);
                    else     
                        dirGauche(charFollow);
                    break;
                case HAUT:
                    if(charFollow.getGameElementsHaut().notEmpty())
                        dirBas(charFollow);
                    else     
                        dirHaut(charFollow);
                    break;
                    
                }
            }
            else {
                dirBas(charFollow);
            }
                
                //System.out.println(charFollow.getDirection());
            
            
            //System.out.println("charfollow pos : " + charFollow.getPosition());


        }

        
            //System.out.println(charFollow.getDirection());
        





        /*if(charFollow.getPosition().x < world.getCharJoueur().getPosition().x - 0.2) {
            charFollow.setDirection(Direction.DROITE);
            System.out.println("gauche");
        } 
        else if(charFollow.getPosition().x > world.getCharJoueur().getPosition().x + 0.2) {
            charFollow.setDirection(Direction.GAUCHE);
            System.out.println("droite");
        }
        else if(charFollow.getPosition().y < world.getCharJoueur().getPosition().y - Char.SIZE) {
            charFollow.setDirection(Direction.HAUT);
            System.out.println("bas");
        }
        else if(charFollow.getPosition().y > world.getCharJoueur().getPosition().y + Char.SIZE) {
            charFollow.setDirection(Direction.BAS);
            System.out.println("haut");

        }*/

            
    } 
    
}
