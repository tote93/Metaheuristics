/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.Solution;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author josel
 */
public class ClimbSolution extends Solution {
    int _numObjs;
    Double _fitness;
    ArrayList<Integer> _sol;

    public ClimbSolution(ClimbInstance instance) {
        this._numObjs = instance.getNumObjs();
        this._fitness = 0.;
        this._sol = new ArrayList<>(Collections.nCopies(instance.getNumObjs(), 0));
        //System.out.println(_sol);
    }
    @Override
    public double getFitness() {
        return _fitness;
    }
    public void putObjectIn(int object, int knapsack) {
        this._sol.set(object, knapsack);
    }
    @Override
    public int whereIsObject(int object) {
        return this._sol.get(object);
    }
    @Override
    public void setFitness(double d) {
        _fitness = d;
    }
}
