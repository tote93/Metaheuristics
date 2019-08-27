/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FunctionOpt;

import java.util.ArrayList;
/**
 * Clase que implementa la optimización local repetitiva de una solución dada
 * @author i62gorej
 */
public class FunctOptLocalSearch {
    /**
     * Vector de doubles donde almacena la calidad de la última solución aceptada
     */
    private ArrayList<Double> _resultsX = new ArrayList<>();
    private ArrayList<Double> _resultsY = new ArrayList<>();
    private ArrayList<Double> _resultsZ = new ArrayList<>();

    public FunctOptLocalSearch() {
    }
    /**
    * Función que optimiza una solución aplicado repetidamente la exploración de su vecindario hasta alcanzar un óptimo local.
    * @param inst Instancia del problema
    * @param exp Operador de exploración del vecindario. La idea es que reciba un operador que bien explore el vecindario devolviendo la primera solución que mejora a la actual, o devolviendo el mejor cambio posible sobre la solución actual
    * @param sol Solución inicial que se optimiza localmente. El resultado final se devuelve en ella.
    */
    public void optimise(FunctOptInstance inst, FunctOptNeighExplorer exp, FunctOptSolution sol) {
        this._resultsX.clear();
        this._resultsX.add(sol.getBestNumberX());
        
        this._resultsY.clear();
        this._resultsY.add(sol.getBestNumberY());

        this._resultsZ.clear();
        this._resultsZ.add(sol.getBestNumberZ());        
        
        FunctOptAssignmentOperation operation = new FunctOptAssignmentOperation();
        
        while (exp.findOperation(inst, sol, operation)) {
            operation.apply(sol);
            this._resultsX.add(sol.getBestNumberX());
            this._resultsY.add(sol.getBestNumberY());
            this._resultsZ.add(sol.getBestNumberZ());
        }
    }
    /**
    * Función que devuelve el vector con los resultados de las soluciones aceptadas, en cada paso, por la búsqueda local
    *
    * @return vector con los resultados de las soluciones aceptadas, en cada paso, por la búsqueda local
    */   
    public ArrayList<Double> getResultsX() {
        return this._resultsX;
    }
    public ArrayList<Double> getResultsY() {
        return this._resultsY;
    } 
    public ArrayList<Double> getResultsZ() {
        return this._resultsZ;
    }     


}
