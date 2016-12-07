package Action;

import Default.Fenetre;

import java.awt.event.ActionListener;

public class JoueHumainOrdi implements Action {
    private Fenetre parent;

    public JoueHumainOrdi(Fenetre parent) {
        this.parent = parent;
    }

    @Override
    public ActionListener getEvent() {
        return e -> parent.getJeu().joueHumainOrdi();
    }

    @Override
    public String getTitle() {
        return "Joue humain ordi";
    }
}
