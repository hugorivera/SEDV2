package Inferencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class ManejoArchivos {
    private final RandomAccessFile encabezado, reglas, salida, entrada;
    public final ArrayList<String> antecedentes, consecuentes;
    private final int tamEnc = 20, tamReg = 15;
    
    public ManejoArchivos(String cEncabezado, String cReglas) throws FileNotFoundException, IOException {
        encabezado = new RandomAccessFile(cEncabezado, "rw");
        reglas = new RandomAccessFile(cReglas, "rw");
        antecedentes = new ArrayList<>();
        consecuentes = new ArrayList<>();
        salida = new RandomAccessFile("salida_inferencia", "rw");        
        entrada = new RandomAccessFile("entrada_inferencia", "rw");
    }
    
    public void Escribir_Encabezado() throws FileNotFoundException, IOException {
        System.out.println("# de Variables Linguisticas?");
        Scanner entrada = new Scanner(System.in);
        int c = entrada.nextInt();
        if (c >= 5)
            c = 5;
        int aux = 5 - c;

        encabezado.writeInt(c);
        String antecedente1;
        for (int i = 0; i < c; i++) {
            System.out.println("Antecedente");
            antecedente1 = entrada.next();
            StringBuffer buffer = new StringBuffer(antecedente1);
            buffer.setLength(tamEnc);
            encabezado.writeChars(buffer.toString());
        }

        for (int i = 0; i < aux; i++) {
            antecedente1 = "";
            StringBuffer buffer = new StringBuffer(antecedente1);
            buffer.setLength(tamEnc);
            encabezado.writeChars(buffer.toString());
        }

        System.out.println("# de consecuentes?");
        c = entrada.nextInt();
        if (c >= 2)
            c = 2;
        aux = 2 - c;
        
        encabezado.writeInt(c);
        String consecuente1;
        for (int i = 0; i < c; i++) {
            System.out.println("consecuente");
            consecuente1 = entrada.next();
            StringBuilder buffer = new StringBuilder(consecuente1);
            buffer.setLength(tamEnc);
            encabezado.writeChars(buffer.toString());
        }

        for (int i = 0; i < aux; i++) {
            consecuente1 = "";
            StringBuilder buffer = new StringBuilder(consecuente1);
            buffer.setLength(tamEnc);
            encabezado.writeChars(buffer.toString());
        }
    }
    
    public void leerEncabezado() throws IOException {
        encabezado.seek(0);
        int numero;
        numero = encabezado.readInt();//numero antecedentes
        for (int i = 0; i < numero; i++)
            antecedentes.add(leeUno(tamEnc, encabezado));
        encabezado.seek(encabezado.getFilePointer()+(5 - numero)*2*tamEnc);
        
        numero = encabezado.readInt();//numero consecuentes
        for (int i = 0; i < numero; i++)
            consecuentes.add(leeUno(tamEnc, encabezado));
    }
    
    public String[] leerRegla(int n) throws FileNotFoundException, IOException {     
        int ta = getTotalAnte();
        int tc = getTotalConse();
        String [] r = new String[ta+tc];
        reglas.seek(n*(tamReg*7*2+4)+4);
        for (int i = 0; i < ta; i++)
            r[i] = leeUno(tamReg, reglas);
        
        reglas.seek(reglas.getFilePointer() + (5 - ta)*(tamReg*2));

        for (int i = ta; i < ta+tc; i++)
            r[i] = leeUno(tamReg, reglas);
        
        return r;
    }

    public String leeUno(int longitud, RandomAccessFile archivo) throws IOException {
        String variable = "";
        for (int i = 0; i < longitud; i++) {
            char readChar = archivo.readChar();
            variable = variable + readChar;
        }

        return variable;
    }

    public int getTotalAnte(){
        return antecedentes.size();
    }
    
    public int getTotalConse(){
        return consecuentes.size();
    }
    
    public int getTotReg() throws IOException{
        return (int)((reglas.length())/(7*tamReg*2+4));
    }
    
    //--------------------------------------------------------------------------
    
    public void insertarSalida(String c, String e, float v) throws IOException{
        salida.seek(0);
        StringBuilder buffer = new StringBuilder(c);
        buffer.setLength(tamEnc);
        c = buffer.toString();
        
        StringBuilder buffer2 = new StringBuilder(e);
        buffer2.setLength(tamReg);
        e = buffer2.toString();
        
        String c1, e1;
        while(salida.getFilePointer()<salida.length()){
            c1 = leeUno(tamEnc, salida);
            e1 = leeUno(tamReg, salida);
            if(c1.equals(c) && e1.equals(e)){
                float v1 = salida.readFloat();
                if(v>v1){
                    salida.seek(salida.getFilePointer() - 4);
                    salida.writeFloat(v);
                }
                return;
            }else
                salida.seek(salida.getFilePointer() + 4);
        }
        
        salida.writeChars(c);
        salida.writeChars(e);
        salida.writeFloat(v);
    }
    
    public float getValor(String va, String et) throws FileNotFoundException, IOException{
        entrada.seek(0);
        String va1, et1;
        while(entrada.getFilePointer()<entrada.length()){
            va1 = leeUno(tamEnc, entrada);
            et1 = leeUno(tamReg, entrada);
            if(va1.equals(va) && et1.equals(et))
                return entrada.readFloat();
            else
                entrada.seek(entrada.getFilePointer() + 4);
        }
        return 0;
    }
    
}
