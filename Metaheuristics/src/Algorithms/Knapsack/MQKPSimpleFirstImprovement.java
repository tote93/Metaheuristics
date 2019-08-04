/*ClimbNeighExplorer
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
public class MQKPSimpleFirstImprovement implements MQKPNeighExplorer {
    

    @Override
    public boolean findOperation(MQKPInstance instance, MQKPSolution solution, MQKPChangeOperation operation) {
        MQKPAssignmentOperation oaOperation = (MQKPAssignmentOperation) operation;
        
        int numObjs = instance.getNumObjs();
        int numKnapsacks = instance.getNumKnapsacks();
        //Crear una permutación de los índices de los objetos e inicializar algunas variables
        ArrayList<Integer> perm = new ArrayList<>();

        MQKPInstance.randomPermutation(numObjs, perm);

        double deltaFitness = 0.;
        for (int i = 0; i < numObjs; i++) {
            for (int j = 0; j <= numKnapsacks; j++) {
                deltaFitness = MQKPEvaluator.computeDeltaFitness(instance, solution, perm.get(i), j);
                if (deltaFitness > 0.0) {
                    oaOperation.setValues(perm.get(i), j, deltaFitness);
                    return true;
                }
            }
        }
        return false;
    }
}
