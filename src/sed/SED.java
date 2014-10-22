package sed;
import MD.ModelosDifusos;
import java.io.*;
import java.util.*;
/**
 *
 * @author Ramón
 */
public class SED {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ModelosDifusos md=new ModelosDifusos();
        md.escribir_Modelo("Temperatura", 0, 100, 3);
        md.leer_Indice();
        md.leer_Modelo();
    }
    
}
