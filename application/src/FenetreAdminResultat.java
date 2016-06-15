


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
public class FenetreAdminResultat extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    public FenetreAdminResultat() {
        initComponents();
        consultationPays();
        afficheTours();
    }
    
    private void afficheTours()
    {
        journeeOuToursBox.addItem("Finale");
        journeeOuToursBox.addItem("Demies");
        journeeOuToursBox.addItem("Quarts");
        journeeOuToursBox.addItem("Huitièmes");
        journeeOuToursBox.addItem("Seizièmes");
        journeeOuToursBox.addItem("Trentesdeuxièmes");
        journeeOuToursBox.addItem("Tour Préliminaire");
    }
    
    private void afficheJournee()
    {
        int nbEquipes = compteNbClub(paysBox.getSelectedItem().toString());
        
        for(int i = 1; i <= (nbEquipes-1)*2; i++)
        {
            journeeOuToursBox.addItem(i);
        }
    }
    
    private int compteNbClub(String nomPays) {
        //liste des attributs récupérés de la BDD:
        int nbClubs=0;

        try {
            openConnection();
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet query;

            
            if(nomPays.equalsIgnoreCase("Europe"))
            {
                /*pour gerer le tour
                if(competitionBox.getSelectedItem().toString().equalsIgnoreCase("ldc"))
                {
                    query = requete.executeQuery(
                        "SELECT DISTINCT count(nomclub) FROM club WHERE ldc = 1");
                }
                else
                {
                    query = requete.executeQuery(
                        "SELECT DISTINCT count(nomclub) FROM club WHERE le = 1");
                }
                */
            }            
            else if(competitionBox.getSelectedItem().toString().equalsIgnoreCase("CN"))
            {
                /*pour gerer le tour
                query = requete.executeQuery(
                    "SELECT DISTINCT count(nomclub) FROM club WHERE nompays = '" + nomPays + "' AND coupe = 1");
                */
            }
            else
            {
                query = requete.executeQuery(
                    "SELECT DISTINCT count(nomclub) FROM club WHERE nompays = '" + nomPays + "' AND division = '"+competitionBox.getSelectedItem().toString()+"'");
            
                while (query.next()) {
                    nbClubs = query.getInt(1);
                    
                }
                query.close();
                requete.close();
                closeConnection();
            }
            /*
            while (query.next()) {
                nbClubs = query.getInt(1);
            }

            query.close();
            requete.close();
            closeConnection();*/
            return nbClubs;
            
        } catch (java.sql.SQLException e) {

        }
            return nbClubs;
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

    
    private void consultationPays() {
        //liste des attributs récupérés de la BDD:
        String nomPays;        
        

        try {
            openConnection();
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet query;
            
            
            query = requete.executeQuery(
            "SELECT DISTINCT nompays FROM club ORDER BY nompays ASC");
            
            
            while (query.next()) {
                nomPays = query.getString(1);

                paysBox.addItem(nomPays);
            }
            
            paysBox.addItem("Europe");
            paysBox.setSelectedIndex(0);
                
            query.close();
            requete.close();
            closeConnection();
        } catch (java.sql.SQLException e) {
            
        }
    }
    
    
    private void consultationCompetition(String nomPays) {
        
        String nomCompetition;        
        
        try {
            openConnection();
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet query;
            
            if(nomPays.equalsIgnoreCase("Europe"))
            {
                competitionBox.addItem("LDC");
                competitionBox.addItem("LE");
                competitionBox.setSelectedIndex(0);
            }
            else
            {
                query = requete.executeQuery(
                    "SELECT DISTINCT division FROM club WHERE nompays = '"+nomPays+"' ORDER BY division ASC");
                        
            
                while (query.next()) {
                    nomCompetition = query.getString(1);

                    competitionBox.addItem(nomCompetition);
                }

                competitionBox.addItem("CN");
                competitionBox.setSelectedIndex(2);
                query.close();
                requete.close();
                closeConnection();
            }
            
        } catch (java.sql.SQLException e) {
            
        }
    }

    

    private void consultationClubPaysTours(String nomPays) {
        //liste des attributs récupérés de la BDD:
        String nomClubs;
        //String nomClub2;
        
        double tour;

        try {
            openConnection();
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet query;
            
                    
            
            
            switch(journeeOuToursBox.getSelectedItem().toString())
            {
                case "Finale":
                    tour = 1000;
                    break;
                case "Demies":
                    tour = 0.5;
                    break;
                case "Quarts":
                    tour = 0.25;
                    break;
                case "Huitièmes":
                    tour = 0.125;
                    break;
                case "Seizièmes":
                    tour = 0.0625;
                    break;
                case "Trentesdeuxièmes":
                    tour = 0.03125;
                    break;
                default :
                    tour = 0;
                    break;
            }           
            
            
            if(nomPays.equalsIgnoreCase("Europe"))
            {
                query = requete.executeQuery(
                    "SELECT DISTINCT nomclub1, nomclub2 FROM matchfootball WHERE nom_competition = '"+competitionBox.getSelectedItem().toString()+"' AND no_journee = "+tour+" ORDER BY nomclub1 ASC");
            }
            else if(competitionBox.getSelectedItem().toString().equalsIgnoreCase("CN"))
            {
                query = requete.executeQuery(
                 "SELECT DISTINCT nomclub1, nomclub2 FROM matchfootball WHERE nom_competition = 'coupe" + nomPays + "' AND no_journee = "+tour+" ORDER BY nomclub1 ASC"); 
            }
            else
            {
                query = requete.executeQuery(
                 "SELECT DISTINCT nomclub1, nomclub2 FROM matchfootball WHERE nom_competition = '" + competitionBox.getSelectedItem().toString() + "' AND no_journee = "+journeeOuToursBox.getSelectedItem()+" ORDER BY nomclub1 ASC"); 
            }
                
            while (query.next()) {
                nomClubs = query.getString(1)+" | "+query.getString(2);
                //nomClub2 = query.getString(1);

                confrotationBox.addItem(nomClubs);
                //club2Box.addItem(nomClub2);
            }

            confrotationBox.setSelectedIndex(0);
            query.close();
            requete.close();
            closeConnection();
        } catch (java.sql.SQLException e) {
            System.out.println("\n erreur : "+e+'\n');
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
        paysBox = new javax.swing.JComboBox();
        competitionBox = new javax.swing.JComboBox();
        journeeOuToursBox = new javax.swing.JComboBox();
        confrotationBox = new javax.swing.JComboBox();
        butDom = new javax.swing.JTextField();
        butExt = new javax.swing.JTextField();
        ajouterResultatConfrontation = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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

        paysBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paysBoxchoixPays(evt);
            }
        });
        getContentPane().add(paysBox);
        paysBox.setBounds(90, 210, 210, 50);

        competitionBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                competitionBoxmodifCompet(evt);
            }
        });
        getContentPane().add(competitionBox);
        competitionBox.setBounds(330, 210, 160, 50);

        journeeOuToursBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                journeeOuToursBoxmodifTourJournee(evt);
            }
        });
        getContentPane().add(journeeOuToursBox);
        journeeOuToursBox.setBounds(510, 210, 120, 50);
        getContentPane().add(confrotationBox);
        confrotationBox.setBounds(640, 210, 310, 50);

        butDom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                butDomKeyTyped(evt);
            }
        });
        getContentPane().add(butDom);
        butDom.setBounds(300, 320, 110, 40);

        butExt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                butExtKeyTyped(evt);
            }
        });
        getContentPane().add(butExt);
        butExt.setBounds(550, 320, 120, 40);

        ajouterResultatConfrontation.setText("Ajouter");
        ajouterResultatConfrontation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ajouterResultatConfrontationActionPerformed(evt);
            }
        });
        getContentPane().add(ajouterResultatConfrontation);
        ajouterResultatConfrontation.setBounds(420, 410, 140, 50);

        jLabel1.setText("nbButsHotes :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(320, 300, 68, 14);

        jLabel2.setText("nbButsVisiteurs :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(570, 300, 80, 14);

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

    private void paysBoxchoixPays(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paysBoxchoixPays
        try
        {
            competitionBox.removeAllItems();
            consultationCompetition(paysBox.getSelectedItem().toString());
            consultationClubPaysTours(paysBox.getSelectedItem().toString());
        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_paysBoxchoixPays

    private void competitionBoxmodifCompet(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_competitionBoxmodifCompet
        try{
            journeeOuToursBox.removeAllItems();
            if(!competitionBox.getSelectedItem().toString().equalsIgnoreCase("CN") && !competitionBox.getSelectedItem().toString().equalsIgnoreCase("LDC") && !competitionBox.getSelectedItem().toString().equalsIgnoreCase("LE")) afficheJournee();
            else afficheTours();
            consultationClubPaysTours(paysBox.getSelectedItem().toString());
        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_competitionBoxmodifCompet

    private void journeeOuToursBoxmodifTourJournee(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_journeeOuToursBoxmodifTourJournee
        try{
            confrotationBox.removeAllItems();
            consultationClubPaysTours(paysBox.getSelectedItem().toString());
        }
        catch(Exception e)
        {

        }
    }//GEN-LAST:event_journeeOuToursBoxmodifTourJournee

    private void butDomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_butDomKeyTyped
        char t = evt.getKeyChar();
        if(t < 48 || t > 57)
        {
            evt.consume();
        }
        else
        {
        }
    }//GEN-LAST:event_butDomKeyTyped

    private void butExtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_butExtKeyTyped
        char t = evt.getKeyChar();
        if(t < 48 || t > 57)
        {
            evt.consume();
        }
        else
        {
        }
    }//GEN-LAST:event_butExtKeyTyped

    private void ajouterResultatConfrontationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ajouterResultatConfrontationActionPerformed
        String club1;
        String club2;
        String vainqueur;
        String perdant;
        String conf = confrotationBox.getSelectedItem().toString();

        club1 = conf.substring(0, conf.indexOf("|")-1);        
        club2 = conf.substring(conf.indexOf("|")+2);

        int butHote = Integer.parseInt(butDom.getText());
        int butVisiteur = Integer.parseInt(butExt.getText());
        
        int diff;



        if(butHote > butVisiteur)
        {
            vainqueur = club1;
            perdant = club2;
        }
        else if(butHote < butVisiteur)
        {
            vainqueur = club2;
            perdant = club1;
        }
        else//égalité
        {
            vainqueur = "aucun";
            perdant = "aucun";
        }
        
        //gerer les vainqueur et perdants
        try {
            openConnection();
            java.sql.PreparedStatement requete;
            //requete = connexion.prepareStatement (
            //    "UPDATE matchfootball SET vainqueur = 'Lyon', perdant = 'Guingamp', BUT_CLUB1 = 5, BUT_CLUB2 = 2 WHERE nomclub1 = 'Lyon' AND nomclub2 = 'Guingamp'");
            requete = connexion.prepareStatement (
                "UPDATE matchfootball SET vainqueur = ?, perdant = ?, BUT_CLUB1 = ?, BUT_CLUB2 = ? WHERE nomclub1 = ? AND nomclub2 = ?");
//SELECT * FROM matchfootball WHERE nomclub1 = 'Lyon' AND NOMCLUB2 = 'Guingamp';

            requete.setString(1, vainqueur);
            requete.setString(2, perdant);
            requete.setInt(3, butHote);
            requete.setInt(4, butVisiteur);
            requete.setString(5, club1);
            requete.setString(6, club2);
            requete.executeUpdate();         
            
            requete.close();
            closeConnection();
            
        } catch (java.sql.SQLException e) {
            System.out.println("erreur ajouterButton1 : "+e);
        }
        
        //pour gérer les points et l'elimination de coupe :
        try {
            openConnection();
            java.sql.PreparedStatement  requete;
            
            
            //commentaire : pour gérer si on modifie en coupe ou championnat
            //if(paysBox.getSelectedItem().toString().equalsIgnoreCase("Europe")){}
            //else if(competitionBox.getSelectedItem().toString().equalsIgnoreCase("CN")){}
            //else{}
            
                
            diff = butHote-butVisiteur;
            if(diff < 0) diff = -diff;
            
            if(vainqueur.equals("aucun"))
            {
                requete = connexion.prepareStatement (
                    "UPDATE club SET ptschamp = ptschamp + 1 where nomclub = ? OR nomclub = ?");
                requete.setString(1, club1);
                requete.setString(2, club2);
            }
            else if(vainqueur.equals(club1))
            {
                requete = connexion.prepareStatement (
                    "UPDATE club SET ptschamp = ptschamp + 3, butchamp = butchamp + "+diff+" where nomclub = ?");
                requete.setString(1, club1);
                requete.executeUpdate();  
                
                requete = connexion.prepareStatement (
                    "UPDATE club SET butchamp = butchamp - "+diff/2+"  where nomclub = ?");
                requete.setString(1, club2);
                requete.executeUpdate();
                
                
            }
            else
            {
                requete = connexion.prepareStatement (
                    "UPDATE club SET ptschamp = ptschamp + 3, butchamp = butchamp + "+diff+" where nomclub = ?");
                requete.setString(1, club2);
                requete.executeUpdate();  
                
                
                requete = connexion.prepareStatement (
                    "UPDATE club SET butchamp = butchamp - "+diff/2+"  where nomclub = ?");
                requete.setString(1, club1);
                requete.executeUpdate();
            }
            

            requete.executeUpdate();          
            
            requete.close();
            closeConnection();
        } catch (java.sql.SQLException e) {
            System.out.println("erreur ajouterButton2 : "+e);
        }
    }//GEN-LAST:event_ajouterResultatConfrontationActionPerformed

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
    private javax.swing.JButton ajouterResultatConfrontation;
    private javax.swing.JLabel background;
    private javax.swing.JTextField butDom;
    private javax.swing.JTextField butExt;
    private javax.swing.JComboBox competitionBox;
    private javax.swing.JComboBox confrotationBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox journeeOuToursBox;
    private javax.swing.JComboBox paysBox;
    private javax.swing.JLabel retour;
    // End of variables declaration//GEN-END:variables
    private java.sql.Connection connexion;
}
