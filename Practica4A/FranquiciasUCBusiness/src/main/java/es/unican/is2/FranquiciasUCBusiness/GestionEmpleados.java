package es.unican.is2.FranquiciasUCBusiness;

import java.util.List;

import es.unican.is2.FranquiciasUCCommon.*;

public class GestionEmpleados implements IGestionEmpleados, IGestionAltasBajas {

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
        
        if (!tienda.getEmpleados().contains(empleado)) {
            throw new OperacionNoValidaException("El empleado no pertenece a la tienda especificada.");
        }

        tienda.getEmpleados().remove(empleado);
        
        tiendasDAO.modificarTienda(tienda);
        
        return empleadosDAO.eliminarEmpleado(dni);
    }

    @Override
    public boolean trasladarEmpleado(String dni, String tiendaActual, String tiendaDestino)
            throws OperacionNoValidaException, DataAccessException {

    	Tienda tiendaActualObj = tiendasDAO.tiendaPorNombre(tiendaActual);
        if (tiendaActualObj == null) {
            throw new OperacionNoValidaException("La tienda de origen no existe.");
        }
        
        Tienda tiendaDestinoObj = tiendasDAO.tiendaPorNombre(tiendaDestino);
        if (tiendaDestinoObj == null) {
            throw new OperacionNoValidaException("La tienda de destino no existe.");
        }

        Empleado empleado = empleadosDAO.empleado(dni);
        if (empleado == null) {
            throw new OperacionNoValidaException("El empleado no existe.");
        }

        if (!tiendaActualObj.getEmpleados().contains(empleado)) {
            throw new OperacionNoValidaException("El empleado no está en la tienda de origen.");
        }

        tiendaDestinoObj.getEmpleados().add(empleado);
        tiendaActualObj.getEmpleados().remove(empleado);

        tiendasDAO.modificarTienda(tiendaActualObj);
        tiendasDAO.modificarTienda(tiendaDestinoObj);
        
        empleadosDAO.modificarEmpleado(empleado);

        return true;
    }
    
    @Override
    public boolean altaMedica(String dni) throws DataAccessException {
        Empleado empleado = empleadosDAO.empleado(dni);
        if (empleado == null) {
            return false;
        }
        empleado.darDeAlta();
        empleadosDAO.modificarEmpleado(empleado);
        return true;
    }
    
    @Override
    public boolean bajaMedica(String dni) throws DataAccessException {
        Empleado empleado = empleadosDAO.empleado(dni);
        if (empleado == null) {
            return false;
        }
        empleado.darDeBaja();
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
