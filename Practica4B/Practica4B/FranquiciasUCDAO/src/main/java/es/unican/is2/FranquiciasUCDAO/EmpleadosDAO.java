package es.unican.is2.FranquiciasUCDAO;

import es.unican.is2.FranquiciasUCCommon.*;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la capa DAO de acceso a Empleados. 
 * Utiliza almacenamiento en base de datos H2 en memoria.
 */
public class EmpleadosDAO implements IEmpleadosDAO {

	public Empleado crearEmpleado(Empleado e) throws DataAccessException {
		String insertStatement = String.format(
				"insert into Empleado(dni, fechaContratacion, baja, nombre, categoria) values (%d, '%s', '%b', '%s', '%s')",
				e.getDNI(),
				e.getFechaContratacion().toString(),
				e.getBaja(),
				e.getNombre(),
				e.getCategoria().toString());
		H2ServerConnectionManager.executeSqlStatement(insertStatement);
		return e;
	}

	public Empleado empleado(String dni) throws DataAccessException {
		Empleado result = null; 
		Connection con = H2ServerConnectionManager.getConnection();
		try {
			Statement statement = con.createStatement();
			String statementText = "select * from empleado where dni = "+ dni;
			ResultSet results = statement.executeQuery(statementText);
			if (results.next()) { 
				result = EmpleadoMapper.toEmpleado(results);
			}
			statement.close(); 
		}
		catch (SQLException e) {
			throw new DataAccessException();
		}
		return result;
		
	}

	public List<Empleado> empleados() throws DataAccessException {
		List<Empleado> empleados = new ArrayList<>(); // Lista para retornar a los alumnos
		Connection con = H2ServerConnectionManager.getConnection(); // creamos una nueva conexi�n con la BD

		try {
			Statement statement = con.createStatement(); // Creamos un nuevo statement
			String statementText = "select * from Empleado"; // Seleccionamos a todos los alumnos
			ResultSet results = statement.executeQuery(statementText); // Le proporcionamos como par�metro al statement
															// el SELECT y lo ejecutamos
			while (results.next()) {
				empleados.add(EmpleadoMapper.toEmpleado(results)); // procesamos cada fila como un autor independiente
			}
			statement.close(); // Cerramos el statement
		} catch (SQLException e) {
			//System.out.println(e);
			throw new DataAccessException();
		}
		return empleados;
	}
	
	
	public Empleado eliminarEmpleado(String dni) throws DataAccessException {
        Connection con = H2ServerConnectionManager.getConnection();
        Empleado eliminado = null;
        try {
            Statement statement = con.createStatement();
            // Antes de eliminar al empleado, lo buscamos para retornarlo después
            eliminado = empleado(dni);
            String deleteStatement = "delete from Empleado where dni='" + dni + "'";
            statement.executeUpdate(deleteStatement);
            statement.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw new DataAccessException();
        }
        return eliminado;
    }

	public Empleado modificarEmpleado(Empleado nuevo) throws DataAccessException {
        Connection con = H2ServerConnectionManager.getConnection();
        try {
            Statement statement = con.createStatement();
            String updateStatement = String.format("update Empleado set nombre='%s', categoria='%s', fechaContratacion='%s', baja=%b where dni='%s'",
                    nuevo.getNombre(), nuevo.getCategoria().toString(), nuevo.getFechaContratacion().toString(), nuevo.getBaja(), nuevo.getDNI());
            statement.executeUpdate(updateStatement);
            statement.close();
        } catch (SQLException e) {
            System.out.println(e);
            throw new DataAccessException();
        }
        return nuevo;
    }
	
	
}
