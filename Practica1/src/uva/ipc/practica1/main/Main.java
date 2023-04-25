package uva.ipc.practica1.main;

import uva.ipc.practica1.modelo.Biblioteca;
import uva.ipc.practica1.vista.GestorVistas;
import uva.ipc.practica1.vista.VistaEdicionManual;

/**
 * Clase Main
 * @author dansana
 * @author saant
 */
public class Main{
    private static GestorVistas gestor;
    private static Biblioteca biblioteca;

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
            java.util.logging.Logger.getLogger(VistaEdicionManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaEdicionManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaEdicionManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaEdicionManual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
        gestor = new GestorVistas();
        biblioteca = new Biblioteca();
        gestor.mostrarVistaMenu();
    }
    public static GestorVistas getGestorVistas(){
            return gestor;
    }
    public static Biblioteca getBiblioteca(){
            return biblioteca;
    }    
}


