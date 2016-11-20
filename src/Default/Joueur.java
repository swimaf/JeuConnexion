package Default;

import java.awt.Color;

class Joueur {
    private static int i = 0;

    private String nom_;
	private Color couleur_;
	private int etoilesMax;

	Joueur(String nom){
		nom_ = nom;
		etoilesMax = 0;
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
}
