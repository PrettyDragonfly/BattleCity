package controller;

import modele.lemonde.Terrain;

import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.charennemi.ChasseurDrapeau;
import modele.leselementsdejeu.leschars.Char.Direction;
import modele.leselementsdejeu.leschars.Char.State;

public class ChasseurDrapeauController {
    private Terrain world;

    private boolean test = true;
    
    public ChasseurDrapeauController(Terrain world) {
        this.world = world;
    }

    public void dirGauche(ChasseurDrapeau charDrapeau) {
        charDrapeau.setDirection(Direction.GAUCHE);   
        charDrapeau.setState(State.ROULE);

        charDrapeau.getVelocity().x = -ChasseurDrapeau.SPEED;
        charDrapeau.getVelocity().y = 0;

        float y = charDrapeau.getPosition().y * 2;
        y = Math.round(y);
        charDrapeau.getPosition().y = y / 2;
    }

    public void dirDroite(ChasseurDrapeau charDrapeau) {
        charDrapeau.setDirection(Direction.DROITE);   
        charDrapeau.setState(State.ROULE);
        
        charDrapeau.getVelocity().x = ChasseurDrapeau.SPEED;
        charDrapeau.getVelocity().y = 0;

        float y = charDrapeau.getPosition().y * 2;
        y = Math.round(y);
        charDrapeau.getPosition().y = y / 2;
    }

    public void dirBas(ChasseurDrapeau charDrapeau) {
        charDrapeau.setDirection(Direction.BAS);   
        charDrapeau.setState(State.ROULE);
        
        charDrapeau.getVelocity().x = 0;
        charDrapeau.getVelocity().y = -ChasseurDrapeau.SPEED;

        float x = charDrapeau.getPosition().x * 2;
        x = Math.round(x);
        charDrapeau.getPosition().x = x / 2;
    }

    public void dirHaut(ChasseurDrapeau charDrapeau) {
        charDrapeau.setDirection(Direction.HAUT);   
        charDrapeau.setState(State.ROULE);
        
        charDrapeau.getVelocity().x = 0;
        charDrapeau.getVelocity().y = ChasseurDrapeau.SPEED;

        float x = charDrapeau.getPosition().x * 2;
        x = Math.round(x);
        charDrapeau.getPosition().x = x / 2;
    }

