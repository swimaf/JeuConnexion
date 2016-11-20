package Action;

import Default.Fenetre;
import Dialogue.AbstractDialog;
import Dialogue.DialogCase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NombreEtoiles implements Action {
    private DialogCase dialogCase;
    public NombreEtoiles(Fenetre parent) {
        dialogCase = new DialogCase(parent, getTitle()) {
            @Override
            public void executer(int x1, int y1) {
                parent.getJeu().nombreEtoiles(x1, y1);

            }
        };
    }

    @Override
    public ActionListener getEvent() {
        return e -> dialogCase.setVisible(true);
    }

    @Override
    public String getTitle() {
        return "Nombre d'étoiles";
    }
}
