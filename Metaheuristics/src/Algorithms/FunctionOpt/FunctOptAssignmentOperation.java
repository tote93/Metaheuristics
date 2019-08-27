package Algorithms.FunctionOpt;



/**
 *
 * @author i62gorej
 */
class FunctOptAssignmentOperation implements FunctOptChangeOperation {
    private int _node = 0;
    private int _size = 0;
    private double _deltaFitness = 0.;

    FunctOptAssignmentOperation() {}

    public void setValues(int indexObject, int valueArray, double deltaFitness) {
        this._deltaFitness = deltaFitness;
        this._node = valueArray;
        this._size = indexObject;
    }

    @Override
    public void apply(FunctOptSolution solution) {
	solution.putNumberInX(_size, _node);
        solution.putNumberInY(_size, _node);
        solution.putNumberInZ(_size, _node);
        
	double actualFitness = solution.getFitness();
	double newFitness = actualFitness + _deltaFitness;
	solution.setFitness(newFitness);   
        solution.setBestNumberX((int) solution.getBestNumberX());
        solution.setBestNumberY((int) solution.getBestNumberY());
        solution.setBestNumberZ((int) solution.getBestNumberZ());        
    }
 	/**
	 * Función que devuelve el objeto sujeto de la operación
	 * @return índice del objeto sujeto de la operación
	 */
	int getSize(){
		return _size;
	}

	/**
	 * Función que devuelve la diferencia en fitness de la operación,
	 * @return Diferencia en fitness de la operación (siempre que la solución actual no se hubiese cambiado cuando se calculo dicha diferencia)
	 */
	double getDeltaFitness(){
		return _deltaFitness;
	}

	/**
	 * Función que devuelve la mochila objeto de la operación
	 * @return mochila objeto de la operación
	 */
	int getNode(){
		return _node;
	}   
}
