package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;
import ule.edi.queuewithrep.LinkedQueueWithRepImpl.QueueWithRepNode;


public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {
	
	// atributos
    private final int capacityDefault = 10; //capacidad por defecto del array
	ElemQueueWithRep<T>[] data; //mi array
    private int count; //numero de posiciones ocupadas del array
   
    
	
	@SuppressWarnings("hiding")
	public class ElemQueueWithRep<T> {
		T elem;
		int num;
		public ElemQueueWithRep (T elem, int num){
			this.elem=elem;
			this.num=num;
		}
	}

///// ITERADOR //////////
	@SuppressWarnings("hiding")
	public class ArrayQueueWithRepIterator<T> implements Iterator<T> {

		private int contador;
		private int current;
		private int times;
		private ElemQueueWithRep<T>[] items;

		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] cola, int count){
			this.contador = count;
			this.items = cola;
			this.current = 0;
			this.times =0;

		}

		@Override
		public boolean hasNext() {
			
			return (current < count);
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}

			times++;
			
			if(this.times == items[current].num) {
				current++;
				times = 0;
				
				return items[current-1].elem;
			}
			
			return items[current].elem;

		}



	}
	////// FIN ITERATOR
	
	
	
    
	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		data=new ElemQueueWithRep[capacityDefault];
		count=0;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl(int capacity) {
		data = new ElemQueueWithRep[capacity];
		count=0;
	}
	
	
			@Override
			public void add(T element, int times) {
				// Implemento excepciones
				if (element==null) {
					throw new NullPointerException();
				}
				if (times<=0) {
					throw new IllegalArgumentException();
				}
				//primero tengo que comprobar que no est� vac�a
				if (!isEmpty()) {
					//he creado el m�todo BuscarIndice en lugar del contains para que adem�s de saber si est�
					//me devuelva la posici�n en la que se encuentra.
					int Encontrado=BuscarIndice(element);
					if (Encontrado==-1)
					{	//no lo ha encontrado, luego es nuevo
						if (count == data.length)
						{	//coinciden count y la longitud del array entonces tengo que expandir capacidad.
							expandCapacity();
						}
						//hay espacio en el array, inserto en el valor count que es el qeu corresponde
						//si count es 3, los tres ocupados son las posiciones 0,1 y 2 luego la posici�n 3 es la 
						//siguiente vac�a.
						data[count] = new ElemQueueWithRep<T>(element,times);
						
						//incrementeo en uno a count.
						count++;
					}
					//si lo contiene deber�a a�adir times veces
					else {
						data[Encontrado].num=data[Encontrado].num+times;
					}
				}else {
					//si est� vac�a es el primero lo a�ado
					data[count] = new ElemQueueWithRep<T>(element,times);
					count++;
					//System.out.println("hola");
					
				}
					
			}

			@Override
			public void add(T element) {
				// implemento excepciones
				if (element==null) {
					throw new NullPointerException();
				}
				//llamo a la otra funci�n add pero pas�ndole 1
				this.add(element, 1);
				
			}

			@Override
			public void remove(T element, int times)  {
				
				if (element == null) {
					throw new NullPointerException("element es null");
				}
				if (times <=0) {
					throw new IllegalArgumentException("n�mero no puede ser negativo");
				}
				if (!contains(element)) {
					throw new NoSuchElementException("el elemento no est� en la lista");
				}
				//necesito saber la posici�n en la que se encuentra.
				int encontrado=-1;
				encontrado=BuscarIndice(element);//me devuelve la posici�n real en el array del elemento encontrado
				
				//if (encontrado !=-1)
				//{
					
					if (data[encontrado].num>times) {
						//si las unidades a restar son menos qeu las almacenadas las resto
						//si son mayores no hago nada.
						data[encontrado].num=data[encontrado].num - times;
					}else {
						throw new IllegalArgumentException("Numero mayor al n�mero almacenado");
					}
				//}
				
			}

			@Override
			public int remove() throws EmptyCollectionException {
				//eliminar el primer elemento del array y devuelve el num que ten�a.
				if (isEmpty())
				{
					throw new EmptyCollectionException("Array Vac�o");
				}
				
				int Almacenaba;
				//valor del num que almacena antes de eliminarlo y que voy a devolver.
				Almacenaba=data[0].num;
				
				//recorro el array porque tengo que ir moviendo todo el array a una posici�n menos
				for (int i=0;i<count-1;i++)
				{
					//guardo en la posici�n i que empieza en 0 la que voy a macharcar el valor de la siguiente
					//y as� sucesivamente hasta llegar al la �ltima posici�n que ser� repetida con la �ltima movida
					//y al salir del bucle la pongo a null y reduzco count en 1
					data[i]=data[i+1];
				}	
				
				data[count-1]=null;
				count--;
				return Almacenaba;	
			}

			@Override
			public void clear() {
				if (!isEmpty()) {
					//lo recorro dando valor null y el count a 0
					for (int i=0; i<count; i++)
					{
						data[i]=null;
						
					}
					count=0;
				}
			}
			

			@Override
			public boolean contains(T element) {
				// implemento excepciones
				if (element==null) {
					throw new NullPointerException();
				}
				//inicializo a -1, no encontrado la vble a devolver.
				int search = -1;
				for (int index=0; index< count && search == -1;index++)
				{
					if (data[index].elem.equals(element)) 
					{
						search = index;
					}
				}
				return (search != -1);
				//return false;
			}
			
			public int BuscarIndice(T element) {
				//TODO
				int search = -1;
				if (isEmpty())
				{
					return search;
				}
				for (int index=0; index< count && search == -1;index++)
				{
					if (data[index].elem.equals(element)) 
					{
						search = index;
					}
				}
				return (search);
				//return false;
			}

			@Override
			public boolean isEmpty() {
				
				return (count==0);
				//return false;
			}

			@Override
			public long size() {
				long acumulado=0;
				if (!isEmpty()) {
					//si no est� vac�a la recorro
					for (int index=0; index< count;index++)
					{
						acumulado=acumulado + data[index].num;
					}
				}
				return acumulado;
			}

			@Override
			public int count(T element) {
				// implemento excepciones
				if (element==null) {
					throw new NullPointerException();
				}
				//devolver la vble num del elemento pasado
				//si existe claro
				int Encontrado=BuscarIndice(element);//de devuelve la posici�n si lo ha encontrado o -1 si no
				
				if (Encontrado!=-1)
				{
					return (data[Encontrado].num);
				}
				
				else {
					return (0);
				}	
		          
			}
			
			private void expandCapacity()
			{
				//ElemQueueWithRep<T>[] nuevo=(ElemQueueWithRep<T>[]) new ElemQueueWithRep[data.length*2];
				
				//creo un nuevo objeto array sum�ndole la capacidad por defecto a la longitud del array actual
				ElemQueueWithRep<T> [] ArrayTemp;
				
				int NuevaCapacidad=count+capacityDefault;
				
				ArrayTemp=new ElemQueueWithRep[NuevaCapacidad];
				
				
				for (int index=0; index <count; index++)
				{
					//cargo el array temporal con los datos del data
					ArrayTemp[index] = data[index];
				}
				//cuando ya lo tengo cargado pero de mayor cantidad se lo asigno al array original
				//el count no var�a porque sigue teniendo los mismos datos.
				data= ArrayTemp;
			}


			@Override
			public Iterator<T> iterator() {
				
				//esto me crea un nuevo objeto iterador que sabe recorrer el array de nodos que estamos creando.
				return new ArrayQueueWithRepIterator(data,count);
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String toString() {
				
				final StringBuffer buffer = new StringBuffer();
				
				buffer.append("(");
					
				//hacerlo con iterador
				Iterator<T> miIterador=iterator();
				
				while (miIterador.hasNext())
				{
					buffer.append(miIterador.next().toString() + ' ');
				}
				
				
				buffer.append(")");
				
				return buffer.toString();
			}
			
//
//
//	

	
}
