package es.unican.is2.FranquiciasUCCommon;



/**
 * Interfaz de negocio para gestionar empleados
 */
public interface IGestionEmpleados {

	/**
	 * Anhade un nuevo empleado a una tienda
	 * @param e Empleado que se quiere anhadir
	 * @param nombre Nombre de la tienda
	 * @return El empleado a�adido 
	 *         null si no se anhade porque no existe la tienda
	 * @throws OperacionNoValidaException Si el empleado ya existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException;

	/**
	 * Elimina un empleado de una tienda
	 * @param dni DNI del empleado
	 * @param nombre Nombre de la tienda
	 * @return El empleado eliminado 
	 *         null si el empleado o la tienda no existen
	 * @throws OperacionNoValidaException Si el empleado no pertenece a la tienda indicada
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException;

	/**
	 * Traslada un empleado de una tienda a otra
	 * @param dni DNI del empleado
	 * @param actual Nombre de la tienda actual del empleado
	 * @param destino Nombre de la tienda destino del empleado
	 * @return true si se traslada al empleado exitosamente 
	 *         false si no se traslada porque el empleado o alguna de las tiendas no existen
	 * @throws OperacionNoValidaException Si el empleado existe pero no pertenece a la
	 *                             tienda actual
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException;

	/**
	 * Retorna el empleado con el dni indicado
	 * @param dni
	 * @return El empleado con el dni indicado 
	 *         null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado empleado(String dni) throws DataAccessException;

}
