package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanneauCarte extends JPanel implements ActionListener {
            // attribut(s)
    private int nombreDeCases;
    private JButton cases[];
    private Color playerColor;
    private String username;
    private int point;
    private JLabel lblPlayerPoints;    
            // methode(s)
    // constructeur(s)
    public PanneauCarte(int nbrLignes, int nbrColonnes) {
        this.nombreDeCases = nbrLignes * nbrColonnes;
        this.cases = new JButton[nombreDeCases];
        for(int i=0; i < nombreDeCases; i++) {
            JButton tempBtn = new JButton("Case " + (i+1));
            if(nombreDeCases <= 40) {
                tempBtn.setFont( new Font("Arial", Font.BOLD, 24) );
            } else {
                tempBtn.setFont( new Font("Arial", Font.BOLD, 12) );
            }
            /*Random rand = new Random();
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
            }*/tempBtn.setBackground(Color.WHITE);
            tempBtn.addActionListener(this);
            cases[i] = tempBtn;
            this.add( cases[i] );
        }
    }
    // accesseur(s)
    public String getUsername() {
        return this.username;
    }
    public int getPoint() {
        return this.point;
    }
    public JLabel getLblPlayerPoints() {
        return this.lblPlayerPoints;
    }
    // mutateur(s)
    public void setUsername(String usr) {
        this.username = usr;
    }
    public void setPoint(int pts) {
        this.point = pts;
    }
    public void setLblPlayerPoints(JLabel lbl) {
        this.lblPlayerPoints = lbl;
    }
    public void SetPlayerColor(Color uneCouleur) {
        this.playerColor = uneCouleur;
    }
    // autre(s)
    @Override
    public void actionPerformed(ActionEvent e) {
        Random rand = new Random();
        for(int i=0; i < nombreDeCases; i++) {
            if(e.getSource() == cases[i] ) {
                //JOptionPane.showMessageDialog(null, "Le bouton " + (i+1) );
                int operande1 = rand.nextInt( ( 10-1 ) + 1 - 1);
                int operande2 = rand.nextInt( ( 10-1 ) + 1 - 1);
                String operateur = "";
                int operateurHasard = rand.nextInt(3) + 1;
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
                double reponse = Double.parseDouble( JOptionPane.showInputDialog(null, "Calculez " + operande1 + operateur + operande2 + " = ?", "Question " + cases[i], JOptionPane.QUESTION_MESSAGE) );
                if( reponse == resultat ) {
                    JOptionPane.showMessageDialog(null, "BRAVO !", "SUCCES", JOptionPane.INFORMATION_MESSAGE);
                    cases[i].setBackground( playerColor );
                    cases[i].setForeground( Color.WHITE );
                    this.setPoint( this.getPoint() + 5 );
                    this.setLblPlayerPoints( new JLabel(username + " : " + this.getPoint() + " points") );
                    // verifier que le point a bien été ajouté
                    JOptionPane.showMessageDialog(null, username + " : " + this.getPoint() + " points");
                } else {
                    JOptionPane.showMessageDialog(null, "ERREUR !", "ECHEC", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
    public static class EcouteurScore extends Thread {
                // attribut(s)
        PanneauCarte refPane;
        JFrame refFrame;
        int refPoints;
                // methode(s)
        // constructeur(s)
        public EcouteurScore(PanneauCarte pane, JFrame frame) {
            this.refPane = pane;
            this.refPoints = pane.getPoint();
            this.refFrame = frame;
        }
        // accesseur(s)
        
        // mutateur(s)
        
        // autre(s)
        @Override
        public void run() {
            int compteur = 1;
            while(true) {
                try {
                    /*
                    if(this.refPane.getPoint() != refPoints) {
                        this.refPoints = this.refPane.getPoint();                        
                        this.refFrame.add( this.refPane.getLblPlayerPoints(), BorderLayout.NORTH);
                    }*/
                    JLabel tempLbl = new JLabel("Compteur = " + compteur);
                    tempLbl.setFont( new Font("Arial", Font.BOLD, 40) );
                    this.refFrame.add( tempLbl, BorderLayout.NORTH );
                    compteur++;
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PanneauCarte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    // Test
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {                
                int lignes = Integer.parseInt( JOptionPane.showInputDialog(null, "Veuillez saisir le nombre de lignes : ", "Question", JOptionPane.QUESTION_MESSAGE) );
                int colonnes = Integer.parseInt( JOptionPane.showInputDialog(null, "Veuillez saisir le nombre de colonnes : ", "Question", JOptionPane.QUESTION_MESSAGE) );
                // création du panneau principal (carte)
                PanneauCarte uneCarte = new PanneauCarte(lignes, colonnes);
                uneCarte.setUsername( JOptionPane.showInputDialog(null, "Veuillez saisir un identifiant : ", "Question", JOptionPane.QUESTION_MESSAGE) );
                // Un joueur est identifié par une couleur 
                uneCarte.SetPlayerColor( JColorChooser.showDialog(null, "Choisir une couleur", Color.WHITE) );
                // Création de la fenetre
                JFrame fen = new JFrame("Programme Test");
                fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                
                //fen.setSize(500, 600);
                fen.setExtendedState(JFrame.MAXIMIZED_BOTH);
                uneCarte.setLayout(new GridLayout(lignes, colonnes) );
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
                // création d'un panneau pour le joueur
                JPanel panneauJoueur = new JPanel( new BorderLayout(100, 200) );
                    uneCarte.setLblPlayerPoints( new JLabel(uneCarte.getUsername() + " : " + uneCarte.getPoint() + " points") );
                    uneCarte.getLblPlayerPoints().setFont( new Font("Arial", Font.PLAIN, 40) );
                panneauJoueur.add( uneCarte.getLblPlayerPoints(), BorderLayout.CENTER );
                fen.add(panneauJoueur, BorderLayout.NORTH);
                EcouteurScore t1 = new EcouteurScore(uneCarte, fen);                
                t1.start();
            }
        }) ;        
    }
}