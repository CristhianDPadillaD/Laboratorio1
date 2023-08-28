/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.umariana.laboratorio1;

import Mundo.Alumno;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Cristhian Padilla
 */
public class Laboratorio1 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // bandera que permite terminar el programa
        try ( // Funcion que permite leer la opcion dell usuario
                Scanner lector = new Scanner (System.in )) {
            // bandera que permite terminar el programa
            boolean activo = true;
            
            //Array donde se guardan los estudiantes
            ArrayList<Alumno>misAlumnos = new ArrayList<Alumno>();
            
            //Cargamos el archivo txt antes de todo 
            cargarReporte (misAlumnos);
            
            do {
                
                //Creamos un try catch en caso de que no se digite un número en el menú
                try{
                    mostrarMenu();
                    
                    int opcion = lector.nextInt();
                    switch (opcion) {
                        
                           //Agregar Alumno
                        case 1:
                            System.out.println("Opcion uno");
                            agregarAlumno (misAlumnos, lector);
                            generarReporte(misAlumnos);
                            
                           //Eliminar Alumno
                            break;
                        case 2:
                            System.out.println("Opcion dos");
                            eliminarAlumno(misAlumnos,lector);
                            generarReporte(misAlumnos);
                            break;
                            
                          //Modificar Alumno
                        case 3:
                            System.out.println("Opcion tres");
                            modificarAlumno(misAlumnos, lector);
                            generarReporte(misAlumnos);
                            break;
                            
                           //Consultar Alumnos
                        case 4:
                            System.out.println("Opcion cuatro");
                            consultarAlumnos(misAlumnos);
                            break;
                            
                           //Generar reporte por semestre
                        case 5:
                            System.out.println("Opcion cinco");
                            generarReporteSemestre(misAlumnos, lector);
                            break;
                            
                            
                           //Borrar el archivo txt
                        case 6:
                            System.out.println("Opcion seis");
                            borrarReporte(misAlumnos);
                            generarReporte(misAlumnos);
                            break;
                            
                           //Cerrar Programa
                        case 7:
                            System.out.println("Opcion siete");
                            activo = false;
                            break;
                            
                            
                        default:
                            System.out.println("Debe seleccionar una de las opciones del menú");
                            break;
                    }
                    
                    
                    
                }catch (InputMismatchException e) {
                    System.out.println("Por favor digite un número entero ");
                    lector.next();
                }
            }while(activo == true);
        }
    }
    
     //método para implementar menú
    public static void mostrarMenu (){
//Creamos un try catch en caso de que no se digite un número en el menú
           
        System.out.println(" - - - - - - Menú de opciones - - - - - - -");
        System.out.println("1. Insertar alumno");
        System.out.println("2. Eliminar alumno");
        System.out.println("3. Modificar alumno");
        System.out.println("4. Consultar alumno");
        System.out.println("5. Generar reporte por semestre");
        System.out.println("6. Borrar el contenido del archivo");
        System.out.println("7. Terminar programa");
        System.out.println(" - - - - - - - - - - - - - - - - - - - - - -");
        
        
    
    }
    
    //método para generar Reporte 
    public static void generarReporte (ArrayList<Alumno>misAlumnos) throws FileNotFoundException{
        
        //Creamos el archivo con la clase File y digitamos la dirección donde queremos que se guarde
        File archivo = new File ("./data/Reporte.txt");
        
        //Creamos la pluma para escribir en el archivo
        PrintWriter pluma = new PrintWriter (archivo);
        
          for(Alumno alumno : misAlumnos){
            // enseñamos los datos recibidos1
           
                pluma.println(alumno.getCedula()+","+ 
                                    alumno.getNombre() +"," +
                                    alumno.getApellido() + ","+ 
                                    alumno.getCorreo() + "," +
                                    alumno.getCelular() +"," +
                                    alumno.getSemestre());  
          }
                
                    pluma.close();
       
    }
    
    //método para agregar alumno
    public static void agregarAlumno (ArrayList<Alumno> misAlumnos, Scanner lector){
        
      System.out.println( "########################################");
                    System.out.println("Introduce la cedula del alumno");
                    String cedula = lector.next();
                    
                    System.out.println("Introduce el nombre del estudiante");
                    String nombre = lector.next();
                    
                    System.out.println("Introduce el apellido del estudiante");
                    String apellido = lector.next();
                    
                    System.out.println("Introduce el correo del estudiante ");
                    String correo = lector.next();
                    
                    System.out.println("Introduce el celular del estudiante ");
                    String celular = lector.next();
                    
                    System.out.println("Introduce el semestre del estudiante");
                    String semestre = lector.next();
                    
                      System.out.println( "########################################");
                    
                    // Se crea un objeto para guardar la información de cada alumno
                    Alumno a = new Alumno(cedula, nombre, apellido, correo, celular, semestre);
                    
                    a.setCedula(cedula);
                    a.setNombre(nombre);
                    a.setApellido(apellido);
                    a.setCorreo(correo);
                    a.setCelular(celular);
                    a.setSemestre(semestre);
                     
                    // se agrega el valor del objeto al listaArray
                    misAlumnos.add(a);     
                    
                    
    }
    
    //método para eliminar estudiante, con las entradas del array y el scanner
    public static void eliminarAlumno (ArrayList<Alumno>misAlumnos, Scanner lector){
          System.out.println( "########################################");
        if (!misAlumnos.isEmpty()){
            
          //pedimos la cedula 
        System.out.println("Digite la cedula del Alumno que desea eliminar");
        
        //guardamos la cedula en una nueva variable
            String cedulaE = lector.next();
        
        // variable para saber si la cedula escrita existe
        boolean encontro= false;
        
          //Creamos un ciclo for para recorrer el array
        for(Alumno estu: misAlumnos){
            
            //Condicional para ver si existe una cedula igual 
            if( cedulaE.equals(estu.getCedula())){
                  
                //se elimina el alumno
                  misAlumnos.remove(estu);
                  
                  System.out.println("Se eliminó al alumno con éxito");
                  //variable verdadera porque se encontro la cedula
                  encontro=true;
                  break; 
            }  
        }
        //concidicional si no se encontró la cedula
        if(encontro == false){
            System.out.println("No existe el estudiante");
        }
        }else {
            //El array esta vacio
            System.out.println("No hay estudiantes");
        }
         System.out.println( "########################################");
        
      
    }
    
    //metodo para modificar a un estudiante
    public static void modificarAlumno(ArrayList<Alumno>misAlumnos, Scanner lector){
        
          if (!misAlumnos.isEmpty()){
            System.out.println( "########################################");
         //pedimos la cedula 
        System.out.println("Digite la cedula del Alumno que desea modificar");
        
        //guardamos la cedula
            String cedulaM = lector.next();
        
        // variable para saber si la cedula existe
        boolean encontro= false;
        
          //Creamos un ciclo for para recorrer el array
        for(Alumno estu: misAlumnos){
            
            //Condicional para ver si existe una cedula igual 
            if( cedulaM.equals(estu.getCedula())){
                
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                
                // Pedimos la nueva cedula 
                System.out.println("Digite la nueva cedula");
                // guardamos el dato pedido
                String cedulaN = lector.next();
                //cambio el dato con el nuevo escrito
                estu.setCedula(cedulaN);
                
                //Pedimos el nuevo nombre
                System.out.println("Digite el nuevo nombre");
                //Lo guardamos en una variable
                String nombreN = lector.next();
                //Reemplazamos el valor antiguo con el nuevo
                estu.setNombre(nombreN);
                
                //Pedimos el nuevo apellido
                System.out.println("Digite el nuevo apellido");
                //Guardamos en una nueva variable
                String apellidoN = lector.next();
                //Se reemplaza por el nuevo dato 
                estu.setApellido(apellidoN);
                
                //Pedimos el nuevo correo 
                System.out.println("Digite el nuevo correo ");
                //Guardamos en nuevo dato
                String correoN = lector.next();
                //Reemplazamos el dato
                estu.setCorreo(correoN);
                
                //Pedimos el nuevo celular
                System.out.println("Digite el nuevo celular ");
                // Guardamos el dato en una variable
                String celularN = lector.next();
                // Reemplazamos el dato
                estu.setCelular(celularN);
                
                //Pedimos el nuevo semestre
                System.out.println("Digite el nuevo semestre ");
                // Guardamos en la variable
                String semestreN = lector.next();
                //Reemplazamos el dato
                estu.setSemestre(semestreN);
                
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                
                  // se encontro la cedula entonces es igual a true
                  encontro=true;
                  break; 
            }  
        }
        //concidicional si no se encontró la cedula
        if(encontro == false){
            System.out.println("No existe el estudiante");
        }
        }else {
            //El array esta vacio
            System.out.println("No hay estudiantes");
        }
        
          System.out.println( "########################################");
    }
    
    //método para mostrar los estudiantes registrados
    public static void consultarAlumnos (ArrayList<Alumno>misAlumnos){
         System.out.println( "########################################");
        //revisamos si esta vacio el contenedor
        if(misAlumnos.isEmpty()){
            
            // está vacio
            System.out.println("No hay alumnos");
              System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }else{
            // creamos una variable donde se guarda en tamaño del array
            int numeroEst = misAlumnos.size();
            
            // si es iguala  uno la palabra es en singular
              if(misAlumnos.size()==1){
                  
                  System.out.println("Hay "+ numeroEst + " alumno");
                  System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                  
              }else{
                  
                  // al ser más de uno la palabra es en plurar
                  System.out.println("Hay "+ numeroEst + " alumnos");
                  System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                  
              }
              
            // ciclo parar recorrer el contenedor 
            for(Alumno listaEstu: misAlumnos  ){
                // enseñamos los datos recibidos1
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                    System.out.println("Alumno "+ numeroEst );
                    System.out.println("Cedula: " + listaEstu.getCedula());
                    System.out.println("Nombre: " + listaEstu.getNombre());
                    System.out.println("Apellido: " + listaEstu.getApellido());
                    System.out.println("Correo: " + listaEstu.getCorreo());
                    System.out.println("Celular: " + listaEstu.getCelular());
                    System.out.println("Semestre: " + listaEstu.getSemestre());
                    System.out.println("- - - - - - - - - - - - - - - - - - - - -");
                    // reducimos en 1 el contador de alumnos
                    numeroEst--;
                
            }
            
        }
          System.out.println( "########################################");
    }
    
     //método para generar Reporte p0or semestre
    public static void generarReporteSemestre (ArrayList<Alumno> misAlumnos, Scanner lector) throws FileNotFoundException{
        
        //Creamos el archivo con la clase file
        File archivo = new File("./data/Reporte.txt");
        
        //Creamos la pluma para escribir el archivo
        PrintWriter pluma = new PrintWriter(archivo);
         if(!misAlumnos.isEmpty()){
        
        System.out.println("Digite el semesetre del cual desea generar el reporte");
        
        String semetreR = lector.next();
            
            System.out.println("Se generó el archivo");
               //Escribimos con la pluma en el archivo
        pluma.println("Reporte Alumnos por semestre");
        pluma.println("============================");
            for(Alumno listaEstu: misAlumnos  ){
                
                
                if(listaEstu.getSemestre().equals (semetreR)){
                // enseñamos los datos recibidos1
                pluma.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                pluma.println("Cedula: " + listaEstu.getCedula());
                pluma.println(","+ "Nombre: " + listaEstu.getNombre());
                pluma.println("," +"Apellido: " + listaEstu.getApellido());
                pluma.println(","+ "Correo: " + listaEstu.getCorreo());
                pluma.println("," +"Celular: " + listaEstu.getCelular());
                pluma.println("," +"Semestre: " + listaEstu.getSemestre());
                pluma.println("- - - - - - - - - - - - - - - - - - - - -");
                    }
                
        }  
             pluma.close();
             
        }else{
             System.out.println("No hay estudiantes");
        }  
        
    }
    
     //método para cargar archivos
    public static void cargarReporte (ArrayList<Alumno> misAlumnos) throws FileNotFoundException, IOException{
        
        //Ubicacion donde leer
        File archivo = new File ("./data/Reporte.txt");
        
        try (FileReader fr = new FileReader (archivo)) {
            
            //Variable donde se va a contener lo leido
           BufferedReader lc = new BufferedReader(fr);
            
            String linea;
            
            while((linea= lc.readLine()) != null){
                
                //Array donde se guardan los datos leidos
                String [] datos = linea.split(",");
                
                String cedula = datos[0].trim();
                String nombre = datos[1].trim();
                String apellido= datos[2].trim();
                String correo = datos[3].trim();
                String celular = datos[4].trim();
                String semestre = datos[5].trim();
                
                Alumno nuevo = new Alumno(cedula, nombre, apellido, correo, celular, semestre);
                misAlumnos.add(nuevo);
                
            }
            
            System.out.println("Se cargo exitosamente el reporte");
        }catch(FileNotFoundException e ){
            
            System.out.println("No se encontró el archivo de lectura");
            
        }

    }
    
    //metodo para borrar el archivo
    public static void borrarReporte (ArrayList<Alumno>misAlumnos){
        
        
        if(!misAlumnos.isEmpty()){
                
        //limpiamos el array
        misAlumnos.clear();
        
        System.out.println("Se ha borrado el contenido del archivo con éxito");
        }else{
            
            System.out.println("Se encuentra vacio el archivo");
            
        }
    
        
        
    }
       
}
