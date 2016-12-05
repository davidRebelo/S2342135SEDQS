/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Jeu;
import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author david
 */
public class ControleurMenu extends ControleurJeu{
    
    public ControleurMenu(Jeu grilleJeu) {
        super(grilleJeu);
    }

    public boolean ouvrirFichier(){
        try{
            FileChooser fc = new FileChooser();
            File fichier = fc.showOpenDialog(null);
            if(fichier != null){
                grilleJeu.getJeuDepuisFichier(fichier.getAbsolutePath());
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
    
}
