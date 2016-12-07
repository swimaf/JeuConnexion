package Default;

import java.awt.*;
import java.util.ArrayList;

abstract class Joueur {
    private static int i = 0;

    protected String nom_;
    protected Color couleur_;
    protected int etoilesMax;
    protected ArrayList<Case> etoiles;
    protected Case centreMasse;
    protected Jeu jeu;

	Joueur(String nom, Jeu jeu){
		nom_ = nom;
		etoilesMax = 0;
        this.jeu = jeu;
        etoiles = new ArrayList<>();
		if (i % 2 == 0){
			couleur_ = Constantes.r;
		} else {
			couleur_ = Constantes.b;
		}
		++i;
	}
	
	public String getNom_() {
		return nom_;
	}

	public void setNom_(String nom_) {
		this.nom_ = nom_;
	}
	
	Color getColor_() {
		return couleur_;
	}

	public void setCouleur_(Color couleur) {
		this.couleur_ = couleur;
	}


    public int getEtoilesMax() {
        return etoilesMax;
    }

    public void setEtoilesMax(int etoilesMax) {
        if(etoilesMax > this.etoilesMax) {
            this.etoilesMax = etoilesMax;
        }
    }

    abstract void jouer();

    public Case getCentreMasse(Fenetre fenetre) {
        if(centreMasse == null) {
            int x = 0;
            int y = 0;
            for (Case c : etoiles) {
                x += c.getX_();
                y += c.getY_();
            }
            centreMasse = fenetre.getCases()[x/etoiles.size()][y/etoiles.size()];
        }
        return centreMasse;
    }

    public void setCentreMasse(Case centreMasse) {
        this.centreMasse = centreMasse;
    }

    public ArrayList<Case> getEtoiles() {
        return etoiles;
    }

    public void addEtoiles(Case etoile) {
        this.etoiles.add(etoile);
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }
}
