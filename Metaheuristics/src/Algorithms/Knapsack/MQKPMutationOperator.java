/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.Solution;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author i62gorej
 */
public class MQKPMutationOperator {
        /**
	 * Variables miembro de la clase
	 * _mutProb Probabilidad de mutación
	 * _numObjs Número de objetos. Se utiliza para reducir el número de consultas a la instancia
	 * _numKnapsacks Número de mochilas del problema. Se utiliza para reducir el número de consultas a la instancia
	 */
	double _mutProb;
	int _numObjs;
	int _numKnapsakcs;

	/**
	 * Función que muta una solución
	 * @param[in,out] sol Solución a mutar
	 */
	void mutate(Solution sol){
            MQKPSolution s = (MQKPSolution) sol;
            Random r = new Random();
		//Recorrer los objetos y, según la probabilidad de mutación,
		//asignarlos a una mochila aleatoria (podrían modificarse 0, 1, o más de 1 gen)
            for(int i = 0; i < _numObjs; i++){
                if((double) r.nextInt()/(Integer.MAX_VALUE) <= _mutProb){
                    s.putObjectIn(i, r.nextInt(_numKnapsakcs + 1));
                }
            }
	}
        /**
	 * Constructor
	 * @param mutProb Probabilidad de mutación
	 * @param instance Instancia del problema a abordar
	 */
	public MQKPMutationOperator(double mutProb, MQKPInstance instance){
		_mutProb = mutProb;
		_numObjs = instance.getNumObjs();
		_numKnapsakcs = instance.getNumKnapsacks();
	} 
	/**
	 * Función que muta un conjunto de soluciones
	 * @param sols Soluciones a mutar
	 */
	public void mutate(ArrayList<Solution> sols){
            for(int i = 0; i < sols.size(); i++)                
                mutate(sols.get(i));
	}        
}
