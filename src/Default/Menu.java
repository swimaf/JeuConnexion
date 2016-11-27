package Default;

import Action.*;
import Action.Action;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Menu extends JPanel {

    private Fenetre frame;
    private JTextArea console;
    private ArrayList<Action> elements;
    private ArrayList<JButton> buttons;

    Menu(Fenetre frame) {
        this.frame = frame;
        this.elements = new ArrayList<>();
        this.buttons = new ArrayList<>();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.createListe();

        this.console = new JTextArea();
        this.console.setEditable(false);
        this.console.setBackground(Color.getColor("#e5e5e5"));
        this.console.setBorder(BorderFactory.createTitledBorder("Console"));

        for (Action element : elements) {
            JButton button = new JButton(element.getTitle());
            button.addActionListener(element.getEvent());
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getMinimumSize().height));
            button.setAlignmentX(CENTER_ALIGNMENT);
            buttons.add(button);
            this.add(button);
        }
        this.add(console);
    }

    private void createListe() {

        elements.add(new ColorerCase(frame));
        elements.add(new AfficherComposants(frame));
        elements.add(new ExisteCheminCases(frame));
        elements.add(new RelierCasesMin(frame));
        elements.add(new NombreEtoiles(frame));
        elements.add(new AfficheScore(frame));
        elements.add(new RelieComposantes(frame));
        elements.add(new EvaluerCaseZ(frame));
        elements.add(new JoueDeuxHumains(frame));
        elements.add(new Abandonner(frame));
    }

    public void inGame(Boolean game) {
        for (JButton button : buttons) {
            button.setEnabled(game);
        }
        buttons.get(buttons.size()-1).setVisible(!game);
        buttons.get(buttons.size()-1).setEnabled(!game);
    }

    public JTextArea getConsole() {
        return console;
    }
}
