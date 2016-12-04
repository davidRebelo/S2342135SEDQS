/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import Modele.ModeleSudoku;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Jérémy
 */
public class sudoku extends Application{
    
    ModeleSudoku m = new ModeleSudoku();
    
    public void initSudoku(){
        m.initModele();
    }
    
    public static void main(){
        sudoku s = new sudoku();
        s.initSudoku();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
