package Action;

import Default.Fenetre;
import Dialogue.DialogCase;

import java.awt.event.ActionListener;

public class AfficherComposants implements Action {

    private DialogCase dialogCase;

    public AfficherComposants(Fenetre parent) {
        dialogCase = new DialogCase(parent, getTitle()) {
            public void executer(int x1, int y1) {
                parent.getJeu().afficheComposante(x1, y1);
            }
        };
    }

    public String getTitle() {
        return "Afficher composants";
    }

    @Override
    public ActionListener getEvent() {
        return e -> dialogCase.setVisible(true);
    }
}
