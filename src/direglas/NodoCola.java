package direglas;

public class NodoCola {
	public NodoArbol elemento;
	private NodoCola siguiente;
	public boolean marcado = false;
	
	public NodoCola(NodoArbol elemento, NodoCola siguiente) {
		this.elemento = elemento;
		this.siguiente = siguiente;
	}
	
	public NodoCola getSiguiente() {
		return siguiente;
	}
	
	public void setSiguiente(NodoCola siguiente) {
		this.siguiente = siguiente;
	}
}
