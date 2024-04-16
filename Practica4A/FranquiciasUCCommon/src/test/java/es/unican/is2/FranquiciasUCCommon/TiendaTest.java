package es.unican.is2.FranquiciasUCCommon;
import static org.junit.jupiter.api.Assertions.assertEquals; 
import static org.junit.jupiter.api.Assertions.assertNotNull;


import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class TiendaTest {

    private Tienda tienda;
    
    @BeforeEach
    public void setUp() throws NullDireccionIncorrecto, NullNombreIncorrecto {
        this.tienda = new Tienda("Tienda1", "Dirección1");
    }
    
	@Test
	public void testConstructor() {
		//casos validos
	    assertEquals("Tienda1", this.tienda.getNombre());
	    assertEquals("Dirección1", this.tienda.getDireccion());
	    
	   //nombre caso no válido
	   assertThrows(NullNombreIncorrecto.class, () -> new Tienda(null, "Dirección1"));
		   
	   //direccion caso no válido
	   assertThrows(NullDireccionIncorrecto.class, () -> new Tienda("Tienda1", null));

	}
    @Test
    public void testGastoMensualSueldos() throws Exception  {
        // Agregar empleados a la tienda
        Empleado empleado1 = new Empleado("12345678U", "Pepe", Categoria.ENCARGADO, LocalDate.of(2021, 1, 1));
        Empleado empleado2 = new Empleado("87654321X", "Juan", Categoria.AUXILIAR, LocalDate.of(2021, 6, 1));
        tienda.getEmpleados().add(empleado1);
        tienda.getEmpleados().add(empleado2);
        
        // Verificar el gasto mensual de sueldos
        assertEquals(3000.0, tienda.gastoMensualSueldos());
    }
    

    @Test
    public void testGetSetNombre() {
        // Verificar getNombre
        assertEquals("Tienda1", tienda.getNombre());
        
        // Verificar setNombre
        tienda.setNombre("NuevaTienda");
        assertEquals("NuevaTienda", tienda.getNombre());
    }
    
    @Test
    public void testGetSetDireccion() {
        // Verificar getDireccion
        assertEquals("Dirección1", tienda.getDireccion());
        
        // Verificar setDireccion
        tienda.setDireccion("NuevaDirección");
        assertEquals("NuevaDirección", tienda.getDireccion());
    }
   
    
    @Test
    public void testGetEmpleados() {
        // Verificar getEmpleados
        List<Empleado> empleados = tienda.getEmpleados();
        assertNotNull(empleados);
    }
}

