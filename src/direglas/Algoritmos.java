package direglas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Algoritmos {

    public static boolean busquedaAnchura(Arbol arbol, int buscar) {
        Cola c = new Cola();
        Leer l = new Leer();
        System.out.println("Inicia busqueda en anchura");
        System.out.println("");
        c.insertar(arbol.getRaiz());
        while (!c.isVacia()) {
            if (c.verTope().elemento.elemento == buscar) {
                System.out.println("Se encontro nodo: " + buscar);
                try {
                    System.out.println("Lugar en Memoria: " + c.verTope().elemento.memoria);//imprime memoria
                    l.leer_aleatorio(c.verTope().elemento.memoria);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Algoritmos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Algoritmos.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            } else {
                c.verTope().marcado = true;
            }

            if (c.verTope().marcado) {
                int t = c.verTope().elemento.getTotalHijos();
                for (int i = 0; i < t; i++) {
                    c.insertar(c.verTope().elemento.getHijo(i));
                }
                System.out.println(c.sacar().elemento);
            }
        }
        return false;
    }

    public static boolean seekanddestroy(Arbol arbol, int buscar) {
        Cola c = new Cola();
        //Leer l=new Leer();
        // ac= new Actualizar();
        Borrar borrar = new Borrar();
        System.out.println("Inicia busqueda en anchura para actualizar");
        System.out.println("");
        c.insertar(arbol.getRaiz());
        while (!c.isVacia()) {
            if (c.verTope().elemento.elemento == buscar) {
                System.out.println("Se encontro nodo: " + buscar);
                try {
                    System.out.println("Lugar en Memoria: " + c.verTope().elemento.memoria);//imprime memoria
                    borrar.Borrado_Reglas(c.verTope().elemento.memoria);
                    borrar.actualizar_Indice(buscar);
                    //l.leer_aleatorio(c.verTope().elemento.memoria);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Algoritmos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Algoritmos.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            } else {
                c.verTope().marcado = true;
            }

            if (c.verTope().marcado) {
                int t = c.verTope().elemento.getTotalHijos();
                for (int i = 0; i < t; i++) {
                    c.insertar(c.verTope().elemento.getHijo(i));
                }
                System.out.println(c.sacar().elemento);
            }
        }
        return false;
    }
}
