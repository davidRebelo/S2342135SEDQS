/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.io.FileNotFoundException;
import java.util.Observable;

/**
 *
 * @author david
 */
public class Jeu extends Observable{
    private Groupe[] tabL;
    private Groupe[] tabC;
    private Groupe[][] tabCa;
    private String tab;
    
    public void init(){
        this.tabL = new Groupe[9];
	this.tabC = new Groupe[9];
	this.tabCa = new Groupe[3][3];
        
        //lecture du sudoku depuis un fichier
        try{
            this.tab = LectureFichiers.LireDepuisFichier("sudoku/sudoku1.txt");
        }
        catch (FileNotFoundException erreur){
            System.out.println(erreur.toString());
        }
        
	String[] tabData = tab.split("");
        
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
    
    public void getJeuDepuisFichier(String chemin_fichier){
        this.tabL = new Groupe[9];
	this.tabC = new Groupe[9];
	this.tabCa = new Groupe[3][3];
        
        //lecture du sudoku depuis un fichier
        try{
            this.tab = LectureFichiers.LireDepuisFichier(chemin_fichier);
        }
        catch (FileNotFoundException erreur){
            System.out.println(erreur.toString());
        }
        
	String[] tabData = tab.split("");
        
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
        
        notifierMAJ();
    }
    
    // permet de faire la notification de la vue suite à une mise à jour
    public void notifierMAJ(){
        setChanged();
        notifyObservers();
    }
    
    public boolean estFinit(){
        boolean estFinit = true;
        for(Groupe tab: tabL){
            for(Case c: tab.getTab()){
                if(c.estEnConflit()) estFinit = false;
            }
        }
        return estFinit;
    }
    
    public int getValeur(int l, int c){
        return this.tabL[l].getValeurDansLigne(c);
    }
    
    public Case obtenirCase(int ligne,int colonne){
        Case c = tabL[ligne].tab[colonne];
        return c;
    }
    
    public void Majcase(int ligne,int colonne,int valeur){
        Case c = obtenirCase(ligne,colonne);
        c.MAJ(valeur);
    }
    
    public void aff_tab(){
        System.out.printf(this.tab);
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
