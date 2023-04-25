/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
import javax.swing.DefaultListModel;
//FICHERO CONTRASTARLO AQUÍ
//TRATAR ERRORES AQUÍ
/**
 *
 */
package uva.ipc.practica1.modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.swing.DefaultListModel;
/*
 * @author saant
 */
public class Biblioteca {
    
    //IMPLEMENTAR
    public enum Estado{
        BUENO,
        REGULAR,
        MALO,
    }
    
    ArrayList<Libro> historial;
    private DefaultListModel <String> lista = new DefaultListModel<>();
    
    public Biblioteca(){
        historial=new ArrayList<>();
    }
    
    public ArrayList<Libro> getHistorial(){
        return this.historial;
    }
   
    public void addEntradaHistorial(Libro entrada){
        this.historial.add(entrada);
    }
    
    public void addEntradaHistorial(Libro entrada, int index){
        this.historial.add(index,entrada);
    }
    
    public void eliminaElementoHistorial(int index){
        this.historial.remove(index);
    }
    
    public void limpiarHistorial(){
        this.historial.clear();
    }
    
    /**
     * Método para comprobar el ISBN de un libro que se quiere crear,
     * con el IBSN de los libros ya existentes en el historial
     * @param historial Listado de libros añadidos
     * @param libroNuevo Libro que se ha creado 
     * @return True o False, dependiendo si el ISBN coincide
     */
    public boolean isbnRepetido(ArrayList<Libro> historial,Libro libroNuevo){
        boolean isRepetido=false;
        for (int i=0;i<historial.size();i++){
            if((historial.get(i).getISBN()).equals(libroNuevo.getISBN())){
                isRepetido=true;
            }
        }
        return isRepetido;
    }
    
    public boolean isbnRepetido(ArrayList<Libro> historial,String isbnAComparar){
        boolean isRepetido=false;
        for (int i=0;i<historial.size();i++){
            if((historial.get(i).getISBN()).equals(isbnAComparar)){
                isRepetido=true;
            }
        }
        return isRepetido;
    }
    
     /** Método de consulta del número de libros que hay en la biblioteca
     * @return Número de libros que hay en la biblioteca.
     */
    public int getNumLibros(){
        if(this.historial.size()>0){
            return this.historial.size();
        }else{
            return 0;
        }
        
    }

     /** Método de consulta de la cantidad de libros que se han leído.
     * @return librosLeidos, libros que se han leído
     */
    public int getLibrosLeidos(){
        int numLibros=getNumLibros();
        int librosLeidos=0;
        Libro libro;
        for(int i=0;i>numLibros;i++){
            libro=this.historial.get(i);
            if(libro.getLeido()){
                librosLeidos++;
            }
        }
        return librosLeidos;
    }

     /** Método de consulta de la cantidad de libros que no se han leído.
     * @return librosNoLeidos, libros que no se han leído
     */
    public int getLibrosNoLeidos(){
        int numLibros=getNumLibros();
        int librosNoLeidos=0;
        Libro libro;
        for(int i=0;i>numLibros;i++){
            libro=this.historial.get(i);
            if(!libro.getLeido()){
                librosNoLeidos++;
            }
        }
        return librosNoLeidos;
    }

    /** 
     * @return 
     */
    public double getPrecioMedio(){
        int numLibros=getNumLibros();
        double sumaPrecio=0;
        Libro libro;
        if(numLibros==0){
            return 0;
        }else{
            for(int i=0;i>numLibros;i++){
                libro=this.historial.get(i);
                sumaPrecio=sumaPrecio+libro.getPrecio();
            }
            return sumaPrecio/numLibros;
        }
        
    }
    
