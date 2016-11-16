/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author david
 */
public enum Valeur {
    Zero(0),
    Un(1),
    Deux(2),
    Trois(3),
    Quatre(4),
    Cinq(5),
    Six(6),
    Sept(7),
    Huit(8),
    Neuf(9);
    
    protected int valeur;
    
    /** Constructeur **/
    Valeur(int pValeur){
        this.valeur = pValeur;
    }
    
    public int getValeur(){
        return this.valeur;
    }
    
}
