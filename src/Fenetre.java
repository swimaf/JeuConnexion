import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Fenetre extends JFrame {
	
	private JPanel panel;
	private Jeu jeu;
	private Case[][] cases;
	
	public Fenetre(Jeu jeu) {
		this.setTitle("Jeu connexion");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setLayout(new GridLayout(Constantes.x, Constantes.y));
		
		this.jeu = jeu;
		cases = new Case [Constantes.x][Constantes.y];
		
		for (int i = 0; i < Constantes.x; i++) {
			for (int j = 0; j < Constantes.y; j++) {
				Case bouton = new Case(i, j);
				cases[i][j] = bouton;
				bouton.setBackground(Color.white);
				bouton.setFont(new Font(null, Font.PLAIN, 40));
				bouton.setForeground(Color.WHITE);
				bouton.addActionListener(new OnClickButton());
				panel.add(bouton);
				jeu.ajouterClasse();
			}
		}
		jeu.initialisationEtoiles(cases);
		this.setContentPane(panel);
		this.setVisible(true);
	}

	public class OnClickButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Case bouton = (Case) e.getSource();
			if (bouton.getJ_() == null) {
				jeu.tour(bouton);
			}
		}
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Case[][] getCases() {
		return cases;
	}

	public void setCases(Case[][] cases) {
		this.cases = cases;
	}
	
	
	
}
