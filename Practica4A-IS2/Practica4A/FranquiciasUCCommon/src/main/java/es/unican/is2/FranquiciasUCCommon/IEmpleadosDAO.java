package es.unican.is2.FranquiciasUCCommon;



import java.util.List;

/**
 * Interfaz DAO para Empleados
 */
public interface IEmpleadosDAO {

	/**
	 * Persite un nuevo empleado
	 * @param e Empleado a anhadir
	 * @return El empleado una vez anhadido 
	 *         null si ya existe un empleado con el mismo dni
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado crearEmpleado(Empleado e) throws DataAccessException;

	/**
	 * Elimina el empleado cuyo DNI se pasa como parametro
	 * @param dni DNI del empleado
	 * @return El empleado eliminado 
	 *         null si no se encuentra el empleado
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado eliminarEmpleado(String dni) throws DataAccessException;

	/**
	 * Actualiza el estado del empleado
	 * @param nuevo Empleado actualizado
	 * @return Empleado actualizado 
	 *         null si no existe
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado modificarEmpleado(Empleado nuevo) throws DataAccessException;

	/**
	 * Retorna el empleado cuyo dni se pasa como parametro
	 * @param dni DNI del empleado
	 * @return El empleado buscado 
	 *         null si no se encuentra
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public Empleado empleado(String dni) throws DataAccessException;

	/**
	 * Retorna la lista completa de empleados
	 * @return La lista de empleados 
	 *         Lista vacia si no hay ninguno
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public List<Empleado> empleados() throws DataAccessException;
}
