package Algorithms.FunctionOpt;

/**
 * clase abstracta que define las operaciones de cualquier operador que explora la vecindad de una solución dada.
 * @author i62gorej
 */
public interface FunctOptNeighExplorer {
	/**
	 * Función que busca una operación que aplicada a la solución devuelva otra solución vecina. Se utilizará para buscar una solución vecina que la mejore, o la mejor de las soluciones vecinas.
	 *
	 * @param instance Instancia del problema de la múltiples mochilas cuadráticas
	 * @param solution Solución cuya vecindad va a ser explorada
	 * @param operation Operación que se devuelve al explorar la vecindad.
	 *
	 * @return Devuelve verdadero si ha encontrado una operación válida, que mejora la solution y que se ha almacenado en operation. Falso en otro caso
	 */    
	public boolean findOperation(FunctOptInstance instance, FunctOptSolution solution, FunctOptChangeOperation operation);
}
