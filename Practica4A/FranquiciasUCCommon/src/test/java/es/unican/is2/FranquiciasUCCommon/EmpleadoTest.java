package es.unican.is2.FranquiciasUCCommon;

import static org.junit.jupiter.api.Assertions.assertEquals;  
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmpleadoTest {
	private Empleado empleado;
  
	@BeforeEach
	public void setUp() throws Exception {
		this.empleado = new Empleado("12345678U", "Pepe", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1));
	}
  
	@Test
	public void testConstructor() {
		//casos validos
	    assertEquals("12345678U", this.empleado.getDNI());
	    assertEquals("Pepe", this.empleado.getNombre());
	    assertEquals(Categoria.ENCARGADO, this.empleado.getCategoria());
	    assertEquals(LocalDate.of(2020, 1, 1), this.empleado.getFechaContratacion());
	    
	   //DNI casos no válidos
	    assertThrows(NullDniIncorrecto.class, () -> new Empleado(null, "Pepe", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));
		assertThrows(DniVacio.class, () -> new Empleado("", "Pepe", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));
		assertThrows(DniFormatoIncorrecto.class, () -> new Empleado("678U", "Pepe", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));
	
	    
	   //nombre casos no válidos
	   assertThrows(NullNombreIncorrecto.class, () -> new Empleado("12345678U", null, Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));
	   assertThrows(NombreVacio.class, () -> new Empleado("12345678U", "", Categoria.ENCARGADO, LocalDate.of(2020, 1, 1)));
		   
	   //nombre casos no válidos
	   assertThrows(NullCategoriaIncorrecto.class, () -> new Empleado("12345678U", "Pepe", null, LocalDate.of(2020, 1, 1)));
	   
	   //nombre casos no válidos
	   assertThrows(NullFechaIncorrecta.class, () -> new Empleado("12345678U", "Pepe", Categoria.ENCARGADO, null));
	
	}
	@Test
	public void testSueldoBruto() {
        // Test para la categoría ENCARGADO y sin antigüedad
        assertEquals(2000.0, this.empleado.sueldoBruto()); 
        // Test para la categoría AUXILIAR y sin antigüedad
        this.empleado.setCategoria(Categoria.AUXILIAR);
        assertEquals(1000.0, this.empleado.sueldoBruto());
        // Test para la categoría VENDEDOR y sin antigüedad
        this.empleado.setCategoria(Categoria.VENDEDOR);
        assertEquals(1500.0, this.empleado.sueldoBruto());
        
        
        
        // Test para la categoría ENCARGADO con antigüedad de mas de 20 años
        this.empleado.setCategoria(Categoria.ENCARGADO);
        this.empleado.setFechaContratacion(LocalDate.of(1990, 1, 1));
        assertEquals(2200.0, this.empleado.sueldoBruto());      
        // Test para la categoría ENCARGADO con antigüedad de 10 años
        this.empleado.setCategoria(Categoria.ENCARGADO);
        this.empleado.setFechaContratacion(LocalDate.of(2013, 1, 1));
        assertEquals(2100.0, this.empleado.sueldoBruto());        
        // Test para la categoría ENCARGADO con antigüedad de 5 años
        this.empleado.setCategoria(Categoria.ENCARGADO);
        this.empleado.setFechaContratacion(LocalDate.of(2017, 1, 1));
        assertEquals(2050.0, this.empleado.sueldoBruto());
        
        
        
        // Test para la categoría AUXILIAR con antigüedad de mas de 20 años
        this.empleado.setCategoria(Categoria.AUXILIAR);
        this.empleado.setFechaContratacion(LocalDate.of(1990, 1, 1));
        assertEquals(1200.0, this.empleado.sueldoBruto());       
        // Test para la categoría AUXILIAR con antigüedad de 10 años
        this.empleado.setCategoria(Categoria.AUXILIAR);
        this.empleado.setFechaContratacion(LocalDate.of(2013, 1, 1));
        assertEquals(1100.0, this.empleado.sueldoBruto());       
        // Test para la categoría AUXILIAR con antigüedad de 5 años
        this.empleado.setCategoria(Categoria.AUXILIAR);
        this.empleado.setFechaContratacion(LocalDate.of(2017, 1, 1));
        assertEquals(1050.0, this.empleado.sueldoBruto());
        
        
        
        // Test para la categoría VENDEDOR con antigüedad de mas de 20 años
        this.empleado.setCategoria(Categoria.VENDEDOR);
        this.empleado.setFechaContratacion(LocalDate.of(1990, 1, 1));
        assertEquals(1700.0, this.empleado.sueldoBruto());        
        // Test para la categoría VENDEDOR con antigüedad de 10 años
        this.empleado.setCategoria(Categoria.VENDEDOR);
        this.empleado.setFechaContratacion(LocalDate.of(2013, 1, 1));
        assertEquals(1600.0, this.empleado.sueldoBruto());      
        // Test para la categoría VENDEDOR con antigüedad de 5 años
        this.empleado.setCategoria(Categoria.VENDEDOR);
        this.empleado.setFechaContratacion(LocalDate.of(2017, 1, 1));
        assertEquals(1550.0, this.empleado.sueldoBruto());
        

        this.empleado.setFechaContratacion(LocalDate.of(2023, 1, 1));
        this.empleado.darDeBaja();
        // Test para empleado de baja (ENCARGADO)
        this.empleado.setCategoria(Categoria.ENCARGADO);
        assertEquals(1500.0, this.empleado.sueldoBruto());
        // Test para empleado de baja (AUXILIAR)
        this.empleado.setCategoria(Categoria.AUXILIAR);
        assertEquals(750.0, this.empleado.sueldoBruto());
        // Test para empleado de baja (VENDEDOR)
        this.empleado.setCategoria(Categoria.VENDEDOR);
        assertEquals(1125.0, this.empleado.sueldoBruto());
        
        
        
        //Fecha de contratacion del empleado es nula
 	   	assertThrows(NullFechaIncorrecta.class, () -> new Empleado("12345678U", "Pepe", Categoria.ENCARGADO, null));

    }
    
    @Test
    public void testDarDeBaja() {
        // Verificar que el empleado esté inicialmente de alta
        empleado.darDeAlta();

        // Dar de baja al empleado y verificar
        empleado.darDeBaja();
        assertTrue(empleado.getBaja());
    }

    @Test
    public void testDarDeAlta() {
        // Dar de baja al empleado primero
        empleado.darDeBaja();

        // Dar de alta al empleado y verificar
        empleado.darDeAlta();
        assertFalse(empleado.getBaja());
    }

    @Test
    public void testGetters() {
        assertEquals("12345678U", empleado.getDNI());
        assertEquals("Pepe", empleado.getNombre());
        assertEquals(Categoria.ENCARGADO, empleado.getCategoria());
        assertEquals(LocalDate.of(2020, 1, 1), empleado.getFechaContratacion());
        assertFalse(empleado.getBaja());
    }

    @Test
    public void testSetters() {
        // Cambiar valores con setters
        empleado.setDNI("87654321X");
        empleado.setNombre("Juan");
        empleado.setCategoria(Categoria.AUXILIAR);
        empleado.setFechaContratacion(LocalDate.of(2019, 1, 1));
        empleado.setBaja(true);

        // Verificar que los getters retornan los nuevos valores
        assertEquals("87654321X", empleado.getDNI());
        assertEquals("Juan", empleado.getNombre());
        assertEquals(Categoria.AUXILIAR, empleado.getCategoria());
        assertEquals(LocalDate.of(2019, 1, 1), empleado.getFechaContratacion());
        assertTrue(empleado.getBaja());
    }
}

