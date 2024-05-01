
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class VendedorEnPracticasTest {
	
	private static vendedorEnPracticas sut;

	@BeforeEach
	public void setUp(){
		sut = new vendedorEnPracticas("Ana", "1", "11111111A");
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sut.getId(), "1");
		assertEquals(sut.getNombre(), "Ana");
		assertEquals(sut.getDni(), "11111111A");
		assertEquals(0.0, sut.getTotalVentas());
		assertEquals(0.0, sut.getC());
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
		sut.setC(100);
		assertTrue(sut.getC()==100.0);
		
		sut.setC(230);
		assertTrue(sut.getC()==230.0);
		
		sut.setC(0);
		assertTrue(sut.getC()==0.0);
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
		vendedorEnPracticas igual = new vendedorEnPracticas("Ana", "1", "11111111A");
		vendedorEnPracticas distintoId = new vendedorEnPracticas("Ana", "2", "11111111A");
		vendedorEnPracticas distintoNombre = new vendedorEnPracticas("Pepe", "1", "222222222A");
		
		assertTrue(sut.equals(igual));
		assertFalse(sut.equals(distintoId));
		assertFalse(sut.equals(distintoNombre));
		
		assertFalse(sut.equals(new Object()));
	}
	
	
	
}
