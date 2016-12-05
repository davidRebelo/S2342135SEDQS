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
        this.v = numVal;
        
        for (Groupe g : tab) {
            if (g.estEnConflit(this)){
                conf = true;
            }
        }
        
        return conf;
    }
    
    public boolean estEnConflit(){
        boolean conf = false;
        
        for (Groupe g : tab) {
            if (g.estEnConflit(this)){
                conf = true;
            }
        }
        
        return conf;
    }
    
    public void ajouterGroupe(Groupe g){
        boolean fait = false;
        for(int i = 0; i < tab.length && fait == false; i++){
            if(tab[i] == null){
                tab[i] = g;
                fait = true;
            }
        }
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
