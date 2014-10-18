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

    public Difusificacion(File salida) 
    {
        this.salida = salida;
    }
    
    public void difusificar(ModeloDifuso md,float x) throws FileNotFoundException, IOException
    {
        float[] valores = new float[5];
        FileOutputStream fos = new FileOutputStream(salida);
        DataOutputStream dos = new DataOutputStream(fos);
        
        for(int i = 0; i < 5; i++) //Recorrer cada etiqueta del modelo
            {
                //Evaluar para cada etiqueta
                valores[i] = md.evalua(i,x);
                
                dos.writeChars(md.nombre());
                dos.writeChars(md.nombreEtiqueta(i));
                dos.writeFloat(valores[i]);
            }
    }
    
}
