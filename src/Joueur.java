import java.awt.Color;

public class Joueur {
	private String nom_;
	private Color couleur_;
	
	private static int i = 0;

	public Joueur (String nom){
		nom_ = nom;
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
	
	public Color getColor_() {
		return couleur_;
	}

	public void setCouleur_(Color couleur) {
		this.couleur_ = couleur;
	}

	
}
