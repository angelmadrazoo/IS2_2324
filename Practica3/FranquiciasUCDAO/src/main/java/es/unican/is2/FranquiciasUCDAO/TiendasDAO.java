package es.unican.is2.FranquiciasUCDAO;


import es.unican.is2.FranquiciasUCCommon.*; 



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementacion de la capa DAO de acceso a Tiendas. 
 * Utiliza almacenamiento en base de datos H2 en memoria.
 */
public class TiendasDAO implements ITiendasDAO {

	public Tienda crearTienda(Tienda t) throws DataAccessException {
		String insertStatement = String.format("insert into Tienda(id, nombre, direccion) values (%d, '%s', '%s')",
				t.getId(), t.getNombre(), t.getDireccion());
		H2ServerConnectionManager.executeSqlStatement(insertStatement);
		return t;
	}

	public Tienda tienda(long id) throws DataAccessException {
		Tienda result = null;
		Connection con = H2ServerConnectionManager.getConnection();
		try {
			Statement statement = con.createStatement();
			String statementText = "select * from Tienda where id = '" + id + "'";
			ResultSet results = statement.executeQuery(statementText);
			if (results.next())
				result = procesaTienda(con, results);
			statement.close();
		} catch (SQLException e) {
			System.out.println(e);
			throw new DataAccessException();
		}
		return result;
	}

	public Tienda tiendaPorNombre(String nombre) throws DataAccessException {
		Tienda result = null;
		Connection con = H2ServerConnectionManager.getConnection();
		try {
			Statement statement = con.createStatement();
			String statementText = "select * from Tienda where nombre = '" + nombre + "'";
			System.out.println(statementText);
			ResultSet results = statement.executeQuery(statementText);
			if (results.next())
				result = procesaTienda(con, results);
			statement.close(); 

		} catch (SQLException e) {
			// System.out.println(e);
			throw new DataAccessException();
		}
		return result;
	}

	public List<Tienda> tiendas() throws DataAccessException {
		List<Tienda> tiendas = new ArrayList<>(); 
		Connection con = H2ServerConnectionManager.getConnection(); 

		try {
			Statement statement = con.createStatement(); 
			String statementText = "select * from Tienda"; 
			ResultSet results = statement.executeQuery(statementText); 
			// Procesamos cada fila como tienda independiente
			while (results.next()) {
				tiendas.add(procesaTienda(con, results)); 
			}
			statement.close(); 
		} catch (SQLException e) {
			// System.out.println(e);
			throw new DataAccessException();
		}

		return tiendas;
	}

	public Tienda modificarTienda(Tienda nuevo) throws DataAccessException {
		// TODO
		return null;
	}

	public Tienda eliminarTienda(long id) throws DataAccessException {
		// TODO
		return null;
	}

	private Tienda procesaTienda(Connection con, ResultSet results) throws SQLException, DataAccessException {
		Tienda result = null;
		result = TiendaMapper.toTienda(results);
		// Cargamos los empleados de la tienda
		Statement statement = con.createStatement();
		String statementText = String.format("select * from Empleado where idTienda = %d", result.getId());
		results = statement.executeQuery(statementText);
		while (results.next()) {
			result.getEmpleados().add(EmpleadoMapper.toEmpleado(results));
		}
		statement.close();
		return result;
	}

}
