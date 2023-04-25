package uva.ipc.practica1.vista;

import javax.swing.JFrame;
import uva.ipc.practica1.modelo.Biblioteca;
//import uva.ipc.practica1.vista.VistaMenu;
/**
 *
 * @author Daniel
 */
public class GestorVistas {
    private JFrame vistaActual;
    private Biblioteca miBiblioteca;
    
    public void mostrarVistaBuscador(){
        if(vistaActual != null){
            vistaActual.setVisible(false);
            vistaActual.dispose();
        }
        vistaActual = new VistaBuscador();
        vistaActual.setVisible(true);
    }

    public void mostrarVistaMenu(){
        if(vistaActual != null){
            vistaActual.setVisible(false);
            vistaActual.dispose();
        }
        vistaActual = new VistaMenu();
        vistaActual.setVisible(true);
//        vistaActual.mostrarMensaje(miBiblioteca.getNumLibros(), miBiblioteca.getLibrosLeidos(),miBiblioteca.getLibrosNoLeidos(),miBiblioteca.getPrecioMedio());
    }
    
    public void mostrarVistaEdicionManual(){
        if(vistaActual != null){
            vistaActual.setVisible(false);
            vistaActual.dispose();
        }
        vistaActual = new VistaEdicionManual();
        vistaActual.setVisible(true);
    }
}