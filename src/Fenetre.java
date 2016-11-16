import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Fenetre extends JFrame {
	
	private JPanel panel;
	private Jeux jeux;
	private Case[][] cases;
	
	public Fenetre(Jeux jeux) {
		this.setTitle("Jeu connexion");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setLayout(new GridLayout(Constantes.x, Constantes.y));
		
		this.jeux = jeux;
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
				jeux.ajouterClasse(bouton);
			}
		}
		jeux.initialisationEtoiles(cases);
		this.setContentPane(panel);
		this.setVisible(true);
	}

	public class OnClickButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Case bouton = (Case) e.getSource();
			if (bouton.getJ_() == null) {
				jeux.tour(bouton);
				
			}
		}
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public ArrayList<Case> getCases() {
		return cases;
	}

	public void setCases(ArrayList<Case> cases) {
		this.cases = cases;
	}
	
	
	
}
