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
        
        //les nombres sont separer par une virgule, ici on separe 
        // tout les numeros recuperer
        
	String[] tabData = tab.split(",");
        
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
                    caree = new CaseNonBloquee(tabData[i]);
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
    
    public void getJeuDepuisFichier(String chemin_fichier, boolean est_une_partie){
        this.tabL = new Groupe[9];
	this.tabC = new Groupe[9];
	this.tabCa = new Groupe[3][3];
        boolean valeur_introduite_par_utilisateur;
        
        //lecture du sudoku depuis un fichier
        try{
            this.tab = LectureFichiers.LireDepuisFichier(chemin_fichier);

        }
        catch (FileNotFoundException erreur){
            System.out.println(erreur.toString());
        }
	String[] tabData = tab.split(",");
        
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
                valeur_introduite_par_utilisateur = false;
                
                // Si on charge une valeur dans un jeu deja jouer et que la 
                // valeur a ete introduite par l'utilisateur alors la valeur 
                // commence par un 'x' dans le fichier
                if(est_une_partie && tabData[i].charAt(0)== 'x'){
                    tabData[i] = tabData[i].replace("x", "");
                    valeur_introduite_par_utilisateur = true;
                }
                
                // si la valeur est 0 ou est une valeur jouer par l'utilisateur 
                // alors la case est non bloquante
		if("0".equals(tabData[i]) || valeur_introduite_par_utilisateur){
                    caree = new CaseNonBloquee(tabData[i]);
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
    
    public void resetAllCaseNonBloq(){
        for(Groupe tab: tabL){
            for(Case c: tab.getTab()){
                if(c instanceof CaseNonBloquee) c.setV(0);
            }
        }
        notifierMAJ();
    }
    
    public boolean resolution(){
        boolean fait = false, finit = false, estInterdit = false;
        int retour = 0;
        
        int[] valeur_anterieur = new int[9];
        for(int init=0; init<valeur_anterieur.length; init++){
            valeur_anterieur[init] = 0;
        } 
        
        for(Groupe g: tabL){
            Case[] c = g.getTab();
            for(int x=0; x<c.length; x++){
                fait = false;
                if(valeur_anterieur[valeur_anterieur.length-1] != 0) return false;
                for(int i = 1; i<=9 && fait == false; i++){

                    for(int h=0; h<valeur_anterieur.length; h++){
                         if(i == valeur_anterieur[h]) estInterdit = true;
                    }
                    if(estInterdit == false){
                        if(c[x].MAJ(i) == false) fait = true;
                    }
                }
                if(fait == false){
                    System.out.printf("sad");
                    retour++;
                    x = x - retour;
                    for(int j=0; j<valeur_anterieur.length; j++){
                        if(valeur_anterieur[j] == 0) valeur_anterieur[j] = c[x].getV();
                    }               
                 }
            }
        }
        
        finit = estFinit();
        notifierMAJ();
        
        return finit;
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

    public String getTab() {
        return tab;
    }
    
    public String toStringPourSauvegarde(){
        String suite_valeurs = new String();
        
        for(Groupe tab: tabL){
            for(Case c: tab.getTab()){
                if(c instanceof CaseNonBloquee && c.getV()!= 0){
                    //si la valeur avait ete introduite par un utilisateur elle est preceder par un 'x'
                    //chaque valeur est suivi de une virgule pour que on puisse diferencier les numero dans un chargement de fichier
                    suite_valeurs = suite_valeurs.concat("x"+c.getV()+",");
                }
                else{
                    suite_valeurs = suite_valeurs.concat(""+c.getV()+",");
                }
            }
        }
        return suite_valeurs;
    }
}
