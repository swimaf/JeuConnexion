package Action;

import Default.Fenetre;

import java.awt.event.ActionListener;


public class AfficheScore implements Action {

    private Fenetre parent;

    public AfficheScore(Fenetre parent) {
        this.parent = parent;
    }

    @Override
    public ActionListener getEvent() {
        return null;
    }

    @Override
    public String getTitle() {
        return "Afficher le score";
    }
}
