package Action;

import Default.Case;
import Default.Fenetre;
import Dialogue.DialogDoubleCase;

import java.awt.event.ActionListener;

public class ExisteCheminCases implements Action {

    private DialogDoubleCase dialogDoubleCase;
    public ExisteCheminCases(Fenetre parent) {
        dialogDoubleCase = new DialogDoubleCase(parent, getTitle()) {
            @Override
            public void executer(Case c1, Case c2) {
                parent.getJeu().existeCheminCases(c1, c2);
            }
        };
    }


    @Override
    public ActionListener getEvent() {
        return e -> dialogDoubleCase.setVisible(true);
    }

    @Override
    public String getTitle() {
        return "Existe Chemin Cases";
    }
}