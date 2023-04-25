package uva.ipc.practica1.vista;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import uva.ipc.practica1.main.Main;
import uva.ipc.practica1.modelo.Libro;
import uva.ipc.practica1.modelo.Biblioteca;

/**
 * Clase ControladorEdicionManual
 * @author dansana
 * @author saant
 */

public class ControladorEdicionManual {
    private VistaEdicionManual miVista;
    private Biblioteca miBiblioteca;
    boolean actualizar;
    
    /**
     * Constructor por defecto de la clase ControladorPractica1
     * @param v objeto de la clase VistaEdicionManual
     */
    
    public ControladorEdicionManual(VistaEdicionManual v){
        this.miVista=v;
        this.miBiblioteca=new Biblioteca();
    }
    
     /**
     * Método para crear un Libro a partir de esos parámetros
     * y comprobar que cada parámetro cumpla las especificaciones dadas
     * @param actualizar, true si se va a actualizar un libro, false si se va a añadir
     * @param lista, lista modelo para la jList
     */
    public void procesaEventoGuardar(boolean actualizar,DefaultListModel <String> lista){
        ArrayList<Libro> historial = this.miBiblioteca.getHistorial();
        Libro libroNuevo=this.miVista.GetLibro();
        String titulo=libroNuevo.getTitulo();
        String ISBN=libroNuevo.getISBN();
        int index=this.miVista.getIndex();
        
        if(titulo.length()>20 || titulo.length()<1){
            this.miVista.mostrarMensaje("Título incorrecto. Debe tener entre 1 y 20 caracteres",true);
        }
        else if(ISBN.length()>13 || ISBN.length()<1){
            this.miVista.mostrarMensaje("ISBN incorrecto. Debe tener entre 1 y 13 caracteres",true);
        }
        else if(this.miBiblioteca.isbnRepetido(historial,libroNuevo) && !actualizar){
            this.miVista.mostrarMensaje("ISBN repetido",true);
        }
        else if(libroNuevo.getPrecio()<0){ 
            this.miVista.mostrarMensaje("Precio incorrecto. Debe ser positivo",true);
        }
        else {
            if (actualizar){
                if(historial.get(index).getISBN().equals(libroNuevo.getISBN())){
                    actualizarLibro(lista,libroNuevo,index);
                }else addLibro(lista,libroNuevo);
            }else{
                addLibro(lista,libroNuevo);
            }
        }
    }
    
    /**
     * Método para editar un Libro del historial, solo si se ha seleccionado
     * @return actualizar, true si se va a editar el libro
     */
    public boolean procesaEventoEditar(){
        ArrayList<Libro> historial;
        actualizar=false;
        int index=this.miVista.selectedIndex();
        if(index==-1){
            this.miVista.mostrarMensaje("No hay ningún libro seleccionado",true);
        }else{
            historial=this.miBiblioteca.getHistorial();
            this.miVista.setValores(index,historial);
            actualizar=true;
        }
        this.miVista.setIndex(index);
        return actualizar;
    }
    
   /** 
     * Método para eliminar un Libro del historial
     * @param lista
     */
    public void procesaEventoBorrar(DefaultListModel <String> lista){
        int index=this.miVista.selectedIndex();
        //ArrayList<Libro> historial=this.miBiblioteca.getHistorial();
        if(index==-1){
            this.miVista.mostrarMensaje("No hay ningún libro seleccionado",true);
        }else{
            this.miBiblioteca.eliminaElementoHistorial(index);
            this.miVista.borrarLibroLista(lista,index);
            this.miVista.mostrarMensaje("Libro eliminado correctamente",false);
        } 
    }
    
    /**
     * Método que sirve para añadir un nuevo libro
     * @param lista lista modelo para la jList
     * @param libroNuevo Libro que se ha creado 
     */
    public void addLibro(DefaultListModel <String> lista,Libro libroNuevo){
        this.miBiblioteca.addEntradaHistorial(libroNuevo);
        this.miVista.addLibroLista(lista,libroNuevo);
        this.miVista.mostrarMensaje("Libro añadido correctamente",false);
    }
    
    /**
     * Método para actualizar un Libro
     * @param lista lista modelo para la jList
     * @param libroNuevo Libro que se ha creado 
     * @param index índice donde se encuentra el libro a actualizar
     */
    public void actualizarLibro(DefaultListModel <String> lista,Libro libroNuevo,int index){
        this.miBiblioteca.addEntradaHistorial(libroNuevo,index);
        this.miVista.setLibroLista(lista,libroNuevo,index);
        this.miVista.mostrarMensaje("Datos del libro actualizados correctamente",false);
    }  

    public void procesaEventoVolverAMenu() {
        Main.getGestorVistas().mostrarVistaMenu();
    }
  
}
