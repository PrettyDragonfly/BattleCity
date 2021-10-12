package modele.lemonde;
 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import eventsbullet.CollisionBulletCOR;
import eventschar.CollisionCharCOR;
import modele.leselementsdejeu.ElementDeJeu;
import modele.leselementsdejeu.ElementDeJeuDynamique;
import modele.leselementsdejeu.ElementDeJeuStatique;
import modele.leselementsdejeu.lescases.Arbre;
import modele.leselementsdejeu.lescases.Drapeau;
import modele.leselementsdejeu.lescases.MurBrique;
import modele.leselementsdejeu.lescases.MurIncassable;
import modele.leselementsdejeu.lescases.MurRenforce;
import modele.leselementsdejeu.leschars.Char;
import modele.leselementsdejeu.leschars.CharEnnemi;
import modele.leselementsdejeu.leschars.CharJoueur;
import modele.leselementsdejeu.leschars.charennemi.ChasseurDrapeau;
import modele.leselementsdejeu.leschars.charennemi.ChasseurCharAlea;
import modele.leselementsdejeu.leschars.charennemi.ChasseurCharFollow;
import modele.lesprojectiles.Projectile;
 
public class Terrain {
 
    /** The cases making up the world **/
    //Array<ElementDeJeu> gameElement = new Array<ElementDeJeu>();
    Array<ElementDeJeu> allGameElement = new Array<ElementDeJeu>();
    Array<ElementDeJeu> allGameElementWithoutChars = new Array<ElementDeJeu>();
    Array<ElementDeJeuStatique> gameElementStatic = new Array<ElementDeJeuStatique>();
    Array<ElementDeJeuDynamique> gameElementDynamic = new Array<ElementDeJeuDynamique>();

    /** Our player controlled hero **/
    CharJoueur charJoueur;

    /** ennemys char **/
    Array<ChasseurCharAlea> charAlea = new Array<ChasseurCharAlea>();
    Array<ChasseurCharFollow> charFollow = new Array<ChasseurCharFollow>();
    Array<ChasseurDrapeau> charDrapeau = new Array<ChasseurDrapeau>();
    Array<Char> charsEnnemis = new Array<Char>();

    /** all char on the map */
    Array<Char> chars = new Array<Char>();

    /** Drapeau */

    public static Drapeau drapeau;

    /**
     * Chaines de responsabilitees
     */
    private CollisionBulletCOR corBullet = CollisionBulletCOR.initCOR();
    private CollisionCharCOR corChar = CollisionCharCOR.initCOR();

    private int XMAX = 29, YMAX = 29;

    public CharJoueur getCharJoueur() {
        return charJoueur;
    }
    
    public Terrain() {
        createWorld();
    }
 
