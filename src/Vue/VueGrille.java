/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.ControleurGrille;
import Modele.Case;
import Modele.CaseBloquee;
import Modele.Groupe;
import Modele.Jeu;
import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.WindowEvent;

/**
 *
 * @author david
 */
public class VueGrille extends VueSudoku {
    
    protected ControleurGrille cGrille;
    protected VueMenuB menuBoutons;
    
    public VueGrille(Jeu grilleJeu, ControleurGrille cGrille, VueMenuB menuBoutons){
        super(grilleJeu);
        this.menuBoutons = menuBoutons;
        this.cGrille = cGrille;
        CreerGrille(grilleJeu);
    }

    private void CreerGrille(Jeu grilleJeu){
        gridPane = new GridPane();
       
        //config GridPane
        gridPane.setPadding(new Insets(10,10,10,10));

        int colonne_case_dans_region = 0, ligne_region = 0, rangee_reel=0;
        int rangee_case_dans_region = 0, colonne_region = 0, colonne_reel=0;
        int n_cicle_col=0, n_cicle_rang=0;
        GridPane region = new GridPane();
        
        //parcourt de toute les valeurs du sudoku
        Groupe[][] ligne_de_regions = grilleJeu.getTabCa();
        for (int i=0; i<ligne_de_regions.length; i++){
            for (int j=0; j<ligne_de_regions[i].length; j++){
                for (Case case_tableau:ligne_de_regions[i][j].getTab()){
                    TextField txt = new TextField();

                    configTextField(txt, case_tableau, colonne_reel, rangee_reel);
                    colonne_reel++;

                    region.add(txt, colonne_case_dans_region, rangee_case_dans_region);
                    colonne_case_dans_region++;
                    
                    if (colonne_case_dans_region > 2){
                        colonne_case_dans_region = 0;
                        rangee_case_dans_region++;
                        
                        colonne_reel = (n_cicle_col*3);
                        rangee_reel++;
                    }
                    if(rangee_case_dans_region > 2){
                        
                        gridPane.add(region, colonne_region, ligne_region);
                        colonne_region++; 
                        if (colonne_region > 2){
                            colonne_region = 0;
                            ligne_region++;
                            n_cicle_rang++;
                            
                        }
                        rangee_reel = 3*n_cicle_rang;
                        rangee_case_dans_region = 0;
                        colonne_case_dans_region = 0;
                        region = new GridPane();
                        region.setPadding(new Insets(3,3,3,3));
                        n_cicle_col++;
                        if(n_cicle_col >2){
                            n_cicle_col = 0;
                        }
                        colonne_reel = (n_cicle_col*3);
                    }
                    
                    
                }
            }
        }
        
    }

    private void configTextField(TextField txt, Case c, final int col, final int rangee){
        ContextMenu menuClicDroit = new ContextMenu();
        InitItemMenuClicDroit(menuClicDroit);
        txt.setPrefHeight(45);
        txt.setPrefWidth(45);
        txt.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        txt.setId(String.valueOf(((rangee*100)+col)));
        if(c.getV() != 0){
            txt.setDisable(true);
            txt.setText(String.valueOf(c.getV()));
            txt.setOpacity(1);
            txt.setStyle("-fx-text-inner-color: blue;");
        }
        else{
            txt.setContextMenu(menuClicDroit);
            txt.setDisable(false);
            txt.setText("");
            txt.setOpacity(1);
        }
        
        evenementGrille(txt, menuClicDroit, rangee, col, c);

    }
    
    private void evenementGrille(TextField txt, ContextMenu menuClicDroit, int rangee, int col, Case c){
        txt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                txt.selectAll();
            }
        });
        
        //Verification si l'utilisateur introduit un numero
        txt.setTextFormatter(new TextFormatter<Integer>((TextFormatter.Change ch) -> {
            if(ch.getControlNewText().matches("-?\\d") || ch.getControlNewText().isEmpty()){
                return ch;
            }
            else{   
                return null;
            }
        }));
        
        //change la couleur de la case si elle est en conflit
        txt.textProperty().addListener((observable, oldValue, newValue) -> {
            if(menuBoutons.isVerification_auto_actif()){
                if(cGrille.verifierConflit(newValue, c) && !(txt.getText().isEmpty())){
                    txt.setStyle("-fx-border-color: red;");
                }
                else txt.setStyle("");                
            }
            else{
                cGrille.MAJCase(newValue, c);
            }
        });

        menuClicDroit.getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(cGrille.verifierConflit(txt.getText(), c)){
                    txt.setStyle("-fx-border-color: red;");
                }
                else{
                    txt.setStyle("");
                }
            }
        });
        
        menuClicDroit.getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                txt.setStyle("");
                txt.clear();
            }
        });
        
        //En cas de mis a jour du jeu les case se mette a jour si elles sont differente 
        grilleJeu.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                Case c_up = grilleJeu.getTabL()[rangee].getTab()[col];
                if(c_up.getV() != 0 && c_up instanceof CaseBloquee){
                    txt.setDisable(true);
                    txt.setText(String.valueOf(c_up.getV()));
                    txt.setStyle("-fx-text-inner-color: blue;");

                }                   
                else{
                    txt.setDisable(false);
                    if(c_up.getV() != 0) txt.setText(Integer.toString(c_up.getV()));
                    else txt.setText("");
                    txt.setOpacity(1);
                    txt.setStyle("-fx-text-inner-color: black;");
                }

            }
        });
    }
    
    private void InitItemMenuClicDroit(ContextMenu menuClicDroit){
        MenuItem verifierItem = new MenuItem("Verifier Case");
        MenuItem nettoyerItem = new MenuItem("Nettoyer");
        menuClicDroit.getItems().addAll(verifierItem, nettoyerItem);
    }

}
