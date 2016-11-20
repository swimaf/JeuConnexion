package Default;

import javafx.util.Pair;

import java.awt.*;
import java.util.*;


public class Jeu {

	private Joueur j1;
	private Joueur j2;
	private Joueur joueurCourant;
	private Fenetre fenetre;
    private int nbEtoilesAConquerir;

	Jeu() {
		fenetre = new Fenetre(this);
        initialisation(fenetre.getCases());
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

	void execute() {
        fenetre.setVisible(true);
    }

	private void changerJoueur() {
		if(joueurCourant.equals(j1)) {
			this.joueurCourant = j2;
		} else {
			this.joueurCourant = j1;
		}
	}

    public void colorerCase(Case bouton) {
        if (bouton.getJ_() == null) {
            bouton.setJ_(joueurCourant);
            bouton.setBackground(bouton.getJ_().getColor_());
            relieComposantes(bouton);
            changerJoueur();

            //A Supprimer
            affichageTerminal();
        } else {
            print("Case déjà colorié");
        }
    }

    public void affichageTerminal() {
        Case button;
        for (int i = 0; i < Constantes.x; i++) {
            for (int j = 0; j < Constantes.y; j++) {
                button = fenetre.getCases()[i][j].getParent_();
                if(button == null) {
                    System.out.print(null + "\t|");
                } else {
                    System.out.print("(" +button.getX_() + ","+button.getY_()  +"T=" + button.getNbLiaison()+ " E="+button.getNbEtoiles()+"\t\t|");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public void colorerCase(int x, int y){
        colorerCase(fenetre.getCases()[x][y]);
    }

	private void initialisation(Case[][] cases) {
        j1 = new Joueur("Joueur rouge");
        j2 = new Joueur("Joueur bleu");
        joueurCourant = j1;

        Random random = new Random();
        ArrayList<Pair<Integer, Integer>> etoiles = new ArrayList<>();
		int etoile = (2 + (random.nextInt(Constantes.MAX_CASE_INIT)));
        nbEtoilesAConquerir = etoile;
        etoile *= 2;
        Pair<Integer, Integer> tmp;
        Case courante;

		while (etoiles.size() != etoile){
            tmp = new Pair<>(random.nextInt(Constantes.x), random.nextInt(Constantes.y));
			if (!(etoiles.contains(tmp))){
				etoiles.add(tmp);
                courante = cases[tmp.getKey()][tmp.getValue()];
                courante.setText("*");
                courante.setNbEtoiles(1);
                colorerCase(courante);
			}
		}
	}

	private Case classe(Case caze) {
        if(caze.getParent_() == null) {
            return caze;
        } else {
            Case parent = classe(caze.getParent_());
            caze.setParent_(parent);
            return parent;
        }
    }

	private int relieComposantes(Case caze) {
        Case racineCaseCourante;
        ArrayList<Case> chemins = new ArrayList<>();
        int nbLiaison = 0;
        int nbEtoiles = 0;
        int departX = caze.getX_() == 0 ? 0 : caze.getX_() - 1;
        int departY = caze.getY_() == 0 ? 0 : caze.getY_() - 1;
        int finX = caze.getX_() == Constantes.x-1 ? caze.getX_() : caze.getX_() + 1;
        int finY = caze.getY_() == Constantes.y-1 ? caze.getY_() : caze.getY_() + 1;

        for (int i = departX; i <= finX; i++) {
            for (int j = departY; j <= finY; j++) {
                racineCaseCourante = classe(fenetre.getCases()[i][j]);
                if (racineCaseCourante.getJ_() == caze.getJ_() && !chemins.contains(racineCaseCourante)) {
                    chemins.add(racineCaseCourante);
                    nbEtoiles += racineCaseCourante.getNbEtoiles();
                    nbLiaison += racineCaseCourante.getNbLiaison();
                    System.out.println(racineCaseCourante);
                    System.out.println(nbEtoiles);
                }
            }
        }

        Collections.sort(chemins, (o1, o2) -> o1.getNbLiaison() > o2.getNbLiaison() ? -1 : 0);
        Case racineDesChemins = chemins.get(0);
        racineDesChemins.setNbLiaison(nbLiaison);
        racineDesChemins.setNbEtoiles(nbEtoiles);
        joueurCourant.setEtoilesMax(nbEtoiles);
        chemins.remove(0);
        for (Case chemin : chemins) {
            chemin.setParent_(racineDesChemins);
        }
        print(chemins.size()+" ont été reliés");

        if(nbEtoiles == nbEtoilesAConquerir) {
            print(joueurCourant.getNom_() + " à gagné");
        }

        return chemins.size();
    }


    public void afficheComposante(int x, int y) {
        String reponse = "{";
        Case base = classe(fenetre.getCases()[x][y]);
        for(int i=0; i<Constantes.x; i++) {
            for(int j=0; j<Constantes.y; j++) {
                if(classe(fenetre.getCases()[i][j]).equals(base)) {
                    reponse+= "(" +fenetre.getCases()[i][j] +"), ";
                }
            }
        }
        reponse += "}";
        print(reponse);
    }

    private void print(String text) {
        System.out.println(text);
        fenetre.printConsole(text);
    }

    public Boolean existeCheminCases(Case c1, Case c2) {
        Boolean response = classe(c1).equals(classe(c2));
        print(response ? "Il existe un chemin " : "Il n'existe pas de chemin" );
        return response;
    }

    public int relierCasesMin(Case c1, Case c2) {
        int response = 0;
        int departX, finX, departY, finY;
        if(c1.getX_() > c2.getX_()) {
            departX = c2.getX_();
            finX = c1.getX_();
        } else {
            departX = c1.getX_();
            finX = c2.getX_();
        }
        if(c1.getY_() > c2.getY_()) {
            departY = c2.getY_();
            finY = c1.getY_();
        } else {
            departY = c1.getY_();
            finY = c2.getY_();
        }
        for (int i = departX; i <= finX; i++) {
            for (int j = departY; j <= finY; j++) {
                fenetre.getCases()[i][j].setBackground(Color.black);
            }
        }
        return response;
    }

    public int nombreEtoiles(int x, int y) {
        Case base = classe(fenetre.getCases()[x][y]);
        print("Il y a "+base.getNbEtoiles()+" étoiles dans cette composante");
        return base.getNbEtoiles();
    }

    public void joueDeuxHumains() {
        fenetre.generateGrille(true);
        initialisation(fenetre.getCases());
    }

    public void afficherScore() {
        print("Score de "+j1.getNom_() +" : "+j1.getEtoilesMax() + "\n"+
              "Score de "+j2.getNom_() +" : "+j2.getEtoilesMax());
    }

    public void abandonner() {
        changerJoueur();
        print(joueurCourant.getNom_() + " a gagné !");
        fenetre.generateGrille(false);
        initialisation(fenetre.getCases());
    }
}
