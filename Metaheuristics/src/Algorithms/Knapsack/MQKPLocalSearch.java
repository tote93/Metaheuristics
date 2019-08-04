/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import java.util.ArrayList;
/**
 * Clase que implementa la optimización local repetitiva de una solución dada
 * @author i62gorej
 */
public class MQKPLocalSearch {
    /**
     * Vector de doubles donde almacena la calidad de la última solución aceptada
     */
    private ArrayList<Double> _results = new ArrayList<Double>();

    public MQKPLocalSearch() {
    }
    /**
    * Función que optimiza una solución aplicado repetidamente la exploración de su vecindario hasta alcanzar un óptimo local.
    * @param inst Instancia del problema
    * @param exp Operador de exploración del vecindario. La idea es que reciba un operador que bien explore el vecindario devolviendo la primera solución que mejora a la actual, o devolviendo el mejor cambio posible sobre la solución actual
    * @param sol Solución inicial que se optimiza localmente. El resultado final se devuelve en ella.
    */
    public void optimise(MQKPInstance inst, MQKPNeighExplorer exp, MQKPSolution sol) {
        this._results.clear();
        this._results.add(sol.getFitness());
        MQKPAssignmentOperation operation = new MQKPAssignmentOperation();
        
        while (exp.findOperation(inst, sol, operation)) {
            operation.apply(sol);
            this._results.add(sol.getFitness());
        }
    }
    /**
    * Función que devuelve el vector con los resultados de las soluciones aceptadas, en cada paso, por la búsqueda local
    *
    * @return vector con los resultados de las soluciones aceptadas, en cada paso, por la búsqueda local
    */   
    public ArrayList<Double> getResults() {
        return this._results;
    }


}
