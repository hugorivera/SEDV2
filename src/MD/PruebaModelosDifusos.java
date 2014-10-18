/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MD;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Hugo
 */
public class PruebaModelosDifusos {

    char variable[] = new char[20];
    char etiqueta[] = new char[15];
    float valor;
    String sTvariable="";
    String stetiqueta="";
    float fvalor;
    RandomAccessFile nuevo, encabezado;
    int n;
    
    public void leerArchivo() throws FileNotFoundException, IOException {
        
        String r_etiqueta = null;
        long ap_actual, ap_final;
        RandomAccessFile archivo = new RandomAccessFile("Prueba_modelos", "r");
        archivo.seek(0);

        while ((ap_actual = archivo.getFilePointer()) != (ap_final = archivo.length())) {

            for (int i = 0; i < 20; i++) {
                char readChar = archivo.readChar();
                sTvariable += readChar;
            }
            System.out.print(sTvariable);
            sTvariable = "";

            for (int i = 0; i < 15; i++) {
                char readChar = archivo.readChar();
                stetiqueta += readChar;
            }
            System.out.print(stetiqueta);
            r_etiqueta=stetiqueta;
            stetiqueta = "";
            System.out.print("/");
            float readFloat = archivo.readFloat();
            fvalor = readFloat;
            System.out.println(""+readFloat);
        }
        archivo.close();
        //return r_etiqueta;
    }

    public void escribir_modelos() throws FileNotFoundException, IOException {
        Scanner teclado = new Scanner(System.in);


        String variable, eti;

        nuevo = new RandomAccessFile("Prueba_modelos", "rw");

        do {
            System.out.println("Variable?");
            variable = teclado.next();
            StringBuffer entrada = new StringBuffer(variable);
            entrada.setLength(20);
            nuevo.writeChars(entrada.toString());
            System.out.println("Etiqueta?");
            eti = teclado.next();
            StringBuffer etiqueta = new StringBuffer(eti);
            etiqueta.setLength(15);
            nuevo.writeChars(etiqueta.toString());
            System.out.println("Valor?");
            valor = teclado.nextFloat();
            nuevo.writeFloat(valor);


            System.out.println("¿Otro? Si=1, No=0");
            n = teclado.nextInt();
        } while (n == 1);
    }
}
