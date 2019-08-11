/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import static java.lang.Math.pow;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author i62gorej
 * Clase que implementa un sistema de colonias de  hormigas para el MQKP.
 */
public class MQKPAntColonyOpt extends MQKPMetaheuristics{
    
    /**
     * Clase que define implementa internamente a una hormiga para el MQKP
     */    
    class MQKPAnt {
        /**
         * Variables miembro de la clase
         * Básicas:
         *  _colony puntero a la clase que la engloba (su colonia)
         *  _sol solución sobre la que trabaja la hormiga
         *
         * Adicionales
         *  _objectsLeft Conjunto de objetos que no se han insertado aún en ninguna mochila. Esta variable permite acelerar el proceso de construcción de soluciones de la hormiga. Su objetivo es mayormente hacer el proceso más eficiente
         *  _candidateListSize Número de opciones a considerar para añadir una nueva componente a la solución. Esta variable permite acelerar el proceso de construcción de soluciones, al impedir que se examinen todas las opciones posibles.
         */
        private MQKPAntColonyOpt _colony = new MQKPAntColonyOpt();
        private MQKPSolution _sol;
        private HashSet<Integer> _objectsLeft = new HashSet<>();
        private double _candidateListSize = 0.;        

        private MQKPAnt(double candidateListSize, MQKPAntColonyOpt colony) {
            _colony = colony;
            _sol = new MQKPSolution((colony._instance));
            _candidateListSize = candidateListSize;            
        }
        
        /**
         * Función que suma los valores de un ArrayList. Se usará para conocer la suma de los valores de relevancia de las opciones que tiene la hormiga
         * @param significances ArrayList con las relevancias de las opciones de la hormiga
         * @return suma de los valores del ArrayList de relevancias
         */
        private double sumSignificances(ArrayList<Double> significances) {
            //Devolver la suma de los elementos en significances
            double sum = 0;
            for (int i = 0; i < significances.size(); i++) 
                sum += significances.get(i);            
            return sum;
        }        

