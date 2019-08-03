/*ClimbNeighExplorer
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.NeighExplorer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author josel
 */
public class ClimbSimpleFirstImprovement extends NeighExplorer {
    boolean findOperation(ClimbInstance instance, ClimbSolution solution,
                          ClimbAssignmentOperation operation) {

        int numObjs = instance.getNumObjs();
        int numKnapsacks = instance.getNumKnapsacks();

        //Crear una permutación de los índices de los objetos e inicializar algunas variables

        ArrayList<Integer> perm = new ArrayList<>();

        ClimbInstance.randomPermutation(numObjs, perm);

        double deltaFitness = 0.;
        for (int i = 0; i < numObjs; i++) {
            for (int j = 0; j <= numKnapsacks; j++) {
                deltaFitness = ClimbEvaluator.computeDeltaFitness(instance, solution, perm.get(i), j);
                if (deltaFitness > 0.0) {
                    operation.setValues(perm.get(i), j, deltaFitness);
                    return true;
                }
            }
        }
        return false;
    }
}
