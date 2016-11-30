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
    
    public void Init(String data){
	this.tabL = new Groupe[9];
	this.tabC = new Groupe[9];
	this.tabCa = new Groupe[3][3];
	String[] tabData = data.split(" "); //separateur espace
        
        for (int initGL = 0; initGL < tabL.length; ++initGL){
            tabL[initGL] = new Groupe();
        }
        for (int initGC = 0; initGC < tabC.length; ++initGC){
            tabC[initGC] = new Groupe();
        }
        for (int initGCa1 = 0; initGCa1 < tabCa.length; ++initGCa1){
            for (int initGCa2 = 0; initGCa2 < tabCa[initGCa1].length; ++initGCa2){
                tabCa[initGCa1][initGCa2] = new Groupe();
            }
        }
	
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
