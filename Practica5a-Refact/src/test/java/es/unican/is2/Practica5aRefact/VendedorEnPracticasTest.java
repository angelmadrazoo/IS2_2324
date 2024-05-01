package es.unican.is2.Practica5aRefact;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class VendedorEnPracticasTest {
	
	private static VendedorEnPracticas sut;

	@BeforeEach
	public void setUp(){
		sut = new VendedorEnPracticas("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sut.getId(), "1");
		assertEquals(sut.getNombre(), "Ana");
		assertEquals(sut.getDni(), "11111111A");
		assertEquals(0.0, sut.getTotalVentas());
		assertEquals(0.0, sut.getComision());
	}
	
	@Test
	public void testSetT() {
		sut.setTotalVentas(100);
		assertTrue(sut.getTotalVentas()==100.0);
		
		sut.setTotalVentas(230);
		assertTrue(sut.getTotalVentas()==230.0);
		
		sut.setTotalVentas(0);
		assertTrue(sut.getTotalVentas()==0.0);
	}
	
	@Test
	public void testSetC() {
		sut.setComision(100);
		assertTrue(sut.getComision()==100.0);
		
		sut.setComision(230);
		assertTrue(sut.getComision()==230.0);
		
		sut.setComision(0);
		assertTrue(sut.getComision()==0.0);
	}

	@Test
	public void testAnhadeVenta() {
		sut.anhade(200);
		assertTrue(sut.getTotalVentas() == 200.0);
		
		sut.anhade(300);
		assertTrue(sut.getTotalVentas() == 500.0);	
		
		sut.anhade(0);
		assertTrue(sut.getTotalVentas() == 500.0);
		
	}
	
	@Test
	public void testEquals() {
		VendedorEnPracticas igual = new VendedorEnPracticas("Ana", "1", "11111111A");
		VendedorEnPracticas distintoId = new VendedorEnPracticas("Ana", "2", "11111111A");
		VendedorEnPracticas distintoNombre = new VendedorEnPracticas("Pepe", "1", "222222222A");
		
		assertTrue(sut.equals(igual));
		assertFalse(sut.equals(distintoId));
		assertFalse(sut.equals(distintoNombre));
		
		assertFalse(sut.equals(new Object()));
	}
	
	
	
}
