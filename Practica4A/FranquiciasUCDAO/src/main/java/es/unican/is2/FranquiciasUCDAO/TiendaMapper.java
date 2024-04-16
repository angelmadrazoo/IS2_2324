package es.unican.is2.FranquiciasUCDAO;
import es.unican.is2.FranquiciasUCCommon.*;




import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Clase de utilidad que mapea filas de la base de datos a objetos 
 * de tipo Tienda.
 */
public class TiendaMapper {
	
	
	/**
	 * Recibe un ResultSet de una Tienda y devuelve un
	 * objeto Tienda con los datos del ResultSet
	 * @param results Fila resultado de una consulta en base de datos
	 * @return Tienda
	 */
	public static Tienda toTienda(ResultSet results) throws DataAccessException {

		Tienda t = new Tienda();
		try {
			t.setId(results.getInt("id"));
			t.setNombre(results.getString("nombre"));
			t.setDireccion(results.getString("direccion"));
		} catch (SQLException e) {
			throw new DataAccessException();
		}
		return t;
	}

}
