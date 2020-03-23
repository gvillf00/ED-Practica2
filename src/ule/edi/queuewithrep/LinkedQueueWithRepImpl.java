package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.queuewithrep.ArrayQueueWithRepImpl.ElemQueueWithRep;

public class LinkedQueueWithRepImpl<T> implements QueueWithRep<T> {

	
	// Atributos
	private QueueWithRepNode<T> front; //es la flecha que apunta al primer nodo de mi lista enlazada.
	int count;
	
	@SuppressWarnings("hiding")
	public class QueueWithRepNode<T> {
		T elem;
		int num;
		QueueWithRepNode<T> next; //necesita una conexi�n entre un nodo y el siguiente.
		
		public QueueWithRepNode (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
	}
	
	///// ITERADOR //////////
		@SuppressWarnings("hiding")
		
		public class LinkedQueueWithRepIterator<T> implements Iterator<T> {
			int valorNum;//contenido de la vble num en el nodo
			QueueWithRepNode<T> nodotemp; //para iniciar una nueva lista enlazada
			
			public LinkedQueueWithRepIterator(QueueWithRepNode<T> nodo) {
				nodotemp=nodo;//le paso a mi lista local el nodo actual
				valorNum=nodo.num;//y extraigo su valor num a la vble local
			}
			
			@Override
			public boolean hasNext() {
				//devuelve true, o sea, tiene siguiente, si es distinto de null
				return (nodotemp!=null);
			}
			@Override
			public T next() {
				if (!hasNext())
				{
					//me devuelve una excepci�n y no sigue la ejecuci�n.
					throw new NoSuchElementException();
				}
				//le resto un valor a lo que llevaba el noto A2 - A1
				valorNum--;
				T elem;
				elem=nodotemp.elem;//almaceno tambi�n el elemento que trae, en este caso A
				if (valorNum==0)
				{
					//si al restar me quedo sin num paso al siguiente porque se ha agotado el nodo
					nodotemp = nodotemp.next;
				}
				//devuelvo el valor que ten�a
				return(elem);
			}
			
		}
		////// FIN ITERATOR
	
	@SuppressWarnings("unchecked")
	public LinkedQueueWithRepImpl() {
		count=0;
		//inicio el nodo flecha front con null, no apunta a nada, la lista est�  vac�a
		front=null;
		//TODO
		
	}
	
	@Override
	public void add(T element, int times) {
		//controlo las excepciones de los par�metros pasados
		if (element==null) {
			throw new NullPointerException();
		}
		if (times<0) {
			throw new IllegalArgumentException();
		}
		
		//creo un nodo para meter los datos que me env�an.
		QueueWithRepNode<T> nodoNuevo; //creada e inicializada a null
		nodoNuevo= new QueueWithRepNode<T>(element, times); //y ahora lo tengo con contenido.
		
		if (isEmpty()) { 
			//est� vac�o
			front=nodoNuevo;
			front.next=null;
			count ++;
		}
		else {
			//no est� vac�o, tengo que mirar si contiene el elemento o no para eso lo recorro.
			//para recorrerlo utilizo un auxiliar
			QueueWithRepNode<T> ListaAux=front;
			QueueWithRepNode<T> AnadirNodo=front;
			
			if (contains(element)) {
				//le incremento las times veces
				
				while (ListaAux!=null) {
					if (ListaAux.elem.equals(element)) {
						//lo hemos encontrado incremento las veces
						ListaAux.num =ListaAux.num + times;
						//en el mismo sitio actualizo la lista enlazada front.
						front.num = ListaAux.num;
					}
					//me desplazo al siguiente
					ListaAux=ListaAux.next;
				}
				
			}else {
				//no est� el elemento lo a�ado al final de la lista enlazada
				
				while (AnadirNodo!=null) {
					AnadirNodo=AnadirNodo.next;
				}
				//al salir del bucle est� en el final
				AnadirNodo=nodoNuevo;
				AnadirNodo.next =null;
				front=AnadirNodo;
				count++;
			}
		}
	}

	@Override
	public void add(T element) {
		// implemento excepciones
		if (element==null) {
			throw new NullPointerException();
		}
		//llamo a la que ya tengo de a�adir pas�ndole 1
		add(element, 1);
	}

	

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		front=null; //la flecha que apunta al primer nodo apunta a null
		count=0;
	}

