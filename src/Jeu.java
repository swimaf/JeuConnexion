import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Jeu {

	private Joueur j1;
	private Joueur j2;
	private Joueur joueur_courant;
	private Fenetre fenetre;
	private List<Classe> listEnsemble;
	
	public Jeu() {
		j1 = new Joueur("Pepito");
		j2 = new Joueur("Pepita");
		joueur_courant = j1;
		listEnsemble = new ArrayList<>();
		fenetre = new Fenetre(this);
	}

	public void ajouterClasse() {
		listEnsemble.add(new Classe());
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
		unionSiPossible(bouton);
		//fenetre.setTitle("A "+joueur_courant.getNom_() +", de jouer");


        //TEST A SUPPRIMER PLUS TARD
        int k = 0;
        for(Classe classe: listEnsemble) {
            if(k % 8 ==0) {
                System.out.println();
            }
            if(classe.getParent() == null) {
                System.out.print(k +"\t|");
            } else {
                System.out.print(classe.getParent().getIndex()+"\t|");
            }
            k++;
        }
        System.out.println();

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
        if(listEnsemble.get(caze.getIndex()).getParent() == null) {
            return caze;
        } else {
            Classe classeParent = listEnsemble.get(caze.getIndex());
            Case racine = classe(classeParent.getParent());
            classeParent.setParent(racine);
            return racine;
        }
    }

	public void unionSiPossible(Case caze) {
        Case racine = classe(caze);
        Case caseCourante;
        Case racineCaseCourante;
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
                        if(racineCaseCourante.getNbLiaison() > racine.getNbLiaison()) {
                            listEnsemble.get(racine.getIndex()).setParent(racineCaseCourante);
                            racineCaseCourante.addLiaison(caseCourante.getNbLiaison());
                        } else {
                            listEnsemble.get(racineCaseCourante.getIndex()).setParent(racine);
                            racine.addLiaison(racineCaseCourante.getNbLiaison());
                        }
                    }

                    //fenetre.getCases()[i][j].setBackground(Color.black);
                }
            }
        }

    }

}
