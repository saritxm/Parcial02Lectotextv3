/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Cliente.View;

/**
 *
 * @author Sara
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        setTitle("Cliente: Aplicación Inclusiva para la Lectura de Textos");
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();
        btnSalir = new javax.swing.JButton();
        btnLeer = new javax.swing.JButton();
        eTitulo = new javax.swing.JLabel();
        eLabel = new javax.swing.JLabel();
        eFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TextArea.setBackground(new java.awt.Color(250, 242, 161));
        TextArea.setColumns(20);
        TextArea.setRows(5);
        jScrollPane1.setViewportView(TextArea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 660, 200));

        btnSalir.setFont(new java.awt.Font("Stencil", 1, 50)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setText("SALIR");
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, 230, 70));

        btnLeer.setFont(new java.awt.Font("Stencil", 1, 50)); // NOI18N
        btnLeer.setForeground(new java.awt.Color(255, 255, 255));
        btnLeer.setText("Leer");
        btnLeer.setBorderPainted(false);
        btnLeer.setContentAreaFilled(false);
        jPanel1.add(btnLeer, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 430, 200, 50));

        eTitulo.setFont(new java.awt.Font("Sylfaen", 1, 24)); // NOI18N
        eTitulo.setForeground(new java.awt.Color(255, 255, 153));
        eTitulo.setText("Aplicación inclusiva para la lectura de Textos");
        jPanel1.add(eTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, -1, -1));

        eLabel.setFont(new java.awt.Font("Segoe UI Symbol", 3, 20)); // NOI18N
        eLabel.setForeground(new java.awt.Color(255, 255, 255));
        eLabel.setText("Texto a leer (Escriba BYE para terminar):");
        jPanel1.add(eLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, -1));

        eFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Cliente/View/GIF.gif"))); // NOI18N
        jPanel1.add(eFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 590));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextArea TextArea;
    public javax.swing.JButton btnLeer;
    public javax.swing.JButton btnSalir;
    private javax.swing.JLabel eFondo;
    private javax.swing.JLabel eLabel;
    private javax.swing.JLabel eTitulo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
