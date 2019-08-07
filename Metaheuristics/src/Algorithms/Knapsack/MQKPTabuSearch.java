/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author i62gorej
 */
public class MQKPTabuSearch  extends MQKPMetaheuristics{
	/**
	 * Variables miembro
	 * _solution Solución al MQKP sobre la que trabaja la búsqueda tabú
	 * _instance Instancia del problema que aborda la metaheurística
	 * _shortTermMem cola que implementa la memoria a corto plazo de la búsqueda tabú
	 * _shortTermMem_aux estructura auxiliar para hacer más eficiente el comprobar que un elemento se encuentra en la memoria tabú
	 * _tabuTennure Tenencia tabú de la metaheurística
	 */
	MQKPSolution _solution;
        MQKPSolution _BestSolution;        
	MQKPInstance _instance = new MQKPInstance();
	Queue<Integer> _shortTermMem;
	Set<Integer> _shortTermMem_aux;
	Integer _tabuTennure;

	/**
	 * vector de doubles donde almacena la calidad de la última solución aceptada
	 */
	ArrayList<Double> _results = new ArrayList<>();    
    
	/**
	 * Constructor
	 */
	public MQKPTabuSearch(){
        this._shortTermMem = new LinkedList<>();
            this._shortTermMem_aux = new HashSet<>();
            _tabuTennure = 0;
	}
    
    
    
    public void initialise(MQKPInstance instance, int tabuTennure) {
            _instance = instance;
            _tabuTennure = tabuTennure;
    }

    public void setSolution(MQKPSolution solution) {

            if (_solution != null){
                System.out.println("No se debe invocar más de una vez el método MQKPTabuSearch::setSolution");
                exit(1);
            }

            this._solution = solution;

            if (_BestSolution == null) {
                    _BestSolution = new MQKPSolution(_instance);
            }

            _BestSolution.copy(solution);
    }

        @Override
    public void run(StopCondition stopCondition) {
            if (_solution == null) {
                System.out.println("Tabu search has not been given an initial solution");
                exit(-1);
            }

            _results.clear();

            int numObjs = _instance.getNumObjs();
            int numKnapsacks = _instance.getNumKnapsacks();
            int numIterations = 0;

            while (!stopCondition.reached()) {

                    ArrayList<Integer> perm = new ArrayList<>();
                    MQKPInstance.randomPermutation(numObjs, perm);
                    double bestDeltaFitness = 0;
                    boolean initialisedDeltaFitness = false;
                    MQKPAssignmentOperation bestOperation = new MQKPAssignmentOperation();

                    //Buscar la mejor operación no tabú
                    for (int i = 0; i < numObjs; i++) {
                            int indexObj = perm.get(i);
                            //Si el objeto no es tabú (utilizar _shortTermMem_aux.find)

                            if (_shortTermMem_aux.contains(indexObj) == false) {

                                    //Probar todas las mochilas (incluida la 0) y elegir la mejor opción
                                    for (int j = 0; j <= numKnapsacks; j++) {

                                            //Saltarse el cambio que no hace nada
                                            if (_solution.whereIsObject(indexObj) == ((int)j))
                                                    continue;

                                            //Obtener la diferencia de fitness de aplicar dicha operación
                                            double deltaFitness = MQKPEvaluator.computeDeltaFitness(_instance, _solution,indexObj,j);

                                            //Si la diferencia de fitness es la mejor hasta el momento, apuntarla para aplicarla después
                                            if (deltaFitness > bestDeltaFitness
                                                            || initialisedDeltaFitness == false) {
                                                    initialisedDeltaFitness = true;
                                                    bestDeltaFitness = deltaFitness;
                                                    bestOperation.setValues(indexObj,j,bestDeltaFitness);
                                            }
                                    }
                            }
                    }
                    bestOperation.apply(_solution);
                    _shortTermMem.add(bestOperation.getObj());
                    _shortTermMem_aux.add(bestOperation.getObj());

                    if(_shortTermMem.size() > _tabuTennure) {
                            int value = _shortTermMem.peek();
                            _shortTermMem.poll();
                            _shortTermMem_aux.remove(value);
                    }

                    //Actualizar la mejor solución
                    if (MQKPEvaluator.compare(_solution.getFitness(),
                                    _BestSolution.getFitness()) > 0) {
                            _BestSolution.copy(_solution);
                    }

                    numIterations++;
                    _results.add(_solution.getFitness());

                    stopCondition.notifyIteration();
            } 
    }
	/**
	 * Función que devuelve el vector con los resultados de las soluciones aceptadas, en cada paso, por la búsqueda local
	 *
	 * @return vector con los resultados de las soluciones aceptadas, en cada paso, por la búsqueda local
	 */
	public ArrayList<Double> getResults() {
		return _results;
	}      
}
