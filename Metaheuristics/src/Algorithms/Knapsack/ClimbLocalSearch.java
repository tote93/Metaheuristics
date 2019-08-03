/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.NeighExplorer;
import BaseClasses.Instance;
import BaseClasses.Solution;
import java.util.ArrayList;

/**
 *
 * @author josel
 */
public class ClimbLocalSearch {
    private ArrayList<Double> _results = new ArrayList<Double>();

    public ClimbLocalSearch() {
    }

    public void optimise(ClimbInstance inst, ClimbSimpleFirstImprovement exp, ClimbSolution sol) {
        this._results.clear();
        this._results.add(sol.getFitness());
        ClimbAssignmentOperation operation = new ClimbAssignmentOperation();
        int c = 0;
        while (exp.findOperation(inst, sol, operation)) {
            operation.apply(sol);
            this._results.add(sol.getFitness());
            /* System.out.println(c);
             c++;*/
        }
    }
    public ArrayList<Double> getResults() {
        return this._results;
    }
}
