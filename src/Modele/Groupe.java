package Modele;

/**
 *
 * @author david
 */
public class Groupe {
    protected Case[] tab = new Case[9];
    
    public void add(Case c){
        boolean fait = false;
        for(int i = 0; i < tab.length && fait == false; i++){
            if(tab[i] == null){
                tab[i] = c;
                tab[i].ajouterGroupe(this);
                fait = true;
            }
        }
    }
    
    public boolean estEnConflit(Case c_demander){
        for (Case c : tab){
            if (c.getV() == c_demander.getV() && c_demander != c){
                return true;
            }
        }
        return false;
    }
    
    public boolean estEnConflit(int nombre){
        for (Case c : tab){
            if (c.getV() == nombre){
                return true;
            }
        }
        return false;
    }
    
    public int getValeurDansLigne(int n){
        return this.tab[n].getV();
    }

    public Case[] getTab() {
        return tab;
    }

    public void setTab(Case[] tab) {
        this.tab = tab;
    }
    
    
    
}
