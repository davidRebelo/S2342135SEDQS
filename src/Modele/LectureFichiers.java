package Modele;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author david
 */
public class LectureFichiers {
    public LectureFichiers(){}

    public static String LireDepuisFichier(String nom_fichier) throws FileNotFoundException{
        String tableauSudoku = new String(), ligne;
        
        try{
            InputStream inputStream = new FileInputStream(new File(nom_fichier));
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            
            BufferedReader bufferR = new BufferedReader(inputStreamReader);

            while((ligne = bufferR.readLine()) != null){
                tableauSudoku += ligne;
            }
            bufferR.close();
        }
        catch (IOException e){
            System.out.println(e.toString());
        }

        return tableauSudoku;
    }
    
    public static boolean SauvegarderDansFichier(String chemin, Jeu grilleJeu) throws IOException{
        String text = grilleJeu.toStringPourSauvegarde();
        
        try{
            PrintWriter fichier = new PrintWriter(new FileWriter(chemin));
            fichier.println(text);
            fichier.close();
            return true;
        } 
        catch (IOException e) {
            return false;
        }
    }
}
