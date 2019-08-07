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
public class MQKPSimulatedAnnealing extends MQKPMetaheuristics{
	/**
	 * Variables miembro
	 * _T almacena la temperatura actual del sistema
	 * _initialProb almacena la probabilidad inicial con la que se aceptan en media los cambios a peores soluciones
	 * _annealingFactor almacena el factor con el que se enfría la temperatura
	 * _solution Solución al MQKP sobre la que trabaja el Enfriamiento Simulado
	 * _instance Instancia del problema que se está abordando
	 */
	double _T;
	double _initialProb;
	double _annealingFactor;
	double _itsPerAnnealing;
	MQKPSolution _solution;
        MQKPSolution _bestSolution;
	MQKPInstance _instance = new MQKPInstance();
        Random r = new Random();
	/**
	 * vector de doubles donde almacena la calidad de la última solución aceptada
	 */
	ArrayList<Double> _results = new ArrayList<>();
	/**
	 * Función que indica si se acepta la nueva solución según su diferencia en fitness con respecto a la actual
	 * @param deltaFitness diferencia en fitnees de la nueva solución con respecto a la actual
	 * @return true si se acepta la nueva solución, false en otro caso
	 */
	boolean accept(double deltaFitness){
            double auxDeltaFitness = deltaFitness;

            if (MQKPEvaluator.isToBeMinimised())
                    auxDeltaFitness = -auxDeltaFitness;
            

            /*
                    A mayor temperatura, mayor probabilidad de aceptación de soluciones peores
                    A menor diferencia de fitness, mayor probabilidad de aceptación de soluciones peores
            */
            double prob = Math.exp(auxDeltaFitness/_T);
            
            double randSample = ((r.nextDouble()) / Integer.MAX_VALUE);
            return (randSample < prob);
        }
        
	/**
	 * Constructor
	 */
	public MQKPSimulatedAnnealing(){
		_T = 0;
		_initialProb = 0;
		_annealingFactor = 0;
		_itsPerAnnealing = 0;
	} 
	/**
	 * Función que ejecuta la metaheurística hasta alcanzar la condición de parada
	 * @param stopCondition Condición de parada para la metaheurística
	 */
        @Override
	public void run(StopCondition stopCondition){
            if (_T <= 0 || _annealingFactor <= 0){
                    System.out.println("Simulated annealing has not been initialised");
                    exit(-1);
            }

            if (_solution == null){
                System.out.println("Simulated annealing has not been given an initial solution");
                    exit(-1);
            }

            _results.clear();
            int numObjs = _instance.getNumObjs();
            int numKnapsacks = _instance.getNumKnapsacks();
            int numIterations = 0;
            
            while (!stopCondition.reached()){
                    int indexObject = r.nextInt(numObjs);
                    int indexKnapsack = r.nextInt(numKnapsacks+1);
                    double deltaFitness = MQKPEvaluator.computeDeltaFitness(_instance, _solution, indexObject, indexKnapsack);

                    if (this.accept(deltaFitness)){
                            _solution.putObjectIn(indexObject, indexKnapsack);
                            _solution.setFitness(_solution.getFitness() + deltaFitness);
                            if (MQKPEvaluator.compare(_solution.getFitness(), _bestSolution.getFitness()) > 0){
                                    _bestSolution.copy(_solution);
                            }
                    }
                    numIterations++;
                    _results.add(_solution.getFitness());

                    if (numIterations % _itsPerAnnealing == 0){
                            _T *= _annealingFactor;
                    }

                    stopCondition.notifyIteration();
            }        
        }      
        
	/**
	 * Función que asigna la solución inicial para la metaheurística
	 * @param solution Solución inicial a partir de la cual aplicar el enfriamiento simulado
	 */
	public void setSolution(MQKPSolution solution){
            if (_T <= 0 || _annealingFactor <= 0){
                    System.out.println("Simulated annealing has not been initialised");
                    exit(-1);
            }

            if (_solution != null){
                    System.out.println("No se debe invocar más de una vez el método MQKPSimAnn::setSolution");
                    exit(1);
            }

            this._solution = solution;

            if (_bestSolution == null){
                    _bestSolution = new MQKPSolution(_instance);
            }

            _bestSolution.copy(solution);        
        }

	/**
	 * Función que inicializa ciertos parámetros de la metaheurística, en particular, se calcula la temperatura inicial del sistema según la probabilidad con la que se desean aceptar los primeros cambios a peores soluciones
	 * @param initialProb Probabilidad con la que se desean aceptar en media los primeros cambios a peor
	 * @param numInitialEstimates Número de soluciones iniciales sobre las que se estima la temperatura necesaria para aceptar cambios a peores soluciones con la probabilidad indicada
	 * @param annealingFactor Factor con el cual se enfría la temperatura
         * @param itsPerAnnealing
	 * @param instance Instancia del problema que se va a abordar
	 */
	public void initialise(double initialProb, int numInitialEstimates, double annealingFactor, double itsPerAnnealing, MQKPInstance instance){
            _initialProb = initialProb;
            _annealingFactor = annealingFactor;
            _instance = instance;
            _itsPerAnnealing = itsPerAnnealing;
            int numObjs = instance.getNumObjs();
            int numKnapsacks = instance.getNumKnapsacks();
            double averageFDiffs = 0.;

            /**
             * Inicialización de la temperatura.
             * Para ello, se generan una serie de soluciones iniciales y de vecinos. Se calcula la diferencia media de fitness hacia peores soluciones y se despeja la temperatura de la función de aceptación
             */

            for (int i = 0; i < numInitialEstimates; i++){
                    MQKPSolution sol = new MQKPSolution(instance);
                    MQKPSolGenerator.genRandomSol(instance, sol);
                    sol.setFitness(MQKPEvaluator.computeFitness(instance, sol));
                    int indexObject = r.nextInt(numObjs);
                    int indexKnapsack = r.nextInt(numKnapsacks + 1);
                    double deltaFitness = MQKPEvaluator.computeDeltaFitness(instance, sol, indexObject, indexKnapsack);
                    averageFDiffs += Math.max(Math.abs(deltaFitness),10.); //He puesto una diferencia mínima de 10 para evitar cambios en el fitness demasiado pequeños (por ejemplo, cuando se modifica una mochila que no es la de la máxima violación (este método se podría mejorar)
            }

            averageFDiffs /= numInitialEstimates;

            _T = -1. * averageFDiffs / Math.log(initialProb);        
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
