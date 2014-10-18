/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package direglas;

import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo
 */
public class Base {

    int llave, memoria, arreglo, llave_actualizar, c;
    String antecedente1, antecedente2, antecedente3, antecedente4, antecedente5, consecuente1, consecuente2;
    RandomAccessFile archi, indice, tamaño_maestro, tamaño_indice, tam_key, encabezado;

    Base() throws FileNotFoundException {
        archi = new RandomAccessFile("Reglas", "rw");
        indice = new RandomAccessFile("indice", "rw");
        tamaño_indice = new RandomAccessFile("tamano indice", "rw");
        tam_key = new RandomAccessFile("key", "rw");
        encabezado = new RandomAccessFile("encabezado", "rw");
        try {
            if (tam_key.length() == 0) {
                c = 0;
            } else {
                tam_key.seek(0);
                c = tam_key.readInt();
            }
        } catch (IOException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escribir_Reglas() throws FileNotFoundException, IOException {//si se utiliza

        int n, aux;
        long pos;

        int total_ant = 0, total_con = 0;
        encabezado.seek(0);
        total_ant = encabezado.readInt();
        encabezado.seek((5 * 40) + 4);
        total_con = encabezado.readInt();
        StringBuffer buffer = null;
        System.out.println("Reglas");
        archi.seek(archi.length());
        System.out.println(archi.length());
        
        System.out.println("Escribre '#' si no quieres escribir antecedente");
        
        Scanner entrada = new Scanner(System.in);

        do {
            pos = archi.length();
            c++;
            archi.writeInt(c);
            aux = 5 - total_ant;
            for (int i = 0; i < total_ant; i++) {

                System.out.println("Antecedente");
                antecedente1 = entrada.next();
                buffer = new StringBuffer(antecedente1);
                buffer.setLength(15);
                archi.writeChars(buffer.toString());
            }

            for (int i = 0; i < aux; i++) {
                antecedente1 = "";
                buffer = new StringBuffer(antecedente1);
                buffer.setLength(15);
                archi.writeChars(buffer.toString());
            }
            aux = 2 - total_con;
            for (int i = 0; i < total_con; i++) {

                System.out.println("consecuente");
                consecuente1 = entrada.next();
                buffer = new StringBuffer(consecuente1);
                buffer.setLength(15);
                archi.writeChars(buffer.toString());
            }
            for (int i = 0; i < aux; i++) {
                consecuente1 = "";
                buffer = new StringBuffer(consecuente1);
                buffer.setLength(15);
                archi.writeChars(buffer.toString());
            }

            leer_secuencial_reglas(pos);
            System.out.println("¿Otro? Si=1, No=0");
            n = entrada.nextInt();
        } while (n == 1);
        //archi.close();
    }

    public void leer_secuencial_reglas(long punto) throws FileNotFoundException, IOException {//si se utiliza

        long ap_actual, ap_final;
        int contador = 0;
        RandomAccessFile leer_archi = new RandomAccessFile("Reglas", "r");
        RandomAccessFile es_indice = new RandomAccessFile("indice", "rw");
        RandomAccessFile tam_key = new RandomAccessFile("key", "rw");
        long lreg = es_indice.length();
        es_indice.seek(lreg);
        leer_archi.seek(punto);
        llave = leer_archi.readInt();
        es_indice.writeInt(llave);
        tam_key.seek(0);
        tam_key.writeInt(llave);
        es_indice.writeInt((int) punto);

        tam_key.close();
        es_indice.close();
        leer_archi.close();
    }

    public void leer_secuencial_indice(boolean flag) throws FileNotFoundException, IOException {//AQUI ES DONDE LEO EL ARCHIVO INDICE
        long ap_actual, ap_final;
        int contador = 0;
        try (RandomAccessFile leer_indice = new RandomAccessFile("indice", "r")) {
            while ((ap_actual = leer_indice.getFilePointer()) != (ap_final = leer_indice.length())) {
                contador++;
                llave = leer_indice.readInt();//EL VALOR DE LLAVE HAY QUE GUARDARLO EN EL ARBOL
                if (llave == 0) {
                    contador--;
                }
                //System.out.println("llave " + llave);
                memoria = leer_indice.readInt();//EL VALOR DE MEMORIA HAY QUE GUARDARLO EN EL ARBOL
                //System.out.println("Memoria " + memoria);
                //System.out.println("contador " + contador);
            }
        }
        tamaño_indice.writeInt(contador);
        if (flag) {
            crear_arbol(contador);
        }

    }

    public void leer_todo_indice() throws FileNotFoundException, IOException {//AQUI ES DONDE LEO EL ARCHIVO INDICE
        long ap_actual, ap_final;
        int contador = 0;
        try (RandomAccessFile leer_indice = new RandomAccessFile("indice", "r")) {
            while ((ap_actual = leer_indice.getFilePointer()) != (ap_final = leer_indice.length())) {
                contador++;
                llave = leer_indice.readInt();//EL VALOR DE LLAVE HAY QUE GUARDARLO EN EL ARBOL
                System.out.print("llave " + llave + "  ");
                memoria = leer_indice.readInt();//EL VALOR DE MEMORIA HAY QUE GUARDARLO EN EL ARBOL
                System.out.println("Memoria " + memoria);
                //System.out.println("contador " + contador);
            }
        }

    }

    public void crear_arbol(int n) throws FileNotFoundException, IOException {
        //arreglo=n;
        int llave[] = new int[n];
        int memoria[] = new int[n];
        int validar;
        long ap_actual, ap_final;
        int k = 0;
        RandomAccessFile leer_indice = new RandomAccessFile("indice", "r");
        while ((ap_actual = leer_indice.getFilePointer()) != (ap_final = leer_indice.length())) {
            validar = leer_indice.readInt();
            if (validar != 0) {
                llave[k] = validar;//se guarda la llave en un arreglo
                memoria[k] = leer_indice.readInt();//se guarda la memoria en un arreglo
                k++;
            } else {
                memoria[k] = leer_indice.readInt();

            }
        }//fin de while
        leer_indice.close();


        Arbol a = new Arbol("Arbol de Indice", llave[0], memoria[0]);


        int j = 1;
        for (int i = 0; i < llave.length; i++) {

            if ((llave.length % 2) == 0) {
                a.agregarElemento(a.getRaiz(), llave[i], llave[j], memoria[j]);//llave del nodo padre, llave de hijo, int de memoria en el archivo
                if (j >= llave.length - 1) {
                    break;
                } else {
                    a.agregarElemento(a.getRaiz(), llave[i], llave[j + 1], memoria[j + 1]);
                    j = j + 2;
                }
            } else {
                if (j >= llave.length) {
                    break;
                } else {
                    a.agregarElemento(a.getRaiz(), llave[i], llave[j], memoria[j]);
                    a.agregarElemento(a.getRaiz(), llave[i], llave[j + 1], memoria[j + 1]);
                    j = j + 2;
                }
            }
        }

        System.out.println(a.getNombre());
        a.imprime(a.getRaiz(), 0);
//----------------------------buscar e imprimir
        Scanner entrada = new Scanner(System.in);
        int b_llave;
        System.out.println("Escribe la llave que deseas buscar");
        b_llave = entrada.nextInt();
        Algoritmos.busquedaAnchura(a, b_llave);

    }

    public void Borrar_Regla() throws FileNotFoundException, IOException {
        RandomAccessFile rep_indice = new RandomAccessFile("indice", "r");
        RandomAccessFile t = new RandomAccessFile("tamano indice", "r");
        int n = t.readInt();
        System.out.println("" + n);
        int llave_[] = new int[n];
        int memoria_[] = new int[n];
        long ap_actual, ap_final;
        int k = 0;
        int validar;
        while ((ap_actual = rep_indice.getFilePointer()) != (ap_final = rep_indice.length())) {
            validar = rep_indice.readInt();
            if (validar != 0) {
                llave_[k] = validar;//se guarda la llave en un arreglo
                memoria_[k] = rep_indice.readInt();//se guarda la memoria en un arreglo
                k++;
            } else {
                memoria_[k] = rep_indice.readInt();
            }
        }//fin de while
        rep_indice.close();


        Arbol a = new Arbol("Borrar Regla", llave_[0], memoria_[0]);


        int j = 1;
        for (int i = 0; i < llave_.length; i++) {

            if ((llave_.length % 2) == 0) {
                a.agregarElemento(a.getRaiz(), llave_[i], llave_[j], memoria_[j]);//llave del nodo padre, llave de hijo, int de memoria en el archivo
                if (j >= llave_.length - 1) {
                    break;
                } else {
                    a.agregarElemento(a.getRaiz(), llave_[i], llave_[j + 1], memoria_[j + 1]);
                    j = j + 2;
                }
            } else {
                if (j >= llave_.length) {
                    break;
                } else {
                    a.agregarElemento(a.getRaiz(), llave_[i], llave_[j], memoria_[j]);
                    a.agregarElemento(a.getRaiz(), llave_[i], llave_[j + 1], memoria_[j + 1]);
                    j = j + 2;
                }
            }
        }

        System.out.println(a.getNombre());
        a.imprime(a.getRaiz(), 0);
//----------------------------buscar e imprimir
        Scanner entrada = new Scanner(System.in);
        int b_llave;
        System.out.println("Escribe la llave de la regla que deseas borrar");
        b_llave = entrada.nextInt();
        Algoritmos.seekanddestroy(a, b_llave);
        //Algoritmos.busquedaAnchuraH(a, b_llave);
    }

    public void Borrar_Archivos() throws IOException {
        File file_reglas = new File("Reglas");
        File file_indice = new File("indice");
        File file_key = new File("key");
        File file_tamindice = new File("tamano indice");
        File file_encabezado = new File("encabezado");
        archi.close();
        indice.close();
        tam_key.close();
        tamaño_indice.close();
        encabezado.close();
        file_reglas.delete();
        file_indice.delete();
        file_key.delete();
        file_tamindice.delete();
        file_encabezado.delete();

    }
}
