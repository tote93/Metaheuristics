/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;
import BaseClasses.Instance;
import BaseClasses.SolGenerator;
import BaseClasses.Solution;
import java.util.Random;
/**
 *
 * @author josel
 */
public class ClimbSolGenerator extends SolGenerator {

    /**
     *
     * @param instance
     * @param sol
     */
    public static void genRandomSol(ClimbInstance instance, ClimbSolution sol) {
        int numObjs = instance.getNumObjs();
        int numKnapsacks = instance.getNumKnapsacks();

        Random rand = new Random();
        for (int i = 0; i < numObjs; i++) {
            int randomKnapsack = rand.nextInt(numKnapsacks + 1);
            sol.putObjectIn(i, randomKnapsack);
        }
    }
}
