/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.ControleurMenu;
import Modele.Jeu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author david
 */
public class VueMenuB extends VueSudoku{
    
    protected ControleurMenu cMenu;
    protected boolean verification_auto_actif;
    
    public VueMenuB(Jeu grilleJeu, ControleurMenu cMenu) {
        super(grilleJeu);
        this.cMenu = cMenu;
        verification_auto_actif = false;
        CreerMenuBouton(grilleJeu);
    }

    private void CreerMenuBouton(Jeu grilleJeu){
        gridPane = new GridPane();
        
        //configuration des boutons
        Button btn_verif_auto = new Button();
        Button btn_nettoyer = new Button();
        Button btn_quit = new Button();
        
        configBoutonMenu(btn_verif_auto, "Activer AutoVerif");
        configBoutonMenu(btn_nettoyer, "Nettoyer");
        configBoutonMenu(btn_quit, "Quitter");

        gridPane.setPadding(new Insets(10,10,10,10));
        
        gridPane.add(btn_verif_auto, 1, 1);
        gridPane.add(btn_nettoyer, 2, 1);
        gridPane.add(btn_quit, 3, 1);
        
        evenementBoutons(btn_verif_auto, btn_nettoyer, btn_quit);
    }
    
    
    private void configBoutonMenu(Button btn, String text){
        btn.setText(text);
        btn.setMinWidth(135);
        btn.setStyle("-fx-padding: 13px; -fx-border-insets: 6px; -fx-background-insets:6px;");
    }
    
    private void evenementBoutons(Button btn_verif_auto, Button btn_nettoyer, Button btn_quit){
        btn_verif_auto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                verification_auto_actif = (!verification_auto_actif);
                if(verification_auto_actif){
                    btn_verif_auto.setText("Desactiver AutoVerif");
                }
                else{
                    btn_verif_auto.setText("Activer AutoVerif");
                }
            }
        });
        
        btn_quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        
        btn_nettoyer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cMenu.resetJeu();
            }
        });
    }

    public boolean isVerification_auto_actif() {
        return verification_auto_actif;
    }
    
    

}
