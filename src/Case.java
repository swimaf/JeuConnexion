import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.*;

public class Case extends JButton {

    private int nbLiaison;
	private int x_;
	private int y_;
	private Joueur j_;
    private Case parent_;

    public Case (int x, int y){
		super();
		x_ = x;
		y_ = y;
        nbLiaison = 1;
    }
	
	public int getX_() {
		return x_;
	}
	public void setX_(int x) {
		this.x_ = x;
	}
	public int getY_() {
		return y_;
	}
	public void setY_(int y) {
		this.y_ = y;
	}
	
	public Joueur getJ_(){
		return j_;
	}
	
	public void setJ_(Joueur j){
		this.j_ = j;
	}

    public int getNbLiaison() {
        return nbLiaison;
    }

    public void addLiaison(int nbLiaison) {
        this.nbLiaison += nbLiaison;
    }

	public Case getParent_() {
		return parent_;
	}

	public void setParent_(Case parent) {
		this.parent_ = parent;
	}

	public boolean equals(Object object) {
        Case caze = (Case) object;
        return caze.getY_() == this.y_ && caze.getX_() == this.x_;
    }

    public String toString() {
        return "Y = "  + this.y_ + " X = " +this.x_;
    }
}
