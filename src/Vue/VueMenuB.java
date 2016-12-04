/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

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
    
    public VueMenuB(Jeu grilleJeu) {
        super(grilleJeu);
        CreerMenu(grilleJeu);
    }
    
    private void CreerMenu(Jeu grilleJeu){
        gridPane = new GridPane();
        
        //configuration des boutons
        Button btn_verif = new Button();
        Button btn_nettoyer = new Button();
        Button btn_ouvrir_sudoku = new Button();
        
        configBoutonMenu(btn_verif, "Verifier");
        configBoutonMenu(btn_nettoyer, "Nettoyer");
        configBoutonMenu(btn_ouvrir_sudoku, "Ouvrir");

        btn_verif.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });

        gridPane.setPadding(new Insets(10,10,10,10));
        
        gridPane.add(btn_verif, 50, 50);
        gridPane.add(btn_nettoyer, 50, 40);
        gridPane.add(btn_ouvrir_sudoku, 50, 30);
    }
    
    private void configBoutonMenu(Button btn, String text){
        btn.setText(text);
        btn.setMinWidth(150);
        btn.setStyle("-fx-padding: 15px; -fx-border-insets: 6px; -fx-background-insets:6px;");
    }
    
}
