


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
public class FenetrePouleLDC extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    public FenetrePouleLDC() {
        initComponents();
        listModelPouleA = new javax.swing.DefaultListModel<>();
        listePouleA.setModel(listModelPouleA);
        listModelPouleB = new javax.swing.DefaultListModel<>();
        listePouleB.setModel(listModelPouleB);
        listModelPouleC = new javax.swing.DefaultListModel<>();
        listePouleC.setModel(listModelPouleC);
        listModelPouleD = new javax.swing.DefaultListModel<>();
        listePouleD.setModel(listModelPouleD);
        listModelPouleE = new javax.swing.DefaultListModel<>();
        listePouleE.setModel(listModelPouleE);
        listModelPouleF = new javax.swing.DefaultListModel<>();
        listePouleF.setModel(listModelPouleF);
        listModelPouleG = new javax.swing.DefaultListModel<>();
        listePouleG.setModel(listModelPouleG);
        listModelPouleH = new javax.swing.DefaultListModel<>();
        listePouleH.setModel(listModelPouleH);
        
        consultationPoule(listModelPouleA, "A");
        consultationPoule(listModelPouleB, "B");
        consultationPoule(listModelPouleC, "C");
        consultationPoule(listModelPouleD, "D");
        consultationPoule(listModelPouleE, "E");
        consultationPoule(listModelPouleF, "F");
        consultationPoule(listModelPouleG, "G");
        consultationPoule(listModelPouleH, "H");
    }
    
    

    // Ouvre une connexion stockée dans la variable connexion
    public void openConnection(javax.swing.DefaultListModel<String> listModel) {
        connexion = ConnexionOracleFactory.creerConnexion();
        if (connexion == null) {
            listModel.addElement("Probleme de connection.");
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

    
    private void consultationPoule(javax.swing.DefaultListModel<String> listModel, String nomPoule) {
        //liste des attributs récupérés de la BDD:
        String nomClub;
        int nbPoints;
        
        //gestion des buts pour les coupes européennes?
        int nbButsMis;
        int nbButsPris;

        listModel.clear();      //listModel.removeAllElements();
        
        //FINIR LA REQUETE DES ELEMENTS !!
        try {
            openConnection(listModel);
            java.sql.Statement requete;
            requete = connexion.createStatement();
            java.sql.ResultSet query;
            query = requete.executeQuery(
                    "SELECT nomclub, ptseurope FROM club WHERE nompoule = '"+nomPoule+"' AND ldc = 1 ORDER BY ptseurope DESC");//gérer nbpointsldc
            while (query.next()) {
                nomClub = query.getString(1);
                nbPoints = query.getInt(2);

                listModel.addElement(nomClub
                        + "    "
                        + nbPoints);
            }
            query.close();
            requete.close();
            closeConnection();
        } catch (java.sql.SQLException e) {
            listModel.addElement("Erreur execution requete " + e.getMessage());
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
        pouleD = new javax.swing.JScrollPane();
        listePouleD = new javax.swing.JList();
        pouleC = new javax.swing.JScrollPane();
        listePouleC = new javax.swing.JList();
        pouleB = new javax.swing.JScrollPane();
        listePouleB = new javax.swing.JList();
        pouleA = new javax.swing.JScrollPane();
        listePouleA = new javax.swing.JList();
        pouleE = new javax.swing.JScrollPane();
        listePouleE = new javax.swing.JList();
        pouleF = new javax.swing.JScrollPane();
        listePouleF = new javax.swing.JList();
        pouleG = new javax.swing.JScrollPane();
        listePouleG = new javax.swing.JList();
        pouleH = new javax.swing.JScrollPane();
        listePouleH = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        phaseFinaleButton = new javax.swing.JButton();
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

        listePouleD.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        pouleD.setViewportView(listePouleD);

        getContentPane().add(pouleD);
        pouleD.setBounds(760, 120, 180, 130);

        listePouleC.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        pouleC.setViewportView(listePouleC);

        getContentPane().add(pouleC);
        pouleC.setBounds(570, 120, 160, 130);

        listePouleB.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        pouleB.setViewportView(listePouleB);

        getContentPane().add(pouleB);
        pouleB.setBounds(330, 120, 150, 130);

        listePouleA.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        pouleA.setViewportView(listePouleA);

        getContentPane().add(pouleA);
        pouleA.setBounds(130, 120, 140, 130);

        listePouleE.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        pouleE.setViewportView(listePouleE);

        getContentPane().add(pouleE);
        pouleE.setBounds(130, 290, 140, 130);

        listePouleF.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        pouleF.setViewportView(listePouleF);

        getContentPane().add(pouleF);
        pouleF.setBounds(330, 290, 150, 130);

        listePouleG.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        pouleG.setViewportView(listePouleG);

        getContentPane().add(pouleG);
        pouleG.setBounds(570, 290, 160, 130);

        listePouleH.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        pouleH.setViewportView(listePouleH);

        getContentPane().add(pouleH);
        pouleH.setBounds(760, 290, 180, 130);

        jLabel1.setText("Poule A");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 260, 70, 14);

        jLabel2.setText("Poule B");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(360, 260, 70, 14);

        jLabel3.setText("Poule C");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(600, 260, 80, 14);

        jLabel5.setText("Poule D");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(790, 260, 70, 14);

        jLabel6.setText("Poule H");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(790, 430, 70, 14);

        jLabel7.setText("Poule E");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(160, 430, 35, 14);

        jLabel8.setText("Poule F");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(360, 430, 35, 14);

        jLabel9.setText("Poule G");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(600, 430, 90, 14);

        phaseFinaleButton.setText("Phase finale");
        phaseFinaleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phaseFinaleButtonActionPerformed(evt);
            }
        });
        getContentPane().add(phaseFinaleButton);
        phaseFinaleButton.setBounds(420, 460, 140, 40);

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

    private void phaseFinaleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phaseFinaleButtonActionPerformed
        FenetrePhaseFinaleLDC phaseFinale;
        phaseFinale = new FenetrePhaseFinaleLDC();
        phaseFinale.setVisible(true);
    }//GEN-LAST:event_phaseFinaleButtonActionPerformed

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
    private javax.swing.JLabel background;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList listePouleA;
    private javax.swing.JList listePouleB;
    private javax.swing.JList listePouleC;
    private javax.swing.JList listePouleD;
    private javax.swing.JList listePouleE;
    private javax.swing.JList listePouleF;
    private javax.swing.JList listePouleG;
    private javax.swing.JList listePouleH;
    private javax.swing.JButton phaseFinaleButton;
    private javax.swing.JScrollPane pouleA;
    private javax.swing.JScrollPane pouleB;
    private javax.swing.JScrollPane pouleC;
    private javax.swing.JScrollPane pouleD;
    private javax.swing.JScrollPane pouleE;
    private javax.swing.JScrollPane pouleF;
    private javax.swing.JScrollPane pouleG;
    private javax.swing.JScrollPane pouleH;
    private javax.swing.JLabel retour;
    // End of variables declaration//GEN-END:variables
    private static javax.swing.DefaultListModel<String> listModelPouleA;
    private static javax.swing.DefaultListModel<String> listModelPouleB;
    private static javax.swing.DefaultListModel<String> listModelPouleC;
    private static javax.swing.DefaultListModel<String> listModelPouleD;
    private static javax.swing.DefaultListModel<String> listModelPouleE;
    private static javax.swing.DefaultListModel<String> listModelPouleF;
    private static javax.swing.DefaultListModel<String> listModelPouleG;
    private static javax.swing.DefaultListModel<String> listModelPouleH;
    private java.sql.Connection connexion;
}
