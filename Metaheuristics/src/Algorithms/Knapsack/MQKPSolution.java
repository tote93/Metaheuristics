/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa una solución al problema
 * @author josel
 */
public class MQKPSolution {
    /**
    * _sol Vector de enteros que será la representación interna de la solución al problema
    * _numObjs Entero donde se almacenará el número de objetos del problema
    * _fitness valor double que almacena la calidad de la solución
    */    
    int _numObjs;
    Double _fitness;
    ArrayList<Integer> _sol;
    /**
    * Constructor
    * @param instance Referencia a un objeto con la información de la instancia del problema MQKP
    */
    public MQKPSolution(MQKPInstance instance) {
        this._numObjs = instance.getNumObjs();
        this._fitness = 0.;
        this._sol = new ArrayList<>(Collections.nCopies(instance.getNumObjs(), 0));
    }
    /**
    * Función que devuelve el fitness de la solución
    *
    * @return fitness de la solución
    */
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
    }
    /**
    * Función que copia la información de otra solución
    * @param solution La solución de donde copiar la información
    */   
    void copy(MQKPSolution solution){
        for(int i = 0; i < this._numObjs; i++){
            this._sol.set(i, solution._sol.get(i));
        }
        this._fitness = solution.getFitness();
    }
}
