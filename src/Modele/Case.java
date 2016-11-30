package Modele;

/**
 *
 * @author david
 */
public abstract class Case {
    protected Valeur condition;
    protected int v;
    protected Groupe[] tab = new Groupe[3];
    
    public boolean MAJ(int numVal){
        boolean conf = false;
        for (Groupe g : tab) {
            if (g.estEnConflit(numVal)){
                conf = true;
            }
        }
        
        if(conf) this.v = numVal;
        
        return conf;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public Groupe[] getTab() {
        return tab;
    }

    public void setTab(Groupe[] tab) {
        this.tab = tab;
    }
    
    
    
}
