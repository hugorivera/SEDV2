/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package MD;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author LVA
 */
public class ModeloDifuso {
RandomAccessFile raf;
long posicion;
float inicio = 0,fin = 0;
String nombre = "";
String[] etiquetas = new String[]{"","","","",""};
String[] tipo = new String[]{"","","","",""};
float[] arg1 = new float[5];
float[] arg2 = new float[5];
float[] arg3 = new float[5];
float[] arg4 = new float[5];
float[] inicios = new float[5];
float[] fines = new float[5];

    public ModeloDifuso(RandomAccessFile archim, long posicion) throws IOException 
    {
        raf = archim;
        this.posicion = posicion;
        raf.seek(posicion);
        raf.readInt();
        for (int j = 0; j < 20; j++)
            nombre += raf.readChar();
        inicio = (float)raf.readDouble();
        fin = (float)raf.readDouble();
        
        for(int n = 0; n < 5; n++)
        {
            //Nombre etiqueta
            for (int k = 0; k < 15; k++) 
                etiquetas[n] += raf.readChar();
            
            //Tipo etiqueta
            for (int i = 0; i < 5; i++) 
                tipo[n] += raf.readChar();
            
            arg1[n] = (float)raf.readDouble();
            arg2[n] = (float)raf.readDouble();
            arg3[n] = (float)raf.readDouble();
            arg4[n] = (float)raf.readDouble();
            inicios[n] = (float)raf.readDouble();
            fines[n] = (float)raf.readDouble();
        }
    }
    

    public float inicio() throws IOException {
        return inicio;
    }

    public float fin() throws IOException {
        return fin;
    }

    public float evalua(int i, float x) throws IOException 
    {    
        boolean flag = true;
        for (int l = 0; l < "tri".length(); l++) 
        {
            if("tri".charAt(l)!= this.tipo[i].charAt(l))
            {                   
                flag = false;
                break;                
            }                
        }
        if(flag)
        {
            if(x < inicios[i])
                return 0;
            if(x < arg1[i])
                return (x - inicios[i]) / (arg1[i] - inicios[i]);
            if(x < fines[i])
                return (fines[i] - x) / (fines[i] - arg1[i]);
            else
                return 0;
        }
        flag = true;
        for (int l = 0; l < "tra".length(); l++) 
        {
            if("tra".charAt(l)!= this.tipo[i].charAt(l))
            {                   
                flag = false;
                break;                
            }                
        }
        if(flag)
        {
            if(x < inicios[i])
                return 0;
            if(x < arg1[i])
                return (x - inicios[i]) / (arg1[i] - inicios[i]);
            if(x < arg2[i])
                return 1;
            if(x < fines[i])
                return (fines[i] - x) / (fines[i] - arg2[i]);
            else
                return 0;
        }
        return 0;
    }

    public String nombre() throws IOException 
    {
        return nombre;
    }

    public String nombreEtiqueta(int i) throws IOException 
    {
        return etiquetas[i];
    }
    
}