	@Override
	public boolean contains(T element) {
		// implemento excepciones
		if (element==null) {
			throw new NullPointerException();
		}
		//utilizo un nodo auxiliar.
		QueueWithRepNode<T> nodoAux;
		nodoAux= front;//le asigno la lista enlazada
		//recorrer y comparar con el equals.
		
		while (nodoAux!=null)
		{
			if (nodoAux.elem.equals(element)) {
				return true;
			}
			nodoAux=nodoAux.next;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (count==0);
	}

	@Override
	public long size() {
		// Recorro la lista enlazada nodo a nodo sumando los num
		//vble que acumula la suma
		//creo un auxiliar para recorrerla
		QueueWithRepNode<T> ListaAux=front;
		long totalcola=0;
		if (!isEmpty()) {
			//recorro la cola si no est� vac�a
			
			while (ListaAux!=null) {
				totalcola=totalcola + ListaAux.num;
				ListaAux=ListaAux.next;
			}
		}
		return totalcola;
	}

	@Override
	public int count(T element) {
		// implemento excepciones
		if (element==null) {
			throw new NullPointerException();
		}
		//vble para devolver
		int CuantosHay=0;
		
		if (!isEmpty()) {
			if (contains(element)) {
				//utilizo una lista enlazada auxiliar para no perder la referencia front de mi lista.
				QueueWithRepNode<T> nodoAux;
				nodoAux=front;
				//si lo contiene tengo que buscarlo para devolver su valor, recorro
				
				while (nodoAux!=null) {
					if (nodoAux.elem.equals(element)) {
						//es igual al element que me pasa, capturo su valor
						CuantosHay=nodoAux.num;
					}
					//encontrado o no paso al siguiente nodo
					nodoAux=nodoAux.next;
				}
			}
		}
		return CuantosHay;
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new LinkedQueueWithRepIterator(front);
	}
	
	@Override
	public String toString() {
		//TODO 
		
		final StringBuffer buffer = new StringBuffer();
		
		buffer.append("(");

		// TODO Ir añadiendo en buffer las cadenas para la representación de esta bolsa
		//creo una lista auxiliar donde meter mi front y la recorro con el iterador
		QueueWithRepNode<T> nodoAux=front;
		//creo una variable de tipo iterador
		Iterator<T> miIterador=iterator();
		if (!isEmpty()) {
			
			while (miIterador.hasNext()) {
				//if (miIterador.hasNext()){
					buffer.append(miIterador.next().toString() + ' ');
				//}
			}
		}
		buffer.append(")");
		
		return buffer.toString();
	}

	@Override
	public int remove() throws EmptyCollectionException {
		int CuantosHay=0;
		//controlo las excepciones de los par�metros pasados
		if (isEmpty()) {
			throw new EmptyCollectionException("Cola vac�a");
		}
		//si no est� vacia capturo el primero directamente del front que no lo var�o
		CuantosHay=front.num;
		//le quito un nodo
		count--;
		//y muevo la flecha de inicio al siguiente, al no apuntar ya al nodo con datos se elimina.
		front=front.next;

		return CuantosHay;
	}

	@Override
	public void remove(T element, int times) {
		//primero las excepciones
		if (element == null) {
			throw new NullPointerException("element es null");
		}
		if (times <0) {
			throw new IllegalArgumentException("n�mero no puede ser negativo");
		}
		if (!contains(element)) {
			throw new NoSuchElementException("el elemento no est� en la lista");

		}
		//lista enlazada auxiliar para recorrerla y le asigno la original, front
		QueueWithRepNode<T> listaAux=front;
		//vble auxiliar booleana para no hacer todo el bucle y salir en cuanto lo encuentre
		boolean encontrado=false;
		
		while (listaAux!=null && !encontrado) {
			if (listaAux.elem.equals(element)) {
				if (listaAux.num <=times) {
					//excepci�n y acaba la ejecuci�n
					throw new IllegalArgumentException();
					//System.out.println("numero de veces mayor a las existentes");
				}
				//le resto lo que me pasan en el nodo correspondiente
				listaAux.num =listaAux.num-times;	
				encontrado=true;
			}
			listaAux=listaAux.next;
		}
		front=listaAux;
	}

}
