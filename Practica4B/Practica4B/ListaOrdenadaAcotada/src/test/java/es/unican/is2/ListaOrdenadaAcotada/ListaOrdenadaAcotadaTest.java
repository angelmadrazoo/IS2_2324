package es.unican.is2.ListaOrdenadaAcotada;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class ListaOrdenadaAcotadaTest {
    
    private IListaOrdenadaAcotada<Integer> lista;
    
    @Before
    public void setUp() {
        lista = new ListaOrdenadaAcotada<>(3);
    }
    
    @Test
    public void testAdd() {
    	//validos
        lista.add(10);
        lista.add(20);
        lista.add(5);
        assertEquals(3, lista.size());
        assertEquals(Integer.valueOf(5), lista.get(0));
        assertEquals(Integer.valueOf(10), lista.get(1));
        assertEquals(Integer.valueOf(20), lista.get(2));
        
        //no valido
        assertThrows(IllegalStateException.class, () -> lista.add(23));
		assertThrows(NullPointerException.class, () -> lista.add(null));
    }
    
    @Test
    public void testGet() {
		//validos
        lista.add(20);
        lista.add(10);
        lista.add(30);
        assertEquals(Integer.valueOf(10), lista.get(0));
        assertEquals(Integer.valueOf(20), lista.get(1));
        assertEquals(Integer.valueOf(30), lista.get(2));
        
    	//No validos
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(3));
    }
    
    @Test
    public void testRemove() {
    	//validos
        lista.add(20);
        lista.add(10);
        lista.add(40);
        assertEquals(Integer.valueOf(10), lista.remove(0));
        assertEquals(2, lista.size());
        assertEquals(Integer.valueOf(20), lista.get(0));
        assertEquals(Integer.valueOf(40), lista.get(1));
        
        //no validos
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(20));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(2));

    }
    
    @Test
    public void testSize() {
        assertEquals(0, lista.size());
        lista.add(10);
        assertEquals(1, lista.size());
        lista.add(20);
        assertEquals(2, lista.size());
        lista.remove(0);
        assertEquals(1, lista.size());
    }
    
    @Test
    public void testClear() {
        lista.add(10);
        lista.add(20);
        lista.add(30);
        lista.clear();
        assertEquals(0, lista.size());
    }
}
