package Algorithms.Knapsack;



/**
 *
 * @author i62gorej
 */
class MQKPAssignmentOperation implements MQKPChangeOperation {
    private int _indexKnapsack = 0;
    private int _indexObj = 0;
    private double _deltaFitness = 0.;

    MQKPAssignmentOperation() {}

    public void setValues(int indexObject, int indexKnapsack, double deltaFitness) {
        this._deltaFitness = deltaFitness;
        this._indexKnapsack = indexKnapsack;
        this._indexObj = indexObject;
    }

    @Override
    public void apply(MQKPSolution sol) {
	sol.putObjectIn(_indexObj, _indexKnapsack);
	sol.setFitness(sol.getFitness() + this._deltaFitness);    
    }
 	/**
	 * Función que devuelve el objeto sujeto de la operación
	 * @return índice del objeto sujeto de la operación
	 */
	int getObj(){
		return _indexObj;
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
	int getKnapsack(){
		return _indexKnapsack;
	}   
}
