package Default;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TreeMap;


public class Jeu {

	private Joueur j1;
	private Joueur j2;
	private Joueur joueurCourant;
	private Fenetre fenetre;
    private int nbEtoilesAConquerir;
    private TreeMap<Integer, ArrayList<Case>> chemins;
    private boolean matrice[][];

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
            union(bouton);
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
                joueurCourant.addEtoiles(courante);
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
    public long relieComposantes(Case caze) {
        long count = 0;
        if(caze.getJ_() == null) {
            count = getVoisins(caze).stream()
                    .map(this::classe)
                    .distinct()
                    .filter(aCase -> aCase.getJ_() != null && aCase.getJ_().equals(joueurCourant))
                    .count();
            print("Nombre de liaison :"+count);
        } else {
            print("Impossible car la case est déjà coloriée ");
        }
        return count;
    }

	private int union(Case caze) {
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


    private ArrayList<Case> getVoisins(Case c1) {
        ArrayList<Case> cases = new ArrayList<>();
        int departX = c1.getX_() == 0 ? 0 : c1.getX_() - 1;
        int departY = c1.getY_() == 0 ? 0 : c1.getY_() - 1;
        int finX = c1.getX_() >= Constantes.x-1 ? c1.getX_() : c1.getX_() + 1;
        int finY = c1.getY_() >= Constantes.y-1 ? c1.getY_() : c1.getY_() + 1;
        for (int i = departX; i <= finX; i++) {
            for (int j = departY; j <= finY; j++) {
                if (!(c1.getX_() == i && c1.getY_() == j)) {
                    cases.add(fenetre.getCases()[i][j]);
                }
            }
        }
        return cases;
    }

    private int relierCasesMin(Case c1, Case c2, int nbCases) {
        System.out.println(c1);
        if(!c1.equals(c2)) {
            for (Case c : getVoisins(c1)) {
                if (!(matrice[c.getX_()][c.getY_()])) {
                    matrice[c.getX_()][c.getY_()] = true;
                    if (fenetre.getCases()[c.getX_()][c.getY_()].getJ_() == null) {
                        ajouterChemin(nbCases + 1, fenetre.getCases()[c.getX_()][c.getY_()]);
                    } else if (fenetre.getCases()[c.getX_()][c.getY_()].getJ_().equals(c2.getJ_())) {
                        ajouterChemin(nbCases, fenetre.getCases()[c.getX_()][c.getY_()]);
                    } else {
                        ajouterChemin(Integer.MAX_VALUE, fenetre.getCases()[c.getX_()][c.getY_()]);
                    }
                }
            }
            Integer integer = chemins.firstEntry().getKey();
            Case base = chemins.firstEntry().getValue().get(0);
            chemins.firstEntry().getValue().remove(0);
            if (chemins.firstEntry().getValue().isEmpty()) {
                chemins.remove(integer);
            }

            return relierCasesMin(base, c2, integer);
        }
        return nbCases;
    }

    public void ajouterChemin(Integer entier, Case c) {
        if(!chemins.containsKey(entier)) {
            chemins.put(entier, new ArrayList<>());
        }
        chemins.get(entier).add(c);
    }

    public int relierCasesMin(Case c1, Case c2) {
        if(c1.getJ_() == null || c2.getJ_() == null) {
            print("La case n'a pas de couleur");
        } else if(!c1.getJ_().equals(c2.getJ_())) {
            print("Les cases n'ont pas la même couleurs");
        } else if(classe(c1).equals(classe(c2))) {
            print("Nombre de case minimum 0");
        } else {
            chemins = new TreeMap<>();
            matrice = new boolean[Constantes.x][Constantes.x];
            for (int j = 0; j < matrice.length; j++) {
                for (int k = 0; k < matrice.length; k++) {
                    matrice[j][k] = false;
                }
            }
            int i = relierCasesMin(c1, c2, 0);
            print("Nombre de case minimum " + i);
            return i;
        }
        return 0;
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

    public void evaluerCase1(Case c1) {
        relierCasesMin(c1, joueurCourant.getCentreMasse(fenetre));
    }
}
