/*ClimbNeighExplorer
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
public class FunctOptSimpleFirstImprovement implements FunctOptNeighExplorer {
    

    @Override
    public boolean findOperation(FunctOptInstance instance, FunctOptSolution solution, FunctOptChangeOperation operation) {
        FunctOptAssignmentOperation oaOperation = (FunctOptAssignmentOperation) operation;
        String type = instance.getFuncType();
        int numElem = instance.getSize();
        //Crear una permutación de los índices de los objetos e inicializar algunas variables
        ArrayList<Integer> perm = new ArrayList<>();

        FunctOptInstance.randomPermutation(numElem, perm);

        double deltaFitness = 0.;
        for (int i = 0; i < numElem; i++) {
            for (int j = 0; j <= 1; j++) {
                deltaFitness = FunctOptEvaluator.computeDeltaFitness(instance, solution, perm.get(i), j);
                if(type.equals("Maximización")){
                    if (deltaFitness > 0.0) {
                        oaOperation.setValues(perm.get(i), j, deltaFitness);
                        return true;
                    }
                }
                else{
                    if (deltaFitness < 0.0) {
                        oaOperation.setValues(perm.get(i), j, deltaFitness);
                        return true;
                    }                    
                }
            }
        }
        return false;
    }
}
