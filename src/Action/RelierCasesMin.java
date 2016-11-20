package Action;

import Default.Case;
import Default.Fenetre;
import Dialogue.DialogDoubleCase;

import java.awt.event.ActionListener;

public class RelierCasesMin implements Action{

    private DialogDoubleCase dialogDoubleCase;


    public RelierCasesMin(Fenetre parent) {
        dialogDoubleCase = new DialogDoubleCase(parent, getTitle()) {
            @Override
            public void executer(Case c1, Case c2) {
                System.out.println("TEST");
                parent.getJeu().relierCasesMin(c1, c2);
            }
        };
    }


    @Override
    public ActionListener getEvent() {
        return e -> dialogDoubleCase.setVisible(true);
    }

    @Override
    public String getTitle() {
        return "Relier cases min";
    }
}

