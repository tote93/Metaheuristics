/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FunctionOpt;

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
        String type = instance.getFuncType();
        boolean initialised = false;
        double bestDeltaFitness = 0;
        double deltaFitness = 0.0;

        for (int i = 0; i < numElem; i++) {
            for (int j = 0; j <= 1; j++) {
                deltaFitness = FunctOptEvaluator.computeDeltaFitness(instance, solution, perm.get(i), j);
                if(type.equals("Maximización")){
                    if (deltaFitness > bestDeltaFitness || !initialised) {
                        initialised = true;
                        bestDeltaFitness = deltaFitness;
                        oaOperation.setValues(perm.get(i), j, bestDeltaFitness);
                    }                    
                }
                else{
                    if (deltaFitness < bestDeltaFitness || !initialised) {
                        initialised = true;
                        bestDeltaFitness = deltaFitness;
                        oaOperation.setValues(perm.get(i), j, bestDeltaFitness);
                    }                    
                }
            }
        }
        return (type.equals("Maximización")) ? (bestDeltaFitness > 0.0) : (bestDeltaFitness < 0.0);
    }
}
