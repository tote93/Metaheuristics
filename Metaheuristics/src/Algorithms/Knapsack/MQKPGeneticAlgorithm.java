/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.SelectionOperator;
import BaseClasses.Solution;
import static java.lang.System.exit;
import java.util.ArrayList;

/**
 *
 * @author i62gorej
 */
public class MQKPGeneticAlgorithm extends MQKPMetaheuristics{
    
	/**
	 * Variables miembro de la clase:
	 *  _popSize Tamaño de la población
	 *  _population Conjunto de individuos en la población
	 *  _selector Operador de selección
	 *  _crossoverOp Operador de cruce
	 *  _mutOp Operador de mutación
	 *  _instancia Instancia del problema abordada
	 */
	int _popSize;
	ArrayList<Solution> _population = new ArrayList<>();
	SelectionOperator _selector = null;
	MQKPCrossoverOperator _crossoverOp = null;
	MQKPMutationOperator _mutOp = null;
	MQKPInstance _instance = null;

	/**
	 * ArrayListes donde se almacenan los resultados
	 *  _results valores fitness de las soluciones generadas
	 *  _popMeanResults Media de los valores fitness presentes en la población
	 *  _offMeanResults Media de los valores fitness de las nuevas soluciones generadas
	 *  _bestPerIterations Mejor valor en cada iteración
	 */
	ArrayList<Double> _results = new ArrayList<>();;
	ArrayList<Double> _popMeanResults = new ArrayList<>();;
	ArrayList<Double> _offMeanResults = new ArrayList<>();;
	ArrayList<Double> _bestPerIterations = new ArrayList<>();;

	/**
	 * Función que busca el índice de la mejor solución en un ArrayList
	 * @param[in] set Vector de soluciones
	 * @return índice de la mejor solución
	 */
	int indexBest(ArrayList<Solution> set) {

		//Buscar el índice de la mejor solución en set
		int indexBest = 0;

		for(int i=1; i < set.size(); i++){
			if(MQKPEvaluator.compare(set.get(i).getFitness(), set.get(indexBest).getFitness()) > 0)
				indexBest = i;
		}


		return indexBest;
	}

	/**
	 * Función que busca el índice de la peor solución en un ArrayList
	 * @param[in] set Vector de soluciones
	 * @return índice de la peor solución
	 */
	int indexWorst(ArrayList<Solution> set) {

		//Buscar el índice de la peor solución en set
		int indexWorst = 0;

		for(int i=1; i < set.size(); i++){
			if(MQKPEvaluator.compare(set.get(i).getFitness(), set.get(indexWorst).getFitness()) < 0)
				indexWorst = i;
		}

		return indexWorst;
	}

	/**
	 * Función que actualiza la nueva población, dado el conjunto de descendientes generado
	 * @param[in] offspring Vector de soluciones descendientes generadas
	 */
	void selectNewPopulation(ArrayList<Solution> offspring) {

		/**
		 * La nueva población será la de descendientes, pero
		 * en caso de que la población actual tenga una mejor solución que la mejor en offspring,
		 * la mejor de la población actual reemplazará a la peor de offspring.
		 *
		 * 1. Encontrar el índice de la mejor solución en _population
		 * 2. Encontrar el índice de la mejor solución en offspring
		 * 3. Si la mejor solución de _population es mejor que la mejor solución de offspring
		 *   a. Encontrar el índice de la peor solución en offspring
		 *   b. Hacer que dicha solución copie a la mejor de _population
		 * 4. Eliminar los individuos de la población actual (liberando memoria)
		 * 5. Almacenar los individuos de offspring en la población actual
		 */
		int indexBestPop = indexBest(_population);
		int indexBestOff = indexBest(offspring);

		if(MQKPEvaluator.compare(_population.get(indexBestPop).getFitness(), offspring.get(indexBestOff).getFitness()) > 0){
			int indexWorstOff = indexWorst(offspring);
                        Solution sol =  _population.get(indexBestPop);
                        offspring.set(indexWorstOff, sol);
		}

		//Eliminar los individuos de la población actual
		for (int i = 0; i < _popSize; i++) {
                    _population.remove(_population.size()-1);
		}

		//Copiar los hijos en la población actual
		int offSize = (int) offspring.size();

		for (int i = 0; i < offSize; i++) {
                    _population.add(offspring.get(offspring.size()-1));
                    offspring.remove(offspring.size()-1);
		}
	}

