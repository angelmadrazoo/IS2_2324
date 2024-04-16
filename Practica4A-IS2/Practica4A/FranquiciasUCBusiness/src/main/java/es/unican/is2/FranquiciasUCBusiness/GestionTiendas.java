package es.unican.is2.FranquiciasUCBusiness;
import java.util.List;

import es.unican.is2.FranquiciasUCCommon.*;

public class GestionTiendas implements IGestionTiendas {

	   private ITiendasDAO tiendasDAO;

	    
	    public GestionTiendas(ITiendasDAO tiendasDAO) {
	        this.tiendasDAO = tiendasDAO;
	    }

	    @Override
	    public Tienda nuevaTienda(Tienda t) throws DataAccessException {

	        if (tiendasDAO.tiendaPorNombre(t.getNombre()) != null) {
	            return null;
	        }


	        return tiendasDAO.crearTienda(t);
	    }

	    @Override
	    public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {

	        Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);
	        if (tienda == null) {
	            return null; 
	        }


	        if (!tienda.getEmpleados().isEmpty()) {
	            throw new OperacionNoValidaException("La tienda tiene empleados y no puede ser eliminada.");
	        }


	        return tiendasDAO.eliminarTienda(tienda.getId());
	    }

	    public Tienda tienda(String nombre) throws DataAccessException {
	        return tiendasDAO.tiendaPorNombre(nombre);
	    }

	    public List<Tienda> tiendas() throws DataAccessException {
	        return tiendasDAO.tiendas();
	    }
	}