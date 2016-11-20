package Action;

import Default.Fenetre;
import java.awt.event.ActionListener;

public class JoueDeuxHumains implements Action {
    private Fenetre parent;

    public JoueDeuxHumains(Fenetre parent) {
        this.parent = parent;
    }

    @Override
    public ActionListener getEvent() {
        return e -> parent.getJeu().joueDeuxHumains();
    }

    @Override
    public String getTitle() {
        return "Joue deux humains";
    }
}