        /**
         * Función que devuelve un conjunto de alternativas que tiene una hormiga para añadir una nueva componente a su solución.
         * @param[out] alternatives Vector donde se almacenan las alternativas que tiene la hormiga
         * @param[out] significances Vector con los valores de relevancia de las alternativas creadas
         */
        private void createAlternatives(
                ArrayList<MQKPAssignmentOperation > alternatives,
                ArrayList<Double> significances) {

            //Obtener la información de la colonia
            MQKPInstance instance = _colony._instance;
            double alpha = _colony._alpha;
            double beta = _colony._beta;
            ArrayList<ArrayList<Double> > phMatrix = _colony._phMatrix;

            int numKnapsacks = instance.getNumKnapsacks();
            int numTries = 0;

            //Para cada objeto que aún no está en ninguna mochila
            
            for (int indexObj = 0; indexObj < _objectsLeft.size(); indexObj++) {

                //Para cada posible mochila y sin superar el número de intentos
                for (int j = 1; j <= numKnapsacks && numTries < _candidateListSize;j++) {

                    //Si el movimiento viola la capacidad de la mochila, descartarlo
                    double maxViolation = instance.getDeltaMaxCapacityViolation(_sol, indexObj, j);

                    if (maxViolation > 0)
                        continue;

                    //Obtener el deltaFitness y contarlo como un intento (como no viola, será iguala a DeltaSumProfits)
                    double deltaFitness = instance.getDeltaSumProfits(_sol, indexObj, j);
                    numTries++;

                    //Saltarse las opciones a peor o nulas (no debieran ocurrir si no hay profits negativos, "pero pa por si")
                    if (deltaFitness <= 0)
                        continue;

                    /**
                     * 1. Crear la operación
                     * 2. Calcular su relevancia como la densidad del objeto^beta * cantidadPheromona[objeto][mochila]^alpha
                     * 3. Incluir la operación en las alternatives y la relevancia en significances
                     */
                    MQKPAssignmentOperation al = new MQKPAssignmentOperation();
                    double density = deltaFitness / instance.getWeight(indexObj);
                    double significance = pow(density, beta) * pow((phMatrix.get(indexObj)).get(j), alpha);
                    al.setValues(indexObj, j, deltaFitness);
                    significances.add(significance);
                    alternatives.add(al);
                }
            }
        }        
        /**
         * Función que devuelve la mejor alternativa de la hormiga para añadir una nueva componente a su solución, de entre un conjunto de alternativas aleatorias evaluadas.
         * @param op alternativa seleccionada como la mejor para la hormiga
         */
        private void selectBestAlternative(MQKPAssignmentOperation op) {

            //Obtener la información de la colonia
            MQKPInstance instance = _colony._instance;
            ArrayList<ArrayList<Double>> phMatrix = _colony._phMatrix;
            double beta = _colony._beta;
            double alpha = _colony._alpha;

            int numKnapsacks = instance.getNumKnapsacks();
            double bestSignificance = -1;
            int numTries = 0;
            //Para cada objeto que aún no está en ninguna mochila
            for (int indexObj :_objectsLeft) {
                //Para cada posible mochila y sin superar el número de intentos
                for (int j = 1; j <= numKnapsacks && numTries < _candidateListSize; j++) {

                    //Si el movimiento viola la capacidad de la mochila, descartarlo
                    double maxViolation = instance.getDeltaMaxCapacityViolation(_sol, indexObj, j);

                    if (maxViolation > 0)
                        continue;

                    //Obtener el deltaFitness y contarlo como un intento (como no viola, será iguala a DeltaSumProfits)
                    double deltaFitness = instance.getDeltaSumProfits(_sol, indexObj, j);
                    numTries++;

                    //TODO Saltarse las opciones a peor o nulas (no debieran ocurrir si no hay profits negativos, "pero pa por si")
                    if (deltaFitness <= 0)
                        continue;

                    /**
                     * 1. Calcular su relevancia como la densidad del objeto^beta * cantidadPheromona[objeto][mochila]^alpha
                     * 2. Si es mejor que la mejor hasta ahora, guardarla en op
                     */
                    double density = deltaFitness / instance.getWeight(indexObj);
                    double significance = pow(density, beta) * pow((phMatrix.get(indexObj)).get(j), alpha);
                    if (significance > bestSignificance) {
                        op.setValues(indexObj, j, deltaFitness);
                        bestSignificance = significance;
                    }
                }
            }
        }
        /**
         * Función que libera la memoria de las alternativas creadas por la hormiga para elegir una
         * @param alt Vector con las alternativas a liberar de memoria
         */
        private void freeAlternatives(ArrayList<MQKPAssignmentOperation > alt) {
            for(int i = 0; i <alt.size(); i++)
                alt.remove(i);
            alt.clear();
        }     
        /**
         * Constructor de una hormiga
         * @param candidateListSize Número de soluciones a evaluar en cada paso
         * @param colony Puntero a la colonia a la que pertenece la hormiga
         */
        public MQKPAnt(int candidateListSize, MQKPAntColonyOpt colony) {
            this._sol = null;
            _colony = colony;
            _sol = new MQKPSolution((colony._instance));
            _candidateListSize = candidateListSize;
        }
        /**
         * Función que resetea la memoria de la hormiga para que empieze a generar una solución desde cero
         */
        public void resetSolution() {

            /**
             * 1. Asignar todos los objetos a la mochila 0 e insertarlos en la memoria _objectsLeft
             * 2. Asignarle un fitness igual a cero
             */
            MQKPInstance instance = _colony._instance;
            for (int indexObject = 0; indexObject < instance.getNumObjs(); indexObject++) {
                _sol.putObjectIn(indexObject, 0);
                _objectsLeft.add(indexObject);
            }
            _sol.setFitness(0.0);
        } 
        /**
         * Función que hace que la hormiga escoja una alternativa y la añada a su solución. También devuelve la opción escogida
         * @param[out] operation Operación de asignación de un objeto a una mochila elegida por la hormiga
         */
        public void chooseOperation(MQKPAssignmentOperation operation) {
            Random rand = new Random();
            //Decidir entre elegir la mejor altnerativa o una según probabilidades
            double randSample = (((double)rand.nextInt()) / Integer.MAX_VALUE);

            if (randSample < _colony._q0) {
                selectBestAlternative(operation);
            }
            else {

                //Crear las alternativas
                ArrayList<MQKPAssignmentOperation > alternatives = new ArrayList<>();
                ArrayList<Double> significances = new ArrayList<>();
                createAlternatives(alternatives, significances);

                //Si la hormiga no encontró alternativas, salir
                if (significances.size() <= 0) {
                    return;
                }

                //Elegir una de las alternativas según probabilidades proporcionales a sus relevancias
                double v_sumSignificances = sumSignificances(significances);
                randSample = (((double) rand.nextInt()) / Integer.MAX_VALUE)* v_sumSignificances;
                randSample -= significances.get(0);
                int opSelected = 0;

                while (randSample > 0) {
                    opSelected++;
                    randSample -= significances.get(opSelected);
                }

                //Asignar la alternativa elegida en operation
                int indexObj = alternatives.get(opSelected).getObj();
                int indexKnapsack = alternatives.get(opSelected).getKnapsack();
                double deltaFitness = alternatives.get(opSelected).getDeltaFitness();
                operation.setValues(indexObj, indexKnapsack, deltaFitness);

                //Liberar las alterantivas de memoria
                freeAlternatives(alternatives);
            }

            //TODO Si se seleccionó alguna alternativa, aplicarla a la solución y eliminar el objeto correspondiente de _objectsLeft
            if (operation.getObj() >= 0) {
                operation.apply(getSolution());
                _objectsLeft.remove(operation.getObj());
            }
        }
        /**
         * Función que devuelve la solución construída por la hormiga
         * @return Solución construída por la hormiga
         */
        public MQKPSolution getSolution() {
            return _sol;
        }          
    } 
	/**
	 * Variables miembro de la colonia de hormigas:
	 *  _q0 Probabilidad de que cada hormiga eleja la mejor alternativa en vez de una en base a probabilidades
	 *  _alpha Relevancia de la cantidad de feromona al calcular la relevancia de cada alternativa
	 *  _beta Relevancia de la información heurística al calcular la relevancia de cada alternativa
	 *  _initTau Cantidad de feromona inicial en el entorno (no interesa que sea 0)
	 *  _evaporation Porcentaje de feromona que se evapora
	 *  _phMatrix Matriz de feromona 2D. El primer índice recorre los objetos del problema. El segundo recorre las mochilas.
	 *  _ants ArrayList hormigas de la colonia
	 *  _instance Instancia del problema abordado
	 */
	double _q0;
	double _alpha;
	double _beta;
	double _initTau;
	double _evaporation;
	ArrayList<ArrayList<Double>> _phMatrix = new ArrayList<>();
	ArrayList<MQKPAnt> _ants = new ArrayList<>();
	MQKPInstance _instance;

