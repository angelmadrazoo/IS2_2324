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

    @Override
    public Empleado nuevoEmpleado(Empleado e, String nombreTienda)
            throws OperacionNoValidaException, DataAccessException {

        Tienda tienda = tiendasDAO.tiendaPorNombre(nombreTienda);
        if (tienda == null) {
            return null;
        }

        if (empleadosDAO.empleado(e.getDNI()) != null) {
            throw new OperacionNoValidaException("El empleado ya existe.");
        }

        tienda.getEmpleados().add(e);
        
        tiendasDAO.modificarTienda(tienda);

        return empleadosDAO.crearEmpleado(e);
    }

    @Override
    public Empleado eliminarEmpleado(String dni, String nombreTienda)
            throws OperacionNoValidaException, DataAccessException {

        Tienda tienda = tiendasDAO.tiendaPorNombre(nombreTienda);
        if (tienda == null) {
            return null;
        }
        
        Empleado empleado = empleadosDAO.empleado(dni);
        if (empleado == null) {
            return null;
        }

        tienda.getEmpleados().remove(empleado);
        
        tiendasDAO.modificarTienda(tienda);
        
        return empleadosDAO.eliminarEmpleado(dni);
    }

    @Override
    public boolean trasladarEmpleado(String dni, String tiendaActual, String tiendaDestino)
            throws OperacionNoValidaException, DataAccessException {

        Tienda tiendaActualObj = tiendasDAO.tiendaPorNombre(tiendaActual);
        Tienda tiendaDestinoObj = tiendasDAO.tiendaPorNombre(tiendaDestino);

        if (tiendaActualObj == null || tiendaDestinoObj == null) {
            return false;
        }

        Empleado empleado = empleadosDAO.empleado(dni);
        if (empleado == null) { // ?
        	return false;
        }

        tiendaDestinoObj.getEmpleados().add(empleado);
        tiendaActualObj.getEmpleados().remove(empleado);

        tiendasDAO.modificarTienda(tiendaActualObj);
        tiendasDAO.modificarTienda(tiendaDestinoObj);
        
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
