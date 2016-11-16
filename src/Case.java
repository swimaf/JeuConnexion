import javax.swing.JButton;

public class Case extends JButton{
	private int x_;
	private int y_;
	private Joueur j_;
	
	public Case (int x, int y){
		super();
		x_ = x;
		y_ = y;
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
	
}
