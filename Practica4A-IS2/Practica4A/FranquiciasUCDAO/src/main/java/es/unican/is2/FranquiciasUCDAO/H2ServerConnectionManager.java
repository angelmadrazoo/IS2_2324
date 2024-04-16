package es.unican.is2.FranquiciasUCDAO;

import es.unican.is2.FranquiciasUCCommon.*;




import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Clase que gestiona el acceso a la base de datos H2 en memoria.
 * Permite abrir conexiones y crear y rellenar la propia base de datos
 * al inicio de la aplicacion
 */
public class H2ServerConnectionManager {

	// Conexion con la base de datos
	protected static Connection connection;

	// Atributos de acceso a la Base de Datos
	protected static String dbName = "test";
	protected static String user = "sa";
	protected static String pass = "";

	/**
	 * Obtiene una conexion con la base de datos
	 * @return La conexion
	 * @throws DataAccessException si no se puede establecer al conexion
	 */
	public static Connection getConnection() throws DataAccessException {

		if (connection == null) { 
			try {
				Class.forName("org.h2.Driver"); //comprueba que el driver esta instalado
				connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa","");		
				cargaDatos();
			} catch (SQLException | ClassNotFoundException e) {
				throw new DataAccessException();
			}
		}
		return connection;
	}

	/**
	 * Crea la base de datos e introduce los datos iniciales
	 * @throws DataAccessException Si hay un fallo en la conexion
	 */
	public static void cargaDatos() throws DataAccessException {
		try {
			Connection con = getConnection();
			
			// Creacion programatica de la BBDD
			Statement stm = con.createStatement(); 
			
			// Creacion de la tabla Tienda
			String sql= "CREATE TABLE Tienda (id BIGINT NOT NULL AUTO_INCREMENT, nombre VARCHAR(100) NOT NULL, "
					+ "direccion VARCHAR(200) NOT NULL, PRIMARY KEY(id))";
			stm.execute(sql); 
			
			// Creacion de la tabla Empleado
			sql  = "CREATE TABLE Empleado (dni CHAR(9) NOT NULL, fechaContratacion DATE NOT NULL, baja BOOLEAN NOT NULL, "
					+ "nombre VARCHAR(100) NOT NULL, categoria VARCHAR(10) NOT NULL, idTienda BIGINT NOT NULL, "
					+ "PRIMARY KEY(dni), FOREIGN KEY(idTienda) REFERENCES Tienda(id))";
			stm.execute(sql); 		
			
			// Inicializa tiendas
			sql = "INSERT INTO Tienda (nombre, direccion) VALUES ('Tienda A', 'Direccion A')"; 
			stm.executeUpdate(sql);
			sql = "INSERT INTO Tienda (nombre, direccion) VALUES ('Tienda B', 'Direccion B')"; 
			stm.executeUpdate(sql);
			sql = "INSERT INTO Tienda (nombre, direccion) VALUES ('Tienda C', 'Direccion C')"; 
			stm.executeUpdate(sql);
			
			// Inicializa empleados
			sql = "INSERT INTO Empleado (dni, fechaContratacion, baja, nombre, categoria, idTienda) "
					+ "VALUES ('11111111A', '2002-01-15', FALSE, 'Juan Perez', 'ENCARGADO', 1)";
			stm.executeUpdate(sql);
			sql = "INSERT INTO Empleado (dni, fechaContratacion, baja, nombre, categoria, idTienda) "
					+ "VALUES ('11111111B', '2016-05-20', TRUE, 'Maria Rodriguez', 'VENDEDOR', 1)";
			stm.executeUpdate(sql);			
			sql = "INSERT INTO Empleado (dni, fechaContratacion, baja, nombre, categoria, idTienda) "
					+ "VALUES ('1111111C', '2022-05-21', FALSE, 'Luis Martinez', 'AUXILIAR', 1)";
			stm.executeUpdate(sql);
			sql = "INSERT INTO Empleado (dni, fechaContratacion, baja, nombre, categoria, idTienda) "
					+ "VALUES ('1111111D', '2010-06-01', FALSE, 'Lucia Ibanhez', 'ENCARGADO', 2)";
			stm.executeUpdate(sql);
			
			// Cierra el statement
			stm.close();
			
		} catch (SQLException e) {
			System.out.println(e);
			throw new DataAccessException();
			
		} 		
	}

	/**
	 * Metodo estatico para ejecutar operaciones SQL y manejar los errores.
	 * 
	 * IMPORTANTE: este metodo solo puede ser llamado para operaciones de INSERT, UPDATE
	 * y DELETE. No debe usarse para realizar SELECTs ni llamadas a PROCEDIMIENTO, las ejecuciones
	 * de ese tipo de operaciones tienen que implementarse en sus respectivos metodos.
	 * 
	 * @param stringStatement Instruccion a ejecutar
	 * @return true si se ha ejecutado la operacion
	 *         false si no se ha ejecutado la operacion
	 * @throws DataAccessException si hay un error en la conexion
	 */
	public static boolean executeSqlStatement(String stringStatement) throws DataAccessException {
		Connection con = getConnection(); 
		try {
			Statement stm = con.createStatement(); 
			stm.execute(stringStatement); 
			stm.close(); 
		}
		catch (SQLException e) {
			return false; 
		}
		return true; 
	}
}
