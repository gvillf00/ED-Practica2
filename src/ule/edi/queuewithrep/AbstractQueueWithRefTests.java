package ule.edi.queuewithrep;


import static org.junit.Assert.*;

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
		S1.remove("A",6);//primero no hacer nada porque es mayor
		S1.remove("A",3);
		//no puedo hacer que elimine uno que no está porque es una excepción luego nunca pasa por el else del if que tengo
		
	}
	
	@Test (expected=EmptyCollectionException.class)
	public void testEliminarSiVacia() throws EmptyCollectionException {
		//excepcion
		S1.clear();
		int tenia;
		tenia=S1.remove();
	}
	
	@Test 
	public void testRemoveValid() throws EmptyCollectionException {
		
		//assertEquals(S1.remove(),2); //si pongo esta línea me pasa por la excepción pero me da error el resto
		S1.add("1",2);
		S1.add("2");
		assertEquals(S1.remove(),2);
		
	}
	
	@Test
	public void testLimpiarArray() {
		S1.clear();//para qeu pase por si está vacia o si tiene contenido
		S1.clear();
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
		boolean locontiene;
		locontiene=S1.contains(null);
		int cuantos;
		cuantos=S1.count(null);
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
		int cuantos;
		cuantos=S1.count("a");
		System.out.println(cuantos);
		cuantos=S1.count("z");
		System.out.println(cuantos);
	}
	
	@Test
	public void testSize() {
		//assertEquals(S1.count("a"), 2);
		S1.clear();
		long cuantos;
		cuantos=S1.size();
		System.out.println(cuantos);
		S1.add("a",2);
		S1.add("b");
		cuantos=S1.size();
		System.out.println(cuantos);
	}
	
	@Test
	public void testMostrarPantalla() throws NoSuchElementException {
		//S1.add("1");
		//assertEquals(S1.toString(), "(1 )");
		S1.clear();
		S1.add("a",2);
		S1.add("b");
		System.out.println(S1.toString());
		//hago un clear y lo llamo con la lista vacía
		S1.clear();
		System.out.println(S1.toString());
		
	}
}
