/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Jeu;

/**
 *
 * @author david
 */
public class ControleurJeu {
    protected Jeu grilleJeu;

    public ControleurJeu(Jeu grilleJeu) {
        this.grilleJeu = grilleJeu;
    }
    
    public int convertirStringAInt(String s){
        if(s == null || s.isEmpty()){
            return 0;
        }
        else{
            return Integer.parseInt(s);
        }
    }
    
    
}
