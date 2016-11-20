package Action;

import Default.Fenetre;

import java.awt.event.ActionListener;

public class Abandonner implements Action {
    private Fenetre parent;

    public Abandonner(Fenetre parent) {
        this.parent = parent;
    }

    @Override
    public ActionListener getEvent() {
        return e -> parent.getJeu().abandonner();
    }

    @Override
    public String getTitle() {
        return "Abandonner";
    }
}
