package Action;

import Default.Case;
import Default.Fenetre;
import Dialogue.AbstractDialog;
import Dialogue.DialogCase;
import Dialogue.DialogDoubleCase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by martinet on 19/11/16.
 */
public class RelieComposantes implements Action {
    private DialogDoubleCase dialogDoubleCase;

    public RelieComposantes(Fenetre parent) {
        dialogDoubleCase = new DialogDoubleCase(parent, getTitle()) {
            @Override
            public void executer(Case c1, Case c2) {
                //parent.getJeu().
            }
        };
    }

    @Override
    public ActionListener getEvent() {
        return e -> dialogDoubleCase.setVisible(true);
    }

    @Override
    public String getTitle() {
        return "Relie composantes";
    }
}
