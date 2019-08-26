/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FunctionOpt;

import BaseClasses.Solution;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que representa una solución al problema
 * @author josel
 */
public class FunctOptSolution implements Solution{
    /**
    * _sol Vector de enteros que será la representación interna de la solución al problema
    * _numObjs Entero donde se almacenará el número de objetos del problema
    * _fitness valor double que almacena la calidad de la solución
    */    
    int _size;
    Double _fitness;
    boolean _fitnessAssigned;
    ArrayList<Integer> _varX = new ArrayList<>();
   
    /**
    * Constructor
    * @param instance Referencia a un objeto con la información de la instancia del problema FunctOpt
    */
    public FunctOptSolution(FunctOptInstance instance) {
        this._size = instance.getSize();
        this._fitness = 0.;
        this._varX = new ArrayList<>(Collections.nCopies(instance.getSize(), 0));    
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
    */    
    public void putNumberInX(int index, int value) {
        this._varX.set(index, value);
        _fitnessAssigned = false;
    }
 
    /**
    * Función que devuelve la mochila en la que está insertado un objeto
    * @param object Índice del objeto consultado
    * @return Índice de la mochila en la que está insertado el objeto
    */
    public int whereIsNum(int object) {
        return this._varX.get(object);
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
        FunctOptSolution auxSol = (FunctOptSolution) solution;
        
        for(int i = 0; i < this._size; i++){
            this._varX.set(i, auxSol._varX.get(i));
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
    public Integer getvalueX(int index) {
        return this._varX.get(index);
    }
    public ArrayList<Integer> getArrayX() {
        return this._varX;
    }        

    void changeElement(int index, int value) {
        this._varX.set(index, value);
    }
}
