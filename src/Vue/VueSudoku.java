/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javafx.scene.layout.GridPane;
import Modele.Jeu;
import javafx.scene.control.Alert;

/**
 *
 * @author david
 */
public class VueSudoku{
    
    protected Jeu grilleJeu;
    protected GridPane gridPane;
    
    public VueSudoku(Jeu grilleJeu) {
        this.grilleJeu = grilleJeu;
    }

    public GridPane getGridPane() {
        return gridPane;
    }
    
    protected Alert creerBoiteMessage(Alert.AlertType type, String message, String titre){
        Alert boite = new Alert(type);
        boite.setTitle(titre);
        boite.setHeaderText(null);
        boite.setContentText(message);
        return boite;
    }
}
