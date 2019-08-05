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
public class MQKPGrasp extends MQKPMetaheuristics{
	/**
	 * Variables miembro de la clase
	 * _alpha será un valor en (0,1), que indica el porcentaje de alternativas a evaluar para seleccionar la mejor opción para añadir a la solución que se está construyendo
	 * _instance es un puntero a la instancia del problema
	 * _sol almacena la solución sobre la que trabaja GRASP, durante las construcciones y la aplicación de la búsqueda local
	 * _ls es el método de búsqueda local interno para refinar la solucioes que genera
	 * _no es el operador de vecindario que utiliza la búsqueda local interna
	 */
	double _alpha;
	MQKPInstance _instance;
	MQKPSolution _sol;
	MQKPLocalSearch _ls = new MQKPLocalSearch();
	MQKPSimpleFirstImprovement _no = new MQKPSimpleFirstImprovement();   
        MQKPSolution _bestSolution;
	/**
	 * vector de doubles donde almacena la calidad de la última solución aceptada
	 */
	ArrayList<Double> _results =  new ArrayList<>();   
        
 	/**
	 * Función que devuelve la mejor operación de asignación de un objeto sin asignar a una mochila de entre una serie de alternativas seleccionadas de forma aleatoria
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

            /**
             * Calcular el número de intentos como el porcentaje _alpha por el número de posibilidades, que es el número de objetos por el número de mochilas.
             *
             * En este paso no se considerará que ya haya objetos asignados a alguna mochila, la mochila 0 que representa objetos sin asignar a ninguna mochila, ni que haya mochilas ya completamente llenas
             */
            double numTries = ((double)(numObjs * numKnapsacks * _alpha));

            for (int i = 0; i < numTries; i++) {
                Random r = new Random();
                    int indexObj = r.nextInt(numObjs);
                    int indexKnapsack = r.nextInt(numKnapsacks)+1; //(rand()%numKnapsacks)+1;

                    double deltaFitness = MQKPEvaluator.computeDeltaFitness(_instance, _sol, indexObj, indexKnapsack); 
                    double density = deltaFitness/_instance.getWeight(indexObj);

                    if (density > bestDensity || initialisedBestDensity == false) {
                            initialisedBestDensity = true;
                            bestDensity = density;
                            bestObj = indexObj;
                            bestKnapsack = indexKnapsack;
                            bestDeltaFitness = deltaFitness;
                    }
            }

            operation.setValues(bestObj, bestKnapsack,
                            bestDeltaFitness);        
        }    
	/**
	 * Función que crea una solución desde la mochila vacía. Para ello, invoca repetidamente a la función chooseOperation hasta que llega un momento en el que no encuentra ninguna otra operación de asignación que mejore la solución actual (de entre las seleccionadas aleatoriamente).
	 * Los cambios se van almacenando en la variable miembro _sol.
	 */
	void buildInitialSolution(){
            int numObjs = _instance.getNumObjs();

            _sol.setFitness(0);
            for (int i = 0; i < numObjs; i++) {
                    _sol.putObjectIn(i, 0);
            }
            
            /** Seleccionar la primera operación */
            MQKPAssignmentOperation operation = new MQKPAssignmentOperation();
            chooseOperation(operation);
            
            while (operation.getDeltaFitness() > 0) {
                    operation.apply(_sol);
                    this._results.add(_sol.getFitness());                   
                    chooseOperation(operation);
            }        
        } 

	/**
	 * Constructor
	 */
	public MQKPGrasp(){
		_sol = null;
		_instance = null;
		_alpha = 0;
	}  
	/**
	 * Función que inicializa ciertos parámetros de la metaheurística.
	 * En particular, las variables miembro _sol y _bestSolution y el porcentaje alpha
	 * @param alpha Porcentaje entre 0 y 1 del número de alternativas aleatorias a evaluar cada vez que escoge la siguiente operación de asignación de un objeto a una mochila para aplicar sobre _sol
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
                    System.out.println("GRASP was not initialised");
                    exit(-1);
            }

            while (!stopCondition.reached()) {
                    buildInitialSolution();
                    
                    _results.add(_sol.getFitness());
                    
                    _ls.optimise(_instance,_no,_sol);
                    
                    ArrayList<Double> auxResults = _ls.getResults();

                    for(int i = 0; i < auxResults.size(); i++)
                        _results.add(auxResults.get(i));

                    if (MQKPEvaluator.compare(_sol.getFitness(), _bestSolution.getFitness()) > 0)
                            _bestSolution.copy(_sol);

                    stopCondition.notifyIteration();
            }        
        }      

	/**
	 * Función que devuelve el vector con los resultados de las soluciones aceptadas, en cada paso, por la búsqueda local
	 * @return vector con los resultados de las soluciones aceptadas, en cada paso, por la búsqueda local
	 */
	public ArrayList<Double> getResults() {
		return _results;
	}





        
}
