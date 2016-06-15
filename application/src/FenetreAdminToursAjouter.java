


import connexion.ConnexionOracleFactory;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rémy
 */
public class FenetreAdminToursAjouter extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    public FenetreAdminToursAjouter() {
        initComponents();
        compteNbClub("France");
        afficheCoupe();
        affichePays();
    }

    private void afficheCoupe() {
        nomCoupeBox.addItem("CN");
        nomCoupeBox.addItem("LDC");
        nomCoupeBox.addItem("LE");
        nomCoupeBox.setSelectedIndex(0);
    }

    private void affichePays() {
        paysBox.addItem("France");
        paysBox.addItem("Italie");
        paysBox.addItem("Espagne");
        paysBox.addItem("Pays-Bas");
        paysBox.addItem("Allemagne");
        paysBox.addItem("Angleterre");
        paysBox.addItem("Russie");
        paysBox.addItem("Portugal");
        paysBox.addItem("Europe");
        paysBox.setSelectedIndex(0);
    }

    // Ouvre une connexion stockée dans la variable connexion
    public void openConnection() {
        connexion = ConnexionOracleFactory.creerConnexion();
        if (connexion == null) {
            System.exit(1);
        }
    }

    public void closeConnection() {
        try {
            connexion.close();	// Fermeture de la connexion
        } catch (java.sql.SQLException e) {
            System.out.println("ERREUR ORACLE" + e.getMessage());
        }
    }

    

    private void consultationClubPays(String nomPays) {
        //liste des attributs récupérés de la BDD:
        String nomClub1;
        //String nomClub2;

        try {
            openConnection();
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet query;

            
            if(nomPays.equalsIgnoreCase("Europe"))
            {
                if(nomCoupeBox.getSelectedItem().toString().equalsIgnoreCase("ldc"))
                {
                    query = requete.executeQuery(
                        "SELECT DISTINCT nomclub FROM club WHERE ldc = 1 ORDER BY nomclub ASC");
                }
                else
                {
                    query = requete.executeQuery(
                        "SELECT DISTINCT nomclub FROM club WHERE le = 1 ORDER BY nomclub ASC");
                }
                
            }
            else
            {
                query = requete.executeQuery(
                 "SELECT DISTINCT nomclub FROM club WHERE nompays = '" + nomPays + "' AND coupe = 1 ORDER BY nomclub ASC"); 
            }

            
            
            while (query.next()) {
                nomClub1 = query.getString(1);
                //nomClub2 = query.getString(1);

                club1Box.addItem(nomClub1);
                //club2Box.addItem(nomClub2);
            }

            club1Box.setSelectedIndex(0);
            consultationClub(club1Box.getSelectedItem().toString(), nomPays);
            query.close();
            requete.close();
            closeConnection();
            compteNbClub(nomPays);
        } catch (java.sql.SQLException e) {

        }
    }
    
    
    

    private void consultationClub(String nomClub1, String nomPays) {
        //liste des attributs récupérés de la BDD:
        String nomClub2;

        try {
            openConnection();
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet query;

            
            if(nomPays.equalsIgnoreCase("Europe"))
            {
                if(nomCoupeBox.getSelectedItem().toString().equalsIgnoreCase("ldc"))
                {
                    query = requete.executeQuery(
                        "SELECT DISTINCT nomclub FROM club WHERE ldc = 1 AND nomclub != '"+nomClub1+"' ORDER BY nomclub ASC");
                }
                else
                {
                    query = requete.executeQuery(
                        "SELECT DISTINCT nomclub FROM club WHERE le = 1 AND nomclub != '"+nomClub1+"' ORDER BY nomclub ASC");
                }
                
            }
            else
            {
                query = requete.executeQuery(
                    "SELECT DISTINCT nomclub FROM club WHERE nompays = '" + nomPays + "' AND coupe = 1 AND nomclub != '"+nomClub1+"' ORDER BY nomclub ASC");
            }
            
            while (query.next()) {
                nomClub2 = query.getString(1);

                club2Box.addItem(nomClub2);
            }

            query.close();
            requete.close();
            closeConnection();
        } catch (java.sql.SQLException e) {

        }
    }
    
    private void compteNbClub(String nomPays) {
        //liste des attributs récupérés de la BDD:
        int nbClubs=0;

        try {
            openConnection();
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet query;

            
            if(nomPays.equalsIgnoreCase("Europe"))
            {
                if(nomCoupeBox.getSelectedItem().toString().equalsIgnoreCase("ldc"))
                {
                    query = requete.executeQuery(
                        "SELECT DISTINCT count(nomclub) FROM club WHERE ldc = 1");
                }
                else
                {
                    query = requete.executeQuery(
                        "SELECT DISTINCT count(nomclub) FROM club WHERE le = 1");
                }
                
            }
            else
            {
                query = requete.executeQuery(
                    "SELECT DISTINCT count(nomclub) FROM club WHERE nompays = '" + nomPays + "' AND coupe = 1");
            }
            
            while (query.next()) {
                nbClubs = query.getInt(1);
            }

            query.close();
            requete.close();
            closeConnection();
            
            
            calculTour(nbClubs);
            
        } catch (java.sql.SQLException e) {

        }
    }
    
    private void calculTour(int nbClubs)
    {
        switch (nbClubs)
        {
            case 2:
                tourTextField.setText("Finale");//
                tour = 1000;                 
                break;
            case 4:
                tourTextField.setText("Demies");// 
                tour = 0.5;
                break;
            case 8:
                tourTextField.setText("Quarts");//
                tour = 0.25;
                break;
            case 16:
                tourTextField.setText("Huitièmes");//
                tour = 0.125;
                break;
            case 32:
                tourTextField.setText("Seizièmes");// 
                tour = 0.0625;
                break;
            case 64:
                tourTextField.setText("Trentesdeuxièmes");// 
                tour = 0.03125;
                break;
            default:
                tourTextField.setText("Tour Préliminaire");// 
                tour = 0;
                break;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        retour = new javax.swing.JLabel();
        club1Box = new javax.swing.JComboBox();
        paysBox = new javax.swing.JComboBox();
        club2Box = new javax.swing.JComboBox();
        ajouterConfrontationButton = new javax.swing.JButton();
        nomCoupeBox = new javax.swing.JComboBox();
        tourTextField = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 533));
        getContentPane().setLayout(null);

        retour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fleche-retour.png"))); // NOI18N
        retour.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                retourMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                retourMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                retourMousePressed(evt);
            }
        });
        getContentPane().add(retour);
        retour.setBounds(910, 400, 70, 70);

        club1Box.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                club1BoxmodifierClub1(evt);
            }
        });
        getContentPane().add(club1Box);
        club1Box.setBounds(410, 250, 200, 30);

        paysBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paysBoxmodifierPays(evt);
            }
        });
        getContentPane().add(paysBox);
        paysBox.setBounds(140, 250, 110, 30);

        club2Box.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                club2BoxActionPerformed(evt);
            }
        });
        getContentPane().add(club2Box);
        club2Box.setBounds(650, 250, 200, 30);

        ajouterConfrontationButton.setText("Ajouter");
        ajouterConfrontationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterConfrontationButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ajouterConfrontationButton);
        ajouterConfrontationButton.setBounds(410, 410, 130, 50);

        nomCoupeBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nomCoupeBoxmodifCoupe(evt);
            }
        });
        getContentPane().add(nomCoupeBox);
        nomCoupeBox.setBounds(280, 250, 110, 30);

        tourTextField.setText("tour");
        getContentPane().add(tourTextField);
        tourTextField.setBounds(740, 320, 60, 50);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/background.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 1000, 506);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void retourMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retourMouseClicked
        ImageIcon i = new ImageIcon(getClass().getResource("/Images/fleche-retour_p.png"));
        retour.setIcon(i);
        
        this.setVisible(false);
    }//GEN-LAST:event_retourMouseClicked

    private void retourMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retourMouseExited
        ImageIcon i = new ImageIcon(getClass().getResource("/Images/fleche-retour.png"));
        retour.setIcon(i);
    }//GEN-LAST:event_retourMouseExited

    private void retourMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_retourMousePressed
        ImageIcon i = new ImageIcon(getClass().getResource("/Images/fleche-retour_p.png"));
        retour.setIcon(i);
    }//GEN-LAST:event_retourMousePressed

    private void club1BoxmodifierClub1(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_club1BoxmodifierClub1
        club2Box.removeAllItems();
        consultationClub(club1Box.getSelectedItem().toString(), paysBox.getSelectedItem().toString());
    }//GEN-LAST:event_club1BoxmodifierClub1

    private void paysBoxmodifierPays(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paysBoxmodifierPays
        try{
            club1Box.removeAllItems();
            club2Box.removeAllItems();
            consultationClubPays(paysBox.getSelectedItem().toString());
        }
        catch(Exception e)
        {
            //System.out.println("erreur modifierPays : "+e);
        }
    }//GEN-LAST:event_paysBoxmodifierPays

    private void ajouterConfrontationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterConfrontationButtonActionPerformed
        String nomClub;

        try {
            openConnection();
            java.sql.PreparedStatement  requete;
            requete = connexion.prepareStatement (
                "INSERT INTO matchfootball (nomclub1, nomclub2, no_journee, nom_competition) VALUES ('"+club1Box.getSelectedItem().toString()+"', '"+club2Box.getSelectedItem().toString()+"', "+tour+", '"+nomCoupeBox.getSelectedItem().toString()+"')");

            requete.executeQuery();
            requete.close();
            closeConnection();
        } catch (java.sql.SQLException e) {
            //System.out.println("erreur ajouterButton : "+e);
        }
    }//GEN-LAST:event_ajouterConfrontationButtonActionPerformed

    private void nomCoupeBoxmodifCoupe(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_nomCoupeBoxmodifCoupe
        try{
            paysBox.removeAllItems();
            club1Box.removeAllItems();
            club2Box.removeAllItems();
            if(nomCoupeBox.getSelectedItem().toString().equalsIgnoreCase("CN"))
            {
                paysBox.addItem("France");
                paysBox.addItem("Italie");
                paysBox.addItem("Espagne");
                paysBox.addItem("Pays-Bas");
                paysBox.addItem("Allemagne");
                paysBox.addItem("Angleterre");
                paysBox.addItem("Russie");
                paysBox.addItem("Portugal");
                paysBox.setSelectedIndex(0);
                consultationClubPays(paysBox.getSelectedItem().toString());

                if(!paysBox.isEnabled()) paysBox.setEnabled(true);
            }
            else
            {
                paysBox.addItem("Europe");
                consultationClubPays(paysBox.getSelectedItem().toString());

                paysBox.setEnabled(false);
            }
        }
        catch(Exception e)
        {
            //System.out.println("erreur modifCoupe : "+e);
        }
    }//GEN-LAST:event_nomCoupeBoxmodifCoupe

    private void club2BoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_club2BoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_club2BoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Test2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test2().setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ajouterConfrontationButton;
    private javax.swing.JLabel background;
    private javax.swing.JComboBox club1Box;
    private javax.swing.JComboBox club2Box;
    private javax.swing.JComboBox nomCoupeBox;
    private javax.swing.JComboBox paysBox;
    private javax.swing.JLabel retour;
    private javax.swing.JTextField tourTextField;
    // End of variables declaration//GEN-END:variables
    private java.sql.Connection connexion;
    private double tour;

}
