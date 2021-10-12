package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public final class TextureFactory {
	private static TextureFactory instance;
	private Texture charJoueur, charJoueurGauche, charJoueurDroite, charJoueurHaut, charJoueurBas,
	  	chasseurCharGauche, chasseurCharDroite, chasseurCharHaut, chasseurCharBas,
		chasseurDrapeauGauche, chasseurDrapeauDroite, chasseurDrapeauHaut, chasseurDrapeauBas,
		arbre, drapeau, drapeauCasse, murBrique, murRenforce, murIncassable,
		projectileGauche, projectileHaut, projectileBas, projectileDroite,
		avion, 
	 	fond;
	
	private TextureFactory() {

		charJoueurGauche = new Texture(Gdx.files.internal("CharJoueurGauche.png"));
		charJoueurDroite = new Texture(Gdx.files.internal("CharJoueurDroite.png"));
		charJoueurHaut = new Texture(Gdx.files.internal("CharJoueurHaut.png"));
		charJoueurBas = new Texture(Gdx.files.internal("CharJoueurBas.png"));

		chasseurCharGauche = new Texture(Gdx.files.internal("ChasseurCharGauche.png"));
		chasseurCharDroite = new Texture(Gdx.files.internal("ChasseurCharDroite.png"));
		chasseurCharHaut = new Texture(Gdx.files.internal("ChasseurCharHaut.png"));
		chasseurCharBas = new Texture(Gdx.files.internal("ChasseurCharBas.png"));

		chasseurDrapeauGauche = new Texture(Gdx.files.internal("ChasseurDrapeauGauche.png"));
		chasseurDrapeauDroite = new Texture(Gdx.files.internal("ChasseurDrapeauDroite.png"));
		chasseurDrapeauBas = new Texture(Gdx.files.internal("ChasseurDrapeauBas.png"));
		chasseurDrapeauHaut = new Texture(Gdx.files.internal("ChasseurDrapeauHaut.png"));

		avion = new Texture(Gdx.files.internal("Avion.png"));

		arbre = new Texture(Gdx.files.internal("Arbre.png"));
		drapeau = new Texture(Gdx.files.internal("Drapeau.png"));
		drapeauCasse = new Texture(Gdx.files.internal("DrapeauCasse.png"));
		murBrique = new Texture(Gdx.files.internal("MurBrique.png"));
		murRenforce = new Texture(Gdx.files.internal("MurRenforce.png"));
		murIncassable = new Texture(Gdx.files.internal("edge.png"));

		projectileGauche = new Texture(Gdx.files.internal("ProjectileGauche.png"));
		projectileDroite = new Texture(Gdx.files.internal("ProjectileDroite.png"));
		projectileBas = new Texture(Gdx.files.internal("ProjectileBas.png"));
		projectileHaut = new Texture(Gdx.files.internal("ProjectileHaut.png"));


		fond = new Texture(Gdx.files.internal("Fond.png"));
	}
	
	public static TextureFactory getInstance() {
		if (instance == null) {
            instance = new TextureFactory();
        }
        return instance;
	}

	public Texture getCharJoueur() {
		return charJoueur;
	}

	public void setCharJoueur(Texture charJoueur) {
		this.charJoueur = charJoueur;
	}


	public Texture getDrapeau() {
		return drapeau;
	}

	public void setDrapeau(Texture drapeau) {
		this.drapeau = drapeau;
	}


	public Texture getFond() {
		return fond;
	}

	public void setFond(Texture fond) {
		this.fond = fond;
	}

	public static void setInstance(TextureFactory instance) {
		TextureFactory.instance = instance;
	}

	public Texture getArbre() {
		return arbre;
	}

	public void setArbre(Texture arbre) {
		this.arbre = arbre;
	}

	public Texture getMurBrique() {
		return murBrique;
	}

	public void setMurBrique(Texture murBrique) {
		this.murBrique = murBrique;
	}

	public Texture getMurRenforce() {
		return murRenforce;
	}

	public void setMurRenforce(Texture murRenforce) {
		this.murRenforce = murRenforce;
	}

	public Texture getCharJoueurGauche() {
		return charJoueurGauche;
	}

	public void setCharJoueurGauche(Texture charJoueurGauche) {
		this.charJoueurGauche = charJoueurGauche;
	}

	public Texture getCharJoueurDroite() {
		return charJoueurDroite;
	}

	public void setCharJoueurDroite(Texture charJoueurDroite) {
		this.charJoueurDroite = charJoueurDroite;
	}

	public Texture getCharJoueurHaut() {
		return charJoueurHaut;
	}

	public void setCharJoueurHaut(Texture charJoueurHaut) {
		this.charJoueurHaut = charJoueurHaut;
	}

	public Texture getCharJoueurBas() {
		return charJoueurBas;
	}

	public void setCharJoueurBas(Texture charJoueurBas) {
		this.charJoueurBas = charJoueurBas;
	}

	public Texture getProjectileGauche() {
		return projectileGauche;
	}

	public void setProjectileGauche(Texture projectileGauche) {
		this.projectileGauche = projectileGauche;
	}

	public Texture getProjectileHaut() {
		return projectileHaut;
	}

	public void setProjectileHaut(Texture projectileHaut) {
		this.projectileHaut = projectileHaut;
	}

	public Texture getProjectileBas() {
		return projectileBas;
	}

	public void setProjectileBas(Texture projectileBas) {
		this.projectileBas = projectileBas;
	}

	public Texture getProjectileDroite() {
		return projectileDroite;
	}

	public void setProjectileDroite(Texture projectileDroite) {
		this.projectileDroite = projectileDroite;
	}

	public Texture getChasseurCharGauche() {
		return chasseurCharGauche;
	}

	public void setChasseurCharGauche(Texture chasseurCharGauche) {
		this.chasseurCharGauche = chasseurCharGauche;
	}

	public Texture getChasseurCharDroite() {
		return chasseurCharDroite;
	}

	public void setChasseurCharDroite(Texture chasseurCharDroite) {
		this.chasseurCharDroite = chasseurCharDroite;
	}

	public Texture getChasseurCharHaut() {
		return chasseurCharHaut;
	}

	public void setChasseurCharHaut(Texture chasseurCharHaut) {
		this.chasseurCharHaut = chasseurCharHaut;
	}

	public Texture getChasseurCharBas() {
		return chasseurCharBas;
	}

	public void setChasseurCharBas(Texture chasseurCharBas) {
		this.chasseurCharBas = chasseurCharBas;
	}

	public Texture getAvion() {
		return avion;
	}

	public void setAvion(Texture avion) {
		this.avion = avion;
	}

	public Texture getMurIncassable() {
		return murIncassable;
	}

	public void setMurIncassable(Texture murIncassable) {
		this.murIncassable = murIncassable;
	}

	public Texture getChasseurDrapeauGauche() {
		return chasseurDrapeauGauche;
	}

	public void setChasseurDrapeauGauche(Texture chasseurDrapeauGauche) {
		this.chasseurDrapeauGauche = chasseurDrapeauGauche;
	}

	public Texture getChasseurDrapeauDroite() {
		return chasseurDrapeauDroite;
	}

	public void setChasseurDrapeauDroite(Texture chasseurDrapeauDroite) {
		this.chasseurDrapeauDroite = chasseurDrapeauDroite;
	}

	public Texture getChasseurDrapeauHaut() {
		return chasseurDrapeauHaut;
	}

	public void setChasseurDrapeauHaut(Texture chasseurDrapeauHaut) {
		this.chasseurDrapeauHaut = chasseurDrapeauHaut;
	}

	public Texture getChasseurDrapeauBas() {
		return chasseurDrapeauBas;
	}

	public void setChasseurDrapeauBas(Texture chasseurDrapeauBas) {
		this.chasseurDrapeauBas = chasseurDrapeauBas;
	}

	public Texture getDrapeauCasse() {
		return drapeauCasse;
	}

	public void setDrapeauCasse(Texture drapeauCasse) {
		this.drapeauCasse = drapeauCasse;
	}
	
}
