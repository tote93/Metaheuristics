
package BaseClasses;

import Algorithms.FunctionOpt.FunctOptEvaluator;
import Algorithms.FunctionOpt.FunctOptInstance;
import Algorithms.FunctionOpt.FunctOptLocalSearch;
import Algorithms.FunctionOpt.FunctOptNeighExplorer;
import Algorithms.FunctionOpt.FunctOptSimpleBestImprovement;
import Algorithms.FunctionOpt.FunctOptSimpleFirstImprovement;
import Algorithms.FunctionOpt.FunctOptSolGenerator;
import Algorithms.FunctionOpt.FunctOptSolution;
import Algorithms.FunctionOpt.genFunction;
import Algorithms.Knapsack.MQKPAntColonyOpt;
import Algorithms.Knapsack.MQKPNeighExplorer;
import Algorithms.Knapsack.MQKPEvaluator;
import Algorithms.Knapsack.MQKPGeneticAlgorithm;
import Algorithms.Knapsack.MQKPGrasp;
import Algorithms.Knapsack.MQKPInstance;
import Algorithms.Knapsack.MQKPIteratedGreedy;
import Algorithms.Knapsack.MQKPLocalSearch;
import Algorithms.Knapsack.MQKPSimpleBestImprovement;
import Algorithms.Knapsack.MQKPSimpleFirstImprovement;
import Algorithms.Knapsack.MQKPSimulatedAnnealing;
import Algorithms.Knapsack.MQKPSolGenerator;
import Algorithms.Knapsack.MQKPSolution;
import Algorithms.Knapsack.MQKPTabuSearch;
import Algorithms.Knapsack.StopCondition;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.data.xyz.XYZSeries;
import com.orsoncharts.data.xyz.XYZSeriesCollection;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author i62gorej
 */
public class mainClass {
	// Algoritmo seleccionado
	String _algorithm = null;
	// Heuristica seleccionada
	String _heuristic = null;
	// JFrame usado para generar un dialogo
	JFrame _frame = new JFrame();
	JDialog _dialog = new JDialog(_frame , "", true);
	// Variable necesaria para obtener las mochilas
	private int _numKnapSack = 0;
        private double _mutatePercent = 0.25;
        private double _crossPercent = 0.8;
        private int _numberTournament = 2;
        private String _selector = "";
        private String _mutate = "";
	// Parametros de parada de las heuristicas
	private int MAX_SOLUTIONS_PER_RUN = 100000;
	private int MAX_INITIAL_SOLUTIONS = 5;
	private int MAX_SECONS_PER_RUN = 5;
        private String function = "";
        private int range = 0, precision = 0;        

