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
public class MQKPCrossoverOperator {

/**
	 * Variables miembro de la clase:
	 *  _instance Instancia de problema abordada. Se utiliza únicamente para crear nuevos objetos MQKPSolution
	 *  _numObjs almacena el número de objetos de la instancia abordada para reducir el número de consultas a la instancia
	 *  _crossProb probabilidad de cruce
	 */
	MQKPInstance _instance;
	int _numObjs;
	double _crossProb;

	/**
	 * Función que cruza dos soluciones según su probabilidad de cruce. En caso de que no haya crucce, la solución devuelta será igual al primer padre
	 * @param s1 primer padre
	 * @param s2 segundo padre
	 * @return Nuevo objeto solución descendiente de haber cruzado s1 y s2. La solución se reserva dinámicamente en memoria. Es responsabilidad del invocador de gestionarla correctamente.
	 */
	MQKPSolution cross(Solution s1, Solution s2) {
		MQKPSolution sol = new MQKPSolution(_instance);
		MQKPSolution  sol1 = (MQKPSolution ) s1;
		MQKPSolution  sol2 = (MQKPSolution ) s2;
                Random r = new Random();
		double randSample = (((double) r.nextDouble()) / Integer.MAX_VALUE);

		if (randSample < _crossProb) {

			//Cruce uniforme de los dos padres,
			//que va eligiendo el valor de uno de los padres aleatoriamente,
			//con la misma probabilidad, para cada gen
			for(int i = 0; i < _numObjs; i++){
				if((((double) r.nextDouble()) / Integer.MAX_VALUE) < 0.5){
					sol.putObjectIn(i, sol1.whereIsObject(i));
				}
				else{
					sol.putObjectIn(i, sol2.whereIsObject(i));
				}
			}
		} else {
			//Si no hay cruce, copiar el primer padre
			sol.copy(sol1);
		}

		return sol;
	}

	/**
	 * Constructor
	 * @param crossProb Probabilidad de cruce
	 * @param instance Instancia del problema abordada
	 */
	MQKPCrossoverOperator(double crossProb, MQKPInstance instance) {
		_instance = instance;
		_numObjs = instance.getNumObjs();
		_crossProb = crossProb;
	}

	/**
	 * Función que aplica el cruce a una población de padres.
	 * @param parents Vector de padres. El cruce se aplica entre cada dos padres consecutivos (1,2), (3,4)...
	 * @param[out] offspring Vector donde se almacenan los descendientes generados. IMPORTANTE: Esta función reserva memoria dinámicamente para las nuevas soluciones en offspring, por lo que es responsabilidad de quien la invoque de gestionar la memoria adecuadamente.
	 */
	void cross(ArrayList<Solution> parents, ArrayList<Solution> offspring) {
            //Se aplicará cruce entre cada dos padres consecutivos (1,2), (3,4), ...
            offspring.clear();
	
            for (int i = 0; i < parents.size(); i = i + 2) {
                MQKPSolution sol = cross(parents.get(i), parents.get(i+1));
                offspring.add(sol);
            }
	}
    
}
