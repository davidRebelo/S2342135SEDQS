/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.Case;
import Modele.CaseBloquee;
import Modele.Jeu;

/**
 *
 * @author david
 */
public class ControleurGrille extends ControleurJeu {
    
    public ControleurGrille(Jeu grilleJeu) {
        super(grilleJeu);
    }
    
    public boolean verifierConflit(String valeur, Case c){
        if(c instanceof CaseBloquee) return false;
        else{
            int v = convertirStringAInt(valeur);
            return c.MAJ(v);
        }
    }
}
