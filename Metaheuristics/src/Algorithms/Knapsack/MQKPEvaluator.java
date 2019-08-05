/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

/**
 * Clase que calcula el fitness de una solución al problema MQKP
 * @author josel
 */
public class MQKPEvaluator {
    /**
     * Variable donde se contabiliza el número de soluciones que se evalúan a través de  computeFitness o computeDeltaFitness
     */
    static int _numEvaluations = 0;
    
    /**
    * Función que calcula el fitness de una solución
    * @param instance Referencia a un objeto con la información de la instancia del problema MQKP
    * @param solution Referencia a un objeto que representa una solución al problema. 
    * @return Fitness de la solución. Será negativa si se viola la capacidad de alguna mochila y positiva si la solución es factible. En ese caso, el valor es igual a la suma de los beneficios individuales y cuadráticos
    */
    public static double computeFitness(MQKPInstance instance, MQKPSolution solution) {
        double fitness = 0.;
        if (instance.getMaxCapacityViolation(solution) > 0)
            fitness = instance.getMaxCapacityViolation(solution) * (-1);
        else
            fitness = instance.getSumProfits(solution);

        MQKPEvaluator._numEvaluations++;
        return fitness;
    }
    /**
    * Función que calcula la diferencia en fitness si a la solución se le aplicase la asignación del objeto indexObject a la mochila indexKnapsack
    * @param instance Referencia a un objeto con la información de la instancia del problema MQKP
    * @param solution Referencia a un objeto que representa una solución al problema.
    * @param indexObject Índice del objeto que se pondría en otra mochila
    * @param indexKnapsack Índice de la mochila donde se pondría el objeto. Puede ser 0, indicando que se saca el objeto de la mochila en la que esté
    *
    * @return Diferencia en fitness si a la solución se le aplicase la asignación del objeto indexObject a la mochila indexKnapsack
    */       
    public static double computeDeltaFitness(MQKPInstance instance,
            MQKPSolution solution, int indexObject, int indexKnapsack) {
        MQKPEvaluator._numEvaluations++;
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
    /**
    * Función que resetea la variable interna que contabiliza el número de evaluaciones
    */ 
    public static void resetNumEvaluations() {
	MQKPEvaluator._numEvaluations = 0;
    }    
    /**
    * Función que devuelve el número de veces que se ha evaluado alguna solución
     * @return _numEvaluations Número de evaluaciones del algoritmo
    */
    public static int getNumEvaluations() {
        return MQKPEvaluator._numEvaluations;
    }
    
    public static double compare(double f1, double f2) {
        return (f1 - f2);
    }

    public Boolean isToBeMinimised() {
        return (compare(0, 1) > 0);
    }    
    
    
    
}
