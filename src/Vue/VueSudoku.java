/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.FontWeight;

import Modele.Jeu;
import Modele.Groupe;
import Modele.Case;

/**
 *
 * @author david
 */
public class VueSudoku extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    public static void MAJ_Vue(Stage primaryStage) {
        
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        Jeu grilleJeu = new Jeu();
        grilleJeu.init();
        
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        
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

                txt.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        txt.selectAll();
                    }
                });
                
                gridPane.add(txt, colonne++, rangee);
 
                if (colonne > 8){
                    colonne = 0;
                    rangee++;
                }
            }
        }

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

        GridPane grid_btn = new GridPane();
        grid_btn.setPadding(new Insets(10,10,10,10));
        
        grid_btn.add(btn_verif, 50, 50);
        grid_btn.add(btn_nettoyer, 50, 40);
        grid_btn.add(btn_ouvrir_sudoku, 50, 30);

        border.setCenter(gridPane);
        border.setRight(grid_btn);
        
        Scene scene = new Scene(border, Color.WHITE);
        
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void configBoutonMenu(Button btn, String text){
        btn.setText(text);
        btn.setMinWidth(150);
        btn.setStyle("-fx-padding: 15px; -fx-border-insets: 6px; -fx-background-insets:6px;");
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
