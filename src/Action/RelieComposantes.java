package Action;

import Default.Fenetre;
import Dialogue.DialogCase;

import java.awt.event.ActionListener;

/**
 * Created by martinet on 19/11/16.
 */
public class RelieComposantes implements Action {
    private DialogCase dialogDoubleCase;

    public RelieComposantes(Fenetre parent) {
        dialogDoubleCase = new DialogCase(parent, getTitle()) {
            @Override
            public void executer(int x1, int y1) {
                parent.getJeu().relieComposantes(parent.getCases()[x1][y1]);
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
