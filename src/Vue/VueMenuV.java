/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.ControleurMenu;
import Modele.Jeu;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;


/**
 *
 * @author david
 */

public class VueMenuV extends VueSudoku{
    
    protected ControleurMenu cMenu;
    protected MenuBar menu;
    
    public VueMenuV(Jeu grilleJeu, ControleurMenu cMenu) {
        super(grilleJeu);
        this.cMenu = cMenu;
        menu = new MenuBar();
        CreerMenuVertical();
    }
    
    private void CreerMenuVertical(){
        MenuItem ouvrirFichier, resetJeu, verifierJeu, resoudreJeu, sauvegarderFichier, chargerFichier, quitter;
        
        //Menu Fichier
        Menu menuFichier = new Menu("Fichier");
        ouvrirFichier = new MenuItem("Ouvrir un fichier");
        sauvegarderFichier = new MenuItem("Sauvegarder la partie");
        chargerFichier = new MenuItem("Charger une partie");
        quitter = new MenuItem("Quitter");
        menuFichier.getItems().addAll(ouvrirFichier, new SeparatorMenuItem(), sauvegarderFichier, chargerFichier, new SeparatorMenuItem(), quitter);

        //Menu Grille
        Menu menuGrille = new Menu("Grille");
        resetJeu = new MenuItem("Nettoyer la grille");
        verifierJeu = new MenuItem("Vérifier la grille");
        resoudreJeu = new MenuItem("Résoudre la grille");
        menuGrille.getItems().addAll(resetJeu,verifierJeu,resoudreJeu);

        menu.getMenus().addAll(menuFichier,menuGrille);
        evenementMenu(ouvrirFichier, resetJeu, verifierJeu, resoudreJeu, quitter, chargerFichier, sauvegarderFichier);
        
    }
    
    private void evenementMenu(MenuItem ouvrirFichier,MenuItem resetJeu,MenuItem verifierJeu,MenuItem resoudreJeu, MenuItem quitter, MenuItem chargerFichier, MenuItem sauvegarderFichier){
        ouvrirFichier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(cMenu.ouvrirFichier(false)){
                    creerBoiteMessage(Alert.AlertType.INFORMATION,"Sudoku ouvert avec succes!", "Ouverture fichier").showAndWait();
                }
                else{
                    creerBoiteMessage(Alert.AlertType.ERROR,"Probleme pendant l'ouverture du fichier!", "Ouverture fichier").showAndWait();
                }
            }
        });
        
        resetJeu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cMenu.resetJeu();
            }
        });
        
        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        chargerFichier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(cMenu.ouvrirFichier(true)){
                    creerBoiteMessage(Alert.AlertType.INFORMATION,"Sudoku ouvert avec succes!", "Ouverture fichier").showAndWait();
                }
                else{
                    creerBoiteMessage(Alert.AlertType.ERROR,"Probleme pendant l'ouverture du fichier!", "Ouverture fichier").showAndWait();
                }
            }
        });
        
        sauvegarderFichier.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if(cMenu.sauvegarderFichier()){
                        creerBoiteMessage(Alert.AlertType.INFORMATION,"La partie a ete sauvegarder avec succes!", "Sauvegarder partie").showAndWait();
                    }
                    else{
                        creerBoiteMessage(Alert.AlertType.ERROR,"Probleme pendant la sauvegarde du jeu!", "Sauvegarder partie").showAndWait();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(VueMenuV.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        verifierJeu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(cMenu.verifierSudoku()){
                    creerBoiteMessage(Alert.AlertType.INFORMATION,"Vous avez reussi! Bravo!", "Verification").showAndWait();
                }
                else{
                    creerBoiteMessage(Alert.AlertType.ERROR,"Essayer encore!","Verification").showAndWait();
                }
            }
        });
        
        resoudreJeu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cMenu.resoudreJeu();
            }
        });
    }

    public MenuBar getMenu() {
        return menu;
    }
    
    
    
}
