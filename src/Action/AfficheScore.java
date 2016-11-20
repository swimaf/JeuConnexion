package Action;

import Default.Fenetre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AfficheScore implements Action {

    private Fenetre parent;

    public AfficheScore(Fenetre parent) {
        this.parent = parent;
    }

    @Override
    public ActionListener getEvent() {
        return e -> parent.getJeu().afficherScore();
    }

    @Override
    public String getTitle() {
        return "Afficher le score";
    }
}
