/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package direglas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Hugo
 */
public class Leer {

    long pos_final;

    public void leer_aleatorio(int n) throws FileNotFoundException, IOException {
        int llave, aux;
        
        boolean no_escribio = false;
        String res = "";
        RandomAccessFile archi = new RandomAccessFile("Reglas_nuevo", "r");
        RandomAccessFile encabezado = new RandomAccessFile("encabezado_nuevo", "r");
        int total_ant, total_con;
        encabezado.seek(0);
        total_ant = encabezado.readInt();
        encabezado.seek((5 * 40) + 4);
        total_con = encabezado.readInt();

        long buscar = n;
        archi.seek(buscar);
        llave = archi.readInt();
        System.out.print(llave + "  ");
        aux = 5 - total_ant;
        for (int i = 0; i < total_ant; i++) {

            char antece1[] = new char[15], temp_ant1;
            for (int c = 0; c < antece1.length; c++) {
                temp_ant1 = archi.readChar();
                if (temp_ant1 == '#') {
                    no_escribio = true;
                }
                res += temp_ant1;
            }
            if (no_escribio) {
                
            }
            System.out.print(res);
            res = "";
        }
        for (int i = 0; i < aux; i++) {
            char antece1[] = new char[15], temp_ant1;
            for (int c = 0; c < antece1.length; c++) {
                temp_ant1 = archi.readChar();
            }
        }

        aux = 2 - total_con;
        for (int i = 0; i < total_con; i++) {

            System.out.print("  ");
            char conse1[] = new char[15], temp_con1;
            for (int c = 0; c < conse1.length; c++) {
                temp_con1 = archi.readChar();
                res += temp_con1;
            }
            System.out.print(res);
            res = "";
        }
        for (int i = 0; i < aux; i++) {
            char conse1[] = new char[15], temp_con1;
            for (int c = 0; c < conse1.length; c++) {
                temp_con1 = archi.readChar();


            }
        }

        System.out.println("");
    }

    public void leer_todo() throws FileNotFoundException, IOException {
        long ap_actual, ap_final;
        int contador = 0, aux;
        long lreg;
        char caracter;
        String res = "";
        RandomAccessFile leer_archi = new RandomAccessFile("Reglas_nuevo", "r");
        RandomAccessFile encabezado = new RandomAccessFile("encabezado_nuevo", "r");
        int total_ant, total_con;
        encabezado.seek(0);
        total_ant = encabezado.readInt();
        encabezado.seek((5 * 40) + 4);
        total_con = encabezado.readInt();
        while ((ap_actual = leer_archi.getFilePointer()) != (ap_final = leer_archi.length())) {


            int llave = leer_archi.readInt();
            if (llave != 0) {
                System.out.print(llave + "  ");
            }
            aux = 5 - total_ant;
            for (int i = 0; i < total_ant; i++) {

                char antece1[] = new char[15], temp_ant1;
                for (int c = 0; c < antece1.length; c++) {
                    temp_ant1 = leer_archi.readChar();
                    res += temp_ant1;
                    if (llave != 0) {
                        System.out.print(res);
                        
                        res = "";
                    }
                }
            }
            for (int i = 0; i < aux; i++) {
                char antece1[] = new char[15], temp_ant1;
                for (int c = 0; c < antece1.length; c++) {
                    temp_ant1 = leer_archi.readChar();
                }
            }

            aux = 2 - total_con;
            for (int i = 0; i < total_con; i++) {

                char conse1[] = new char[15], temp_con1;
                for (int c = 0; c < conse1.length; c++) {
                    temp_con1 = leer_archi.readChar();
                    res+=temp_con1;
                    if (llave != 0) {
                        System.out.print(res);
                        res = "";
                    }

                }
            }
            for (int i = 0; i < aux; i++) {
                char conse1[] = new char[15], temp_con1;
                for (int c = 0; c < conse1.length; c++) {
                    temp_con1 = leer_archi.readChar();
                }
            }

            if (llave != 0) {
                System.out.println("");
            }
        } //fin de while

        leer_archi.close();
    }

    public void leer_uno() throws FileNotFoundException, IOException {

        RandomAccessFile leer_archi = new RandomAccessFile("Reglas_nuevo", "r");
        RandomAccessFile encabezado = new RandomAccessFile("encabezado_nuevo", "r");
        int total_ant, total_con, aux;
        encabezado.seek(0);
        total_ant = encabezado.readInt();
        encabezado.seek((5 * 40) + 4);
        total_con = encabezado.readInt();
        leer_archi.seek(pos_final);

        int llave = leer_archi.readInt();
        if (llave != 0) {
            System.out.print(llave + "  ");
        }
        aux = 5 - total_ant;
        for (int i = 0; i < total_ant; i++) {

            char antece1[] = new char[15], temp_ant1;
            for (int c = 0; c < antece1.length; c++) {
                temp_ant1 = leer_archi.readChar();
                if (llave != 0) {
                    System.out.print(temp_ant1);
                }
            }
        }
        for (int i = 0; i < aux; i++) {
            char antece1[] = new char[15], temp_ant1;
            for (int c = 0; c < antece1.length; c++) {
                temp_ant1 = leer_archi.readChar();
            }
        }

        aux = 2 - total_con;
        for (int i = 0; i < total_con; i++) {

            char conse1[] = new char[15], temp_con1;
            for (int c = 0; c < conse1.length; c++) {
                temp_con1 = leer_archi.readChar();
                if (llave != 0) {
                    System.out.print(temp_con1);
                }
            }
        }
        for (int i = 0; i < aux; i++) {
            char conse1[] = new char[15], temp_con1;
            for (int c = 0; c < conse1.length; c++) {
                temp_con1 = leer_archi.readChar();
            }
        }
        if (llave != 0) {
            System.out.println("");
        }
        pos_final = leer_archi.getFilePointer();
        if (pos_final == leer_archi.length()) {
            System.out.println("Ultima regla");
            pos_final = 0;
        }
        leer_archi.close();
    }
}
