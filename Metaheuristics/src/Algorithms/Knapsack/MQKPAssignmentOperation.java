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

    public void apply(MQKPSolution sol) {
	sol.putObjectIn(_indexObj, _indexKnapsack);
	sol.setFitness(sol.getFitness() + this._deltaFitness);    
    }
}
