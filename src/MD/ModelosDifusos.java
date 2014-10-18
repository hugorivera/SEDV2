/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MD;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Hugo
 */
public class ModelosDifusos {
    int llave;
    //String nom_mod,nom_eti,tipo_fun;
    //double inicio,fin,arg;
    
    public void escribir_Modelo (String nom_mod, double inicio, double fin, int num_eti) throws IOException{
        int n=1,aux=num_eti;
        if(num_eti>5)
            num_eti=5;
        String nom_eti,tipo_fun;
        StringBuffer buffer=null;
        System.out.println("");
        RandomAccessFile archi=new RandomAccessFile("modelos_difusos","rw");
        long pos=archi.length();
        archi.seek(pos);
        System.out.println(pos);
        escribe_Indice(pos);
        Scanner entrada=new Scanner(System.in);
        //archi.writeInt(llave);
        buffer=new StringBuffer(nom_mod);
        buffer.setLength(20);
        archi.writeChars(buffer.toString());
        archi.writeDouble(inicio);
        archi.writeDouble(fin);
        do{
            double arg1=0,arg2=0,arg3=0,arg4=0;
            System.out.println("Ingresa el nombre de la etiqueta "+n);
            nom_eti=entrada.next();
            buffer=new StringBuffer(nom_eti);
            buffer.setLength(15);
            archi.writeChars(buffer.toString());
            System.out.println("Ingresa el tipo de función (triangular=tri, trapezoidal=tra, otra)");
            tipo_fun=entrada.next();
            buffer=new StringBuffer(tipo_fun);
            buffer.setLength(5);
            archi.writeChars(buffer.toString());
            switch(tipo_fun){
                case "tri":
                    System.out.println("Ingresa el valor en X del punto máximo de la función");
                    arg1=entrada.nextDouble();
                    break;
                case "tra":
                    System.out.println("Ingresa el valor en X del primer punto máximo de la función");
                    arg1=entrada.nextDouble();
                    System.out.println("Ingresa el valor en X del segundo punto máximo de la función");
                    arg2=entrada.nextDouble();
                    break;
                case "otra":
                    break;
            }
            archi.writeDouble(arg1);
            archi.writeDouble(arg2);
            archi.writeDouble(arg3);
            archi.writeDouble(arg4);
            num_eti--;
            n++;
        }while (num_eti!=0);
        if(aux<5){
            for(int i=5-aux;i>0;i--)
            {
                buffer=new StringBuffer("");
                buffer.setLength(15);
                archi.writeChars(buffer.toString());
                buffer=new StringBuffer("");
                buffer.setLength(5);
                archi.writeChars(buffer.toString());
                archi.writeDouble(0);
                archi.writeDouble(0);
                archi.writeDouble(0);
                archi.writeDouble(0);
            }
        }
        archi.close();
    }
    
    public void escribe_Indice (long pos) throws IOException{
        int llave;
        RandomAccessFile indice=new RandomAccessFile("indice", "rw");
        long posi=indice.length(),aux=posi;
        if(posi==0)
            llave=1;
        else{
            posi-=12;
            indice.seek(posi);
            llave=indice.readInt()+1;
        }
        indice.seek(aux);
        indice.writeInt(llave);
        indice.writeLong(pos);
    }
    public void leer_Indice() throws IOException{
        long ap_actual,ap_final;
        RandomAccessFile archi=new RandomAccessFile("indice", "r");
        while((ap_actual=archi.getFilePointer())!=(ap_final=archi.length())){
            System.out.println(archi.readInt());
            System.out.println("-"+archi.readLong()+"\n");
        }
        archi.close();
    }
    public void leer_Modelo () throws IOException{
        long ap_actual,ap_final;
        RandomAccessFile archi=new RandomAccessFile("modelos_difusos", "r");
        while((ap_actual=archi.getFilePointer())!=(ap_final=archi.length())){
            char nom_mod[]=new char[20],nom_eti[]=new char[15],tipo_fun[]=new char[5],temp;
            String nom_m="",nom_e,tipo_f;
            for(int c=0;c<nom_mod.length;c++){
                temp=archi.readChar();
                //nom_mod[c]=temp;
                nom_m+=temp;
            }
            //new String(nom_mod).replace('\0',' ');
            System.out.println("Variable: "+nom_m);
            System.out.println("Universo de Discurso: "+archi.readDouble()+" - "+archi.readDouble());
            for(int i=0;i<5;i++){
                nom_e=""; tipo_f="";
                for(int c=0;c<nom_eti.length;c++){
                    temp=archi.readChar();
                    //nom_eti[c]=temp;
                    nom_e+=temp;
                }
                //new String(nom_eti).replace('\0',' ');
                System.out.println("Etiqueta: "+nom_e);
                for(int c=0;c<tipo_fun.length;c++){
                    temp=archi.readChar();
                    tipo_f+=temp;
                }
                //new String(tipo_fun).replace('\0', ' ');
                System.out.println("Tipo función: "+tipo_f);
                for(int j=0;j<4;j++){
                    System.out.println("Argumento "+j+": "+archi.readDouble());
                }
            }
            
        }
        archi.close();
    }
}
