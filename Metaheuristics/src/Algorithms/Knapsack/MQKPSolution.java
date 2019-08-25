/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.Solution;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa una solución al problema
 * @author josel
 */
public class MQKPSolution implements Solution{
    /**
    * _sol Vector de enteros que será la representación interna de la solución al problema
    * _numObjs Entero donde se almacenará el número de objetos del problema
    * _fitness valor double que almacena la calidad de la solución
    */    
    int _numObjs;
    Double _fitness;
    ArrayList<Integer> _sol;
    boolean _fitnessAssigned;
    /**
    * Constructor
    * @param instance Referencia a un objeto con la información de la instancia del problema MQKP
    */
    public MQKPSolution(MQKPInstance instance) {
        this._numObjs = instance.getNumObjs();
        this._fitness = 0.;
        this._sol = new ArrayList<>(Collections.nCopies(instance.getNumObjs(), 0));
        this._fitnessAssigned = false;
    }
    /**
    * Función que devuelve el fitness de la solución
    *
    * @return fitness de la solución
    */
    @Override
    public double getFitness() {
        return _fitness;
    }
    /**
    * Función que asigna un objeto a la mochila indicada
    * @param object Índice del objeto a insertar en la mochila indicada
    * @param knapsack Índice de la mochila donde insertar el objeto
    */    
    public void putObjectIn(int object, int knapsack) {
        this._sol.set(object, knapsack);
        _fitnessAssigned = false;
    }
    /**
    * Función que devuelve la mochila en la que está insertado un objeto
    * @param object Índice del objeto consultado
    * @return Índice de la mochila en la que está insertado el objeto
    */
    public int whereIsObject(int object) {
        return this._sol.get(object);
    }
    /**
    * Función que asigna el fitness de la solución
    * @param fitness Fitness de la solución
    */
    public void setFitness(double fitness) {
        _fitness = fitness;
        _fitnessAssigned = true;
    }

    @Override
    public void copy(Solution solution) {
        MQKPSolution auxSol = (MQKPSolution) solution;
        
        for(int i = 0; i < this._numObjs; i++){
            this._sol.set(i, auxSol._sol.get(i));
        }
        this._fitness = auxSol.getFitness();
        this._fitnessAssigned = auxSol._fitnessAssigned;        
    }
	/**
	 * Función que indica si el fitness de la solución es válido (deja de serlo si se cambia un objeto de mochila; y se convierte en válido cuando se le asigna)
	 *
         */
	boolean hasValidFitness(){
		return _fitnessAssigned;
	}    
}
