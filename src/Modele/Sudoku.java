/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author p1408997
 */
public class Sudoku extends Application{
    
    public static void main(String[] args){
        
        String s ="5 3 0 0 7 0 0 0 0 6 0 0 1 9 5 0 0 0 0 9 8 0 0 0 0 6 0 8 0 0 0 6 0 0 0 3 4 0 0 8 0 3 0 0 1 7 0 0 0 2 0 0 0 6 0 6 0 0 0 0 2 8 0 0 0 0 0 4 1 9 0 0 5 0 0 0 0 8 0 0 7 9 ";
        Jeu grilleJeu = new Jeu();
        grilleJeu.init(s);
        
        for (Groupe g : grilleJeu.getTabL()) {
            for (Case c : g.getTab()) {
                System.out.print(c.getV()+" ");
            }
            System.out.println();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
