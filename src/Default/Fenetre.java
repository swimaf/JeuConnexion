package Default;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private JPanel grille;
    private JPanel panel;
    private Menu menu;
	private Jeu jeu;
	private Case[][] cases;
	
	Fenetre(Jeu jeu) {
		this.setTitle("Jeu connexion");
		this.setSize(Constantes.SCREEN_WIDTH, Constantes.SCREEN_HEIGHT);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
        this.jeu = jeu;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        menu = new Menu(this);

		grille = new JPanel();
		grille.setLayout(new GridLayout(Constantes.x, Constantes.y));

        generateGrille(false);

        panel.add(grille);
        panel.add(menu);


        this.setContentPane(panel);
	}

	public void generateGrille(Boolean game) {
        cases = new Case [Constantes.x][Constantes.y];
        grille.removeAll();
        for (int i = 0; i < Constantes.x; i++) {
			for (int j = 0; j < Constantes.y; j++) {
				Case bouton = new Case(i, j);
				cases[i][j] = bouton;
				bouton.setBackground(Color.WHITE);
				bouton.setFont(new Font(null, Font.PLAIN, 40));
				bouton.setForeground(Color.WHITE);
                bouton.setEnabled(game);
                bouton.addActionListener(new OnClickButton());
				grille.add(bouton);
			}
		}
		menu.inGame(!game);
		panel.updateUI();
	}

	private class OnClickButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof Case) {
                Case bouton = (Case) e.getSource();
				jeu.colorerCaseJeu(bouton);
            }
		}
	}

	public JPanel getGrille() {
		return grille;
	}

    public Case[][] getCases() {
		return cases;
	}

    public Jeu getJeu() {
        return jeu;
    }

    public void printConsole(String result) {
        menu.getConsole().setText(result);
    }

}