	/**
	 * ArrayLists donde se almacenan los resultados
	 *  _results valores fitness de las soluciones generadas
	 *  _bestPerIteration Mejor fitness generado en cada iteración
	 *  _currentItMeans Media de los valores fitness de las soluciones generadas en cada iteración
	 */
	private ArrayList<Double> _results = new ArrayList<>();
	private ArrayList<Double> _bestPerIteration = new ArrayList<>();
	private ArrayList<Double> _currentItMeans = new ArrayList<>();   
	/**
	 * Función que aplica la actualización local de feromona (cuando una hormiga anda, se lleva parte de pheromona; ver fórmula en diapositivas)
	 * @param op Opción que escogió la hormiga y donde se va a aplicar la actualización
	 */
	private void localUpdate(MQKPAssignmentOperation op) {
            double oldPh = _phMatrix.get(op.getObj()).get(op.getKnapsack());
            double newPh = (1 - _evaporation)*oldPh + _evaporation*_initTau;
            _phMatrix.get(op.getObj()).set(op.getKnapsack(), newPh);		
	} 

	/**
	 * Función que libera a las hormigas para que construyan sus soluciones
	 */
	private void releaseAnts() {

		HashSet<Integer> movingAnts = new HashSet<>();
		HashSet<Integer> stoppedAnts = new HashSet<>();
		int i = 0;

		//Resetear las soluciones de cada hormiga e insertar sus índices en movingAnts
		for (MQKPAnt ant : _ants) {
			ant.resetSolution();
                        movingAnts.add(i);
			i++;
		}
		//Mientras haya hormigas que se estén moviendo
		while (movingAnts.size() > 0) {
			stoppedAnts.clear();
                    
                    //Mover cada hormiga
                    movingAnts.forEach((iAnt) -> {
                        MQKPAnt ant = _ants.get(iAnt);
                        MQKPAssignmentOperation op = new MQKPAssignmentOperation();
                        op.setValues(-1, -1, 0);
                        
                        double oldFit = ant.getSolution().getFitness();
                        ant.chooseOperation(op);
                        double newFit = ant.getSolution().getFitness();
                        
                        //Si la hormiga se ha movido, entonces aplicar la actualización local de feromona. Si no, apuntarla en stoppedAnts para eliminarla después de movingAnts
                        if (oldFit != newFit) {
                            localUpdate(op);
                        } else {
                            stoppedAnts.add(iAnt);
                        }
                    });
			for (int iAnt = 0; iAnt < stoppedAnts.size(); iAnt++) {
				movingAnts.remove(movingAnts.size()-1);
			}  
		}
               
		//Actualizar la mejor Solución
		double bestFitness = _bestSolution.getFitness();

		for (MQKPAnt ant : _ants) {
			MQKPSolution sol = ant.getSolution();
			double currentFitness = ant.getSolution().getFitness();

			if (MQKPEvaluator.compare(currentFitness, bestFitness) > 0) {
				_bestSolution = sol;
				bestFitness = currentFitness;
			}
		}

	}

