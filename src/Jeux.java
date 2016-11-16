import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Jeux {

	private Joueur j1;
	private Joueur j2;
	private Joueur joueur_courant;
	private Fenetre fenetre;
	private List<Classe> classes;
	
	public Jeux() {
		j1 = new Joueur("Pepito");
		j2 = new Joueur("Pepita");
		joueur_courant = j1;
		classes = new ArrayList<Classe>();
		fenetre = new Fenetre(this);
	}

	public void ajouterClasse(Case caze) {
		classes.add(new Classe(caze));
	}
	
	public Joueur getJ1() {
		return j1;
	}

	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}

	public Joueur getJoueurCourant() {
		return joueur_courant;
	}

	public void changerJoueur() {
		if(joueur_courant.equals(j1)) {
			this.joueur_courant = j2;
		} else {
			this.joueur_courant = j1;
		}
	}
	
	public void tour(Case bouton) {
		bouton.setJ_(joueur_courant);
		colorerCase(bouton);
		changerJoueur();
		//fenetre.setTitle("A "+joueur_courant.getNom_() +", de jouer");
	}
	
	public void colorerCase(Case bouton) {
		bouton.setBackground(bouton.getJ_().getColor_());
	}

	public void initialisationEtoiles(Case [][] cases) {
		int etoile;
		ArrayList<Integer> etoiles = new ArrayList<Integer>();
		int tab = Constantes.x * Constantes.y;
		Random random = new Random();
		etoile = (2 + random.nextInt(Constantes.MAX_CASE-1)) *2;
		int tmp;
		
		while (etoiles.size() != etoile){
			tmp = random.nextInt(tab);
			if (!(etoiles.contains(tmp))){
				etoiles.add(tmp);
			}
		}
		
		/*for(Integer i:etoiles) {
			cases.get(i).setText("*");
			tour(cases.get(i));
		}*/
	}
	
	public void tets(Case caze) {
		try {
			int a,b, index;
			for(int x = -1; x <= 1; x++) {
				for(int y = -1; y <= 1; y++) {
					if(x != 0 || y != 0) {
						a = caze.getX_() + x;
						b = caze.getY_() + y;
						if(!(a / Constantes.x == 0 || a % Constantes.x == 0 || b / Constantes.y == 0 || b % Constantes.y == 0)) {
							index = a*b;
							if(fenetre.getCases().get(index).getJ_() == null) {
								
							}
						}
					} 
				}
			}
		} catch(Exception e) {
			
		}
	}
}
