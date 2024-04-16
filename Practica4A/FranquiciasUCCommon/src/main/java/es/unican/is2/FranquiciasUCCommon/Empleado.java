package es.unican.is2.FranquiciasUCCommon;

import java.time.LocalDate;
import java.time.Period;
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {
	
	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;
	
	public Empleado() {	}
	
	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
	 * @throws NullNombreIncorrecto 
	 * @throws NombreVacio 
	 * @throws NullFechaIncorrecta 
	 * @throws NullDniIncorrecto 
	 * @throws DniVacio 
	 * @throws DniFormatoIncorrecto 
	 * @throws NullCategoriaIncorrecto 
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) throws NullNombreIncorrecto, NombreVacio, NullFechaIncorrecta, NullDniIncorrecto, DniVacio, DniFormatoIncorrecto, NullCategoriaIncorrecto {
		this.nombre = nombre;
		if (nombre == null) {
			throw new NullNombreIncorrecto(); 
		}
		if (nombre.isBlank()) {
			throw new NombreVacio(); 
		}
		this.DNI = DNI;
		if (DNI == null) {
			throw new NullDniIncorrecto(); 
		}
		
		if (DNI.isBlank()) {
			throw new DniVacio(); 
		}
		
		if (!DNI.isBlank() && (DNI.length() > 9 || DNI.length() < 9)) {
			throw new DniFormatoIncorrecto(); 
		}
		
		this.categoria = categoria;
		if (categoria == null) {
			throw new NullCategoriaIncorrecto(); 
		}
		this.fechaContratacion = fechaContratacion;
		if (fechaContratacion == null) {
			throw new NullFechaIncorrecta(); 
		}
		
	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 * @throws NullFechaIncorrecta si la fecha es nula
	 */
	public double sueldoBruto() {
		double sueldoCategoria = 0.0;
		
		switch(categoria) {
		case AUXILIAR:
			sueldoCategoria = 1000.0;
			break;
		case ENCARGADO:
			sueldoCategoria = 2000.0;
			break;
		case VENDEDOR:
			sueldoCategoria = 1500.0;
			break;
	
		}
		
		LocalDate fechaActual = LocalDate.now();
	    int antiguedad = Period.between(fechaContratacion, fechaActual).getYears();
	    double complementoAntiguedad = 0.0;
	    
	    if (antiguedad > 20) {
	        complementoAntiguedad = 200.0;
	    } else if (antiguedad > 10) {
	        complementoAntiguedad = 100.0;
	    } else if (antiguedad > 5) {
	        complementoAntiguedad = 50.0;
	    }
	    
	    
	    double sueldo = sueldoCategoria + complementoAntiguedad;
	    
	    if (baja) {
	        sueldo *= 0.75; // Reducción del 25%
	    }

	    
	    return sueldo;
		
	}
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
	
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	
	/**
	 * Retorna si el empleado est� de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}
		
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
