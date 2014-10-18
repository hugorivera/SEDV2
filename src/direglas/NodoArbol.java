package direglas;

import java.util.ArrayList;

public class NodoArbol {
	public int elemento;
	public int memoria;
	private ArrayList<NodoArbol> enlaces;
	
	public NodoArbol(int elemento, int memoria) {
		this.elemento = elemento;
		this.memoria = memoria;
		enlaces = new ArrayList<>();
	}
	
	public void agregarHijo(int elemento, int memoria){
		enlaces.add(new NodoArbol(elemento, memoria));
	}
	
	public void eliminarHijos(){
		enlaces = new ArrayList<>();
	}
	
	public NodoArbol getHijo(int i){
		if(i<enlaces.size())
			return enlaces.get(i);
		else
			return null;
	}
	
	public int getTotalHijos(){
		return enlaces.size();
	}
}
