package es.unican.is2.FranquiciasUCMain;

import org.fest.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import es.unican.is2.FranquiciasUCBusiness.*; 
import es.unican.is2.FranquiciasUCDAO.*;
import es.unican.is2.FranquiciasUCGUI.VistaGerente;


public class VistaGerenteTest {
    private FrameFixture demo;

    @Before
    public void setUp() {
        
    	 EmpleadosDAO empleadosDAO = new EmpleadosDAO();
         TiendasDAO tiendasDAO = new TiendasDAO();

         
         GestionTiendas tiendas = new GestionTiendas(tiendasDAO);
         GestionEmpleados empleados = new GestionEmpleados(empleadosDAO, tiendasDAO);
        
        
        VistaGerente gui = new VistaGerente(tiendas, empleados);
        demo = new FrameFixture(gui);
        
        
        gui.setVisible(true);
        
        
    }

    @After
    public void tearDown() {
        demo.cleanUp();
    }

    @Test
    public void testBuscarTiendaExistente() {
        demo.button("btnBuscar").requireText("Buscar");

        demo.textBox("txtNombreTienda").enterText("Tienda A");
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Direccion A");
        
     // Sleep para visualizar como se realiza el test
     		try {
     			Thread.sleep(2000);
     		} catch (InterruptedException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        
    }
//
    @Test
    public void testBuscarTiendaNoExistente() {
        demo.button("btnBuscar").requireText("Buscar");

        demo.textBox("txtNombreTienda").enterText("Tienda");
        demo.button("btnBuscar").click();
        demo.textBox("txtDireccionTienda").requireText("Tienda no existe");
        
     // Sleep para visualizar como se realiza el test
     		try {
     			Thread.sleep(2000);
     		} catch (InterruptedException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
        
    }
}