        ArrayList<Double> ejesCoordenadas = new ArrayList<>(); 
        private String _funcType;
	/**
	 * Constructor por defecto
	 */
	public mainClass() {}
	/**
	 *
	 * @param Al Algoritmo seleccionado por el usuario
	 * @param hc Heuristica seleccionada por el usuario
	 */
	public mainClass(String Al, String hc) {
		this._algorithm = Al;
		this._heuristic = hc;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		_dialog.setLocation(dim.width / 2 - _dialog.getSize().width / 2, dim.height / 2 - _dialog.getSize().height / 2);
		_dialog.setLayout(new GridLayout(3, 2));
	}
	/**
	 * Inicializa los parametros, instancias, etc y ejecuta el algoritmo
	 * @return XYSeriesCollection Conjunto de Series XY para hacer el display
	 */
	public XYSeriesCollection initialise()  {
		//Selector de algoritmo y _heuristica usada
		XYSeriesCollection dataset = new XYSeriesCollection();
                ArrayList<Double> results = new ArrayList<>();    
		switch (_algorithm) {
		case "Algoritmo de la mochila":
			switch (_heuristic) {
			case "LocalSearch":
				generateOptionsKnapsackProblem();
				generateDialog(120, 350,"Parámetros Knapsack Problem");
				//Fin de la generación del modal
				MQKPInstance climb = new MQKPInstance();
				try {
					climb.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", this._numKnapSack);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}
                                                               
				MQKPSimpleFirstImprovement firstExplorer = new MQKPSimpleFirstImprovement();
                                XYSeries serieFirst = new XYSeries("Escalada Simple");
                                serieFirst.setDescription("Escalada Simple");
				this.runALSExperiment(results, climb, firstExplorer, serieFirst);
                                dataset.addSeries(serieFirst);
                                
                                XYSeries serieBest = new XYSeries("Escalada Max. Pendiente");
                                serieBest.setDescription("Escalada Max. Pendiente");
                                MQKPSimpleBestImprovement  bestExplorer = new MQKPSimpleBestImprovement();
                                this.runALSExperiment(results, climb, bestExplorer, serieBest);
                                dataset.addSeries(serieBest); 
				break;
                        case "Grasp":
				generateOptionsKnapsackProblem();
				generateDialog(120, 350,"Parámetros Knapsack");
				MQKPInstance Grasp = new MQKPInstance();
				try {
					Grasp.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", this._numKnapSack);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}				                                
                                XYSeries serieGrasp = new XYSeries("Grasp");
                                serieGrasp.setDescription("Grasp");
                                XYSeries BestGrasp = new XYSeries("BestGrasp");
                                BestGrasp.setDescription("BestGrasp");                                
                                this.runAGraspExperiment(results, Grasp,serieGrasp, BestGrasp);
                                dataset.addSeries(serieGrasp); 
                                dataset.addSeries(BestGrasp);
                                                                
                            break;
                        case "IteratedGreedy":
				generateOptionsKnapsackProblem();
				generateDialog(120, 350,"Parámetros Knapsack");
				MQKPInstance IG = new MQKPInstance();
				try {
					IG.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", this._numKnapSack);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}				                                
                                XYSeries serieIG = new XYSeries("IG");
                                serieIG.setDescription("IG");
                                XYSeries BestIG = new XYSeries("BestIG");
                                BestIG.setDescription("BestIG");                                    
                                this.runIGreedyExperiment(results, IG,serieIG, BestIG);
                                dataset.addSeries(serieIG); 
                                dataset.addSeries(BestIG); 
                            break;
                       case "SimulatedAnnealing":
				generateOptionsKnapsackProblem();
				generateDialog(120, 350,"Parámetros Knapsack");
				MQKPInstance SimulatedAnnealing = new MQKPInstance();
				try {
					SimulatedAnnealing.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", this._numKnapSack);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}				                                
                                XYSeries serieSimulatedAnnealing = new XYSeries("SA");
                                serieSimulatedAnnealing.setDescription("SA");                            
                                this.runSAExperiment(results, SimulatedAnnealing, serieSimulatedAnnealing);
                                dataset.addSeries(serieSimulatedAnnealing); 
                              
                            break;        
                       case "TabuSearch":
				generateOptionsKnapsackProblem();
				generateDialog(120, 350,"Parámetros Knapsack");
				MQKPInstance TabuSearch = new MQKPInstance();
				try {
					TabuSearch.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", this._numKnapSack);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}				                                
                                XYSeries serieTabuSearch = new XYSeries("TS");
                                serieTabuSearch.setDescription("TS");                            
                                this.runTSExperiment(results, TabuSearch, serieTabuSearch);
                                dataset.addSeries(serieTabuSearch); 
                            break;       
                       case "AllTrayectories":
				generateOptionsKnapsackProblem();
				generateDialog(120, 350,"Parámetros Knapsack");
				MQKPInstance instance = new MQKPInstance();
				try {
					instance.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", this._numKnapSack);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}				                                
                                XYSeries AllTabuSearch = new XYSeries("TS");
                                XYSeries AllGrasp = new XYSeries("Grasp");
                                XYSeries AllSimAnn = new XYSeries("SA");
                                XYSeries AllIteratedGreedy = new XYSeries("IG");
                                
                                AllTabuSearch.setDescription("TS");   
                                AllGrasp.setDescription("Grasp");   
                                AllSimAnn.setDescription("SA");   
                                AllIteratedGreedy.setDescription("IG");
                                
                                this.runAllTrayectoriesExperiment(results, instance, AllTabuSearch, AllGrasp ,AllSimAnn , AllIteratedGreedy);
                                dataset.addSeries(AllSimAnn);
                                dataset.addSeries(AllTabuSearch);         
                                dataset.addSeries(AllGrasp); 
                                dataset.addSeries(AllIteratedGreedy); 
                                
                            break; 
                       case "GeneticAlgorithm":
				generateOptionsGAKnapsackProblem();
				generateDialog(300, 550,"Parámetros Knapsack");
				MQKPInstance ga = new MQKPInstance();
				try {
					ga.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", this._numKnapSack);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}				                                
                                XYSeries currentGenetic = new XYSeries("CurrentGenetic");
                                XYSeries bestGenetic = new XYSeries("BestGenAlgorithm");
                                bestGenetic.setDescription("BestGenAlgorithm");
                                this.runAGExperiment(results, ga, currentGenetic, bestGenetic);
                                dataset.addSeries(currentGenetic); 
                                dataset.addSeries(bestGenetic); 
                            break; 
                       case "AntColony":
				generateOptionsACOKnapsackProblem();
                                generateDialog(200, 400,"Parámetros Knapsack");
				MQKPInstance antC = new MQKPInstance();
				try {
					antC.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", this._numKnapSack);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}				                                
                                XYSeries currenACO = new XYSeries("CurrentACO");
                                XYSeries bestACO = new XYSeries("bestACO");
                                bestACO.setDescription("bestACO");
                                this.runACOExperiment(results, antC, currenACO, bestACO);
                                dataset.addSeries(currenACO); 
                                dataset.addSeries(bestACO);
                                break;
			default:
				System.out.println("DEFAULT ENTRY");
				break;
			}
			break;
		default:
			break;
		}

		return dataset;
	}
        private void generateOptionsKnapsackProblem(){
            //Generamos los elementos del modal:
            JLabel label = new JLabel("Número de mochilas");
            label.setFont(new Font("Serif", Font.BOLD, 18));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            _dialog.add(label);
            //Tras el titulo, insertamos un campo de texto para el número de mochilas
            JTextField txt = new JTextField();
            txt.setHorizontalAlignment(JTextField.CENTER);
            txt.setText("5");
            _dialog.add(txt);
            //Insertamos el boton de validar los datos
            JButton btn = new JButton("Validar");
            btn.addActionListener(new ActionListener(){
                boolean numeric = false;
                //ActionPermormed, comprueba si el texto introducido es númerico
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Integer.parseInt(txt.getText());
			numeric = true;
			}catch(NumberFormatException except){
                            numeric = false;
			}
			if(!numeric)
                            btn.setBackground(Color.red);
			else{
                            _numKnapSack = Integer.parseInt(txt.getText());
                            _frame.dispose();
			}
		}
            });
            _dialog.add(btn);            
        }
	/**
	 * Genera una ventana de dialogo modal
	 * @param height Altura de la ventana
	 * @param width Anchura de la ventana
	 * @param title Titulo que tendrá la ventana
	 */
	private void generateDialog(int height, int width, String title) {
		_dialog.setTitle(title);
		_dialog.setSize(width, height);
		_dialog.setVisible(true);
	}

      /**
	 * Funcion que ejecuta el algoritmo de Escalada
	 * @param results Vector de resultados de fitness
	 * @param instance Instancia del problema
	 * @param exp Objeto que explorará el vecindario de soluciones
         * @param serie Serie de elementos para mostrar por pantalla
	 */
    public void runALSExperiment(ArrayList results, MQKPInstance instance, MQKPNeighExplorer exp, XYSeries serie) {
                
		MQKPLocalSearch ls = new MQKPLocalSearch();

		MQKPSolution initialSolution = new MQKPSolution(instance);

		MQKPEvaluator.resetNumEvaluations();

		MQKPSolGenerator.genRandomSol(instance, initialSolution);
		double currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
		
                //Reseteamos el array de results para evitar concatenar series
                results.clear();
		initialSolution.setFitness(currentFitness);
		results.add(currentFitness);
                
		int numInitialSolutions = 0;
		long start = System.currentTimeMillis();
		long timeElapsed = 0;               		
		serie.add(0, currentFitness);

		while (timeElapsed <= MAX_SECONS_PER_RUN && MQKPEvaluator.getNumEvaluations() < MAX_SOLUTIONS_PER_RUN &&
		        numInitialSolutions < MAX_INITIAL_SOLUTIONS) {
			MQKPSolGenerator.genRandomSol(instance, initialSolution);
			currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
			initialSolution.setFitness(currentFitness);

			results.add(currentFitness);
			
                        ls.optimise(instance, exp, initialSolution);                        
                        //Volcado de los datos al array de resultados
			ArrayList<Double> resultsLS = ls.getResults();
			for (int i = 0; i < resultsLS.size(); i++) 
				results.add(resultsLS.get(i));
			
			timeElapsed = (long) ((System.currentTimeMillis() - start) / 1000F);
                        numInitialSolutions++;
		}
		for (int i = 0; i < results.size(); i++)
			serie.add(i+1, (double) results.get(i));                
	}                
    public void runAGraspExperiment(ArrayList results, MQKPInstance instance, XYSeries serie, XYSeries bestSerie){
            MQKPSolution initialSolution = new MQKPSolution(instance);
            MQKPGrasp grasp = new MQKPGrasp();
            StopCondition stopCond = new StopCondition();
            MQKPEvaluator.resetNumEvaluations();
            grasp.initialise(0.25, instance);
            results.clear();
            ArrayList <Double> BestofResults = new ArrayList<>();
            stopCond.setConditions(MAX_SOLUTIONS_PER_RUN, 0, MAX_SECONS_PER_RUN);
            //Generar solución aleatoria para inicialiar la mejor solución
            MQKPSolGenerator.genRandomSol(instance, initialSolution);
            double currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
            initialSolution.setFitness(currentFitness);
            BestofResults.add(currentFitness);
            double bestFitness = currentFitness;
            serie.add(0, currentFitness);
            bestSerie.add(0, bestFitness);
            //Ejecutamos Grasp
            grasp.run(stopCond);
            //Obtención de resultados
            ArrayList<Double> resultsGrasp = grasp.getResults();
            
            for(int i = 0; i < resultsGrasp.size(); i++){
                serie.add(i+1, (double) resultsGrasp.get(i));  
                
                bestSerie.add(i+1,Math.max(bestSerie.getMaxY(), (double) resultsGrasp.get(i)));                
            }
        }
    public void runIGreedyExperiment(ArrayList results, MQKPInstance instance, XYSeries serie, XYSeries bestSerie){
            MQKPSolution initialSolution = new MQKPSolution(instance);
            MQKPIteratedGreedy ig = new MQKPIteratedGreedy();
            StopCondition stopCond = new StopCondition();
            MQKPEvaluator.resetNumEvaluations();
            ig.initialise(0.25, instance);
            results.clear();
            ArrayList <Double> BestofResults = new ArrayList<>();
            stopCond.setConditions(MAX_SOLUTIONS_PER_RUN, 0, MAX_SECONS_PER_RUN);
            
            //Generar solución aleatoria para inicialiar la mejor solución
            MQKPSolGenerator.genRandomSol(instance, initialSolution);
            double currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
            initialSolution.setFitness(currentFitness);
            BestofResults.add(currentFitness);
            double bestFitness = currentFitness;
            serie.add(0, currentFitness);
            bestSerie.add(0, bestFitness);
            //Ejecutamos Grasp
            ig.run(stopCond);
            
            //Obtención de resultados
            ArrayList<Double> resultsIG = ig.getResults();
            
            for(int i = 0; i < resultsIG.size(); i++){
                serie.add(i+1, (double) resultsIG.get(i));                 
                bestSerie.add(i+1,Math.max(bestSerie.getMaxY(), (double) resultsIG.get(i)));                
            }
        }        
    public void runSAExperiment(ArrayList results, MQKPInstance instance, XYSeries serie){
            MQKPSolution initialSolution = new MQKPSolution(instance);
            MQKPSimulatedAnnealing SA = new MQKPSimulatedAnnealing();
            StopCondition stopCond = new StopCondition();
            MQKPEvaluator.resetNumEvaluations();
            SA.initialise(0.9, 10, 0.9999, 50, instance);
            results.clear();
            ArrayList <Double> BestofResults = new ArrayList<>();
            stopCond.setConditions(MAX_SOLUTIONS_PER_RUN, 0, MAX_SECONS_PER_RUN);
            
            //Generar solución aleatoria para inicialiar la mejor solución
            MQKPSolGenerator.genRandomSol(instance, initialSolution);
            double currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
            initialSolution.setFitness(currentFitness);
            BestofResults.add(currentFitness);
            serie.add(0, currentFitness);
            SA.setSolution(initialSolution);
            //Ejecutamos Grasp
            SA.run(stopCond);
            
            //Obtención de resultados
            ArrayList<Double> resultsSA = SA.getResults();
            
            for(int i = 0; i < resultsSA.size(); i++){
                serie.add(i+1, (double) resultsSA.get(i));                              
            }
        }            
    public void runTSExperiment(ArrayList results, MQKPInstance instance, XYSeries serie){
            MQKPSolution initialSolution = new MQKPSolution(instance);
            MQKPTabuSearch TS = new MQKPTabuSearch();
            StopCondition stopCond = new StopCondition();
            MQKPEvaluator.resetNumEvaluations();
            TS.initialise(instance, (int) (instance.getNumObjs()/2.5));
            results.clear();
            ArrayList <Double> BestofResults = new ArrayList<>();
            stopCond.setConditions(MAX_SOLUTIONS_PER_RUN, 0, MAX_SECONS_PER_RUN);
            
            //Generar solución aleatoria para inicialiar la mejor solución
            MQKPSolGenerator.genRandomSol(instance, initialSolution);
            double currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
            initialSolution.setFitness(currentFitness);
            BestofResults.add(currentFitness);
            serie.add(0, currentFitness);
            TS.setSolution(initialSolution);
            //Ejecutamos Grasp
            TS.run(stopCond);
            
            //Obtención de resultados
            ArrayList<Double> resultsTS = TS.getResults();
            
            for(int i = 0; i < resultsTS.size(); i++){
                serie.add(i+1, (double) resultsTS.get(i));                              
            }
        }
    public void runAllTrayectoriesExperiment(ArrayList results, MQKPInstance instance, XYSeries AllTabuSearch, XYSeries AllGrasp, XYSeries AllSimAnn ,XYSeries AllIteratedGreedy){
            MQKPSolution initialSolution = new MQKPSolution(instance);
            MQKPTabuSearch TS = new MQKPTabuSearch();
            StopCondition stopCond = new StopCondition();
            MQKPEvaluator.resetNumEvaluations();
            TS.initialise(instance, (int) (instance.getNumObjs()/2.5));
            results.clear();
            ArrayList <Double> BestofResults = new ArrayList<>();
            stopCond.setConditions(MAX_SOLUTIONS_PER_RUN, 0, MAX_SECONS_PER_RUN);
            
            //Generar solución aleatoria para inicialiar la mejor solución
            MQKPSolGenerator.genRandomSol(instance, initialSolution);
            double currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
            initialSolution.setFitness(currentFitness);
            BestofResults.add(currentFitness);
            AllTabuSearch.add(0, currentFitness);
            TS.setSolution(initialSolution);
            //Ejecutamos Grasp
            TS.run(stopCond);
            
            //Obtención de resultados
            ArrayList<Double> resultsTS = TS.getResults();
            
            for(int i = 0; i < resultsTS.size(); i++){
                AllTabuSearch.add(i+1, (double) resultsTS.get(i));                              
            }
            
            /**********************************************************/

            MQKPSimulatedAnnealing SA = new MQKPSimulatedAnnealing();
            MQKPEvaluator.resetNumEvaluations();
            SA.initialise(0.9, 10, 0.9999, 50, instance);
            results.clear();
            stopCond.setConditions(4000, 0, MAX_SECONS_PER_RUN);
            
            //Generar solución aleatoria para inicialiar la mejor solución
            MQKPSolGenerator.genRandomSol(instance, initialSolution);
            currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
            initialSolution.setFitness(currentFitness);

            AllSimAnn.add(0, currentFitness);
            SA.setSolution(initialSolution);
            //Ejecutamos Grasp
            SA.run(stopCond);
            
            //Obtención de resultados
            ArrayList<Double> resultsSA = SA.getResults();
            
            for(int i = 0; i < resultsSA.size(); i++){
                AllSimAnn.add(i+1, (double) resultsSA.get(i));                              
            }  
            
            /**********************************************************/

            MQKPIteratedGreedy ig = new MQKPIteratedGreedy();

            MQKPEvaluator.resetNumEvaluations();
            ig.initialise(0.25, instance);
            results.clear();

            stopCond.setConditions(MAX_SOLUTIONS_PER_RUN, 0, MAX_SECONS_PER_RUN);
            
            //Generar solución aleatoria para inicialiar la mejor solución
            MQKPSolGenerator.genRandomSol(instance, initialSolution);
            currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
            initialSolution.setFitness(currentFitness);
            BestofResults.add(currentFitness);
            double bestFitness = currentFitness;
            AllIteratedGreedy.add(0, currentFitness);

            //Ejecutamos Grasp
            ig.run(stopCond);
            
            //Obtención de resultados
            ArrayList<Double> resultsIG = ig.getResults();
            
            for(int i = 0; i < resultsIG.size(); i++)
                AllIteratedGreedy.add(i+1, (double) resultsIG.get(i));                               
            
            /************************************************************/
            MQKPGrasp grasp = new MQKPGrasp();
            MQKPEvaluator.resetNumEvaluations();
            grasp.initialise(0.25, instance);
            results.clear();

            stopCond.setConditions(MAX_SOLUTIONS_PER_RUN, 0, MAX_SECONS_PER_RUN);
            //Generar solución aleatoria para inicialiar la mejor solución
            MQKPSolGenerator.genRandomSol(instance, initialSolution);
            currentFitness = MQKPEvaluator.computeFitness(instance, initialSolution);
            initialSolution.setFitness(currentFitness);
            BestofResults.add(currentFitness);

            AllGrasp.add(0, currentFitness);
            //Ejecutamos Grasp
            grasp.run(stopCond);
            
            //Obtención de resultados
            ArrayList<Double> resultsGrasp = grasp.getResults();
            
            for(int i = 0; i < resultsGrasp.size(); i++)
                AllGrasp.add(i+1, (double) resultsGrasp.get(i));              
        }
    public void runAGExperiment(ArrayList results, MQKPInstance instance,XYSeries serieGenetic, XYSeries bestGenetic){
	//Inicialización
            MQKPGeneticAlgorithm ga = new MQKPGeneticAlgorithm();
            StopCondition stopCond = new StopCondition();
            MQKPEvaluator.resetNumEvaluations();
            ga.initialise(60, instance, this._crossPercent, this._mutatePercent, this._numberTournament, this._selector, this._mutate );
            stopCond.setConditions(MAX_SOLUTIONS_PER_RUN, 0, MAX_SECONS_PER_RUN);
            
            //Ejecutar el GA
            ga.run(stopCond);
           
            //Almacenar los resultados
            ArrayList<Double> resultsGA = ga.getResults();
            ArrayList<Double> bestSoFarResults = new ArrayList<>();

            for(int i = 0; i < resultsGA.size();i++){  
                
		if (bestSoFarResults.size() > 0)
                    bestSoFarResults.add(Math.max(bestSoFarResults.get(bestSoFarResults.size()-1), resultsGA.get(i)));
		else
                    bestSoFarResults.add(resultsGA.get(i)); 
                serieGenetic.add(i, resultsGA.get(i));
                bestGenetic.add(i, (double)bestSoFarResults.get(i));
            }            
        }
       
    void runACOExperiment(ArrayList results,MQKPInstance instance,XYSeries currenACO, XYSeries bestACO){
	//Inicialización
            MQKPAntColonyOpt aco = new MQKPAntColonyOpt();
            StopCondition stopCond = new StopCondition();
            MQKPEvaluator.resetNumEvaluations();
            aco.initialise(5, 0.1, 1, 0.5, 0.1, 0.5, 20, instance);
            stopCond.setConditions(MAX_SOLUTIONS_PER_RUN, 0, MAX_SECONS_PER_RUN);

            //Ejecutar el ACO
            aco.run(stopCond);

            //Almacenar los resultados
            ArrayList<Double> resultsACO = aco.getResults();

            ArrayList<Double> bestSoFarResults = aco.getBestPerIteration();
            for(int i = 0; i < bestSoFarResults.size(); i++)
                bestACO.add(i, bestSoFarResults.get(i));            
            for (int i = 0; i < resultsACO.size();i++)
                currenACO.add(i, resultsACO.get(i));           
       }
    
    private void generateOptionsGAKnapsackProblem(){
            this._dialog.setLayout(new GridLayout(0,2,1,2));
            //Generamos los elementos del modal:
            JLabel label = new JLabel("Número de mochilas");
            label.setFont(new Font("Serif", Font.BOLD, 18));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            _dialog.add(label);                      
            //Tras el titulo, insertamos un campo de texto para el número de mochilas
            JTextField txtKnapSack = new JTextField();
            txtKnapSack.setHorizontalAlignment(JTextField.CENTER);
            txtKnapSack.setText("5");
            _dialog.add(txtKnapSack);
            
            //Mutate Percent to GA
            JLabel percent = new JLabel("Probabilidad de mutación: (0-1)");
            percent.setFont(new Font("Serif", Font.BOLD, 18));
            percent.setHorizontalAlignment(JLabel.CENTER);
            percent.setVerticalAlignment(JLabel.CENTER);            
            _dialog.add(percent);
            
            JTextField txtMutatePercent = new JTextField();
            txtMutatePercent.setHorizontalAlignment(JTextField.CENTER);
            txtMutatePercent.setText("0.25");
            _dialog.add(txtMutatePercent); 
            
            //Cross Percent to GA
            JLabel percentCross = new JLabel("Probabilidad de Cruce: (0-1)");
            percentCross.setFont(new Font("Serif", Font.BOLD, 18));
            percentCross.setHorizontalAlignment(JLabel.CENTER);
            percentCross.setVerticalAlignment(JLabel.CENTER);
            _dialog.add(percentCross);
            
            JTextField txtCrossPercent = new JTextField();
            txtCrossPercent.setHorizontalAlignment(JTextField.CENTER);
            txtCrossPercent.setText("0.8");
            _dialog.add(txtCrossPercent);  
            //List Selection
            String[] list = {"Selección por ruleta", "Selección por torneo", "Selección por torneo KL","Selección por ranking"};            
            JComboBox Lista = new JComboBox(list);
            Lista.setFont(new Font("Serif", Font.BOLD, 18));
            JLabel selectOperator = new JLabel("Operador de selección:");
            selectOperator.setFont(new Font("Serif", Font.BOLD, 18));
            selectOperator.setHorizontalAlignment(JLabel.CENTER);
            selectOperator.setVerticalAlignment(JLabel.CENTER);            
            Lista.setSelectedIndex(0);
            _dialog.add(selectOperator);

            JLabel Tournament = new JLabel("Tamaño del torneo:");
            Tournament.setFont(new Font("Serif", Font.BOLD, 18));
            Tournament.setHorizontalAlignment(JLabel.CENTER);
            Tournament.setVerticalAlignment(JLabel.CENTER);
            Tournament.setVisible(false);
           
            JTextField TournamentNumber = new JTextField();
            TournamentNumber.setHorizontalAlignment(JTextField.CENTER);
            TournamentNumber.setVisible(false);
   
            Lista.addActionListener((ActionEvent e) -> {
                if(Lista.getSelectedItem().equals("Selección por torneo") ||
                        Lista.getSelectedItem().equals("Selección por torneo KL")){
                    Tournament.setVisible(true);
                    TournamentNumber.setVisible(true);
                }
                else{
                    Tournament.setVisible(false);
                    TournamentNumber.setVisible(false);
                }
            });
            
            _dialog.add(Tournament); 
            _dialog.add(Lista);
            
            _dialog.add(TournamentNumber); 

            //List Selection
            String[] listMutate = {"Mutación Uniforme", "Mutación de intercambios", "Mutación de inserción"};            
            JComboBox ListaMutate = new JComboBox(listMutate);
            ListaMutate.setFont(new Font("Serif", Font.BOLD, 18));
            JLabel MutateOperator = new JLabel("Operador de mutación:");
            MutateOperator.setFont(new Font("Serif", Font.BOLD, 18));
            MutateOperator.setHorizontalAlignment(JLabel.CENTER);
            MutateOperator.setVerticalAlignment(JLabel.CENTER);            
            ListaMutate.setSelectedIndex(0);
            _dialog.add(MutateOperator);
            _dialog.add(ListaMutate);

            
            //Fill Spaces            
            JLabel information = new JLabel();
            information.setVisible(false);
            _dialog.add(information);
            //Insertamos el boton de validar los datos
            JButton btn = new JButton("Validar");
            btn.addActionListener(new ActionListener(){
                boolean numeric = false;
                //ActionPermormed, comprueba si el texto introducido es númerico
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Integer.parseInt(txtKnapSack.getText());
                        Double.parseDouble(txtMutatePercent.getText());
                        Double.parseDouble(txtCrossPercent.getText());
                        
			numeric = true;
			}catch(NumberFormatException except){
                            numeric = false;
			}
			if(!numeric || Double.parseDouble(txtCrossPercent.getText()) < 0. || 
                                Double.parseDouble(txtCrossPercent.getText()) > 1.0 ||
                                Double.parseDouble(txtMutatePercent.getText()) < 0. ||
                                Double.parseDouble(txtMutatePercent.getText()) > 1.0)
                            btn.setBackground(Color.red);
			else{
                            _numKnapSack = Integer.parseInt(txtKnapSack.getText());
                            _mutatePercent = Double.parseDouble(txtMutatePercent.getText());
                            _crossPercent = Double.parseDouble(txtCrossPercent.getText());
                            _selector = Lista.getSelectedItem().toString();
                            if(Lista.getSelectedItem().equals("Selección por Torneo"))                                
                                _numberTournament = Integer.parseInt(TournamentNumber.getText());
                            _mutate = ListaMutate.getSelectedItem().toString();
                            _frame.dispose();
			}
		}
            });            
            _dialog.add(btn);            
        }

    private void generateOptionsACOKnapsackProblem(){
            this._dialog.setLayout(new GridLayout(0,2,1,2));
            //Generamos los elementos del modal:
            JLabel label = new JLabel("Número de mochilas");
            label.setFont(new Font("Serif", Font.BOLD, 18));
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            _dialog.add(label);                      
            //Tras el titulo, insertamos un campo de texto para el número de mochilas
            JTextField txtKnapSack = new JTextField();
            txtKnapSack.setHorizontalAlignment(JTextField.CENTER);
            txtKnapSack.setText("5");
            _dialog.add(txtKnapSack);
            
            //Numero hormigas de la colonia
            JLabel numAnts = new JLabel("Número de hormigas");
            numAnts.setFont(new Font("Serif", Font.BOLD, 18));
            numAnts.setHorizontalAlignment(JLabel.CENTER);
            numAnts.setVerticalAlignment(JLabel.CENTER);            
            _dialog.add(numAnts);
            
            JTextField txtNumAnts = new JTextField();
            txtNumAnts.setHorizontalAlignment(JTextField.CENTER);
            txtNumAnts.setText("5");
            _dialog.add(txtNumAnts); 
            
            //Numero alternativas a evaluar por las hormigas
            JLabel alternatives = new JLabel("Número de alternativas");
            alternatives.setFont(new Font("Serif", Font.BOLD, 18));
            alternatives.setHorizontalAlignment(JLabel.CENTER);
            alternatives.setVerticalAlignment(JLabel.CENTER);
            _dialog.add(alternatives);
            
            JTextField txtAlt = new JTextField();
            txtAlt.setHorizontalAlignment(JTextField.CENTER);
            txtAlt.setText("20");
            _dialog.add(txtAlt);  
            
            //Fill Spaces            
            JLabel information = new JLabel();
            information.setVisible(false);
            _dialog.add(information);
            //Insertamos el boton de validar los datos
            JButton btn = new JButton("Validar");
            btn.addActionListener(new ActionListener(){
                boolean numeric = false;
                //ActionPermormed, comprueba si el texto introducido es númerico
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        Integer.parseInt(txtKnapSack.getText());
                        Integer.parseInt(txtNumAnts.getText());
                        Integer.parseInt(txtAlt.getText());                        
			numeric = true;
			}catch(NumberFormatException except){
                            numeric = false;
			}
			if(!numeric || Integer.parseInt(txtNumAnts.getText()) <= 0. || 
                                Integer.parseInt(txtAlt.getText()) <= 0.)
                            btn.setBackground(Color.red);
			else{
                            _numKnapSack = Integer.parseInt(txtKnapSack.getText());
                            _mutatePercent = Double.parseDouble(txtAlt.getText());
                            _crossPercent = Double.parseDouble(txtNumAnts.getText());
                            _frame.dispose();
			}
		}
            });            
            _dialog.add(btn);            
        }       

    private void createDialog() {
        
        this._dialog.setSize(420, 380);
        genFunction gen = new genFunction();
        this._dialog.setLayout(new GridLayout(1,6,1,6));
        this._dialog.add(gen);
        this._dialog.setVisible(true);
        this.function = gen.getFunct();
        this.range = gen.getRange();
        this.precision = gen.getPrecision();
        this._dialog.dispose();
        this._funcType = gen.getFunctType();
    }

    private void runALSExperiment(ArrayList<Double> results, FunctOptInstance instance, FunctOptNeighExplorer exp,  XYZSeries<String> serie) {
                
		FunctOptLocalSearch ls = new FunctOptLocalSearch();

		FunctOptSolution initialSolution = new FunctOptSolution(instance);

		MQKPEvaluator.resetNumEvaluations();
                
		FunctOptSolGenerator.genRandomSol(instance, initialSolution);
		double currentFitness = FunctOptEvaluator.computeFitness(instance, initialSolution);                
                double FitnessRound = Math.round(currentFitness * Math.pow(10, instance.getPrecision())) / Math.pow(10, instance.getPrecision());
                //Reseteamos el array de results para evitar concatenar series
                results.clear();
		initialSolution.setFitness(FitnessRound);
		results.add(FitnessRound);
                
		int numInitialSolutions = 0;
		long start = System.currentTimeMillis();
		long timeElapsed = 0;               		
		serie.add(initialSolution.getBestNumberX(), initialSolution.getBestNumberY(), initialSolution.getBestNumberZ());
                ArrayList<Double> resultsLSX = new ArrayList<>();
                ArrayList<Double> resultsLSY = new ArrayList<>();
                ArrayList<Double> resultsLSZ = new ArrayList<>();
		while (timeElapsed <= MAX_SECONS_PER_RUN && MQKPEvaluator.getNumEvaluations() < MAX_SOLUTIONS_PER_RUN &&
		        numInitialSolutions < MAX_INITIAL_SOLUTIONS*10){
			FunctOptSolGenerator.genRandomSol(instance, initialSolution);
			FitnessRound = FunctOptEvaluator.computeFitness(instance, initialSolution);
                        FitnessRound = Math.round(FitnessRound * Math.pow(10, instance.getPrecision())) / Math.pow(10, instance.getPrecision());
			initialSolution.setFitness(FitnessRound);

			results.add(FitnessRound);
			serie.add(initialSolution.getBestNumberX(), initialSolution.getBestNumberY(), initialSolution.getBestNumberZ());

                        ls.optimise(instance, exp, initialSolution);                        
                        //Volcado de los datos al array de resultados
			resultsLSX = ls.getResultsX();
                        resultsLSY = ls.getResultsY();
                        resultsLSZ = ls.getResultsZ();
			for (int i = 0; i < resultsLSX.size(); i++) 
				serie.add(resultsLSX.get(i), resultsLSY.get(i),  resultsLSZ.get(i));
			
			timeElapsed = (long) ((System.currentTimeMillis() - start) / 1000F);
                        numInitialSolutions++;
		}
                ejesCoordenadas.add(resultsLSX.get(resultsLSX.size()-1));
                ejesCoordenadas.add(resultsLSY.get(resultsLSY.size()-1));
                ejesCoordenadas.add(resultsLSZ.get(resultsLSZ.size()-1));
    }

    public String getFunctionType() {
        return function;
    }
    public ArrayList<Double> getBestPoints(){        
        return ejesCoordenadas;
    }
    public XYZDataset<String> initialise3D()  {
		//Selector de algoritmo y _heuristica usada
		XYZSeriesCollection<String> dataset = new XYZSeriesCollection<>();
                ArrayList<Double> results = new ArrayList<>();    
		switch (_algorithm) {
                case "Optimización de funciones":
                    switch(_heuristic){
                        case "LocalSearch":
                                createDialog();
                                FunctOptInstance climb = new FunctOptInstance();
                                climb.setFunction(function);
                                climb.setRange(range);
                                climb.setPrecision(precision);
                                climb.setSize();     
                                climb.setFuncType(this._funcType);
				FunctOptSimpleFirstImprovement firstExplorer = new FunctOptSimpleFirstImprovement();
                                XYZSeries<String> serieFirst = new XYZSeries<>("Escalada Simple");                                
				this.runALSExperiment(results, climb, firstExplorer, serieFirst);
                                dataset.add(serieFirst);
                                
                                XYZSeries<String> serieBest = new  XYZSeries<>("Max. Pendiente");
                                FunctOptSimpleBestImprovement  bestExplorer = new FunctOptSimpleBestImprovement();
                                this.runALSExperiment(results, climb, bestExplorer, serieBest);
                                dataset.add(serieBest);
                                break;
                    }
                    break;
		default:
			break;
		}
		return dataset;
	}
}