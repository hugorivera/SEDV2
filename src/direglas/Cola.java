package direglas;

public class Cola {
	private NodoCola inicio;
	public void insertar(NodoArbol elemento){
		if(inicio==null)
			inicio = new NodoCola(elemento, null);
		else{
			NodoCola temp = inicio;
			while(temp.getSiguiente()!=null)
				temp = temp.getSiguiente();
			temp.setSiguiente(new NodoCola(elemento, null));
		}
	}
	
	public NodoArbol sacar(){
		if(inicio==null){
			System.out.println("Error, no hay elementos en la cola");
			return null;
		}
		NodoCola temp = inicio;
		inicio = temp.getSiguiente();
		return temp.elemento;
	}

	public NodoCola verTope(){
		if(inicio==null){
			System.out.println("Error, no hay elementos en la cola");
			return null;
		}
		return inicio;
	}
	
	public void imprimeCola(){
		NodoCola temp = inicio;
		while(temp!=null){
			System.out.println(temp.elemento);
			temp = temp.getSiguiente();
		}
	}
	
	public void vaciar(){
		inicio = null;
	}
	
	public boolean isVacia(){
		return inicio==null;
	}
}
