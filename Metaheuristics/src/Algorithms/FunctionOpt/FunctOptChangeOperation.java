package Algorithms.FunctionOpt;

/**
 * Clase abstracta para representar cualquier operación de modificación sobre una solución.
 * @author i62gorej
 */
public interface FunctOptChangeOperation {
	/**
	 * Función que aplica el cambio que define el objeto sobre la solución que recibe como argumento
	 * @param sol Objeto solución sobre el que se aplicará el cambio
	 */    
	public void apply(FunctOptSolution sol);
}
