package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
            // attribut(s)
    
            // methode(s)
    // constructeur(s)

    // accesseur(s)
    
    // mutateur(s)
    
    // autre(s)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame fenetrePrincipal = new JFrame("Page d'accueil");
                fenetrePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                fenetrePrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);// source de : http://stackoverflow.com/questions/11570356/jframe-in-full-screen-java
                fenetrePrincipal.setVisible(true);
                fenetrePrincipal.setLocationRelativeTo(null);
                
                String username = JOptionPane.showInputDialog(null,"Veuillez saisir votre nom : ", "Question", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Bienvenue " + username + " à Vortex & Portal !", "Info", JOptionPane.PLAIN_MESSAGE);
                // Les lignes suivantes : la construction de la carte de jeu
                int nbrLignes = Integer.parseInt( JOptionPane.showInputDialog(null, "Veuillez saisir le nombre de lignes : ", "Question", JOptionPane.QUESTION_MESSAGE) );
                int nbrColonnes = Integer.parseInt( JOptionPane.showInputDialog(null, "Veuillez saisir le nombre de colonnes : ", "Question", JOptionPane.QUESTION_MESSAGE) );
                    // création d'une liste de boutons (cases du jeu)
                    ArrayList<JButton> listeDeBoutons = new ArrayList<>();
                    /*for(int i=1; i < (nbrLignes*nbrColonnes); i++) {
                        listeDeBoutons.add( new JButton("Case " + i) );
                    }*/
                JPanel map = new JPanel();
                map.setLayout( new GridLayout(nbrLignes, nbrColonnes) );
                int cpt = nbrLignes*nbrColonnes;
                boolean leftToRight = true;
                if( cpt % 2 == 0 ) {
                    for(int i=1; i <= nbrLignes; i++) {
                        if( leftToRight ) {
                            for(int j=1; j <= nbrColonnes; j++) {
                                if( cpt == nbrLignes * nbrColonnes ) {
                                    JButton special = new JButton("Case " + cpt);
                                    special.setBackground(Color.YELLOW);
                                    listeDeBoutons.add( special );
                                    map.add( special );                                    
                                } else {
                                    listeDeBoutons.add(new JButton("Case " + cpt));
                                    map.add( listeDeBoutons.get( listeDeBoutons.size() - 1 ) );
                                }
                                cpt--;                                
                            }
                            cpt = cpt - nbrColonnes + 1;
                        } else {                        
                            for(int j=0; j < nbrColonnes; j++) {
                                listeDeBoutons.add(new JButton("Case " + cpt));
                                map.add( listeDeBoutons.get( listeDeBoutons.size() - 1 ) );
                                cpt++;
                            }                        
                            cpt = cpt - (nbrColonnes+1);
                        }                        
                        leftToRight = !leftToRight;
                    }                
                } else {
                    for(int i=0; i < nbrLignes; i++) {                        
                        if( leftToRight ) {
                            cpt = cpt - nbrColonnes + 1;
                            for(int j=0; j < nbrColonnes; j++) {
                                if(cpt == nbrLignes * nbrColonnes) {
                                    JButton special = new JButton("Case " + cpt);
                                    special.setBackground(Color.YELLOW);
                                    map.add(special);
                                } else {
                                    map.add(new JButton("Case " + cpt));
                                }
                                cpt++;
                            }                                                    
                        } else {                        
                            cpt = cpt - (nbrColonnes + 1);
                            for(int j=0; j < nbrColonnes; j++) {
                                map.add(new JButton("Case " + cpt));
                                cpt--;
                            }
                        }                        
                        leftToRight = !leftToRight;
                    }
                }
                    // Ajouter un ActionListener aux boutons
                    for(int i=0; i < listeDeBoutons.size(); i++) {
                        listeDeBoutons.get(i).addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null, "Un bouton a été cliqué !", "Message", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });                        
                    }
                fenetrePrincipal.add(map, BorderLayout.CENTER);
                
                // Création d'un label pour afficher le nom de l'utilisateur
                JLabel lblUsername = new JLabel("Connecté : " + username);
                fenetrePrincipal.add( lblUsername, BorderLayout.NORTH);        
                
                // Les lignes suivantes : Création des menus
                JMenuBar barreDesMenus = new JMenuBar();
                    JMenu mFichier = new JMenu("Fichier");
                        JMenuItem miQuitter = new JMenuItem("Quitter");
                        miQuitter.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(null, "Bye Bye " + username + " !! :D", "Avis", JOptionPane.QUESTION_MESSAGE);
                                System.exit(0);                        
                            }                    
                        });
                    mFichier.add( miQuitter );
                    
                    JMenu mJeu = new JMenu("Jeu");
                        JMenuItem miDemarrer = new JMenuItem("Démarrer");
                        miDemarrer.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JFrame fenetreJeu = new JFrame("Vortex & Portal");
                                fenetreJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                //fenetreJeu.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                fenetreJeu.setSize(500, 500);
                                fenetreJeu.setVisible(true);
                                fenetreJeu.setLocationRelativeTo( null );
                            }
                        });
                        JMenuItem miRoulerDe = new JMenuItem("Rouler dé");
                        int max = Integer.parseInt( JOptionPane.showInputDialog(null, "Veuillez saisir le nombre de faces : ", "Question", JOptionPane.QUESTION_MESSAGE) );
                        int min = 1;
                        miRoulerDe.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Random rand = new Random();
                                int faceDebout = rand.nextInt((max - min) + 1) + min;
                                JOptionPane.showMessageDialog(null, "Le dé est tombé sur " + faceDebout, "Info", JOptionPane.INFORMATION_MESSAGE);
                            }                            
                        });
                    mJeu.add( miDemarrer );
                    mJeu.add( miRoulerDe );
                barreDesMenus.add(mFichier);
                barreDesMenus.add( mJeu );
                fenetrePrincipal.setJMenuBar( barreDesMenus );
                
                JButton btnTestImage = new JButton("Test", new ImageIcon("./images/Koala.jpg") );
                fenetrePrincipal.add( btnTestImage, BorderLayout.SOUTH);
            }            
        });
    }
}
