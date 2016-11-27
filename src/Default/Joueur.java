package Default;

import java.awt.*;
import java.util.ArrayList;

class Joueur {
    private static int i = 0;

    private String nom_;
	private Color couleur_;
	private int etoilesMax;
	private ArrayList<Case> etoiles;
    private Case centreMasse;

	Joueur(String nom){
		nom_ = nom;
		etoilesMax = 0;
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

    public Case getCentreMasse(Fenetre fenetre) {
        if(centreMasse == null) {
            int x = 0;
            int y = 0;
            for (Case c : etoiles) {
                x += c.getX_();
                y += c.getY_();
            }
            centreMasse = fenetre.getCases()[x/etoiles.size()][y/etoiles.size()];
            centreMasse.setText("M");
            centreMasse.setBackground(Color.black);
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
}
