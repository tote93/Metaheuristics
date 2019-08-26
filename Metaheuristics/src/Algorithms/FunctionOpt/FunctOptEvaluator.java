/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FunctionOpt;

import java.util.ArrayList;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Clase que calcula el fitness de una solución al problema FunctOpt
 * @author i62gorej
 */
public class FunctOptEvaluator {
    /**
     * Variable donde se contabiliza el número de soluciones que se evalúan a través de  computeFitness o computeDeltaFitness
     */
    static int _numEvaluations = 0;
    
    /**
    * Función que calcula el fitness de una solución
    * @param instance Referencia a un objeto con la información de la instancia del problema FunctOpt
    * @param solution Referencia a un objeto que representa una solución al problema. 
    * @return Fitness de la solución. Será negativa si se viola la capacidad de alguna mochila y positiva si la solución es factible. En ese caso, el valor es igual a la suma de los beneficios individuales y cuadráticos
    */
    public static double computeFitness(FunctOptInstance instance, FunctOptSolution solution) {
        
        int value = transformInteger(solution.getArrayX());
        double fitness = new ExpressionBuilder(instance.getFunction())
            .variables("x")
            .build()
            .setVariable("x", value)
           // .setVariable("y", 0.25d)
           //.setVariable("z", 0.15d)
            .evaluate();
        
        FunctOptEvaluator._numEvaluations++;
        return fitness;
    }
    private static int transformInteger(ArrayList<Integer> arrayX){
        int value = 0, j = arrayX.size()-1;
        for(int i = 0; i < arrayX.size();i++, j--){
            if(arrayX.get(i) != 0)
                value += Math.pow(2, j);          
        }
        return value;        
    }
    /**
    * Función que calcula la diferencia en fitness si a la solución se le aplicase la asignación del objeto indexObject a la mochila indexKnapsack
    * @param instance Referencia a un objeto con la información de la instancia del problema FunctOpt
    * @param solution Referencia a un objeto que representa una solución al problema.
     * @param index
     * @param value
    *
    * @return Diferencia en fitness si a la solución se le aplicase la asignación del objeto indexObject a la mochila indexKnapsack
    */       
    public static double computeDeltaFitness(FunctOptInstance instance,
            FunctOptSolution solution, int index, int value) {
        _numEvaluations += (1. / instance.getSize());
        
        double actualFitness = solution.getFitness();
        FunctOptSolution sol = solution;
        //Actualizamos el nodo cambiando el nodo del indice "index" por node
        int node_aux = solution.getvalueX(index);
        sol.changeElement(index, value);

        //Calculamos Fitness del cambio de nodo
        double newFitness = FunctOptEvaluator.computeFitness(instance, sol);
        //retornamos la diferencia de fitness
        sol.changeElement(index, node_aux);
        return newFitness - actualFitness;
    }
    /**
    * Función que resetea la variable interna que contabiliza el número de evaluaciones
    */ 
    public static void resetNumEvaluations() {
	FunctOptEvaluator._numEvaluations = 0;
    }    
    /**
    * Función que devuelve el número de veces que se ha evaluado alguna solución
     * @return _numEvaluations Número de evaluaciones del algoritmo
    */
    public static int getNumEvaluations() {
        return FunctOptEvaluator._numEvaluations;
    }
    
    public static double compare(double f1, double f2) {
        return (f1 - f2);
    }

    public static Boolean isToBeMinimised() {
        return (compare(0, 1) > 0);
    }    
    
    
    
}
