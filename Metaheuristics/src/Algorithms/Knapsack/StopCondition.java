/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

/**
 *
 * @author i62gorej
 */
public class StopCondition {
	/**
	 * Variables miembro de la clase
	 * _maxEvaluations unsigned Máximo número de evaluaciones de soluciones
	 * _maxIterations unsigned Máximo número de iteraciones de la metaheurística
	 * _maxTime double que indica el máximo tiempo de ejecución de la metaheurística, medido en segundos
	 * _numIterations unsigned Cuenta el número de iteraciones ejecutadas
	 * _time Objeto de la clase Timer que cuenta los segundos de ejecución de la metaheurística
	 */
	int _maxEvaluations;
	int _maxIterations;
	double _maxTime;
	int _numIterations;
        long _start;
	/**
	 * Constructor
	 */
	public StopCondition(){
		_maxEvaluations = 0;
		_maxIterations = 0;
		_maxTime = 0;
		_numIterations = 0;
                _start = 0;
	}        
        public boolean reached(){
		boolean result = false;

		if (_maxEvaluations > 0 && MQKPEvaluator.getNumEvaluations() >= _maxEvaluations)
			result = true;

		if (_maxIterations > 0 && _numIterations >= _maxIterations)
			result = true;
 		if (_maxTime > 0 && (System.currentTimeMillis()/1000 - _start) >= _maxTime)
			result = true;
		return result;            
        }
	/**
	 * Función que inicializa las variables miembro
	 * @param maxEvaluations Máximo número de evaluaciones de soluciones
	 * @param maxIterations Máximo número de iteraciones de la metaheurística
	 * @param maxTime Máximo tiempo de ejecución de la metaheurística, medido en segundos
	 */
	public void setConditions(int maxEvaluations, int maxIterations, double maxTime){
		_maxEvaluations = maxEvaluations;
		_maxIterations = maxIterations;
		_maxTime = maxTime;
		_start = System.currentTimeMillis()/1000;
	}
      	/**
	 * Función que notifica al objeto condición de parada que se ha ejecutado una nueva iteración de la metaheurística
	 */
	void notifyIteration(){
		_numIterations++;
	}  
}
