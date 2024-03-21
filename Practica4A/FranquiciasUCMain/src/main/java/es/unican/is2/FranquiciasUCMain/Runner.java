package es.unican.is2.FranquiciasUCMain;
import es.unican.is2.FranquiciasUCBusiness.*; 
import es.unican.is2.FranquiciasUCDAO.*;
import es.unican.is2.FranquiciasUCGUI.*;



/**
 * Clase principal que construye la aplicacion de tres capas y lanza su ejecucion
 */
public class Runner {

	public static void main(String[] args) {
		// Crear componentes capa DAO
		TiendasDAO tiendasDAO = new TiendasDAO();
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		
		// Crear componentes capa negocio
		GestionTiendas gTiendas = new GestionTiendas(tiendasDAO);
		GestionEmpleados gEmpleados = new GestionEmpleados(empleadosDAO, tiendasDAO);
		
		// Crear componentes capa presentacion
		VistaGerente vista = new VistaGerente(gTiendas, gEmpleados);
		
		// Lanzar ejecucion (hacer visible la interfaz)
		vista.setVisible(true);
		
	
		
	}

}
