/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import java.util.ArrayList;

/**
 *
 * @author josel
 */
public class MQKPSimpleBestImprovement implements MQKPNeighExplorer{
    public MQKPSimpleBestImprovement(){}
  

    @Override
    public boolean findOperation(MQKPInstance instance, MQKPSolution solution, MQKPChangeOperation operation) {
            
        MQKPAssignmentOperation oaOperation = (MQKPAssignmentOperation) operation;
        //Crear una permutación de los índices de los objetos e inicializar algunas variables
        ArrayList<Integer> perm = new ArrayList<>();

        int numObjs = instance.getNumObjs();
        MQKPInstance.randomPermutation(numObjs, perm);
        int numKnapsacks = instance.getNumKnapsacks();
        boolean initialised = false;
        double bestDeltaFitness = 0;
        double deltaFitness = 0.0;

        for (int i = 0; i < numObjs; i++) {
            for (int j = 0; j <= numKnapsacks; j++) {
                deltaFitness = MQKPEvaluator.computeDeltaFitness(instance, solution, perm.get(i), j);
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
