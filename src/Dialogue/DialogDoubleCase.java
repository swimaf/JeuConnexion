package Dialogue;


import Default.Case;
import Default.Fenetre;

import javax.swing.*;
import java.awt.*;

/**
 * Created by martinet on 19/11/16.
 */
public abstract class DialogDoubleCase extends AbstractDialog {

    public DialogDoubleCase(Fenetre parent, String title){
        super(parent, title);
        JPanel jPanel = new JPanel();

        JTextField jTextFieldX = new JTextField("X1");
        JTextField jTextFieldY = new JTextField("Y1");


        JTextField jTextFieldX2 = new JTextField("X2");
        JTextField jTextFieldY2 = new JTextField("Y2");
        JButton jButton = new JButton("Valider");

        jButton.addActionListener(e -> {
            //try {
                Case c1 = parent.getCases()[Integer.parseInt(jTextFieldX.getText())][ Integer.parseInt(jTextFieldY.getText())];
                Case c2 = parent.getCases()[Integer.parseInt(jTextFieldX2.getText())][ Integer.parseInt(jTextFieldY2.getText())];

                executer(c1,c2);
            /*}catch (Exception i) {
                System.out.println("Champs texte non conforme !");
                System.out.println(i);
            }*/
        });

        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        jPanel.add(jTextFieldX);
        jPanel.add(jTextFieldY);

        jPanel.add(Box.createRigidArea(new Dimension(20,20)));
        jPanel.add(jTextFieldX2);
        jPanel.add(jTextFieldY2);
        jPanel.add(jButton);


        this.setContentPane(jPanel);
    }

    public abstract void executer(Case c1, Case c2);

}
