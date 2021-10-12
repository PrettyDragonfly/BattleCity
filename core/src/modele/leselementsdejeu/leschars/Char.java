package modele.leselementsdejeu.leschars;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.ElementDeJeuDynamique;
import modele.lesprojectiles.Projectile;

public abstract class Char extends ElementDeJeuDynamique implements Cloneable{


    public Char(Vector2 pos) {
        super(pos);
        setBounds(pos.x, pos.y, SIZE, SIZE);
        setBoundsBas(getPosition().x + 0.2f, getPosition().y - 0.15f, Char.SIZE - 0.4f, Char.SIZE - 1.4f);
        setBoundsDroite(getPosition().x + 1.55f, getPosition().y + 0.2f, Char.SIZE - 1.4f, Char.SIZE - 0.4f);
        setBoundsGauche(getPosition().x - 0.15f, getPosition().y + 0.2f, Char.SIZE - 1.4f, Char.SIZE - 0.4f);
        setBoundsHaut(getPosition().x + 0.2f, getPosition().y + 1.55f, Char.SIZE - 0.4f, Char.SIZE - 1.4f);
    }

    public static final float SIZE = 2f; // a unit
    public static final float SPEED = 5f; // unit per second

    public static final int BULLETMAX = 1; // unit per second

    private Rectangle boundsGauche = new Rectangle();
    private Rectangle boundsHaut = new Rectangle();
    private Rectangle boundsDroite = new Rectangle();
    private Rectangle boundsBas = new Rectangle();


    private Array<Projectile> bulletTires = new Array<Projectile>(BULLETMAX);

    private Array<ElementDeJeu> gameElementsGauche = new Array<ElementDeJeu>(4);
    private Array<ElementDeJeu> gameElementsDroite = new Array<ElementDeJeu>(8);
    private Array<ElementDeJeu> gameElementsHaut = new Array<ElementDeJeu>(8);
    private Array<ElementDeJeu> gameElementsBas = new Array<ElementDeJeu>(8);

    public enum Direction {
        GAUCHE, DROITE, HAUT, BAS
    }

    public enum State {
        ARRET, ROULE, MEURT
    }

    State  state = State.ARRET;

    private int vie;

    Vector2  acceleration = new Vector2();
    Vector2  velocity = new Vector2();

    private Direction direction = Direction.HAUT;
    
    
    private ArrayList<String> casesTraversables = new ArrayList<String>();

    public void update (float delta) {
        /*getPosition().x = getPosition().x + velocity.x * delta;
        getPosition().y = getPosition().y + velocity.y * delta;  

        if(getPosition().x <= 0) getPosition().x = 0;
        else if(getPosition().x >= 26 - Char.SIZE) getPosition().x = 26 - Char.SIZE;
        if(getPosition().y <= 0) getPosition().y = 0;
        else if(getPosition().y >= 26 - Char.SIZE) getPosition().y = 26 - Char.SIZE;

        getBounds().x = getPosition().x; 
        getBounds().y = getPosition().y; 

        for (Projectile projectile : bulletTires) {
            projectile.update(delta);
            if(projectile.getPosition().x <= 0) getBulletTires().removeValue(projectile, true);
            else if(projectile.getPosition().x >= 26 - Projectile.SIZE) getBulletTires().removeValue(projectile, true);
            if(projectile.getPosition().y <= 0) getBulletTires().removeValue(projectile, true);
            else if(projectile.getPosition().y >= 26 - Projectile.SIZE) getBulletTires().removeValue(projectile, true);
        }*/
        
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        return super.clone();
    }


    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public ArrayList<String> getCasesTraversables() {
        return casesTraversables;
    }

    public void addCasesTraversables(String casesTraversables) {
        this.casesTraversables.add(casesTraversables);
    }

    public void setCasesTraversables(ArrayList<String> casesTraversables) {
        this.casesTraversables = casesTraversables;
    }


    @Override
    public String toString() {
        return "Char [direction=" + direction + ", position vecteur : x = " + getPosition().x +
         ", y = " + getPosition().y + ", vie=" + vie + "]";
    }

    public static float getSize() {
        return SIZE;
    }

    public static float getSpeed() {
        return SPEED;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Vector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector2 acceleration) {
        this.acceleration = acceleration;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }


    public static int getBulletmax() {
        return BULLETMAX;
    }



    public Array<Projectile> getBulletTires() {
        return bulletTires;
    }



    public void setBulletTires(Array<Projectile> bulletTires) {
        this.bulletTires = bulletTires;
    }


    public void clearGameElementsVoisins() {
        this.gameElementsBas.clear();
        this.gameElementsDroite.clear();
        this.gameElementsGauche.clear();
        this.gameElementsHaut.clear();
    }

    public Rectangle getBoundsGauche() {
        return boundsGauche;
    }

    public void setBoundsGauche(float x, float y, float width, float height) {
        this.boundsGauche.x = x;
        this.boundsGauche.y = y;
        this.boundsGauche.width = width;
        this.boundsGauche.height = height;
    }

    public Rectangle getBoundsHaut() {
        return boundsHaut;
    }

    public void setBoundsHaut(float x, float y, float width, float height) {
        this.boundsHaut.x = x;
        this.boundsHaut.y = y;
        this.boundsHaut.width = width;
        this.boundsHaut.height = height;
    }

    public Rectangle getBoundsDroite() {
        return boundsDroite;
    }

    public void setBoundsDroite(float x, float y, float width, float height) {
        this.boundsDroite.x = x;
        this.boundsDroite.y = y;
        this.boundsDroite.width = width;
        this.boundsDroite.height = height;
    }

    public Rectangle getBoundsBas() {
        return boundsBas;
    }

    public void setBoundsBas(float x, float y, float width, float height) {
        this.boundsBas.x = x;
        this.boundsBas.y = y;
        this.boundsBas.width = width;
        this.boundsBas.height = height;
    }

    public Array<ElementDeJeu> getGameElementsGauche() {
        return gameElementsGauche;
    }

    public void setGameElementsGauche(Array<ElementDeJeu> gameElementsGauche) {
        this.gameElementsGauche = gameElementsGauche;
    }

    public Array<ElementDeJeu> getGameElementsDroite() {
        return gameElementsDroite;
    }

    public void setGameElementsDroite(Array<ElementDeJeu> gameElementsDroite) {
        this.gameElementsDroite = gameElementsDroite;
    }

    public Array<ElementDeJeu> getGameElementsHaut() {
        return gameElementsHaut;
    }

    public void setGameElementsHaut(Array<ElementDeJeu> gameElementsHaut) {
        this.gameElementsHaut = gameElementsHaut;
    }

    public Array<ElementDeJeu> getGameElementsBas() {
        return gameElementsBas;
    }

    public void setGameElementsBas(Array<ElementDeJeu> gameElementsBas) {
        this.gameElementsBas = gameElementsBas;
    }

}
