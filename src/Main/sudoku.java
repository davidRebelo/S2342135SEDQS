/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controleur.ControleurGrille;
import Controleur.ControleurMenu;
import Modele.Jeu;
import Vue.VueGrille;
import Vue.VueMenuB;
import Vue.VueMenuV;
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
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Jeu grilleJeu = new Jeu();
        grilleJeu.init();
        
        boolean verification_auto_actif = false;

        BorderPane border = new BorderPane();
        
        ControleurGrille cGrille = new ControleurGrille(grilleJeu);
        ControleurMenu cMenu = new ControleurMenu(grilleJeu);
        VueMenuB vMenuB = new VueMenuB(grilleJeu, cMenu);
        VueMenuV vMenuV = new VueMenuV(grilleJeu, cMenu);
        VueGrille vGrille = new VueGrille(grilleJeu, cGrille, vMenuB);
        
        border.setTop(vMenuV.getMenu());
        border.setCenter(vGrille.getGridPane());
        border.setBottom(vMenuB.getGridPane());
        
        Scene scene = new Scene(border, Color.WHITE);
        
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