	/**
	 * Función que evalúa las soluciones de un ArrayList
	 * @param[in,out] set Conjunto de soluciones a evaluar. Una vez evaluados, les asigna el fitness
	 */
	void evaluate(ArrayList<Solution> set) {

		for (Solution sol:  set) {
			MQKPSolution s = (MQKPSolution) sol;

			/**
			 * Se ha añadido una funcionalidad en Solution para detectar si su fitness ya estaba calculado,
			 * útil para cuando el descendiente es copia del padre. Por tanto, sólo se evaluarán las soluciones
			 * que no tentan un fitness válido
			 */
			if (!(s.hasValidFitness())) {

				//Evaluar
				double fitness = MQKPEvaluator.computeFitness((this . _instance), s);
                                _results.add(fitness);
				s.setFitness(fitness);

				//Actualizar la mejor solución
				if (MQKPEvaluator.compare(fitness, _bestSolution.getFitness()) > 0)
					_bestSolution.copy(s);				
			}
		}
	}

	/**
	 * Función que inicializa la población del genético
	 * @param[in] popSize Tamaño de la población
	 */
	void initPopulation(int popSize) {

		if (_instance == null) {
                    System.out.println("The evolutionary algorithm has not been initialised. At least, its _instance is null");
                    exit(1);
		}

		/**
		 * 1. Generar soluciones aleatorias
		 * 2. Evaluarlas
		 * 3. Actualizar la mejor solución _bestSolution
		 * 4. Insertarlas en la población
		 */
		MQKPSolution  sol;
		double fitness;
		boolean firstIt = true;

		for (int i = 0; i < popSize; i++) {
			//Generar solucion aleatoria
			sol = new MQKPSolution((this._instance));
			MQKPSolGenerator.genRandomSol((this._instance), sol);

			//Evaluar solucion
			fitness = MQKPEvaluator.computeFitness((this._instance), sol);
			sol.setFitness(fitness);

			//Actualizar _bestSolution
			if (firstIt || MQKPEvaluator.compare(fitness, _bestSolution.getFitness()) > 0) {
				firstIt = false;
				_bestSolution.copy(sol);
			}

			//Insertar solucion en la poblacion
                        _results.add(fitness);
                        _population.add(sol);
		}
	}

	/**
	 * Función que calcula la media del fitness de un conjunto de soluciones
	 * @param[int] set Conjunto de soluciones del que obtener la media del fitness
	 * @return media del fitness de las soluciones
	 */
	double computeMeanFitness(ArrayList<Solution>set) {
		double mean = 0.;
		int numElements = (int) set.size();
		double i_numElements = 1. / numElements;

                mean = set.stream().map((sol) -> sol.getFitness()).map((fitness) -> (fitness * i_numElements)).reduce(mean, (accumulator, _item) -> accumulator + _item);

		return mean;
	}

	/**
	 * Constructor
	 */
	public MQKPGeneticAlgorithm() {
		_popSize = 0;
		_bestSolution = null;
	}

