/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uva.ipc.practica1.vista;

import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import uva.ipc.practica1.main.Main;
import uva.ipc.practica1.modelo.Biblioteca;
import uva.ipc.practica1.modelo.Libro;

/**
 *
 * @author saant
 */
public class ControladorBuscador {
    private VistaBuscador miVista;
    private Biblioteca miBiblioteca;
    Biblioteca biblioteca;
    //iniciar el programa con un fichero propio del programa. así ya tendría 
    
    /**
     * Constructor por defecto de la clase ControladorPractica1
     * @param v objeto de la clase VistaEdicionManual
     */
    
    public ControladorBuscador(VistaBuscador v){
        this.miVista=v;
        this.miBiblioteca=new Biblioteca();
        biblioteca = Main.getBiblioteca();
    }

    public void procesaEventoEditarLibro() {
        //poner todo a punto para que salga ya todo para editar
        Main.getGestorVistas().mostrarVistaEdicionManual();
    }

    public void procesaEventoBuscar(DefaultListModel <String> lista,boolean buscarTitulo) {
        //terminar
        String cadena=miVista.getCadenaBuscar();
        String [][] datosTabla=miBiblioteca.buscaLibros(buscarTitulo,cadena);
        miVista.cargarTabla(datosTabla);
        
    }
    
//    A partir de un fichero, añade libros a la aplicación. si mantenerLista==0, se borra lista, si mantenerLista==1, se guarda
    public void procesaEventoAddLibrosFichero(DefaultListModel <String> lista,String ruta, int mantenerLista) {
        //hacer lo del filechooser
        
        int tipoError=miBiblioteca.cargarFichero(ruta,mantenerLista);
        ArrayList<Libro> historial = this.miBiblioteca.getHistorial();
        String [][] datosTabla=this.miBiblioteca.creaListaTable(historial);
        miVista.cargarTabla(datosTabla);
        //miVista.cargaLista(lista, historial);
        
    }

    //guarda los libros de la aplicacion a un fichero
    public void procesaEventoGuardarLibros(File archivoSeleccionado) {
        miBiblioteca.guardarFichero(archivoSeleccionado);
    }

    public void procesaEventoOrdenaPorNombre() {
        ArrayList<Libro> historial = this.miBiblioteca.getHistorial();
        String [][] datosTabla=miBiblioteca.ordenarLista(historial,true);
        miVista.cargarTabla(datosTabla);
    }

    public void procesaEventoOrdenaPorPrecio() {
        ArrayList<Libro> historial = this.miBiblioteca.getHistorial();
        String [][] datosTabla=miBiblioteca.ordenarLista(historial,false);
        miVista.cargarTabla(datosTabla);
    }

    public void procesaEventoVolver() {
        Main.getGestorVistas().mostrarVistaMenu();
    }
}
