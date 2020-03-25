package ule.edi.queuewithrep;


import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.*;

import ule.edi.exceptions.EmptyCollectionException;

public abstract class AbstractQueueWithRefTests {

	protected abstract <T> QueueWithRep<T> createQueueWithRep();
	

	private QueueWithRep<String> S1;

	private QueueWithRep<String> S2;
	
	
	@Before
	public void setupQueueWithReps() {

		this.S1 = createQueueWithRep(); //lo usaré para los arrays
		
		this.S2 = createQueueWithRep(); //lo usaré para la lista enlazada.
		
		
		
		//S2.add(null, 2);
		//S2.add("prueba",-2);
		S1.add("A");
		S1.add("B", 2);
		S1.add("C", 3);
		
	}
	@Test
	public void testAnadirSiExiste() {
		//con datos qeu ya existen 
		S1.add("C", 10);	
	}
	@Test
	public void testConstructionIsEmpty() {
		//con el before lo he llenado luego no está vacío
		assertFalse(S1.isEmpty());
		S1.clear();//lo vacío
		assertTrue(S1.isEmpty());
	}
	
	@Test (expected=NullPointerException.class) 
	public void testAnadirBasico() {
		//la excepción
		S1.add(null);
	}
	@Test (expected=NullPointerException.class) 
	public void testAnadirCompleto() {
		//la excepción
		S1.add(null,2);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testAnadirDatoIlegal() {
		//con datos qeu ya existen y compruebo las excepciones
		S1.add("A", -1);
	}
	
	
	@Test
	public void testExpandirCapacidad() {
		//añado 7 nodos más y en el octavo debe expandir
		S1.add("D", 4);
		S1.add("E", 5);
		S1.add("F", 6);
		S1.add("G", 7);
		S1.add("H", 8);
		S1.add("I", 9);
		S1.add("J", 10);
		//CON ESTE EXPANDE LA CAPACIDAD
		S1.add("K", 11);
		
	}
	
	@Test (expected=NullPointerException.class) 
	public void testEliminarnulo() {
		//la excepción
		S1.remove(null,2);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarDatoIlegal() {
		//excepcion
		S1.remove("A", -1);
		S1.remove("A",5);
	}
	
	@Test (expected=NoSuchElementException.class)
	public void testEliminarSiNoEsta() {
		//excepcion
		S1.remove("XXX", 2);
	}
	
	@Test
	public void testEliminarYaExistente() {
		//con datos qeu ya existen
		S1.clear();
		S1.add("A",5);
		S1.add("B",10);
		
		S1.remove("B",1);
		
		
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testEliminarDemasiados() throws IllegalArgumentException {
		S1.remove("B",12);
	}
	
	
	
	@Test (expected=EmptyCollectionException.class)
	public void testEliminarSiVacia() throws EmptyCollectionException {
		//excepcion
		S1.clear();
		int tenia;
		tenia=S1.remove();
		assertTrue(S1.isEmpty());
	}
	
	@Test 
	public void testRemoveValid() throws EmptyCollectionException {
		//assertEquals(S1.remove(),2); //si pongo esta línea me pasa por la excepción pero me da error el resto
		S1.add("1",2);
		S1.add("2");
		assertEquals(S1.remove(),1);
		
	}
	
	@Test
	public void testLimpiarArray() {
		//S1.clear();//para qeu pase por si está vacia o si tiene contenido
		//assertTrue(S1.isEmpty());
		//S1.clear();
		//assertTrue(S1.isEmpty());
		
		assertEquals(S1.size(),6);
		assertFalse(S1.isEmpty());
		S1.clear();//para qeu pase por si est? vacia o si tiene contenido
		assertEquals(S1.size(),0);
		assertTrue(S1.isEmpty());
		S1.clear();
		assertEquals(S1.size(),0);
		assertTrue(S1.isEmpty());

	}
	
	
	
	@Test
	public void testSiContieneElemento() throws NullPointerException {
		S1.clear();
		S1.add("a",2);
		S1.add("b");
		assertTrue(S1.contains("a"));	
	}
	
	//si contiene cuando el elemento es nulo
	@Test (expected=NullPointerException.class) 
	public void testContieneNull() {
		//boolean locontiene;
		//locontiene=S1.contains(null);
		assertFalse(S1.contains(null));
		//int cuantos;
		//cuantos=S1.count(null);
		assertEquals(S1.count(null), 3);
	}
	
	@Test (expected=NullPointerException.class) 
	public void testCuantosDeNull() {
		int cuantos;
		cuantos=S1.count(null);
	}
	
	@Test
	public void testCuantosDeX() {
		//assertEquals(S1.count("a"), 2);
		S1.clear();
		S1.add("a",2);
		S1.add("b");
		
		assertEquals(S1.count("b"), 1);
		//System.out.println(cuantos);
		//cuantos=S1.count("z");
		assertEquals(S1.count("z"), 0);
		//System.out.println(cuantos);
		S1.clear();
		assertEquals(S1.count("a"), 0);
	}
	
	@Test
	public void testSize() {
		
		
		assertEquals(S1.count("A"), 1);
		S1.clear();
		assertTrue(S1.isEmpty());
		assertEquals(S1.size(), 0);
		S1.add("a",2);
		S1.add("b");
		assertEquals(S1.size(), 3);


	}
	
	@Test
	public void testIteratorWithDuplicates2() {
			S1.clear();
			S1.add("A", 2);
			S1.add("B", 1);
				
			Iterator<String> Si = S1.iterator();
			//Tu código no lo pasa, porque no están bien los iteradores
			Assert.assertTrue(Si.hasNext());
			Assert.assertEquals(Si.next().toString(), "A");
			Assert.assertTrue(Si.hasNext());
			Assert.assertEquals(Si.next().toString(), "A");
			Assert.assertTrue(Si.hasNext());
			Assert.assertEquals(Si.next().toString(), "B");
			
		}
	@Test
	//Iterador sobre una cola CON duplicados: ")
	public void testSalidaDatos() {
			S1.clear();
			S1.add("A", 2);
			S1.add("B", 1);
				
			//Tu código no lo pasa, porque no están bien los iteradores
			
			Assert.assertEquals(S1.toString(), "(A A B )");
			
		}
	
}
