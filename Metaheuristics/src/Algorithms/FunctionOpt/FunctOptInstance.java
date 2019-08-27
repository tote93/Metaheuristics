/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FunctionOpt;

import BaseClasses.Instance;
import java.util.ArrayList;
import java.util.Random;



/**
 * Clase que almacena la información de una instancia del problema FunctOpt
 * @author i62gorej
 */
public class FunctOptInstance extends Instance{
    int _range;
    int _precision;
    int _size;
    String _function = "";
    String _functType = "";

    /**
    * Constructor por defecto
    */
    public FunctOptInstance() {
        this._range = 0;
        this._precision = 0;
        this._size = 0;
        this._functType = "";
    }
    /**
     * Funciones Getter que devuelven la información de la instancia
     * @return 
     */
    public int getRange() {
        return this._range;
    }
    
    public String getFuncType() {
        return this._functType;
    }
    
    public int getPrecision() {
        return this._precision;
    }
    
    public String getFunction(){
        return _function;
    }
    
    public int getSize() {
        return this._size;
    }    
    /**
     * Funciones Sets que establecen valores a la instancia 
     */
    
    public void setSize() {
        int value = (int) (this.getRange()*(Math.pow(10, this.getPrecision()))); 
        int log = (int) (Math.log(value) / Math.log(2)); 
        if(value > Math.pow(2, log)){
            log++;
        }
        this._size = log;
    }    
    
    public void setRange(int R) {
        this._range = R;
    }
    
    public void setFuncType(String FT) {
        this._functType = FT;
    }   
    
    public void setFunction(String F) {
        this._function = F;
    }
    public void setPrecision(int prec) {
        this._precision = prec;
    }
    
    /**
    * Función que genera una permutación de los enteros de 0 a (size - 1)
    * @param size Tamaño de la permutación
    * @param perm Vector donde se almacenará la permutación
    */
    public static void randomPermutation(int size, ArrayList<Integer> perm) {
        /**
         * 1. Vacía el vector perm
         * 2. Llénalo con la permutación identidad
         * 3. Recórrelo intercambiando cada elemento con otro escogido de forma aleatoria.
         */
        for (int i = 0; i < size; i++)
            perm.add(i);
        
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int pos = rand.nextInt(2);
            int aux = perm.get(i);
            perm.set(i, perm.get(pos));
            perm.set(pos, aux);
        }
    }          
}
