package es.unican.is2.FranquiciasUCCommon;





import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class EmpleadoTest {

	private Empleado empleado;
	
	@BeforeEach
	public void setUp() throws Exception {
		empleado = new Empleado("12345678U", "Pepe", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1));
	}
	
	
	
	
	@Test
	public void testConstructor() {
		// Casos validos
		assertEquals("12345678U", empleado.getDNI());
		assertEquals("Pepe", empleado.getNombre());
		assertEquals(Categoria.ENCARGADO, empleado.getCategoria());
		assertEquals(Categoria.AUXILIAR, empleado.getCategoria());
		assertEquals(Categoria.VENDEDOR, empleado.getCategoria());
		assertEquals(LocalDate.of(2020, 1, 1), empleado.getFechaContratacion());
		
		// Casos DNI Incorrecto
		assertThrows(NullDniIncorrecto.class, () -> new Empleado (null, "Pepe", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));
		assertThrows(DniVacio.class, () -> new Empleado ("", "Pepe", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));
		assertThrows(DniFormatoIncorrecto.class, () -> new Empleado ("1234A", "Pepe", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));
		
		// Casos Nombre Incorrecto
		assertThrows(NullNombreIncorrecto.class, () -> new Empleado ("12345678U", null, Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));
		assertThrows(NombreVacio.class, () -> new Empleado ("12345678U", "", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));		
		
		// Casos Categoria Incorrecta
		assertThrows(NullCategoriaIncorrecto.class, () -> new Empleado ("12345678U", "Pepe", null, LocalDate.of(2020, 1, 1)));

		// Casos FechaContratacion Incorrecta
		assertThrows(NullFechaIncorrecta.class, () -> new Empleado ("12345678U", "Pepe", Categoria.AUXILIAR, null));

	} 
	
	@Test
	public void testSueldoBruto() {
		//Casos validos
		assertEquals(1000, empleado.sueldoBruto());
		assertEquals(1500, empleado.sueldoBruto());
	}
	
	
	
	
	
}

