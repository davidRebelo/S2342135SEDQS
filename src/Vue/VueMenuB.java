/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.ControleurMenu;
import Modele.Jeu;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author david
 */
public class VueMenuB extends VueSudoku{
    
    protected ControleurMenu cMenu;
    
    public VueMenuB(Jeu grilleJeu, ControleurMenu cMenu) {
        super(grilleJeu);
        this.cMenu = cMenu;
        CreerMenu(grilleJeu);
    }
    
    private void CreerMenu(Jeu grilleJeu){
        gridPane = new GridPane();
        Text resultat = new Text("");
        resultat.setStyle("-fx-padding: 30 30 30 30;");
        
        //configuration des boutons
        Button btn_verif = new Button();
        Button btn_nettoyer = new Button();
        Button btn_ouvrir_sudoku = new Button();
        
        configBoutonMenu(btn_verif, "Verifier");
        configBoutonMenu(btn_nettoyer, "Nettoyer");
        configBoutonMenu(btn_ouvrir_sudoku, "Ouvrir");
        
        btn_ouvrir_sudoku.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Ouvrir un fichier");
                cMenu.ouvrirFichier();
            }
        });
        
        btn_verif.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(grilleJeu.estFinit()) resultat.setText("Vous avez reussi! Bravo!");
                else resultat.setText("Essayer encore!");
            }
        });

        gridPane.setPadding(new Insets(10,10,10,10));
        
        gridPane.add(btn_verif, 50, 50);
        gridPane.add(btn_nettoyer, 50, 40);
        gridPane.add(btn_ouvrir_sudoku, 50, 30);
        gridPane.add(resultat, 50, 60);
    }
    
    private void configBoutonMenu(Button btn, String text){
        btn.setText(text);
        btn.setMinWidth(150);
        btn.setStyle("-fx-padding: 15px; -fx-border-insets: 6px; -fx-background-insets:6px;");
    }
    
}