    public void update(float delta, ChasseurDrapeau charDrapeau) {
        
        if(charDrapeau.getPosition().x < 0) {
            charDrapeau.getPosition().x = 0;
            charDrapeau.setDirection(Direction.HAUT);
        } 
        else if(charDrapeau.getPosition().x > 27 - Char.SIZE) {
            charDrapeau.setDirection(Direction.BAS);
            charDrapeau.getPosition().x = 27 - Char.SIZE;
            
        } 
        else if(charDrapeau.getPosition().y < 0) {
            charDrapeau.getPosition().y = 0;
            charDrapeau.setDirection(Direction.GAUCHE);
        }
        else if(charDrapeau.getPosition().y > 27 - Char.SIZE) {
            charDrapeau.getPosition().y = 27 - Char.SIZE;
            charDrapeau.setDirection(Direction.GAUCHE);
        }

        if(charDrapeau.getPosition().x < Terrain.drapeau.getPosition().x - 3f && test) {
            //System.out.println("chasseur Drapeau a gauche du drapeau");
            if(charDrapeau.getGameElementsDroite().notEmpty()) {
                switch(charDrapeau.getDirection()) {
                case BAS:
                    if(charDrapeau.getGameElementsBas().notEmpty())
                        dirHaut(charDrapeau);
                    else     
                        dirBas(charDrapeau);
                    break;
                case DROITE:
                    if(charDrapeau.getGameElementsDroite().notEmpty())
                        dirBas(charDrapeau);
                    else      
                        dirDroite(charDrapeau);
                    break;
                case GAUCHE:
                    if(charDrapeau.getGameElementsGauche().notEmpty())
                        dirDroite(charDrapeau);
                    else      
                        dirGauche(charDrapeau);
                    break;
                case HAUT:
                    if(charDrapeau.getGameElementsHaut().notEmpty())
                        dirGauche(charDrapeau);
                    else    
                        dirHaut(charDrapeau);
                    break;
                    
                }
            }
            else
                dirDroite(charDrapeau);
        } 
        else if(charDrapeau.getPosition().x > Terrain.drapeau.getPosition().x + 3f && test) {
            //System.out.println("chasseur Drapeau a droite du du drapeau");
            if(charDrapeau.getGameElementsGauche().notEmpty()) {
                switch(charDrapeau.getDirection()) {
                case BAS:
                    if(charDrapeau.getGameElementsBas().notEmpty())
                        dirHaut(charDrapeau);
                    else    
                        dirBas(charDrapeau);
                    break;
                case DROITE:
                    if(charDrapeau.getGameElementsDroite().notEmpty())
                        dirGauche(charDrapeau);
                    else 
                        dirDroite(charDrapeau);
                    break;
                case GAUCHE:
                    if(charDrapeau.getGameElementsGauche().notEmpty())
                        dirBas(charDrapeau);
                    break;
                case HAUT:
                    if(charDrapeau.getGameElementsHaut().notEmpty())
                        dirDroite(charDrapeau);
                    else 
                    dirHaut(charDrapeau);
                    break;
                    
                }
            }
            else
                dirGauche(charDrapeau);
        }
        else if(charDrapeau.getPosition().y < Terrain.drapeau.getPosition().y - 0.5f) {
            test = false;
            //System.out.println("chasseur Drapeau en-dessous du drapeau");
            if(charDrapeau.getGameElementsHaut().notEmpty()) {
                switch(charDrapeau.getDirection()) {
                case BAS:
                    if(charDrapeau.getGameElementsBas().notEmpty())
                        dirHaut(charDrapeau);
                    else      
                        dirBas(charDrapeau);
                    break;
                case DROITE:
                    if(charDrapeau.getGameElementsDroite().notEmpty())
                        dirBas(charDrapeau);
                    else        
                        dirDroite(charDrapeau);
                    break;
                case GAUCHE:
                    if(charDrapeau.getGameElementsGauche().notEmpty())
                        dirDroite(charDrapeau);
                    else        
                        dirGauche(charDrapeau);
                    break;
                case HAUT:
                    if(charDrapeau.getGameElementsHaut().notEmpty())
                        dirGauche(charDrapeau);
                    break;
                    
                }
            }
            else {
                test = true;
                dirHaut(charDrapeau);
            }
                

        }
        else if(charDrapeau.getPosition().y > Terrain.drapeau.getPosition().y + 0.5f) {
            test = false;
           // System.out.println("chasseur Drapeau au-dessus du drapeau");
            if(charDrapeau.getGameElementsBas().notEmpty()) {                //Comme après avoir changer de direction il est plus overlaps, sa direction redevient directement BAS
                switch(charDrapeau.getDirection()) {                         // -> boucle infinie de changement de direction
                case BAS:
                    if(charDrapeau.getGameElementsBas().notEmpty())
                        dirGauche(charDrapeau);
                    break;
                case DROITE:
                    if(charDrapeau.getGameElementsDroite().notEmpty())
                        dirHaut(charDrapeau);
                    else      
                        dirDroite(charDrapeau);
                    break;
                case GAUCHE:
                    if(charDrapeau.getGameElementsGauche().notEmpty())
                        dirDroite(charDrapeau);
                    else     
                        dirGauche(charDrapeau);
                    break;
                case HAUT:
                    if(charDrapeau.getGameElementsHaut().notEmpty())
                        dirBas(charDrapeau);
                    else     
                        dirHaut(charDrapeau);
                    break;
                    
                }
            }
            else {
                test = true;
                dirBas(charDrapeau);
            }
                
                //System.out.println(charDrapeau.getDirection());
            
            
            //System.out.println("charDrapeau pos : " + charDrapeau.getPosition());


        }

        
            //System.out.println(charDrapeau.getDirection());
        





        /*if(charDrapeau.getPosition().x < world.getCharJoueur().getPosition().x - 0.2) {
            charDrapeau.setDirection(Direction.DROITE);
            System.out.println("gauche");
        } 
        else if(charDrapeau.getPosition().x > world.getCharJoueur().getPosition().x + 0.2) {
            charDrapeau.setDirection(Direction.GAUCHE);
            System.out.println("droite");
        }
        else if(charDrapeau.getPosition().y < world.getCharJoueur().getPosition().y - Char.SIZE) {
            charDrapeau.setDirection(Direction.HAUT);
            System.out.println("bas");
        }
        else if(charDrapeau.getPosition().y > world.getCharJoueur().getPosition().y + Char.SIZE) {
            charDrapeau.setDirection(Direction.BAS);
            System.out.println("haut");

        }*/

            
    }

    public Terrain getWorld() {
        return world;
    }

    public void setWorld(Terrain world) {
        this.world = world;
    } 
    
}
