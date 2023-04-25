package uva.ipc.practica1.modelo;

import uva.ipc.practica1.vista.VistaEdicionManual;

/**
 * Clase Libro
 * @author dansana
 * @author saant
 */

public class Libro implements Comparable<Libro>{
    private String titulo;
    private String descripcion;
    private String ISBN;
    private String estado;
    private double precio;
    private boolean leido;
    
    /**
     * Constructor por defecto para asignar cada parámetro de un Libro
     * @param titulo Título del Libro
     * @param descripcion Descripción del Libro
     * @param ISBN ISBN del Libro
     * @param estado Estado del Libro
     * @param precio Precio del Libro
     * @param leido Indica si ha leido (true) o no (false) el Libro
     */
    
    public Libro(String titulo, String descripcion, String ISBN, String estado, double precio, boolean leido){
            this.titulo=titulo;
            this.descripcion=descripcion;
            this.ISBN=ISBN;
            this.estado=estado;
            this.precio=precio;
            this.leido = leido;
        
    }
    
    /**
     * Método para conocer el título del Libro
     * @return Título del Libro
     */
    
    public String getTitulo(){
        return titulo;
    }
    
    /**
     * Método para conocer la descripción del Libro
     * @return Descripción del Libro
     */
    
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Método para conocer el ISBN del Libro
     * @return el ISBN del Libro
     */
    
    public String getISBN(){
        return ISBN;
    }
    
    /**
     * Método para conocer el estado del Libro
     * @return el estado del Libro
     */
    
    public String getEstado(){
        return estado;
    }
    
    /**
     * Método para conocer el precio del Libro
     * @return el precio del Libro
     */
    
    public double getPrecio(){
        return precio;
    }
    
    /**
     * Método para conocer si se ha leido el Libro
     * @return True o False, dependiendo si se ha leido 
     */
    
    public boolean getLeido(){
        return leido;
    }
    
    /**
     * Método para fijar el título del Libro
     * @param titulo Título del Libro
     */
    
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }
    
    /**
     * Método para fijar la descripción del Libro
     * @param descripcion Descripción del Libro
     */
    
    public void setdescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    
    /**
     * Método para fijar el estado del Libro
     * @param estado Estado del Libro
     */
    
    public void setEstado(String estado){
        this.estado=estado;
    }
    
    /**
     * Método para fijar el precio del Libro
     * @param precio Precio del Libro
     */
    
    public void setPrecio(double precio){
        this.precio=precio;
    }
    
    /**
     * Método para señalar si se ha leido o no el Libro
     * @param leido Indica si ha leido (true) o no (false) el Libro
     */
    
    public void setleido(boolean leido){
        this.leido=leido;
    }
    
    
    @Override
    public boolean equals(Object obj){
        if(obj==null){
            return false;
        }
        if(obj==this){
            return true;
        }
        if(obj.getClass()!=this.getClass()){
            return false;
        }
        Libro otroLibro=(Libro)obj;
        return (this.getTitulo()==otroLibro.getTitulo() && this.getDescripcion()==otroLibro.getDescripcion() && this.getISBN()==otroLibro.getISBN() && this.getEstado()==otroLibro.getEstado() && this.getPrecio()==otroLibro.getPrecio() == this.getLeido()==otroLibro.getLeido());
    }

    @Override
    public int compareTo(Libro o) {
        return this.getTitulo().compareTo(o.getTitulo());
    }
    
}
