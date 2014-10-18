/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package direglas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Hugo
 */
public class Borrar {

    RandomAccessFile archi,indice,encabezado;
    
    String antecedente1, antecedente2, antecedente3, antecedente4, antecedente5, consecuente1, consecuente2;
    StringBuffer buffer = null;

    public void Borrado_Reglas(int a) throws FileNotFoundException, IOException {
        archi = new RandomAccessFile("Reglas", "rw");
        encabezado=new RandomAccessFile("encabezado", "r");
        
        int total_ant = 0, total_con = 0;
        encabezado.seek(0);
        total_ant = encabezado.readInt();
        encabezado.seek((total_ant * 30) + 4);
        total_con = encabezado.readInt();
        
        archi.seek(a);
        archi.writeInt(0);
        for (int i = 0; i < total_ant; i++) {
            
        System.out.println("Borrando Antecedente...");
        antecedente1 = "";
        buffer = new StringBuffer(antecedente1);
        buffer.setLength(15);
        archi.writeChars(buffer.toString());
        }
        //------------------------------------------
//        System.out.println("Antecedente 2");
//        antecedente2 = "";
//        buffer = new StringBuffer(antecedente2);
//        buffer.setLength(15);
//        archi.writeChars(buffer.toString());
//        //------------------------------------------
//        System.out.println("Antecedente 3");
//        antecedente3 = "";
//        buffer = new StringBuffer(antecedente3);
//        buffer.setLength(15);
//        archi.writeChars(buffer.toString());
//        //------------------------------------------
//        //System.out.println("Antecedente 1");
//        antecedente4 = "";
//        buffer = new StringBuffer(antecedente4);
//        buffer.setLength(15);
//        archi.writeChars(buffer.toString());
//        //-----------------------------------------
//        antecedente5 = "";
//        buffer = new StringBuffer(antecedente4);
//        buffer.setLength(15);
//        archi.writeChars(buffer.toString());
        //-----------------------------------------
        for (int i = 0; i < total_con; i++) {
            
        System.out.println("Borrando Consecuente...");
        consecuente1 ="";
        buffer = new StringBuffer(consecuente1);
        buffer.setLength(15);
        archi.writeChars(buffer.toString());
        }
        //----------------------------------------
        //System.out.println("consecuente");
//        consecuente2 = "";
//        buffer = new StringBuffer(consecuente2);
//        buffer.setLength(15);
//        archi.writeChars(buffer.toString());
        
        archi.close();
    }
    
    public void actualizar_Indice(int a) throws FileNotFoundException, IOException{
        indice =new RandomAccessFile("indice", "rw");
        long ap_actual, ap_final;
        int llave;
        while ((ap_actual = indice.getFilePointer()) != (ap_final = indice.length())) {
                ap_actual=indice.getFilePointer();
                llave = indice.readInt();
                if(a==llave){
                    indice.seek(ap_actual);
                    indice.writeInt(0);
                    break;
                }
                indice.readInt();
            }
    }
}