	/**
	 * Función que ejecuta el algoritmo genético
	 * @param stopCondition Objeto que define cuándo se llega a la condición de parada
	 */
        @Override
	public void run(StopCondition stopCondition) {

		/**
		 * 1. Inicializar la poblacion
		 * 2. Mientras no se alcance la condición de parada
		 *   a. Almacenar la media de la población actual y la mejor solución
		 *   b. Seleccionar los padres
		 *   c. Cruzar los padres
		 *   d. Mutar los descendientes
		 *   f. Almacenar la media de los descendientes
		 *   g. Seleccionar la nueva población
		 *
		 * 3. Almacenar la media de la poblaciónfinal y la mejor solución
		 */
		int bestSolIndex;

		//Inicializar la poblacion
		this.initPopulation(this._popSize);

		while (stopCondition.reached() == false) {

			//Almacenar la media de la población actual y la mejor solución
                        _popMeanResults.add(computeMeanFitness(_population));

			bestSolIndex = indexBest(_population);
			_bestPerIterations.add(
					_population.get(bestSolIndex).getFitness());
			if(MQKPEvaluator.compare(_bestPerIterations.get(_bestPerIterations.size()-1), _bestSolution.getFitness()) > 0)
				_bestSolution.copy(_population.get(bestSolIndex));

			//Seleccionar los padres
			ArrayList<Solution> parents = new ArrayList<>();
			_selector.select(_population, parents);

			//Cruzar los padres
			ArrayList<Solution> offspring = new ArrayList<>();
			_crossoverOp.cross(parents, offspring);

			//Mutar los descendientes
			_mutOp.mutate(offspring);

			//Evaluar los descendientes
			this.evaluate(offspring);

			//Almacenar la media de los descendientes
			_offMeanResults.add(computeMeanFitness(offspring));

			//Seleccionar la nueva población
			selectNewPopulation(offspring);

			stopCondition.notifyIteration();
		}

		//Almacenar la media de la población actual y la mejor solución (NEW)
		_popMeanResults.add(computeMeanFitness(_population));

		bestSolIndex = indexBest(_population);
		_bestPerIterations.add(
				_population.get(bestSolIndex).getFitness());
		if(MQKPEvaluator.compare(_bestPerIterations.get(_bestPerIterations.size()-1), _bestSolution.getFitness()) > 0)
			_bestSolution.copy(_population.get(bestSolIndex));
	}

	/**
	 * Función que inicializa el algoritmo
	 * @param popSize Tamaño de la población
	 * @param instance Instancia del problema a abordar
	 */
	public void initialise(int popSize, MQKPInstance instance) {
		_instance = instance;

		if (popSize <= 0) {
			System.out.println("The population size must be greater than 0");
			exit(1);
		}

		if (_bestSolution != null) {
			_bestSolution = null;
		}

		_bestSolution = new MQKPSolution(_instance);
		MQKPSolGenerator.genRandomSol(_instance, _bestSolution);
		double fitness = MQKPEvaluator.computeFitness(_instance, _bestSolution);
		_bestSolution.setFitness(fitness);

		_popSize = popSize;

		/**
		 * Se está configurando para que utilice torneo binario, los operadores genéticos implementados y dichas probabilidades,
		 * pero se podrían usar otros operadores simplemente cambiando el objeto.
		 */
		if (_crossoverOp == null) {
			_crossoverOp = new MQKPCrossoverOperator(0.8, _instance);
		}

		if (_mutOp == null) {
			_mutOp = new MQKPMutationOperator((0.25 / _instance.getNumObjs()),_instance);
		}

		if (_selector == null) {
			_selector = new TournamentSelector(2); //Se puede probar con varios valores de presión selectiva
		}
	}

	/**
	 * Función que devuelve el ArrayList de fitness de las soluciones generadas
         * @return Results Array
	 */
	public ArrayList<Double> getResults() {
		return _results;
	}

	/**
	 * Función que asigna un nuevo operador de cruce
	 */
	public void setCrossoverOp(MQKPCrossoverOperator crossoverOp) {

		//if (_crossoverOp != null)
			//_crossoverOp = new MQKPCrossoverOperator();

		_crossoverOp = crossoverOp;
	}

	/**
	 * Función que asigna un nuevo operador de mutación
	 */
	void setMutOp(MQKPMutationOperator mutOp) {

/*		if (_mutOp != null)
			delete _mutOp;*/
		_mutOp = mutOp;
	}

	/**
	 * Función que asigna un nuevo operador de selección
	 */
	void setSelector(SelectionOperator selector) {
/*
		if (_selector != null)
			delete _selector;*/

		_selector = selector;
	}

	/**
	 * Función que devuelve el ArrayList con la media de las poblaciones de descendientes generadas en cada iteración
         * @return 
	 */
	public ArrayList<Double> getOffMeanResults() {
		return _offMeanResults;
	}

	/**
	 * Función que devuelve el ArrayList con la media de la población actual en cada iteración
         * @return 
	 */
	public ArrayList<Double> getPopMeanResults() {
		return _popMeanResults;
	}

	/**
	 * Función que devuelve la mejor solución de la población en cada iteración
         * @return 
	 */
	public ArrayList<Double> getBestsPerIterations() {
		return _bestPerIterations;
	}    
    
    
}
