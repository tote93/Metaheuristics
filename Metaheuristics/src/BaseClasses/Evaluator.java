/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseClasses;

/**
 *
 * @author josel
 */
public class Evaluator {
	static int _numEvaluations;
	public Evaluator() {
		_numEvaluations = 0;
	}

	public static double computeFitness(Instance i, Solution sol) {
		return 0;
	}

	public static  double computeDeltaFitness(Instance i, Solution sol) {
		return 0;
	}

	public static void resetNumEvaluations() {
		Evaluator._numEvaluations = 0;
	}

	public static int getNumEvaluations() {
		return Evaluator._numEvaluations;
	}

	public double compare(double f1, double f2) {
		return (f1 - f2);
	}

	public Boolean isToBeMinimised() {
		return (compare(0, 1) > 0);
	}
}
