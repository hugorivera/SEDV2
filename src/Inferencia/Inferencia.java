package Inferencia;

import java.io.IOException;
public class Inferencia {
    
    private ManejoArchivos ma;

    public void MIN_MAX() throws IOException {
        ma = new ManejoArchivos("encabezado", "reglas");
        ma.leerEncabezado();
        float [] max = new float[ma.getTotReg()];
        for (int i = 0; i < max.length; i++) {
            String [] r = ma.leerRegla(i);
            float min = 1;
            for (int j = 0; j < r.length-1; j++) {
                if(min>ma.getValor(ma.antecedentes.get(j), r[j])){
                    min = ma.getValor(ma.antecedentes.get(j), r[j]);
                }
            }
            max[i] = min;
            ma.insertarSalida(ma.consecuentes.get(0),r[r.length-1],min);
        }
    }
    
}
