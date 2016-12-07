/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Jeu;
import static Modele.LectureFichiers.SauvegarderDansFichier;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.stage.FileChooser;

/**
 *
 * @author david
 */
public class ControleurMenu extends ControleurJeu{
    
    public ControleurMenu(Jeu grilleJeu) {
        super(grilleJeu);
    }

    public boolean ouvrirFichier(boolean partie_charger){
        try{
            FileChooser fc = new FileChooser();
            File fichier = fc.showOpenDialog(null);
            if(fichier != null){
                grilleJeu.getJeuDepuisFichier(fichier.getAbsolutePath(), partie_charger);
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception ex){
            return false;
        }
    }
    
    public boolean sauvegarderFichier() throws IOException{
        FileChooser fc = new FileChooser();
        boolean ouvert;
        fc.setTitle("Charger une partie");
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT","*.txt"));
        File dir = fc.showSaveDialog(null);
        
        ouvert = SauvegarderDansFichier(dir.getAbsolutePath(), grilleJeu);
        return ouvert;
    }
    
    public void resetJeu(){
        grilleJeu.resetAllCaseNonBloq();
    }
    
    public void resoudreJeu(){
        grilleJeu.resolution();
    }
    
    public boolean verifierSudoku(){
        return grilleJeu.estFinit();
    }

}
