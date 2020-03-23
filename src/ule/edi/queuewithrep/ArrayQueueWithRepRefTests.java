package ule.edi.queuewithrep;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ArrayQueueWithRepRefTests extends AbstractQueueWithRefTests {

	@Override
	protected <T> QueueWithRep<T> createQueueWithRep() {
		
		return new ArrayQueueWithRepImpl<T>();
	}
	
	@Test
	public void TestBuscarIndice() {
		ArrayQueueWithRepImpl<String> cola= new ArrayQueueWithRepImpl();
		int indice=-1;
		assertTrue(cola.BuscarIndice("A")==indice);
		
	}
	@Test
	public void TestColaConCapacidad() {
		 
		ArrayQueueWithRepImpl<String> S3=new ArrayQueueWithRepImpl(50);
		
	}

}