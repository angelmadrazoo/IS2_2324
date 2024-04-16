package es.unican.is2.FranquiciasUCCommon;




/**
 * Interfaz de negocio para la gestion de altas y bajas medicas
 */
public interface IGestionAltasBajas {

	/**
	 * Registrar la baja medica de un empleado
	 * @param dni DNI del empleado
	 * @return true si lo da de baja 
	 *         false si no existe el empleado
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public boolean bajaMedica(String dni) throws DataAccessException;

	/**
	 * Registrar el alta medica de un empleado
	 * @param dni DNI del empleado
	 * @return true si lo da de alta 
	 *         false si no existe el empleado
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public boolean altaMedica(String dni) throws DataAccessException;
}
