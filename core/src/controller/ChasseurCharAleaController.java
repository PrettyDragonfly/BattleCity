package controller;

import javax.lang.model.util.ElementScanner6;

import modele.lemonde.Terrain;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.ElementDeJeuDynamique;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.Char.Direction;
import modele.leselementsdejeu.leschars.Char.State;
import modele.leselementsdejeu.leschars.charennemi.ChasseurCharAlea;

public class ChasseurCharAleaController {
    private Terrain world;

    private boolean changeDir = false;
    
    public ChasseurCharAleaController(Terrain world) {
        this.world = world;

    }

    public void dirGauche(ChasseurCharAlea charAlea) {
        charAlea.setDirection(Direction.GAUCHE);   
        charAlea.setState(State.ROULE);

        charAlea.getVelocity().x = -Char.SPEED;
        charAlea.getVelocity().y = 0;

        /*float y = charAlea.getPosition().y * 2;
        y = Math.round(y);
        charAlea.getPosition().y = y / 2;*/
    }

    public void dirDroite(ChasseurCharAlea charAlea) {
        charAlea.setDirection(Direction.DROITE);   
        charAlea.setState(State.ROULE);
        
        charAlea.getVelocity().x = Char.SPEED;
        charAlea.getVelocity().y = 0;

        /*float y = charAlea.getPosition().y * 2;
        y = Math.round(y);
        charAlea.getPosition().y = y / 2;*/
    }

    public void dirBas(ChasseurCharAlea charAlea) {
        charAlea.setDirection(Direction.BAS);   
        charAlea.setState(State.ROULE);
        
        charAlea.getVelocity().x = 0;
        charAlea.getVelocity().y = -Char.SPEED;

        /*float x = charAlea.getPosition().x * 2;
        x = Math.round(x);
        charAlea.getPosition().x = x / 2;*/
    }

    public void dirHaut(ChasseurCharAlea charAlea) {
        charAlea.setDirection(Direction.HAUT);   
        charAlea.setState(State.ROULE);
        
        charAlea.getVelocity().x = 0;
        charAlea.getVelocity().y = Char.SPEED;

        /*float x = charAlea.getPosition().x * 2;
        x = Math.round(x);
        charAlea.getPosition().y = x / 2;*/
    }


