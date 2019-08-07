/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author i62gorej
 */
public class MQKPIteratedGreedy extends MQKPMetaheuristics{
    	/**
	 * Variables miembro de la clase
	 * _alpha guarda la probabilidad con que los objetos se sacan de sus mochilas correspondientes.
	 * _instance tiene un puntero a la instancia del problema abordada
	 * _sol tiene la solución sobre la que trabaja Iterated Greedy durante las destrucciones y construcciones
	 */
	double _alpha;
	MQKPInstance _instance;
	MQKPSolution _sol;
	/**
	 * vector de doubles donde almacena la calidad de la última solución aceptada
	 */
	ArrayList<Double> _results = new ArrayList<>();
	/**
	 * Función que devuelve la mejor operación de asignación de un objeto sin asignar a una mochila
	 * @param[out] operation Operación de asignación de un objeto a mochila seleccionada
	 */
	void chooseOperation(MQKPAssignmentOperation operation){
            int bestObj = 0;
            int bestKnapsack = 0;
            double bestDensity = 0;
            double bestDeltaFitness = 0;
            boolean initialisedBestDensity = false;
            int numObjs = _instance.getNumObjs();
            int numKnapsacks = _instance.getNumKnapsacks();

            for (int i = 0; i < numObjs; i++) {

                    int indexObj = i;

                    if (_sol.whereIsObject(indexObj) == 0) { 

                            for (int j = 1; j <= numKnapsacks; j++) { 

                                    int indexKnapsack = j;

                                    double deltaFitness = MQKPEvaluator.computeDeltaFitness(_instance, _sol,indexObj, indexKnapsack);
                                    double density = deltaFitness / _instance.getWeight(indexObj);

                                    if (density > bestDensity || initialisedBestDensity == false) {
                                            initialisedBestDensity = true;
                                            bestDensity = density;
                                            bestObj = indexObj;
                                            bestKnapsack = indexKnapsack;
                                            bestDeltaFitness = deltaFitness;
                                    }
                            }
                    }
            }
            operation.setValues(bestObj, bestKnapsack, bestDeltaFitness);
        }

	/**
	 * Función que reconstruye la solución _sol. Para ello, invoca repetidamente a la función chooseOperation hasta que no encuentra ninguna otra operación de asignación que mejore la solución actual.
	 * Los cambios se van almacenando en la variable miembro _sol.
	 */
	void rebuild(){
            /** Seleccionar la primera operación */
            MQKPAssignmentOperation operation = new MQKPAssignmentOperation();
            chooseOperation(operation);

            while (operation.getDeltaFitness() > 0) {
                    operation.apply(_sol);
                    _results.add(_sol.getFitness());
                    chooseOperation(operation);
            }
        }

	/**
	 * Función que destruye parcialmente la solución _sol. Para ello, saca objetos de sus mochilas correspondientes con probabilidad _alpha
	 */
	void destroy(){
            int numObjs = _instance.getNumObjs();
            Random r = new Random();
            for (int i = 0; i < numObjs; i++){
                    
                    double randSample = ((double)(r.nextDouble())) / Integer.MAX_VALUE;

                    if (randSample <= _alpha){
                            _sol.putObjectIn(i, 0);
                    }
            }

            double fitness = MQKPEvaluator.computeFitness(_instance, _sol);
            _sol.setFitness(fitness);
            _results.add(_sol.getFitness());            
        }      
        
	/**
	 * Constructor
	 */
	public MQKPIteratedGreedy(){
		_alpha = 0.;
		_instance = null;
		_sol = null;
	}        
        
	/**
	 * Función que inicializa ciertos parámetros de la metaheurística.
	 * En particular, las variables miembro _sol y _bestSolution y la probabilidad alpha
	 * @param alpha Probabilidad entre 0 y 1 de sacar objetos de sus mochilas en la fase de destrucción
	 * @param instance Instancia del problema que se va a abordar
	 */
	public void initialise(double alpha, MQKPInstance instance){
            _sol = new MQKPSolution(instance);
            _bestSolution = new MQKPSolution(instance);
            _bestSolution.copy(_sol);
            _instance = instance;
            _alpha = alpha;
        }        
        
	/**
	 * Función que ejecuta la metaheurística hasta alcanzar la condición de parada
	 * @param stopCondition Condición de parada para la metaheurística
	 */
        @Override
	public void run(StopCondition stopCondition){
            if (_sol == null) {
                    System.out.println("IG was not initialised");
                    exit(-1);
            }

            /** Crear la primera solución */
            rebuild();

            if (MQKPEvaluator.compare(_sol.getFitness(), _bestSolution.getFitness()) > 0)
                    _bestSolution.copy(_sol);

            while (stopCondition.reached() == false) {
                    destroy();
                    rebuild();
                    _results.add(_sol.getFitness());


                    if (MQKPEvaluator.compare(_sol.getFitness(), _bestSolution.getFitness()) > 0)
                            _bestSolution.copy(_sol);
                    else
                            _sol.copy(_bestSolution);

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
