
package BaseClasses;

import Algorithms.Knapsack.MQKPNeighExplorer;
import Algorithms.Knapsack.MQKPEvaluator;
import Algorithms.Knapsack.MQKPInstance;
import Algorithms.Knapsack.MQKPLocalSearch;
import Algorithms.Knapsack.MQKPSimpleBestImprovement;
import Algorithms.Knapsack.MQKPSimpleFirstImprovement;
import Algorithms.Knapsack.MQKPSolGenerator;
import Algorithms.Knapsack.MQKPSolution;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author josel
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
	// Parametros de parada de las heuristicas
	int MAX_SOLUTIONS_PER_RUN = 100000;
	int MAX_INITIAL_SOLUTIONS = 5;
	int MAX_SECONS_PER_RUN = 5;
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
	public XYSeriesCollection initialise() {
		//Selector de algoritmo y _heuristica usada
		//Selector de algoritmo y _heuristica usada
		XYSeriesCollection dataset = new XYSeriesCollection();
		switch (_algorithm) {
		case "Algoritmo de la mochila":
			switch (_heuristic) {
			case "LocalSearch":
				//Generamos los elementos del modal:
				JLabel label = new JLabel("Número de mochilas");
				label.setFont(new Font("Serif", Font.BOLD, 18));
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setVerticalAlignment(JLabel.CENTER);
				_dialog.add(label);

				//Tras el titulo, insertamos un campo de texto para el número de mochilas
				JTextField txt = new JTextField();
				txt.setHorizontalAlignment(JTextField.CENTER);
				_dialog.add(txt);

				//Insertamos el boton de validar los datos
				JButton btn = new JButton("Validar");
				btn.addActionListener(new ActionListener(){
				    boolean numeric = false;
				    //ActionPermormed, comprueba si el texto introducido es númerico
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        try{
				            int num = Integer.parseInt(txt.getText());
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
				generateDialog(120, 350,"Parámetros Knapsack");
				//Fin de la generación del modal

				MQKPInstance climb = new MQKPInstance();
				try {
					climb.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", this._numKnapSack);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}
                                
				ArrayList<Double> results = new ArrayList<>();                                
				MQKPSimpleFirstImprovement firstExplorer = new MQKPSimpleFirstImprovement();
                                XYSeries serieFirst = new XYSeries("Escalada Simple");
				this.runALSExperiment(results, climb, firstExplorer, serieFirst);
                                dataset.addSeries(serieFirst);
                                
                                XYSeries serieBest = new XYSeries("Escalada Max. Pendiente");
                                MQKPSimpleBestImprovement  bestExplorer = new MQKPSimpleBestImprovement();
                                this.runALSExperiment(results, climb, bestExplorer, serieBest);
                                dataset.addSeries(serieBest);                                
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
                //System.out.println("Total de iteraciones: "+numInitialSolutions+" y evaluator: "+MQKPEvaluator.getNumEvaluations());
	}        
}

