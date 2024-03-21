package es.unican.is2.FranquiciasUCBusiness;

import java.util.List;

import es.unican.is2.FranquiciasUCCommon.*;

public class GestionEmpleados implements IGestionEmpleados {

    private IEmpleadosDAO empleadosDAO;
    private ITiendasDAO tiendasDAO;

 
    public GestionEmpleados(IEmpleadosDAO empleadosDAO, ITiendasDAO tiendasDAO) {
        this.empleadosDAO = empleadosDAO;
        this.tiendasDAO = tiendasDAO;
    }

    
    public Empleado nuevoEmpleado(Empleado e, String nombreTienda)
            throws OperacionNoValidaException, DataAccessException {

        Tienda tienda = tiendasDAO.tiendaPorNombre(nombreTienda);
        if (tienda == null) {
            throw new OperacionNoValidaException("La tienda no existe.");
        }


        if (empleadosDAO.empleado(e.getDNI()) != null) {
            throw new OperacionNoValidaException("El empleado ya existe.");
        }


        tienda.getEmpleados().add(e);

        return empleadosDAO.crearEmpleado(e);
    }

    
    public Empleado eliminarEmpleado(String dni, String nombreTienda)
            throws OperacionNoValidaException, DataAccessException {

        Tienda tienda = tiendasDAO.tiendaPorNombre(nombreTienda);
        if (tienda == null) {
            throw new OperacionNoValidaException("La tienda no existe.");
        }


        return empleadosDAO.eliminarEmpleado(dni);
    }

    
    public boolean trasladarEmpleado(String dni, String tiendaActual, String tiendaDestino)
            throws OperacionNoValidaException, DataAccessException {

    	
    	
        Tienda tiendaActualObj = tiendasDAO.tiendaPorNombre(tiendaActual);
        Tienda tiendaDestinoObj = tiendasDAO.tiendaPorNombre(tiendaDestino);

        if (tiendaActualObj == null || tiendaDestinoObj == null) {
            throw new OperacionNoValidaException("La tienda actual o la tienda destino no existe.");
        }


        Empleado empleado = empleadosDAO.empleado(dni);
        if (empleado == null) { // ?
            throw new OperacionNoValidaException("El empleado no pertenece a la tienda actual.");
        }


       
        tiendaDestinoObj.getEmpleados().add(empleado);
        tiendaActualObj.getEmpleados().remove(empleado);


        empleadosDAO.modificarEmpleado(empleado);

        return true;
    }


    
    public Empleado empleado(String dni) throws DataAccessException {
        return empleadosDAO.empleado(dni);
    }

    
    public List<Empleado> empleados() throws DataAccessException {
        return empleadosDAO.empleados();
    }
}
