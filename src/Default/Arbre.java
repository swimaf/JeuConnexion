package Default;

import java.util.ArrayList;

/**
 * Created by martinet on 07/12/16.
 */
public class Arbre {
    private Arbre parent;
    private Case value;
    private ArrayList<Arbre> arbres;

    public Arbre(Arbre parent, Case value) {
        this.parent = parent;
        this.value = value;
        arbres = new ArrayList<>();
    }

    public Arbre getParent() {
        return parent;
    }

    public void setParent(Arbre parent) {
        this.parent = parent;
    }

    public ArrayList<Arbre> getArbres() {
        return arbres;
    }

    public Arbre addChild(Case c) {
        Arbre arbre = new Arbre(this, c);
        arbres.add(arbre);
        return arbre;
    }

    public Case getValue() {
        return value;
    }

    public void setValue(Case value) {
        this.value = value;
    }
}
