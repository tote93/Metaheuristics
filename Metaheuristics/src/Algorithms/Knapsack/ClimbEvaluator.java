/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.Evaluator;

/**
 *
 * @author josel
 */
public class ClimbEvaluator extends Evaluator {

    static int _numEvaluations = 0;


    public static double computeFitness(ClimbInstance instance, ClimbSolution solution) {
        double fitness = 0.;
        if (instance.getMaxCapacityViolation(solution) > 0)
            fitness = instance.getMaxCapacityViolation(solution) * (-1);
        else
            fitness = instance.getSumProfits(solution);

        ClimbEvaluator._numEvaluations++;
        return fitness;
    }

    public static double computeDeltaFitness(ClimbInstance instance,
            ClimbSolution solution, int indexObject, int indexKnapsack) {

        double actualMaxViolation = instance.getMaxCapacityViolation(solution);
        double deltaMaxCapacityViolation = instance.getDeltaMaxCapacityViolation(solution, indexObject, indexKnapsack);
        double newMaxViolation = actualMaxViolation + deltaMaxCapacityViolation;
        double actualSumProfits = instance.getSumProfits(solution);
        double deltaSumProfits = instance.getDeltaSumProfits(solution, indexObject, indexKnapsack);
        double newSumProfits = actualSumProfits + deltaSumProfits;

        if (actualMaxViolation > 0 && newMaxViolation > 0) {
            return (-1.) * deltaMaxCapacityViolation;
        } else if (actualMaxViolation <= 0 && newMaxViolation <= 0) {
            return deltaSumProfits;
        } else if (actualMaxViolation > 0) {
            return (newSumProfits - deltaMaxCapacityViolation);
        } else {
            return (-1.) * (actualSumProfits + newMaxViolation);
        }
    }
    public static int getNumEvaluations() {
        return ClimbEvaluator._numEvaluations;
    }
}
