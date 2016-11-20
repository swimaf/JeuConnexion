package Dialogue;

import Default.Fenetre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class DialogCase extends AbstractDialog implements ActionListener{

    private JTextField coordonneeX;
    private JTextField coordonneeY;

    public DialogCase(Fenetre parent, String title){
        super(parent, title);
        JPanel jPanel = new JPanel();

        coordonneeX = new JTextField("X");
        coordonneeY = new JTextField("Y");
        JButton jButton = new JButton("Valider");

        jButton.addActionListener(this);

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(coordonneeX);
        jPanel.add(coordonneeY);
        jPanel.add(jButton);
        this.setContentPane(jPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int x1 = Integer.parseInt(coordonneeX.getText());
            int y1 = Integer.parseInt(coordonneeY.getText());
            executer(x1,y1);
        }catch (Exception i) {
            System.out.println("Champs texte non conforme !");
        }
    }
    abstract public void executer(int x1, int y1);
}