    public void update(float delta, ChasseurCharAlea charAlea) {

        if(charAlea.getGameElementsBas().isEmpty() &&
            charAlea.getGameElementsDroite().isEmpty() &&
            charAlea.getGameElementsGauche().isEmpty() &&
            charAlea.getGameElementsHaut().isEmpty()) {
                if(changeDir == false) {
                    changeDir = true;
                    int nombreAleatoire = 0 + (int)(Math.random() * ((3 - 0) + 1));
                    if(nombreAleatoire == 0)
                        charAlea.setDirection(Direction.GAUCHE);
                    else if(nombreAleatoire == 1)
                        charAlea.setDirection(Direction.HAUT);
                    else if(nombreAleatoire == 2)
                        charAlea.setDirection(Direction.DROITE);
                    else if(nombreAleatoire == 3)
                        charAlea.setDirection(Direction.BAS);
                }

        }
        else {
            switch(charAlea.getDirection()) {
                case BAS:
                    if(charAlea.getGameElementsBas().notEmpty()) {
                        int nombreAleatoire = 0 + (int)(Math.random() * ((2 - 0) + 1));
                        if(nombreAleatoire == 0)
                            charAlea.setDirection(Direction.GAUCHE);
                        else if(nombreAleatoire == 1)
                            charAlea.setDirection(Direction.HAUT);
                        else if(nombreAleatoire == 2)
                            charAlea.setDirection(Direction.DROITE);
                        changeDir = false;
                    }
                    else {
                        if(charAlea.getGameElementsGauche().isEmpty() && charAlea.getGameElementsDroite().isEmpty() && !changeDir) {
                            int nombreAleatoire = 0 + (int)(Math.random() * ((1 - 0) + 1));
                            if(nombreAleatoire == 0)
                                charAlea.setDirection(Direction.GAUCHE);
                            else if(nombreAleatoire == 1)
                                charAlea.setDirection(Direction.DROITE);
                        }
                        else if(charAlea.getGameElementsGauche().isEmpty() && !changeDir) {
                            charAlea.setDirection(Direction.GAUCHE);
                            changeDir = true;
                        }
                            
                        else if(charAlea.getGameElementsDroite().isEmpty() && !changeDir) {
                            charAlea.setDirection(Direction.DROITE);
                            changeDir = true;
                        }
                            
                        if(charAlea.getGameElementsGauche().notEmpty() && charAlea.getGameElementsDroite().notEmpty()) 
                            changeDir = false;
                    }

                    break;
                case DROITE:
                    if(charAlea.getGameElementsDroite().notEmpty()) {
                        int nombreAleatoire = 0 + (int)(Math.random() * ((2 - 0) + 1));
                        if(nombreAleatoire == 0)
                            charAlea.setDirection(Direction.GAUCHE);
                        else if(nombreAleatoire == 1)
                            charAlea.setDirection(Direction.HAUT);
                        else if(nombreAleatoire == 2)
                            charAlea.setDirection(Direction.BAS);
                        changeDir = false;
                    }
                    else {
                        if(charAlea.getGameElementsBas().isEmpty() && charAlea.getGameElementsHaut().isEmpty() && !changeDir) {
                            int nombreAleatoire = 0 + (int)(Math.random() * ((1 - 0) + 1));
                            if(nombreAleatoire == 0)
                                charAlea.setDirection(Direction.BAS);
                            else if(nombreAleatoire == 1)
                                charAlea.setDirection(Direction.HAUT);
                        }
                        else if(charAlea.getGameElementsBas().isEmpty() && !changeDir) {
                            charAlea.setDirection(Direction.BAS);
                            changeDir = true;
                        }
                            
                        else if(charAlea.getGameElementsHaut().isEmpty() && !changeDir) {
                            charAlea.setDirection(Direction.HAUT);
                            changeDir = true;
                        }
                            
                        if(charAlea.getGameElementsHaut().notEmpty() && charAlea.getGameElementsBas().notEmpty()) 
                            changeDir = false;
                    }
                    break;
                case GAUCHE:
                    if(charAlea.getGameElementsGauche().notEmpty()) {
                        int nombreAleatoire = 0 + (int)(Math.random() * ((2 - 0) + 1));
                        if(nombreAleatoire == 0)
                            charAlea.setDirection(Direction.BAS);
                        else if(nombreAleatoire == 1)
                            charAlea.setDirection(Direction.HAUT);
                        else if(nombreAleatoire == 2)
                            charAlea.setDirection(Direction.DROITE);
                        changeDir = false;
                    }
                    else {
                        if(charAlea.getGameElementsBas().isEmpty() && charAlea.getGameElementsHaut().isEmpty() && !changeDir) {
                            int nombreAleatoire = 0 + (int)(Math.random() * ((1 - 0) + 1));
                            if(nombreAleatoire == 0)
                                charAlea.setDirection(Direction.BAS);
                            else if(nombreAleatoire == 1)
                                charAlea.setDirection(Direction.HAUT);
                        }
                        else if(charAlea.getGameElementsBas().isEmpty() && !changeDir) {
                            charAlea.setDirection(Direction.BAS);
                            changeDir = true;
                        }
                            
                        else if(charAlea.getGameElementsHaut().isEmpty() && !changeDir) {
                            charAlea.setDirection(Direction.HAUT);
                            changeDir = true;
                        }
                            
                        if(charAlea.getGameElementsHaut().notEmpty() && charAlea.getGameElementsBas().notEmpty()) 
                            changeDir = false;
                    }

                    break;
                case HAUT:
                    if(charAlea.getGameElementsHaut().notEmpty()) {
                        int nombreAleatoire = 0 + (int)(Math.random() * ((2 - 0) + 1));
                        if(nombreAleatoire == 0)
                            charAlea.setDirection(Direction.GAUCHE);
                        else if(nombreAleatoire == 1)
                            charAlea.setDirection(Direction.BAS);
                        else if(nombreAleatoire == 2)
                            charAlea.setDirection(Direction.DROITE);
                        changeDir = false;
                    }
                    else {
                        if(charAlea.getGameElementsGauche().isEmpty() && charAlea.getGameElementsDroite().isEmpty() && !changeDir) {
                            int nombreAleatoire = 0 + (int)(Math.random() * ((1 - 0) + 1));
                            if(nombreAleatoire == 0)
                                charAlea.setDirection(Direction.GAUCHE);
                            else if(nombreAleatoire == 1)
                                charAlea.setDirection(Direction.DROITE);
                        }
                        else if(charAlea.getGameElementsGauche().isEmpty() && !changeDir) {
                            charAlea.setDirection(Direction.GAUCHE);
                            changeDir = true;
                        }
                            
                        else if(charAlea.getGameElementsDroite().isEmpty() && !changeDir) {
                            charAlea.setDirection(Direction.DROITE);
                            changeDir = true;
                        }
                            
                        if(charAlea.getGameElementsGauche().notEmpty() && charAlea.getGameElementsDroite().notEmpty()) 
                            changeDir = false;
                    }
                    break;
                    
            }
        }

        switch(charAlea.getDirection()) {
            case BAS:
                dirBas(charAlea);
                break;
            case DROITE:
                dirDroite(charAlea);
                break;
            case GAUCHE:
                dirGauche(charAlea);
                break;
            case HAUT:
                dirHaut(charAlea);
                break;
                
        }

    }

    public Terrain getWorld() {
        return world;
    }

    public void setWorld(Terrain world) {
        this.world = world;
    }

}
