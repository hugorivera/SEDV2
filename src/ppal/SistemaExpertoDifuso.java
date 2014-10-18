/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ppal;

import MD.ModeloDifuso;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import sed.Difusificacion;
import sed.ModelosDifusos;

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
        Difusificacion dif = new Difusificacion(new File("entrada_inferencia"));
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
                ModeloDifuso m = md.buscarModelo(argumentos[1]);
                dif.difusificar(m, Float.parseFloat(argumentos[2]));
            }
            else
            {
                System.out.println("Comando no reconocido");
            }
            
            System.out.print("SED#>");
        }
       
    }
    
}
