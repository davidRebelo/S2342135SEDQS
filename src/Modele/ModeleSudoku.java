/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.util.Observable;

/**
 *
 * @author Jérémy
 */
public class ModeleSudoku extends Observable{
    
    Jeu j = new Jeu();
    
    public void initSudoku(){
        j.init();
    }
    
    public void modifierCase(int ligne,int colonne,int valeur){
        j.Majcase(ligne,colonne,valeur);
        setChanged();
        notifyObservers();
    }
}
