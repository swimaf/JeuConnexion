import java.util.ArrayList;
import java.util.Random;


public class Jeu {

	private Joueur j1;
	private Joueur j2;
	private Joueur joueurCourant;
	private Fenetre fenetre;

	public Jeu() {
		j1 = new Joueur("Pepito");
		j2 = new Joueur("Pepita");
		joueurCourant = j1;
		fenetre = new Fenetre(this);
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
		return joueurCourant;
	}

	public void changerJoueur() {
		if(joueurCourant.equals(j1)) {
			this.joueurCourant = j2;
		} else {
			this.joueurCourant = j1;
		}
	}
	
	public void tour(Case bouton) {
		bouton.setJ_(joueurCourant);
		colorerCase(bouton);
		changerJoueur();
		unionSiPossible(bouton);
		//fenetre.setTitle("A "+joueurCourant.getNom_() +", de jouer");


        //TEST A SUPPRIMER PLUS TARD
        Case button;
		for (int i = 0; i < Constantes.x; i++) {
			for (int j = 0; j < Constantes.y; j++) {
                button = fenetre.getCases()[i][j].getParent_();
                if(button == null) {
                    System.out.print(null + "\t|");
                } else {
                    System.out.print("(" +button.getX_() + ","+button.getY_()  +"T=" + button.getNbLiaison()+ ")\t|");
                }

			}
            System.out.println();
        }

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

	public Case classe(Case caze) {
        if(caze.getParent_() == null) {
            return caze;
        } else {
            Case parent = classe(caze.getParent_());
            caze.setParent_(parent);
            return parent;
        }
    }

	public void unionSiPossible(Case caze) {
        Case racine = classe(caze);
        Case caseCourante;
        Case racineCaseCourante;
        Case racinePredente = null;
        int departX = caze.getX_() == 0 ? 0 : caze.getX_() - 1;
        int departY = caze.getY_() == 0 ? 0 : caze.getY_() - 1;
        int finX = caze.getX_() == Constantes.x-1 ? caze.getX_() : caze.getX_() + 1;
        int finY = caze.getY_() == Constantes.y-1 ? caze.getY_() : caze.getY_() + 1;

        for (int i = departX; i <= finX; i++) {
            for (int j = departY; j <= finY; j++) {
                if (!(i == caze.getX_() && j == caze.getY_())) {
                    caseCourante = fenetre.getCases()[i][j];
                    racineCaseCourante = classe(caseCourante);

                    if (caseCourante.getJ_() == racine.getJ_() ) {
                        System.out.println("Racine predecente"+racinePredente);
                        System.out.println("Racine racineCaseCourante"+racineCaseCourante);

                        if(racinePredente == null || !racineCaseCourante.equals(racinePredente)) {
                            if (racineCaseCourante.getNbLiaison() > racine.getNbLiaison()) {
                                racine.setParent_(racineCaseCourante);
                                racineCaseCourante.addLiaison(racineCaseCourante.getNbLiaison());
                                racinePredente = racineCaseCourante;
                            } else {
                                racineCaseCourante.setParent_(racine);
                                racine.addLiaison(racine.getNbLiaison());
                                racinePredente = racine;
                            }
                        } else {
                            System.out.println("test");
                        }
                    }
                    //fenetre.getCases()[i][j].setBackground(Color.black);
                }
            }
        }

    }

}
