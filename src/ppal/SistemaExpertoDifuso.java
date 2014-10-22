
package ppal;

import Desdifusificacion.Desdifusificador;
import Inferencia.Inferencia;
import MD.ModeloDifuso;
import MD.ModelosDifusos;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import sed.Difusificacion;


/**
 *
 * @author LVA
 */
public class SistemaExpertoDifuso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException 
    {
        ModelosDifusos md=new ModelosDifusos();
        Difusificacion dif = new Difusificacion(new File("entrada_inferencia_nuevo"));
        Inferencia inf = new Inferencia();
        Desdifusificador des = new Desdifusificador(new File("salida_inferencia_nuevo"));
        Scanner entrada = new Scanner(System.in);
        String linea = "";
        
        System.out.print("SED#>");
        while(!((linea = entrada.nextLine()).equals("salir")))
        {
            String[] argumentos = linea.split(" ");
            if(linea.startsWith("ModDifuso"))
            {                
                md.escribir_Modelo(argumentos[1], Double.parseDouble(argumentos[2]), Double.parseDouble(argumentos[3]), Integer.parseInt(argumentos[4]));
            }
            else
            if(linea.startsWith("ver modelos"))
            {
                md.leer_Modelo();
            }            
            else
            if(linea.startsWith("Difusificar"))
            {
                System.out.println("Difusificación de "+argumentos[1]);
                ModeloDifuso m = md.buscarModelo(argumentos[1]);
                if(m != null)
                    dif.difusificar(m, Float.parseFloat(argumentos[2]));
                else
                    System.out.println("");
            }
            else
            if(linea.startsWith("Inferir"))
            {
                System.out.println("Infiriendo... (MAX-MIN)");
                inf.MIN_MAX();
                System.out.println("Inferido");
            }
            else
            if(linea.startsWith("Desdifusificar"))
            {
                System.out.println("Desdifusificando "+argumentos[1]);
                ModeloDifuso m = md.buscarModelo(argumentos[1]);
                float resultado = des.desdifusificarPorCentroide(m);
                System.out.println("Resultado:\t"+resultado);
            }
            else
            {
                System.out.println("Comando no reconocido");
            }
            
            System.out.print("SED#>");
        }
       
    }
    
}
