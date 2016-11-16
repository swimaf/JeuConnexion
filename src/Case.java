import javax.swing.JButton;

public class Case extends JButton{

	private static int nbButton = 0;

	private int index;
    private int nbLiaison;
	private int x_;
	private int y_;
	private Joueur j_;
	
	public Case (int x, int y){
		super();
		index = nbButton;
		x_ = x;
		y_ = y;
        nbLiaison = 1;
        nbButton++;
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

    public int getNbLiaison() {
        return nbLiaison;
    }

    public void addLiaison(int nbLiaison) {
        this.nbLiaison += nbLiaison;
    }
}
