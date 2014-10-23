package sed;

import MD.ModeloDifuso;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Ramón
 */
public class Difusificacion 
{
    File salida;
    DataOutputStream dos;

    public Difusificacion(File salida) throws FileNotFoundException 
    {
        this.salida = salida;
        FileOutputStream fos = new FileOutputStream(salida);   
        dos = new DataOutputStream(fos);
    }
    
    public void difusificar(ModeloDifuso md,float x) throws FileNotFoundException, IOException
    {
        float[] valores = new float[5];
        for(int i = 0; i < 5; i++) //Recorrer cada etiqueta del modelo
            {
                //Evaluar para cada etiqueta
                valores[i] = md.evalua(i,x);
                
                dos.writeChars(md.nombre());
                System.out.print(md.nombre()+"\t");                
                dos.writeChars(md.nombreEtiqueta(i));
                System.out.print(md.nombreEtiqueta(i)+"\t");
                dos.writeFloat(valores[i]);
                System.out.print(valores[i]+"\n");
            }
        dos.flush();
    }
    
}
