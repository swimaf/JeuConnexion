package Default;

import javax.swing.*;

public class Case extends JButton {

    private int nbLiaison;
	private int x_;
	private int y_;
	private Joueur j_;
    private Case parent_;
	private int nbEtoiles;

    Case(int x, int y){
		super();
		x_ = x;
		y_ = y;
        nbLiaison = 1;
		nbEtoiles = 0;
    }
	
	int getX_() {
		return x_;
	}
	public void setX_(int x) {
		this.x_ = x;
	}
	int getY_() {
		return y_;
	}
	public void setY_(int y) {
		this.y_ = y;
	}
	
	Joueur getJ_(){
		return j_;
	}
	
	void setJ_(Joueur j){
		this.j_ = j;
	}

    int getNbLiaison() {
        return nbLiaison;
    }

    void setNbLiaison(int nbLiaison) {
        this.nbLiaison = nbLiaison;
    }

	Case getParent_() {
		return parent_;
	}

	void setParent_(Case parent) {
		this.parent_ = parent;
	}

	public boolean equals(Case caze) {
        return caze.getY_() == this.y_ && caze.getX_() == this.x_;
    }

    public String toString() {
        return this.x_ + ", " +this.y_;
    }

    public int getNbEtoiles() {
        return nbEtoiles;
    }

    public void setNbEtoiles(int nbEtoiles) {
        this.nbEtoiles = nbEtoiles;
    }

}
