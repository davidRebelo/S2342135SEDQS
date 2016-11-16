/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.io.*;


/**
 *
 * @author david
 */
public class Jeu {
    private Groupe[] tabL;
    private Groupe[] tabC;
    private Groupe[][] tabCa;
    
    public Jeu(){}
    
    public void Init(String data){
	this.tabL = new Groupe[9];
	this.tabC = new Groupe[9];
	this.tabCa = new Groupe[3][3];
	String[] tabData = data.split(" "); //separateur espace
        
	
	for(int i=0; i < tabData.length; i++){
		Case caree;
		if(tabData[i] == "0"){
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
}
