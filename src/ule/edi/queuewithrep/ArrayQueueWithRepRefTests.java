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
	@Test
	public void expandCapacityTest() {
		ArrayQueueWithRepImpl<Integer> aq = new ArrayQueueWithRepImpl<Integer>(2);
		aq.add(1);
		aq.add(2);
		aq.add(3);
		aq.add(4);
		aq.add(5);
		aq.add(6);
		aq.add(7);
		aq.add(8);
		aq.add(15);
		aq.add(16);
		aq.add(17);
		aq.add(18);
		
	}

}