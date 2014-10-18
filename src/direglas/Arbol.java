package direglas;

public class Arbol {
	private NodoArbol raiz;
	private String nombre;
	
	public Arbol(String nombre, int elemento, int memoria) {
		this.nombre = nombre;
		raiz = new NodoArbol(elemento, memoria);
	}
	public String getNombre() {
		return nombre;
	}
	public NodoArbol getRaiz() {
		return raiz;
	}
	public void agregarElemento(NodoArbol n, int elementoReferencia, int elementoAgregar, int memoria){
		if(n.elemento == elementoReferencia)
			n.agregarHijo(elementoAgregar, memoria);
		else
			for (int i = 0; i < n.getTotalHijos(); i++) 
				agregarElemento(n.getHijo(i), elementoReferencia, elementoAgregar, memoria);
		
	}
	public void eliminarHijos(NodoArbol n, int elemento){
		if(n.elemento == elemento)
			n.eliminarHijos();
		else
			for (int i = 0; i < n.getTotalHijos(); i++) 
				eliminarHijos(n.getHijo(i), elemento);
	}
	
	public void imprime(NodoArbol n, int espacio){
		for (int i = 0; i < espacio; i++)
			System.out.print("-");
		System.out.println(n.elemento);
		for (int i = 0; i < n.getTotalHijos(); i++)
			imprime(n.getHijo(i), espacio+2);
	}
}
