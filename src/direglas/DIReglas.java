/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package direglas;

import Inferencia.ManejoArchivos;
import MD.ModelosDifusos;
import MD.PruebaModelosDifusos;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class DIReglas {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Base b = new Base();
        Leer l = new Leer();
        Validaciones v = new Validaciones();
        ManejoArchivos ma = new ManejoArchivos("encabezado_nuevo","Reglas_nuevo");
        PruebaModelosDifusos pmd=new PruebaModelosDifusos();
        Scanner scan = new Scanner(System.in);
        int opcion;
        int seguir;
        do {
            System.out.println("Elija una de las siguientes opciones: \n"
                    + "1) Encabezado\n"
                    + "2) Agregar Reglas\n"
                    + "3) Ver Reglas\n"
                    + "4) Ver Regla aleatoriamente\n"
                    + "5) Borrar Regla\n"
                    + "6) Borrar Todo\n"
                    + "7) Leer indice\n"
                    + "8) Uno X Uno\n"
                    + "9) Modelo difusos\n"
                    + "10) Salir\n"
                    + "Introduzca numero de la opcion");
            opcion = scan.nextInt();
            System.out.println("");
            switch (opcion) {
                case 1:
                    if (v.Reescribir_encabezado()) {
                        ma.Escribir_Encabezado();
                    }
                    ma.leerEncabezado();
                    break;
                case 2:
                    if (v.Validar_encabezado()) {
                        b.escribir_Reglas();
                    }
                    break;
                case 3:
                    if (v.Validar_reglas()) {
                        l.leer_todo();
                    }
                    break;
                case 4:
                    if (v.Validar_reglas()) {

                        b.leer_secuencial_indice(true);
                    }
                    break;
                case 5:
                    if (v.Validar_reglas()) {
                        b.leer_secuencial_indice(false);
                        b.Borrar_Regla();
                    }
                    break;
                case 6:
                    if (v.Validar_todo()) {
                        b.Borrar_Archivos();
                        System.exit(0);
                    }
                    break;
                case 7:
                    if (v.Validar_reglas()) {
                        b.leer_todo_indice();
                    }
                    break;
                case 8:
                    if (v.Validar_reglas()) {
                        l.leer_uno();
                    }
                    break;
                case 9:
//                    MD.ModelosDifusos md = new ModelosDifusos();
//                    md.escribir_Modelo("Temperatura", 0, 100, 3);
//                    md.leer_Modelo();
//                    md.leer_Indice();
                    
                    pmd.escribir_modelos();
                    pmd.leerArchivo();
                    break;
                default:
                    System.exit(0);
                    break;
            }
            System.out.println("Menu: SI=1, NO=2  ");
            seguir = scan.nextInt();
            System.out.println("\n");
        } while (seguir == 1);
    }
}
