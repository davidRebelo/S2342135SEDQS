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
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import Modele.Jeu;
import Modele.Groupe;
import Modele.Case;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author david
 */
public class VueSudoku extends Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void MAJ_Vue(Stage primaryStage) {
        
    }
    
    @Override
    public void start(Stage primaryStage) {
        Jeu grilleJeu = new Jeu();
        String tabl = "5 3 0 0 7 0 0 0 0 6 0 0 1 9 5 0 0 0 0 9 8 0 0 0 0 6 0 8 0 0 0 6 0 0 0 3 4 0 0 8 0 3 0 0 1 7 0 0 0 2 0 0 0 6 0 6 0 0 0 0 2 8 0 0 0 0 4 1 9 0 0 5 0 0 0 0 8 0 0 7 9";
        int valeur_tableau = 0;
        grilleJeu.init(tabl);
        
        for (Groupe g : grilleJeu.getTabL()) {
            for (Case c : g.getTab()) {
                System.out.print(c.getV()+" ");
            }
            System.out.println();
        }
        
        
        BorderPane border = new BorderPane();
        GridPane gridPane = new GridPane();
        int colonne = 0;
        int rangee = 0;
        
        for (int i = 0; i < 81; ++i){
            valeur_tableau = grilleJeu.getValeur(rangee, colonne);
            final Text t;
            
            
            if(valeur_tableau == 0) t = new Text(" ");
            else t = new Text(Integer.toString(valeur_tableau));
            
            t.setWrappingWidth(30);
            t.setFont(Font.font("Arial", 25));
            t.setTextAlignment(TextAlignment.CENTER);
            gridPane.setStyle("-fx-background-color: white; -fx-grid-lines-visible: true");

            gridPane.add(t, colonne++, rangee);
            
            if (colonne > 8){
                colonne = 0;
                rangee++;
            }
            
            

        }

        
        

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        border.setCenter(gridPane);
        Scene scene = new Scene(border, Color.WHITE);
        primaryStage.setTitle("Sudoku");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
