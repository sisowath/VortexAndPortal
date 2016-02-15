package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanneauCarte extends JPanel implements ActionListener {
            // attribut(s)
    private int nombreDeCases;
    private JButton cases[];
            // methode(s)
    // constructeur(s)
    public PanneauCarte(int nbrLignes, int nbrColonnes) {
        this.nombreDeCases = nbrLignes * nbrColonnes;
        this.cases = new JButton[nombreDeCases];
        for(int i=1; i <= nombreDeCases; i++) {
            JButton tempBtn = new JButton("Case " + i);
            Random rand = new Random();
            int tempNbr = rand.nextInt( ( 7 - 1 ) + 1 ) - 1;
            switch( tempNbr ) {
                case 1 : tempBtn.setBackground(Color.RED);
                            break;
                case 2 : tempBtn.setBackground(Color.ORANGE);
                            break;
                case 3 : tempBtn.setBackground(Color.YELLOW);
                            break;
                case 4 : tempBtn.setBackground(Color.GREEN);
                            break;
                case 5 : tempBtn.setBackground(Color.BLUE);
                            break;
                case 6 : tempBtn.setBackground(Color.PINK);
                            break;
                case 7 : tempBtn.setBackground(Color.MAGENTA);
                            break;
            }
            tempBtn.addActionListener(this);
            cases[i-1] = tempBtn;
            this.add( cases[i-1] );
        }
    }
    // accesseur(s)
    
    // mutateur(s)
    
    // autre(s)
    @Override
    public void actionPerformed(ActionEvent e) {
        Random rand = new Random();
        for(int i=0; i <= nombreDeCases; i++) {
            if(e.getSource() == cases[i] ) {
                //JOptionPane.showMessageDialog(null, "Le bouton " + (i+1) );
                int operande1 = rand.nextInt( ( 10-1 ) + 1 - 1);
                int operande2 = rand.nextInt( ( 10-1 ) + 1 - 1);
                String operateur = "";
                int operateurHasard = rand.nextInt((4-1)+1-1);
                double resultat = 0;
                switch( operateurHasard ) {
                    case 1 :    operateur = " + "; 
                                resultat = operande1+operande2;
                                break;
                    case 2 :    operateur = " - "; 
                                resultat = operande1-operande2;
                                break;
                    case 3 :    operateur = " * "; 
                                resultat = operande1*operande2;
                                break;
                    case 4 :    operateur = " / "; 
                                resultat = operande1/operande2;
                                break;
                }                
                double reponse = Integer.parseInt( JOptionPane.showInputDialog(null, "Calculez " + operande1 + operateur + operande2 + " = ?", "Question " + cases[i+1], JOptionPane.QUESTION_MESSAGE) );
                if( reponse == resultat ) {
                    JOptionPane.showMessageDialog(null, "BRAVO !", "SUCCES", JOptionPane.INFORMATION_MESSAGE);
                    cases[i].setBackground(Color.WHITE);
                } else {
                    JOptionPane.showMessageDialog(null, "ERREUR !", "ECHEC", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    // Test
    public static void main(String[] args) {
        int lignes = Integer.parseInt( JOptionPane.showInputDialog(null, "Veuillez saisir le nombre de lignes : ", "Question", JOptionPane.QUESTION_MESSAGE) );
        int colonnes = Integer.parseInt( JOptionPane.showInputDialog(null, "Veuillez saisir le nombre de colonnes : ", "Question", JOptionPane.QUESTION_MESSAGE) );
        PanneauCarte uneCarte = new PanneauCarte(lignes, colonnes);
        
        JFrame fen = new JFrame("Programme Test");
        fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fen.setSize(500, 600);
        fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fen.setVisible(true);
        fen.setLocationRelativeTo(null);
        
        fen.add( uneCarte, BorderLayout.CENTER);/*
        int tailleMax = 1000;
        while(true) {
            if( tailleMax == 1000 )
                tailleMax = 500;
            for(int hauteur = 500; hauteur < 1000; hauteur++) {
                for(int largeur = 600; largeur < 1000; largeur++) {
                    uneCarte.setSize(largeur, hauteur);
                    uneCarte.repaint();
                }
            }
        }*/
    }
}