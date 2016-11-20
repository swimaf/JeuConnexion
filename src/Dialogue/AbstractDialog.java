package Dialogue;


import Default.Case;
import Default.Fenetre;

import javax.swing.*;
import java.awt.*;

/**
 * Created by martinet on 19/11/16.
 */
public abstract class AbstractDialog extends JDialog {

    protected Fenetre parent;

    public AbstractDialog(Fenetre parent, String title){
        super(parent, title);
        this.parent = parent;
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

}
