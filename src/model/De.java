package model;

import java.util.Random;

public class De {
            // attribut(s)
    int nombreDeFaces;    
            // methode(s)
    // constructeur(s)
    
    // accesseur(s)
    
    // mutateur(s)
    
    // autre(s)    
    public int rouler() {
        Random rand = new Random();        
        return rand.nextInt( (this.nombreDeFaces - 1) + 1) + 1;
    }
}
