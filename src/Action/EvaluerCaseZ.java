package Action;

import Default.Fenetre;
import Dialogue.DialogCase;

import java.awt.event.ActionListener;


public class EvaluerCaseZ implements Action {

    private DialogCase dialogCase;

    public EvaluerCaseZ(Fenetre parent) {
        dialogCase = new DialogCase(parent, getTitle()) {
            public void executer(int x1, int y1) {
                parent.getJeu().evaluerCase1(parent.getCases()[x1][y1]);
            }
        };
    }

    public String getTitle() {
        return "Evaluer case";
    }

    @Override
    public ActionListener getEvent() {
        return e -> dialogCase.setVisible(true);
    }
}
