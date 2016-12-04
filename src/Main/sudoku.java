/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Modele.Jeu;
import Modele.ModeleSudoku;
import Vue.VueGrille;
import Vue.VueMenuB;
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
        VueGrille vGrille = new VueGrille(grilleJeu);
        VueMenuB vMenu = new VueMenuB(grilleJeu);

        border.setCenter(vGrille.getGridPane());
        border.setRight(vMenu.getGridPane());
        
        Scene scene = new Scene(border, Color.WHITE);
        
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
