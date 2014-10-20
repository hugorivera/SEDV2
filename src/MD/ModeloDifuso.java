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
    
    public ModeloDifuso(RandomAccessFile archim, long posicion) 
    {
        raf = archim;
        this.posicion = posicion;
    }
    

    public float inicio() throws IOException {
        
        raf.seek(posicion);
        raf.readInt();
        for (int j = 0; j < 20; j++) 
        {
            raf.readChar();
        }
        return (float)raf.readDouble();
    }

    public float fin() throws IOException {
        int i = 5;
        raf.seek(posicion);
        raf.readInt();
        for (int j = 0; j < 20; j++) 
        {
            raf.readChar();
        }
        raf.readDouble();
        raf.readDouble();
        for (int j = 0; j < i; j++) 
        {
            for (int k = 0; k < 15; k++) 
            {
                raf.readChar();
            }
            for (int k = 0; k < 5; k++) 
            {
                raf.readChar();
            }
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
        }
        
        for (int k = 0; k < 15; k++) 
            {
                raf.readChar();
            }
        String tipo = "";
            for (int k = 0; k < 5; k++) 
            {
                tipo += raf.readChar();
            }
            float punto1 = (float)raf.readDouble();
            float punto2 = (float)raf.readDouble();
            float punto3 = (float)raf.readDouble();
            float punto4 = (float)raf.readDouble();
            float inicio = (float)raf.readDouble();
            return (float)raf.readDouble();
    }

    public float evalua(int i, float x) throws IOException 
    {
        raf.seek(posicion);
        raf.readInt();
        for (int j = 0; j < 20; j++) 
        {
            raf.readChar();
        }
        raf.readDouble();
        raf.readDouble();
        for (int j = 0; j < i; j++) 
        {
            for (int k = 0; k < 15; k++) 
            {
                raf.readChar();
            }
            for (int k = 0; k < 5; k++) 
            {
                raf.readChar();
            }
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
        }
        
        for (int k = 0; k < 15; k++) 
            {
                raf.readChar();
            }
        String tipo = "";
            for (int k = 0; k < 5; k++) 
            {
                tipo += raf.readChar();
            }
            float punto1 = (float)raf.readDouble();
            float punto2 = (float)raf.readDouble();
            float punto3 = (float)raf.readDouble();
            float punto4 = (float)raf.readDouble();
            float inicio = (float)raf.readDouble();
            float fin = (float)raf.readDouble();
            
            boolean flag = true;
        for (int l = 0; l < "tri".length(); l++) 
        {
            if("tri".charAt(l)!= tipo.charAt(l))
            {                   
                flag = false;
                break;                
            }                
        }
        if(flag)
        {
            if(x < inicio)
                return 0;
            if(x < punto1)
                return (x - inicio) / (punto1 - inicio);
            if(x < fin)
                return (fin - x) / (fin - punto1);
            else
                return 0;
        }
        flag = true;
        for (int l = 0; l < "tra".length(); l++) 
        {
            if("tra".charAt(l)!= tipo.charAt(l))
            {                   
                flag = false;
                break;                
            }                
        }
        if(flag)
        {
            if(x < inicio)
                return 0;
            if(x < punto1)
                return (x - inicio) / (punto1 - inicio);
            if(x < punto2)
                return 1;
            if(x < fin)
                return (fin - x) / (fin - punto2);
            else
                return 0;
        }
        return 0;
    }

    public String nombre() throws IOException 
    {
        raf.seek(posicion);
        raf.readInt();
        String temp = "";
        for (int j = 0; j < 20; j++) 
        {
            temp += raf.readChar();
        }
        return temp;
    }

    public String nombreEtiqueta(int i) throws IOException 
    {
        raf.seek(posicion);
        raf.readInt();
        for (int j = 0; j < 20; j++) 
        {
            raf.readChar();
        }
        raf.readDouble();
        raf.readDouble();
        for (int j = 0; j < i; j++) 
        {
            for (int k = 0; k < 15; k++) 
            {
                raf.readChar();
            }
            for (int k = 0; k < 5; k++) 
            {
                raf.readChar();
            }
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
            raf.readDouble();
        }
        
        String nombre = "";
        for (int k = 0; k < 15; k++) 
            {
                nombre += raf.readChar();
            }
        return nombre;
    }
    
}
