package Action;

import Default.Fenetre;
import Dialogue.DialogCase;

import java.awt.event.ActionListener;


public class ColorerCase implements Action {

    private DialogCase dialogCase;

    public ColorerCase(Fenetre parent) {
        dialogCase = new DialogCase(parent, getTitle()) {
            public void executer(int x1, int y1) {
                parent.getJeu().colorerCase(x1, y1);
            }
        };
    }

    public String getTitle() {
        return "Colorer case";
    }

    @Override
    public ActionListener getEvent() {
        return e -> dialogCase.setVisible(true);
    }
}