	/**
	 * Función que guarda estadísticas de las soluciones generadas en sus vectores miembro
	 */
	private void saveStatistics() {

		MQKPSolution firstSol = _ants.get(0).getSolution();
		double bestFitness = firstSol.getFitness();
		double meanFitness = 0.;
		int numAnts = (int) _ants.size();
		double inverseNumAnts = 1. / numAnts;

		for (MQKPAnt ant : _ants) {
			MQKPSolution sol = ant.getSolution();
			double currentFitness = sol.getFitness();
			_results.add(currentFitness);
			meanFitness += (currentFitness * inverseNumAnts);

			if (MQKPEvaluator.compare(currentFitness, bestFitness) > 0) {
				bestFitness = currentFitness;
			}
		}

		_bestPerIteration.add(bestFitness);
		_currentItMeans.add(meanFitness);
	}

	/**
	 * Función que ejecuta una iteración del algoritmo ACO, es decir, liberar las hormigas para que construyan sus soluciones, y actualizar la matriz de pheromona
	 */
	private void iterate() {

		//Liberar las hormigas
		releaseAnts();
		saveStatistics();

		//aplicar pheromona con la mejor solución
		int numObjs = _instance.getNumObjs();
		double fitness = _bestSolution.getFitness();
		double oldPh;
		double newPh;
		//Para cada objeto, depositar feromona en el par objeto y mochila en la que está dicho objeto.
		for (int i = 0; i < numObjs; i++) {
			oldPh = _phMatrix.get(i).get(_bestSolution.whereIsObject(i));
			newPh = (1 - _evaporation)*oldPh + _evaporation*fitness;
			_phMatrix.get(i).set(_bestSolution.whereIsObject(i), newPh);
		}
	}
	/**
	 * Constructor
	 */
        public MQKPAntColonyOpt() {
		_bestSolution = null;
		_q0 = 0.8;
		_alpha = 1;
		_beta = 1;
		_initTau = 0.1;
		_evaporation = 0.1;
		_instance = null;
	}

	/**
	 * Función que inicializa el algoritmo
	 * @param numAnts Número de hormigas en la colonia
	 * @param q0 Probabilidad de que las hormigas elijan la mejor opción posible, en vez de basada en probabilidades
	 * @param alpha Relevancia de la cantidad de feromona depositada al evaluar las alternativas
	 * @param beta Relevancia de la heurística al evaluar las alternativas
	 * @param initTau Cantidad inicial de feromona en el entorno
	 * @param evaporation Ratio de evaporación de feromona
	 * @param candidateListSize Número de alternativas que cada hormiga evalúa a la hora de elegir una opción a añadir a la solución
	 * @param instance Instancia del problema que se va a abordar
	 */
	public void initialise(int numAnts, double q0, double alpha, double beta,
			double initTau, double evaporation, int candidateListSize,
			MQKPInstance instance) {
		_instance = instance;
		_q0 = q0;
		_alpha = alpha;
		_beta = beta;
		_initTau = initTau;
		_evaporation = evaporation;

		if (numAnts <= 0) {
			System.out.println("The number of ants must be greater than 0");
			exit(1);
		}
		//Generación de una solución inicial para _bestSolution
		_bestSolution = new MQKPSolution(_instance);
		MQKPSolGenerator.genRandomSol(_instance, _bestSolution);
		double fitness = MQKPEvaluator.computeFitness(_instance, _bestSolution);
		_bestSolution.setFitness(fitness);


		//Creación de las hormigas
		_ants.clear();
		for (int i = 0; i < numAnts; i++) {
			_ants.add(new MQKPAnt(candidateListSize, this));
		}


		//Inicialización de la matriz de feromona con la feromona inicial
		int numObjs = _instance.getNumObjs();
		int numKnapsacks = _instance.getNumKnapsacks() + 1;

		for (int i = 0; i < numObjs; i++) {
			ArrayList<Double> aVector = new ArrayList<>();
			_phMatrix.add(aVector);

			for (int j = 0; j < numKnapsacks; j++) {
				aVector.add(_initTau);
			}
		}
	}

	/**
	 * Función que ejecuta el algoritmo ACO
         * @param stopCondition
	 */
        @Override
	public void run(StopCondition stopCondition) {

		if (_instance == null) {
			System.out.println("The ACO algorithm has not been initialised");
			exit(1);
		}

		//Mientras no se llegue a la condición de parada, iterar
		while (stopCondition.reached() == false) {
			iterate();
			stopCondition.notifyIteration();
		}
	}

	/**
	 * Función que devuelve el vector con las mejores soluciones generadas por iteración
	 * @return Las mejores soluciones generadas en cada iteración
	 */
	public ArrayList<Double> getBestPerIteration() {
		return _bestPerIteration;
	}

	/**
	 * Función que devuelve el vector con la media de las soluciones generadas en cada iteración
	 * @return La media de las soluciones generadas en cada iteración
	 */
	public ArrayList<Double> getAntsMeanResults() {
		return _currentItMeans;
	}

	/**
	 * Función que devuelve el fitness de las soluciones generadas en cada momento
	 * @return El fitness de las soluciones generadas en cada momento
	 */
	public ArrayList<Double> getResults() {
		return _results;
	}
    
}
