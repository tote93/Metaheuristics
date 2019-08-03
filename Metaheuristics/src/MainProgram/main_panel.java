/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainProgram;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author josel
 * Clase principal para representar el programa
 */
public class main_panel extends javax.swing.JFrame {

    // Variable empleada para seleccionar la heuristica a usar
    String _heuristicSelected = null;
  
    /**
     * Constructor por defecto, inicializa los elementos visuales y les modifica el estado
     * @throws IOException 
     */
    public main_panel() throws IOException {
        initComponents();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);        
        this.jpanelList.setVisible(false);
        this.txtInfo.setVisible(false);
        this.jpanelInfo.setVisible(false);
        this.startAlgorithm.setVisible(false);
        this.DisplayPanel.setVisible(true);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ParamsDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        jpanelList = new javax.swing.JScrollPane();
        listAlgorithms = new javax.swing.JList<>();
        jpanelInfo = new javax.swing.JScrollPane();
        txtInfo = new javax.swing.JTextArea();
        startAlgorithm = new javax.swing.JButton();
        DisplayPanel = new java.awt.Panel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        simpleClimb = new javax.swing.JMenuItem();
        HillClimb = new javax.swing.JMenuItem();
        SimAnn = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        testingButton = new javax.swing.JMenuItem();

        javax.swing.GroupLayout ParamsDialogLayout = new javax.swing.GroupLayout(ParamsDialog.getContentPane());
        ParamsDialog.getContentPane().setLayout(ParamsDialogLayout);
        ParamsDialogLayout.setHorizontalGroup(
            ParamsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        ParamsDialogLayout.setVerticalGroup(
            ParamsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(java.awt.Color.lightGray);
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTitle.setText("Bienvenido a Metaheuristics");
        jPanel1.add(labelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 345, 36));

        listAlgorithms.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        listAlgorithms.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Algoritmo de la mochila", "Algoritmo de mis pelotis" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listAlgorithms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listAlgorithmsMouseClicked(evt);
            }
        });
        jpanelList.setViewportView(listAlgorithms);

        jPanel1.add(jpanelList, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 330, 190));

        txtInfo.setEditable(false);
        txtInfo.setColumns(20);
        txtInfo.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtInfo.setLineWrap(true);
        txtInfo.setRows(5);
        txtInfo.setWrapStyleWord(true);
        jpanelInfo.setViewportView(txtInfo);

        jPanel1.add(jpanelInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 330, 190));

        startAlgorithm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        startAlgorithm.setText("Iniciar");
        startAlgorithm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startAlgorithmMouseClicked(evt);
            }
        });
        jPanel1.add(startAlgorithm, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, -1, -1));

        DisplayPanel.setBackground(new java.awt.Color(0, 102, 102));

        javax.swing.GroupLayout DisplayPanelLayout = new javax.swing.GroupLayout(DisplayPanel);
        DisplayPanel.setLayout(DisplayPanelLayout);
        DisplayPanelLayout.setHorizontalGroup(
            DisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        DisplayPanelLayout.setVerticalGroup(
            DisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Abrir archivo");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("jMenuItem2");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Heurísticas");

        simpleClimb.setText("Búsqueda Local");
        simpleClimb.setToolTipText("");
        simpleClimb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                simpleClimbMousePressed(evt);
            }
        });
        jMenu2.add(simpleClimb);

        HillClimb.setText("Escalada Máxima Pendiente");
        jMenu2.add(HillClimb);

        SimAnn.setText("Enfriamiento Simulado");
        jMenu2.add(SimAnn);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Extra");

        testingButton.setText("Testing");
        testingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                testingButtonMousePressed(evt);
            }
        });
        testingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                testingButtonActionPerformed(evt);
            }
        });
        jMenu3.add(testingButton);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DisplayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
            .addComponent(DisplayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Función usada para agilizar el proceso de depuración
     * @param evt 
     */
    private void testingButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_testingButtonMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_testingButtonMousePressed

    /**
     * Función ejecutada al seleccionar la heuristica de Climb
     * @param evt 
     */
    private void simpleClimbMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_simpleClimbMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_simpleClimbMousePressed

    /**
     * Función con la que obtenemos el algoritmo seleccionado de la lista
     * @param evt 
     */
    private void listAlgorithmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listAlgorithmsMouseClicked
        // TODO add your handling code here:           
    }//GEN-LAST:event_listAlgorithmsMouseClicked
    /**
     * Una vez obtenidos la heuristica y el algoritmo, se ejecutará dicha función, iniciando el algoritmo
     * @param evt 
     */
    private void startAlgorithmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startAlgorithmMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_startAlgorithmMouseClicked

    private void testingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_testingButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_testingButtonActionPerformed
    

    
    /**
     * Se realizan las comprobaciones iniciales y en caso de que no se cumpla, se ejecuta el método para crear la jerarquia
     * @see Reset
     * @throws IOException 
     */
    public final void InitialCheck() throws IOException{       
    }
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
            java.util.logging.Logger.getLogger(main_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new main_panel().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(main_panel.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Panel DisplayPanel;
    private javax.swing.JMenuItem HillClimb;
    private javax.swing.JDialog ParamsDialog;
    private javax.swing.JMenuItem SimAnn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jpanelInfo;
    private javax.swing.JScrollPane jpanelList;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JList<String> listAlgorithms;
    private javax.swing.JMenuItem simpleClimb;
    private javax.swing.JButton startAlgorithm;
    private javax.swing.JMenuItem testingButton;
    private javax.swing.JTextArea txtInfo;
    // End of variables declaration//GEN-END:variables
}

/*
                System.out.println("Hola");
                ClimbInstance ci = new ClimbInstance();
        try {
            ci.readInstance("/src/Algorithms/Climb/fichero.txt", 10);
        } catch (IOException ex) {
            Logger.getLogger(main_panel.class.getName()).log(Level.SEVERE, null, ex);
        }

*/