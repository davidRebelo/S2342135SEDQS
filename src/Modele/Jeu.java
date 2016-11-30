/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author david
 */
public class Jeu {
    private Groupe[] tabL;
    private Groupe[] tabC;
    private Groupe[][] tabCa;

    public Jeu(){
       /* pour test (sans charger le sudoku depuis un fichier) */ 
       String tabl = "5 3 0 0 7 0 0 0 0 6 0 0 1 9 5 0 0 0 0 9 8 0 0 0 0 6 0 8 0 0 0 6 0 0 0 3 4 0 0 8 0 3 0 0 1 7 0 0 0 2 0 0 0 6 0 6 0 0 0 0 2 8 0 0 0 0 4 1 9 0 0 5 0 0 0 0 8 0 0 7 9";
       Init(tabl);
    }
    
    public void Init(String data){
	this.tabL = new Groupe[9];
	this.tabC = new Groupe[9];
	this.tabCa = new Groupe[3][3];
	String[] tabData = data.split(" "); //separateur espace
        
	
	for(int i=0; i < tabData.length; i++){
		Case caree;
		if("0".equals(tabData[i])){
                    caree = new CaseNonBloquee();
		}
		else{
                    caree = new CaseBloquee(tabData[i]);
		}
		
		int numL = i/9;
		int numC = i%9;
		tabL[numL].add(caree);
		tabC[numC].add(caree);
		tabCa[numL/3][numC/3].add(caree);
	}
    }
    
    public int getValeur(int l, int c){
        return this.tabL[l].getValeurDansLigne(c);
    }

    public Groupe[] getTabL() {
        return tabL;
    }

    public void setTabL(Groupe[] tabL) {
        this.tabL = tabL;
    }

    public Groupe[] getTabC() {
        return tabC;
    }

    public void setTabC(Groupe[] tabC) {
        this.tabC = tabC;
    }

    public Groupe[][] getTabCa() {
        return tabCa;
    }

    public void setTabCa(Groupe[][] tabCa) {
        this.tabCa = tabCa;
    }
    
    
}
