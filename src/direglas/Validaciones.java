/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package direglas;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Hugo
 */
public class Validaciones {

    File encabezado, reglas, indice, tamaño_indice, key;

    public boolean Validar_encabezado() {
        boolean flag;
        encabezado = new File("encabezado");
        if (encabezado.length()!=0) {
            flag = true;
        } else {
            flag = false;
            System.out.println("No existe Encabezado");
        }

        return flag;
    }

    public boolean Validar_reglas() {
        boolean flag;
        reglas = new File("Reglas");
        if (reglas.length() != 0) {
            flag = true;
        } else {
            flag = false;
            System.out.println("No tienes reglas");
        }
        return flag;
    }

    public boolean Validar_todo() {
        boolean flag;
        reglas = new File("Reglas");
        encabezado = new File("encabezado");
        indice = new File("indice");
        tamaño_indice = new File("tamano indice");
        key = new File("key");
        if (reglas.exists() || reglas.exists() || indice.exists() || tamaño_indice.exists() || key.exists()) {
            flag = true;
        } else {
            flag = false;
            System.out.println("No hay archivos que borrar");
        }
        return flag;
    }
    
    public boolean Reescribir_encabezado(){
        boolean flag;
        encabezado = new File("encabezado");
        if(encabezado.length()!=0){
            Scanner entrada =new Scanner(System.in);
            System.out.println("Desea reescribir el encabezado\n Si=1  No=0");
            int reescribir=entrada.nextInt();
            if(reescribir==1){
                flag=true;
            }else{
                flag=false;
            }
        }else{
            flag=true;
        }
        return flag;
    }
    
}
