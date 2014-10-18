
package Desdifusificacion;


import MD.ModeloDifuso;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Desdifusificador 
{
    File entradas;
    
    //Constructor del desdifusificador
    public Desdifusificador(File salidasDifusas)
    {
        //Este archivo es el generado en la inferencia
        entradas = salidasDifusas;
    }
    
    public float desdifusificarPorCentroide(ModeloDifuso md) throws IOException
    {
        float centroide = 0, //Centroide del modelo
                x = md.inicio(), //Valor inicial de X
                delta = 0.01f,  //Incremento de un X a otro
                sumaDeMomentos = 0, //Sumatoria X * grado de pertenencia
                sumaDePonderaciones = 0, //Sumatorias de grados de pertenencia
                gradoPertenencia = 0;     //Grado de pertenencia de X
        float[] valores = new float[5];
        
        //Generar valores desde el inicio del universo del modelo hasta el final
        while(x < md.fin())            
        {            
            for(int i = 0; i < 5; i++) //Recorrer cada etiqueta del modelo
            {
                //Evaluar para cada etiqueta
                valores[i] = md.evalua(i,x);
                //Recortar 
                valores[i] = ajuste(md,i,valores[i]);
            }
            //Maximizar
            Arrays.sort(valores);
            //El valor máximo está en el último lugar del arreglo
            gradoPertenencia = valores[4];
            
            sumaDeMomentos += x * gradoPertenencia;
            sumaDePonderaciones += gradoPertenencia;            
            x += delta; //Generar siguiente X
        }
        
        //Calcular centro de gravedad
        centroide = sumaDeMomentos / sumaDePonderaciones;       
        
        return centroide;
    }

    private float ajuste(ModeloDifuso md, int i, float f) throws FileNotFoundException, IOException 
    {
        float x = 0;
        FileInputStream fis = new FileInputStream(entradas);
        DataInputStream dis = new DataInputStream(fis);
        while(dis.available() != 0)
        {
            String nombreMod = "";
            for (int j = 0; j < 20; j++)
                nombreMod += dis.readChar(); 
            
            String nombreEtiqueta = "";            
            for(int k = 0; k < 15; k ++)
                nombreEtiqueta += dis.readChar();
            if(nombreMod.equals(md.nombre()) && nombreEtiqueta.equals(md.nombreEtiqueta(i)))
            {
                float temp = dis.readFloat();
                return (x > temp)?temp:x;
            }
            else
            {
                dis.readFloat();
            }
        }
        dis.reset();
        fis.reset();
        dis.close();
        fis.close();
        return x;
    }
}

    

