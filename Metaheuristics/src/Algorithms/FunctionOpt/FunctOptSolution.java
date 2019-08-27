/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FunctionOpt;

import BaseClasses.Solution;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
    double _bestNumberX;
    double _bestNumberY;
    double _bestNumberZ;
    boolean _AssignedFitness;
    ArrayList<Integer> _varX = new ArrayList<>();
    ArrayList<Integer> _varY = new ArrayList<>();
    ArrayList<Integer> _varZ = new ArrayList<>();    
   
    /**
    * Constructor
    * @param instance Referencia a un objeto con la información de la instancia del problema FunctOpt
    */
    public FunctOptSolution(FunctOptInstance instance) {
        this._size = instance.getSize();
        this._fitness = 0.;
        this._varX = new ArrayList<>(Collections.nCopies(instance.getSize(), 0)); 
        this._varY = new ArrayList<>(Collections.nCopies(instance.getSize(), 0)); 
        this._varZ = new ArrayList<>(Collections.nCopies(instance.getSize(), 0)); 
        this._AssignedFitness = false;
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
    /*
    * Get y Set funciones Para array de ejes
    *
    */
    public double getBestNumberX() {
        return _bestNumberX;
    }
    public void setBestNumberX(double n) {
        _bestNumberX = n;
    }   
    
    public double getBestNumberY() {
        return _bestNumberY;
    }
    public void setBestNumberY(double n) {
        _bestNumberY = n;
    }
    
    public double getBestNumberZ() {
        return _bestNumberZ;
    }
    public void setBestNumberZ(double n) {
        _bestNumberZ = n;
    }      
    /**
    * Función que asigna un objeto a la mochila indicada
     * @param index
     * @param value
    */    
    public void putNumberInX(int index, int value) {
        this._varX.set(index, value);
        _AssignedFitness = false;
    }
    public void putNumberInY(int index, int value) {
        this._varY.set(index, value);
    }    
    public void putNumberInZ(int index, int value) {
        this._varZ.set(index, value);
    }  
    /**
    * Función que devuelve la mochila en la que está insertado un objeto
    * @param object Índice del objeto consultado
    * @return Índice de la mochila en la que está insertado el objeto
    */
    public int whereIsNumX(int object) {
        return this._varX.get(object);
    }
    public int whereIsNumY(int object) {
        return this._varY.get(object);
    }   
    public int whereIsNumZ(int object) {
        return this._varZ.get(object);
    }       
    /**
    * Función que asigna el fitness de la solución
    * @param fitness Fitness de la solución
    */
    public void setFitness(double fitness) {
        _fitness = fitness;
        _AssignedFitness = true;
    }

    @Override
    public void copy(Solution solution) {
        FunctOptSolution auxSol = (FunctOptSolution) solution;
        
        for(int i = 0; i < this._size; i++){
            this._varX.set(i, auxSol._varX.get(i));
            this._varY.set(i, auxSol._varY.get(i));
            this._varZ.set(i, auxSol._varZ.get(i));
        }
        this._fitness = auxSol.getFitness();
        this._AssignedFitness = auxSol._AssignedFitness;        
    }
    /**
    * Función que indica si el fitness de la solución es válido (deja de serlo si se cambia un objeto de mochila; y se convierte en válido cuando se le asigna)
    *
    */
    boolean hasValidFitness(){
        return _AssignedFitness;
    } 
    public Integer getvalueX(int index) {
        return this._varX.get(index);
    }
    
    public Integer getvalueY(int index) {
        return this._varY.get(index);
    } 
    
    public Integer getvalueZ(int index) {
        return this._varZ.get(index);
    } 
    
    public ArrayList<Integer> getArrayX() {
        return this._varX;
    }        
    public ArrayList<Integer> getArrayY() {
        return this._varY;
    }  
    public ArrayList<Integer> getArrayZ() {
        return this._varZ;
    }  
    
    void changeElementX(int index, int value) {
        this._varX.set(index, value);
    }
    
    void changeElementY(int index, int value) {
        this._varY.set(index, value);
    }  

    void changeElementZ(int index, int value) {
        this._varZ.set(index, value);
    } 
    
    public ArrayList<Double> transformInteger(int precision){
        ArrayList<Double> array = new ArrayList<>();
        double valueX = 0., valueY = 0., valueZ = 0.;
        int max_value = 0;
        BigDecimal bd;
        for(int i = 0, j = _varX.size()-1; i < _varX.size();i++, j--){
            if(_varX.get(i) != 0)
                valueX += Math.pow(2, j);
            
            if(_varY.get(i) != 0)
                valueY += Math.pow(2, j);
            
            if(_varZ.get(i) != 0)
                valueZ += Math.pow(2,j);
            
            max_value += Math.pow(2, j);
        }
        //Se ha obtenido el valor en binario//cos(x)*e^y
        
        valueX = (valueX*this._size)/max_value; 

        bd = new BigDecimal(valueX);
        valueX = bd.setScale(precision,RoundingMode.HALF_UP).doubleValue();
        this.setBestNumberX(valueX);
        
        valueY = (valueY*this._size)/max_value;
        bd = new BigDecimal(valueY);
        valueY = bd.setScale(precision,RoundingMode.HALF_UP).doubleValue();
        this.setBestNumberY(valueY); 
 
        valueZ = (valueZ*this._size)/max_value;
        bd = new BigDecimal(valueZ);
        valueZ = bd.setScale(precision,RoundingMode.HALF_UP).doubleValue();
        this.setBestNumberZ(valueZ);
        
        array.add(valueX);
        array.add(valueY);
        array.add(valueZ);

        return array;        
    } 
}