    public String[][] creaListaTable(ArrayList<Libro> listaAux){
        int i;
        Libro libroAdd;
        String [][] lTable= new String[listaAux.size()][3];
        for(i=0;i<listaAux.size();i++){
            libroAdd=listaAux.get(i);
            lTable[i][0]=libroAdd.getTitulo();
            lTable[i][1]=String.valueOf(libroAdd.getPrecio());
            lTable[i][2]=String.valueOf(libroAdd.getISBN()); 
        }
        return lTable;
    }
    
//    busca libros en el jtextfield
    public String [][] buscaLibros(boolean buscarTitulo,String cadena){
        ArrayList<Libro> listaAux=new ArrayList<>();
        Libro libroAAnalizar;
        int i;
        for(i=0; i<historial.size();i++){
            libroAAnalizar=historial.get(i);
            //si buscamos titulo
            if(buscarTitulo){
                if(libroAAnalizar.getTitulo().contains(cadena)){
                    listaAux.add(libroAAnalizar);
                }
            }else{
                if(libroAAnalizar.getISBN().contains(cadena)){
                    listaAux.add(libroAAnalizar);
                }
            }
        }
        String [][] lTable=creaListaTable(listaAux);
        
        return lTable;
    }
    
    
    public String [][] ordenarLista(ArrayList <Libro> historial,boolean ordenAlfabetico){
        if(ordenAlfabetico){
            Collections.sort(historial);
        }else{
            Comparator <Libro> comparadorPrecio= new Comparator<Libro>(){
                public int compare(Libro o1,Libro o2){
                    return (int)(o1.getPrecio() - o2.getPrecio());
                }
            };
            Collections.sort(historial,comparadorPrecio);
        }
        return creaListaTable(historial);
    }
    
    
     //Método que lee el fichero importado y guarda los libros 
    public int cargarFichero(String ruta,int mantenerLista) {
        String datos;
        int tipoError=0;
        try{
            File fichero=new File(ruta);
            Scanner myReader=new Scanner(fichero);
            if(mantenerLista==0){
                limpiarHistorial();
            }
            while(myReader.hasNextLine() && tipoError==0){
                datos=myReader.nextLine();
                //suponiendo que todo es correcto
                tipoError=stringToLibro(datos);
            }
            myReader.close();
            System.out.println(tipoError);
            return tipoError;
        }
        catch(FileNotFoundException e) {
            return 2;
        }
    }
    
    public int stringToLibro(String datos){
        String titulo = null; //listaDatos[0]
        String descripcion=null; //listaDatos[1]
        String ISBN = null; //listaDatos[2]
        //enum estado 
        String estado=null; //listaDatos[3]
        double precio=0; //listaDatos[4]
        boolean leido=false; //listaDatos[5]
        String [] listaDatos=datos.split(";");
        
        //titulo
        if(listaDatos[0].length()>0 && listaDatos[0].length()<=20){
            titulo=listaDatos[0];
        }else{
            return 1;
        }
        //descripcion
        descripcion=listaDatos[1];
        //isbn
        if(isNumeric(listaDatos[2]) && listaDatos[2].length()<=13 && listaDatos[2].length()>0 && !isbnRepetido(historial,listaDatos[2])){
            ISBN=listaDatos[2];
        }else{
            return 1;
        }
        //estado
        if(listaDatos[3].equals("Bueno") || listaDatos[3].equals("Regular") || listaDatos[3].equals("Malo")){
            estado=listaDatos[3];
        }else{
            return 1;
        }
        //precio
        String precioStr=listaDatos[4];
        if(isNumeric(precioStr)){
            precio=Double.parseDouble(precioStr);
        }else{
            return 1;
        }
        //leido
        if(listaDatos[5].equals("true")){
            leido=true;
        }else if(listaDatos[5].equals("false")){
            leido=false;
        }else{
            return 1;
        }
        addEntradaHistorial(new Libro(titulo,descripcion,ISBN,estado,precio,leido));
        return 0;
    }
    
    public boolean isNumeric(String cadena){
        char charCadena;
        if(cadena==null){
            return false;
        }else if(cadena.equals("")){
            return false;
        }else{
            for(int i=0; i<cadena.length();i++){
                charCadena=cadena.charAt(i);
                if (((int)(charCadena)>=0 && (int)(charCadena)<=9) || (int)(charCadena)==46){
                    return false;
                }
            }
        }
        return true;
    }
    
//throws IOException
    public void guardarFichero(File ficheroSeleccionado) {
        Libro libroAGuardar;
        String linea;
        if(ficheroSeleccionado.exists()){
            System.out.println("Ya existe ese fichero con ese nombre"); //Flag
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSeleccionado, true));  // Para escribir en el fichero. Escribe en una línea completa en el fichero

                for (Libro libro : historial) { 
                    linea = libro.getTitulo() + ";" + libro.getDescripcion() + ";" + libro.getISBN() + ";" + libro.getEstado() + ";" + libro.getPrecio() + ";" + libro.getLeido() + "\n";
                    bw.write(linea); 
                }
                bw.close();

            }catch(FileNotFoundException e){
                System.out.println("Error al guardar el fichero");
            }catch(IOException e) {
                System.out.println("Error al guardar, en la salida");
            }

        }else{
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSeleccionado, true));

                for (Libro libro : historial) { 
                    linea = libro.getTitulo() + ";" + libro.getDescripcion() + ";" + libro.getISBN() + ";" + libro.getEstado() + ";" + libro.getPrecio() + ";" + libro.getLeido() + "\n";
                    bw.write(linea); 
                }
                bw.close();

            }catch(FileNotFoundException e){
                System.out.println("Error al guardar el fichero");
            }catch(IOException e) {
                System.out.println("Error al guardar, en la salida");
            }
        }
    }
}
