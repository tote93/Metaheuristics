/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FunctionOpt;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
        ArrayList<Double> values = solution.transformInteger(instance.getPrecision());
        double fitness = new ExpressionBuilder(instance.getFunction())
            .variables("x")
            .variables("y")
            .variables("z")                
            .build()
            .setVariable("x", values.get(0))
            .setVariable("y", values.get(1))
            .setVariable("z", values.get(2))
            .evaluate();
        //Transform to double with precision
        BigDecimal bd = new BigDecimal(fitness);
        bd = bd.setScale(instance.getPrecision(),RoundingMode.HALF_UP);
        
        FunctOptEvaluator._numEvaluations++;        
        return bd.doubleValue();
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
        int node_auxY = solution.getvalueY(index);
        int node_auxZ = solution.getvalueZ(index);
        
        sol.changeElementX(index, value);
        sol.changeElementY(index, value);
        sol.changeElementZ(index, value);
        
        //Calculamos Fitness del cambio de nodo
        double newFitness = FunctOptEvaluator.computeFitness(instance, sol);
        //retornamos la diferencia de fitness
        sol.changeElementX(index, node_aux);
        sol.changeElementY(index, node_auxY);
        sol.changeElementZ(index, node_auxZ);
        
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
