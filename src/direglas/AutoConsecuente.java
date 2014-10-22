/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package direglas;

import java.util.ArrayList;

/**
 *
 * @author Hugo
 */
public class AutoConsecuente {

    ArrayList positivas = new ArrayList();
    ArrayList negativas = new ArrayList();

    public void agregaPositivas(Object... valor) {
        for (int i = 0; i < valor.length; i++) {
            positivas.add(valor[i]);
        }
    }

    public void agregaNegativas(Object... valor) {
        for (int i = 0; i < valor.length; i++) {
            negativas.add(valor[i]);
        }
    }

    public String comparacion(String... valores) {
        int p = 0;
        int n = 0;
        String respuesta;
        for (int i = 0; i < valores.length; i++) {
            if (positivas.contains(valores[i])) {
                p++;
            }
            if (negativas.contains(valores[i])) {
                n++;
            }
        }
        if (n == p) {
            respuesta = "reprobado";
        }
        if (n > p) {
            respuesta = "reprobado";
        } else {
            respuesta = "aprobado";
        }
        System.out.println(respuesta);
        return respuesta;

//        Operaciones o = new Operaciones();
//
//        o.agregaPositivas("mucho", "formal","casual","regular");
//        o.agregaNegativas("nada", "regular", "informal");
////Ejemplos        
//        o.comparacion("nada", "nada", "nada", "nada", "informal");
//        o.comparacion("nada", "mucho", "nada", "mucho", "formal");
//        o.comparacion("regular", "nada", "nada", "regular", "casual");


    }
}
