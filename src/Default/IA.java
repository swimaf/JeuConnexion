package Default;

import java.util.ArrayList;
import java.util.TreeMap;


class IA extends Joueur{

    IA(String nom, Jeu jeu) {
        super(nom, jeu);
    }

    @Override
    void jouer() {
        TreeMap<Integer, ArrayList<Case>> chemins = new TreeMap<>();
        Case aCase = getCentreMasse(jeu.getFenetre());
        if(aCase.getJ_() == null) {
            jeu.colorerCaseJeu(centreMasse);
        } else if(!aCase.getJ_().equals(this)) {
            for(Case c:jeu.getVoisins(aCase)) {
                if (aCase.getJ_() == null) {
                    centreMasse = c;
                    break;
                }
            }
        } else {
            int taille;
            boolean impossible = false;
            for(Case etoile:etoiles) {
                if(!jeu.existeCheminCases(etoile, centreMasse)) {
                    taille = jeu.relierCasesMin(etoile, centreMasse);
                    chemins.put(taille, jeu.getChemin());
                    if(Math.abs(taille) == Integer.MAX_VALUE) {
                        impossible = true;
                        break;
                    }
                }
            }
            if(impossible) {
                jeu.abandonner();
            }else {
                for (Case c : chemins.firstEntry().getValue()) {
                    if (c.getJ_() == null) {
                        jeu.colorerCaseJeu(c);
                        break;
                    }
                }
            }
        }
    }
}
