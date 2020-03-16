package ule.edi.queuewithrep;

import java.util.Iterator;
import java.util.NoSuchElementException;

import ule.edi.exceptions.EmptyCollectionException;

public class ArrayQueueWithRepImpl<T> implements QueueWithRep<T> {
	
	// atributos
	
    private final int capacityDefault = 10;
	
	ElemQueueWithRep<T>[] data; //mi array
    private int count; //contador para recorrerlo
    
	
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
		int actual; //indice del array en el que leo
		int contador; //posiciones del array ocupadas.
		int nuevocaracter; //
		ElemQueueWithRep<T> [] MiArray;
		
		public ArrayQueueWithRepIterator(ElemQueueWithRep<T>[] ArrayDeNodos, int count){
			//TODO
			contador=count;
			actual=0;
			nuevocaracter=0;
			MiArray=(ElemQueueWithRep<T>[]) ArrayDeNodos;
		}

		@Override
		public boolean hasNext() {
			//TODO
			return (actual < contador);
			//return false;
		
		}

		@Override
		public T next() {
			//TODO
			//ME VA A DEVOLVER ELEM SOLO
			//LLAMO A ESTE MÉTODO CUANDO QUIERO RECORRER DE MANERA SECUENCIAL EL ARRAY. COMO ES EL STRING
			if (! hasNext())
			{
				throw new NoSuchElementException();
			}
	
			T Elemento=MiArray[actual].elem;
			nuevocaracter++;
			if (nuevocaracter==MiArray[actual].num)
			{
				nuevocaracter=0;
				actual++;
			}
				
			return Elemento;
			//return null;
		}
		
		//hay que implementar el remove del iterador.
		//@Override
		//public T remove() {
			
		//}

	}
	////// FIN ITERATOR
	
	
    
	@SuppressWarnings("unchecked")
	public ArrayQueueWithRepImpl() {
		//TODO
		data=new ElemQueueWithRep[capacityDefault];
		count=0;
	}
	
	
			@Override
			public void add(T element, int times) {
				// TODO Auto-generated method stub
				int Encontrado=BuscarIndice(element);
				if (Encontrado==-1)
				{
					if (size() == data.length)
					{
						expandCapacity();
					}
					data[count].elem = element;
					data[count].num=times;
				}
				//si lo contiene debería añadir una unidad
				else {
					data[Encontrado].num=data[Encontrado].num+times;
				}
				
				
			}
			


			@Override
			public void add(T element) {
				this.add(element, 1);
				
			}

			@Override
			public void remove(T element, int times)  {
				
				//busco en el array si está el elemento
				if (contains(element))
				{
					if (data[count].num>=times) {
						//si las veces que me pasa son igual o mayor de las que tiene almacenadas lo elimino entero
						//NO SE LO QUE ESTOY HACIENDO AQUÍ.
						T result;
						result= data[count].elem;
						data[search] = data[count-1];
						data[count-1]=null;
						count--;
						//return result;
					}
					else
					{
						//si son menores las resto
						data[count].num=data[count].num - times;
					}
				}
				
			}

			@Override
			public int remove() throws EmptyCollectionException {
				//eliminar el primer elemento del array y devuelve el num que tenía.
				
				if (isEmpty())
				{
					throw new EmptyCollectionException("Array Vacío");
				}
				
				int Almacenaba;
				
				Almacenaba=data[0].num;
				
				//recorrer con bucle e ir pasando de uno en uno a la posición anterior.
				
				//primero pongo el array en la posición count a null y decremento el count
					
				
				//TODO		
			}

			@Override
			public void clear() {
				//TODO
				//lo recorro dando valor null y el count a 0
				//LA VOY RECORRIENDO Y LLAMANDO AL METODO REMOVE.
				for (int i=0; i<count; i++)
				{
					
					
				}
			}
			

			@Override
			public boolean contains(T element) {
				//TODO
				int search = -1;
				for (int index=0; index< count && search == -1;index++)
				{
					if (data[index].equals(element)) 
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
				for (int index=0; index< count && search == -1;index++)
				{
					if (data[index].equals(element)) 
					{
						search = index;
					}
				}
				return (search);
				//return false;
			}

			@Override
			public boolean isEmpty() {
				// TODO Auto-generated method stub
				return (count==0);
				//return false;
			}

			@Override
			public long size() {
				// TODO Auto-generated method stub
				return count;
			 
			}

			@Override
			public int count(T element) {
				// TODO Auto-generated method stub
				//devolver la vble num del elemento pasado
				//si existe claro
				if (contains(element))
				{
					return data[count].num;
				}
				else 
				{
					return 0;
				}
		          
			}
			
			private void expandCapacity()
			{
				//creo un nuevo objeto array con el doble de capacidad por defecto
				ElemQueueWithRep<T> [] ArrayTemp;
				ArrayTemp=new ElemQueueWithRep[data.length*2];
				
				for (int index=0; index <data.length; index++)
				{
					//ArrayTemp[index].elem =data[index].elem;
					//ArrayTemp[index].num =data[index].num;
					//esto no estoy segura de tener que ponerlo, o mejor solo con esto vale.
					ArrayTemp[index] = data[index];
				}
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

				// TODO Ir aÃ±adiendo en buffer las cadenas para la representaciÃ³n de esta cola
				for (int index=0; index < count; index++)
				{
					buffer.append(data[index].toString() + '\n');
				}
				
				buffer.append(")");
				
				return buffer.toString();
			}
			
//
//
//	

	
}
