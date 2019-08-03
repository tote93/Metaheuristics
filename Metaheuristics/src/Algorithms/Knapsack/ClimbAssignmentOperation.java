/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.Solution;
import BaseClasses.ChangeOperation;

/**
 *
 * @author josel
 */
class ClimbAssignmentOperation extends ChangeOperation {
	private int _indexKnapsack = 0;
	private int _indexObj = 0;
	private double _deltaFitness = 0.;

	ClimbAssignmentOperation() {}

	public void apply(ClimbSolution sol) {
		sol.putObjectIn(_indexObj, _indexKnapsack);
		sol.setFitness(sol.getFitness() + this._deltaFitness);
	}
	public void setValues(int indexObject, int indexKnapsack, double deltaFitness) {
		this._deltaFitness = deltaFitness;
		this._indexKnapsack = indexKnapsack;
		this._indexObj = indexObject;
	}
}
