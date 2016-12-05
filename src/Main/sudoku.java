/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controleur.ControleurGrille;
import Controleur.ControleurJeu;
import Controleur.ControleurMenu;
import Modele.Jeu;
import Modele.ModeleSudoku;
import Vue.VueGrille;
import Vue.VueMenuB;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Jérémy
 */
public class sudoku extends Application{
    
    ModeleSudoku m = new ModeleSudoku();
    
    public void initSudoku(){
        //m.initModele();
    }
    
    public static void main(String[] args) {
        sudoku s = new sudoku();
        s.initSudoku();
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Jeu grilleJeu = new Jeu();
        grilleJeu.init();

        BorderPane border = new BorderPane();
        
        ControleurGrille cGrille = new ControleurGrille(grilleJeu);
        VueGrille vGrille = new VueGrille(grilleJeu, cGrille);
        ControleurMenu cMenu = new ControleurMenu(grilleJeu);
        VueMenuB vMenu = new VueMenuB(grilleJeu, cMenu);

        border.setCenter(vGrille.getGridPane());
        border.setRight(vMenu.getGridPane());
        
        Scene scene = new Scene(border, Color.WHITE);
        
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
