/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Case;
import Modele.Groupe;
import Modele.Jeu;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 *
 * @author david
 */
public class VueGrille extends VueSudoku {
    
    public VueGrille(Jeu grilleJeu){
        super(grilleJeu);
        CreerGrille(grilleJeu);
    }

    private void CreerGrille(Jeu grilleJeu){
        gridPane = new GridPane();
        
        //config GridPane
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setStyle("-fx-background-color:white;");
        
        Text t;
        int colonne = 0;
        int rangee = 0;
        
        //parcourt de toute les valeurs du sudoku
        for (Groupe lignes_tableau:grilleJeu.getTabL()){
            for (Case case_tableau:lignes_tableau.getTab()){
                TextField txt = new TextField();
                configTextField(txt, case_tableau);
                
                gridPane.add(txt, colonne++, rangee);
 
                if (colonne > 8){
                    colonne = 0;
                    rangee++;
                }
            }
        }
        
    }

    private void configTextField(TextField txt, Case c){
        txt.setPrefHeight(45);
        txt.setPrefWidth(45);
        txt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        txt.setStyle("-fx-border-color: black;");
        
        if(c.getV() != 0){
            txt.setDisable(true);
            txt.setText(String.valueOf(c.getV()));
            txt.setOpacity(0.6);
        }
        else{
            txt.setDisable(false);
            txt.setText("");
        }        
        
        txt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txt.selectAll();
            }
        });

    }

}
