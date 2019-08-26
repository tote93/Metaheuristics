/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FunctionOpt;

import Algorithms.FunctionOpt.FunctOptChangeOperation;
import Algorithms.FunctionOpt.FunctOptInstance;
import Algorithms.FunctionOpt.FunctOptSolution;
import java.util.ArrayList;

/**
 *
 * @author josel
 */
public class FunctOptSimpleBestImprovement implements FunctOptNeighExplorer{
    public FunctOptSimpleBestImprovement(){}
  

    @Override
    public boolean findOperation(FunctOptInstance instance, FunctOptSolution solution, FunctOptChangeOperation operation) {
            
        FunctOptAssignmentOperation oaOperation = (FunctOptAssignmentOperation) operation;
        //Crear una permutación de los índices de los objetos e inicializar algunas variables
        ArrayList<Integer> perm = new ArrayList<>();

        int numElem = instance.getSize();
        FunctOptInstance.randomPermutation(numElem, perm);
        int rango = instance.getRange();
        boolean initialised = false;
        double bestDeltaFitness = 0;
        double deltaFitness = 0.0;

        for (int i = 0; i < numElem; i++) {
            for (int j = 0; j <= rango; j++) {
                deltaFitness = FunctOptEvaluator.computeDeltaFitness(instance, solution, perm.get(i), j);
                if (deltaFitness > bestDeltaFitness || !initialised) {
                    initialised = true;
                    bestDeltaFitness = deltaFitness;
                    oaOperation.setValues(perm.get(i), j, bestDeltaFitness);
                }
            }
        }
        return (bestDeltaFitness > 0.0);
    }
}