    private void createWorld() {
        try {
            creerGrille();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean collisionProjectile(Projectile p, ElementDeJeu ele, Char c) {
        return corBullet.collisionDuProjectile(this, p, ele, c);
    }

    public boolean collisionChar(ElementDeJeu ele, Char c, Rectangle r) {
        return corChar.collisionDuChar(this, ele, c, r);
    }



            /**
     * transforme les caracteres du .csv en case
     * @param c le caractere
     * @param x position X
     * @param y position Y
     * @return la case associee au caractere
     */
    public void charEnCase(char c, int x, int y) {
        ElementDeJeu ele;

        switch (c) {
        case 'I':
            ele = new MurIncassable(new Vector2(x, y), MurIncassable.SIZE);
            gameElementStatic.add((ElementDeJeuStatique)(ele));
            allGameElement.add(ele);
            allGameElementWithoutChars.add(ele);
            break;
        case 'A':
            ele = new Arbre(new Vector2(x, y));
            gameElementStatic.add((ElementDeJeuStatique)(ele));
            allGameElement.add(ele);
            allGameElementWithoutChars.add(ele);
            break;

        case 'D':
            ele = new Drapeau(new Vector2(x, y));
            gameElementStatic.add((ElementDeJeuStatique)(ele));
            allGameElement.add(ele);
            allGameElementWithoutChars.add(ele);
            drapeau = (Drapeau)ele;
            break;

        case '#':
            ele = new MurBrique(new Vector2(x, y));
            gameElementDynamic.add((ElementDeJeuDynamique)(ele));
            allGameElement.add(ele);
            allGameElementWithoutChars.add(ele);
            break;

        case '/':
            ele = new MurRenforce(new Vector2(x, y));
            gameElementDynamic.add((ElementDeJeuDynamique)(ele));
            allGameElement.add(ele);
            allGameElementWithoutChars.add(ele);
            break;
            
        case 'J':
            CharJoueur j = new CharJoueur(new Vector2(x, y));
            charJoueur = j;
            chars.add(j);
            allGameElement.add(j);
            //gameElementDynamic.add((ElementDeJeuDynamique)(j));
            break;
        case 'C':
            ele = new ChasseurCharAlea(new Vector2(x, y));
            charAlea.add((ChasseurCharAlea)ele);
            chars.add((ChasseurCharAlea)(ele));
            allGameElement.add(ele);
            gameElementDynamic.add((ElementDeJeuDynamique)(ele));
            charsEnnemis.add((ChasseurCharAlea)(ele));
            break;
        case 'F':
            ele = new ChasseurCharFollow(new Vector2(x, y));
            charFollow.add((ChasseurCharFollow)ele);
            chars.add((ChasseurCharFollow)(ele));
            allGameElement.add(ele);
            gameElementDynamic.add((ElementDeJeuDynamique)(ele));
            charsEnnemis.add((ChasseurCharFollow)(ele));
            break;
        case 'O' : 
            ele = new ChasseurDrapeau(new Vector2(x, y));
            charDrapeau.add((ChasseurDrapeau)ele);
            chars.add((ChasseurDrapeau)(ele));
            allGameElement.add(ele);
            gameElementDynamic.add((ElementDeJeuDynamique)(ele));
            charsEnnemis.add((ChasseurDrapeau)(ele));
            break;

        }
    }



                /**
     * Verifie si le fichier .csv est lisible et le retourne si c'est le cas
     * @param orderPath le fichier
     * @return le fichier
     */
    public List<String> verifFichier(Path orderPath) {

        List<String> lines = null;

        try {
            lines = Files.readAllLines(orderPath);          //Verifie si le fichier est lisible
        } catch (IOException e) {
            System.out.println("Impossible de lire le fichier\n");
        }
        return lines;
    }



        /**
     * Fonction qui retourne le n° correspondant a la derniere ligne de fichier qui commence par un char (= debut de la grille)
     * @param orderPath le fichier
     * @param split une certaine ligne modifie (generalement sans ';')
     * @param lines une certaine ligne du fichier
     * @return
     */
    public int trouverFinLigne(Path orderPath, String[] split, List<String> lines) {

        int nbrLigne = lines.size() - 1;    //Compteur initialise au max de ligne
        boolean trouver = false;                //booleen remit a false
        int finLigne = 0;
     
        do {

            split = lines.get(nbrLigne).split(" ");

            try {
                split[0].charAt(0); //Verifie chaque premier terme du split pour voir si c'est un char
                trouver = true;
                finLigne = nbrLigne;
            } catch (NumberFormatException nfe) { }

            nbrLigne = nbrLigne - 1;

        } while(!(nbrLigne < 0 || (trouver)));     //Cherche la derniere ligne rencontree qui commence par un char

        XMAX = finLigne;
        return finLigne;

    }


           /**
     * Creer la grille du niveau voulu
     * 
     * @throws IOException
     */
    public void creerGrille() throws IOException {

        Path orderPath = Paths.get("core/src/plateaux/plateau1.csv");
        //Path orderPath = Paths.get("E:/Projet/Battle_City/core/src/plateaux/plateau"+niveau+".csv");
        String[] split = null;
        List<String> lines = null;
        int finLigne = 0;           //Cherche la fin de ligne qui correspond au tableau

        lines = verifFichier(orderPath);

        finLigne = trouverFinLigne(orderPath, split, lines);

        ////////////////////////////////////////////////////////////////////////////

        int compteur = 0;       //Comme la boucle "pour" ne commence pas a 0, il nous faut un compteur

        //for(int i = 0; i <= finLigne; i++) {
        for(int i = finLigne; i >= 0; i--) {

            split = lines.get(i).split(" ");
            YMAX = split.length - 1;

            try {
                for(int ParcoursSplit = 0; ParcoursSplit < split.length; ParcoursSplit++) {
                    charEnCase(split[ParcoursSplit].charAt(0), ParcoursSplit, compteur);
                    //tableau[ParcoursSplit][compteur].setPositionX(ParcoursSplit);
                    //tableau[ParcoursSplit][compteur].setPositionY(compteur);
                }
                
            } catch (NumberFormatException nfe) {
                System.out.println("Je sais pas comment tu as fait pour arriver la, mais bien joue !");
            }  

            compteur++;
            
        }


        System.out.println(chars);

        //searchAll();


    }

    public Array<Char> getChars() {
        return chars;
    }
    public void setChars(Array<Char> chars) {
        this.chars = chars;
    }

    /*public Array<ElementDeJeu> getGameElement() {
        return gameElement;
    }

    public void setGameElement(Array<ElementDeJeu> gameElement) {
        this.gameElement = gameElement;
    }*/

    public Array<ElementDeJeuStatique> getGameElementStatic() {
        return gameElementStatic;
    }

    public void setGameElementStatic(Array<ElementDeJeuStatique> gameElementStatic) {
        this.gameElementStatic = gameElementStatic;
    }

    public Array<ElementDeJeuDynamique> getGameElementDynamic() {
        return gameElementDynamic;
    }

    public void setGameElementDynamic(Array<ElementDeJeuDynamique> gameElementDynamic) {
        this.gameElementDynamic = gameElementDynamic;
    }

    public void setCharJoueur(CharJoueur charJoueur) {
        this.charJoueur = charJoueur;
    }

    public int getXMAX() {
        return XMAX;
    }

    public void setXMAX(int xMAX) {
        XMAX = xMAX;
    }

    public int getYMAX() {
        return YMAX;
    }

    public void setYMAX(int yMAX) {
        YMAX = yMAX;
    }

    public Array<ElementDeJeu> getAllGameElement() {
        return allGameElement;
    }

    public void setAllGameElement(Array<ElementDeJeu> allGameElement) {
        this.allGameElement = allGameElement;
    }

    public Array<ElementDeJeu> getAllGameElementWithoutChars() {
        return allGameElementWithoutChars;
    }

    public void setAllGameElementWithoutChars(Array<ElementDeJeu> allGameElementWithoutChars) {
        this.allGameElementWithoutChars = allGameElementWithoutChars;
    }

    public CollisionBulletCOR getCorBullet() {
        return corBullet;
    }

    public void setCorBullet(CollisionBulletCOR corBullet) {
        this.corBullet = corBullet;
    }

    public Array<ChasseurCharAlea> getCharAlea() {
        return charAlea;
    }

    public void setCharAlea(Array<ChasseurCharAlea> charAlea) {
        this.charAlea = charAlea;
    }

    public Array<ChasseurCharFollow> getCharFollow() {
        return charFollow;
    }

    public void setCharFollow(Array<ChasseurCharFollow> charFollow) {
        this.charFollow = charFollow;
    }

    public CollisionCharCOR getCorChar() {
        return corChar;
    }

    public void setCorChar(CollisionCharCOR corChar) {
        this.corChar = corChar;
    }

    public Array<Char> getCharsEnnemis() {
        return charsEnnemis;
    }

    public void setCharsEnnemis(Array<Char> charsEnnemis) {
        this.charsEnnemis = charsEnnemis;
    }
    
}