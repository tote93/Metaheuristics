
package BaseClasses;

import Algorithms.Knapsack.ClimbEvaluator;
import Algorithms.Knapsack.ClimbInstance;
import Algorithms.Knapsack.ClimbLocalSearch;
import Algorithms.Knapsack.ClimbSimpleFirstImprovement;
import Algorithms.Knapsack.ClimbSolGenerator;
import Algorithms.Knapsack.ClimbSolution;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
	 * @return
	 */
	public XYSeriesCollection initialise() {
		//Selector de algoritmo y _heuristica usada
		//Selector de algoritmo y _heuristica usada
		XYSeriesCollection dataset = null;
		switch (_algorithm) {
		case "Algoritmo de la mochila":
			switch (_heuristic) {
			case "LocalSearch":
				//Generamos los elementos del modal:
				/* JLabel label = new JLabel("Número de mochilas");
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
				 _dialog.add(btn)

				 generateDialog(120, 350,"Parámetros Knapsack");;*/
				//Fin de la generación del modal

				ClimbInstance climb = new ClimbInstance();
				try {
					climb.readInstance("/Algorithms/Knapsack/exampleKnapsack.txt", 5);
				} catch (IOException ex) {
					Logger.getLogger(mainClass.class.getName()).log(Level.SEVERE, null, ex);
				}
				ArrayList<Double> results = new ArrayList<>();
				ClimbSimpleFirstImprovement firstExplorer = new ClimbSimpleFirstImprovement();
				//dataset = this.runALSExperiment(results, climb, firstExplorer);

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
	 * @return XYSeriesCollection un frame para representar visualmente
	 */
	public XYSeriesCollection runALSExperiment(ArrayList results, ClimbInstance instance, ClimbSimpleFirstImprovement exp) {

		ClimbLocalSearch ls = new ClimbLocalSearch();

		ClimbSolution initialSolution = new ClimbSolution(instance);

		ClimbEvaluator.resetNumEvaluations();


		ClimbSolGenerator.genRandomSol(instance, initialSolution);
		double currentFitness = ClimbEvaluator.computeFitness(instance, initialSolution);
		double bestFitness = currentFitness;
		initialSolution.setFitness(currentFitness);
		results.add(currentFitness);
		int numInitialSolutions = 0;
		long start = System.nanoTime();
		long timeElapsed = 0;
		XYSeriesCollection dataset = new XYSeriesCollection( );
		XYSeries serie = new XYSeries("Fitness");
		serie.add(currentFitness, 0);

		while (timeElapsed <= MAX_SECONS_PER_RUN && ClimbEvaluator.getNumEvaluations() < MAX_SOLUTIONS_PER_RUN &&
		        numInitialSolutions < MAX_INITIAL_SOLUTIONS) {
			ClimbSolGenerator.genRandomSol(instance, initialSolution);
			currentFitness = ClimbEvaluator.computeFitness(instance, initialSolution);

			initialSolution.setFitness(currentFitness);

			results.add(currentFitness);
			numInitialSolutions++;

			ls.optimise(instance, exp, initialSolution);
			ArrayList<Double> resultsLS = ls.getResults();

			for (int i = 0; i < resultsLS.size(); i++) {
				results.add(resultsLS.get(i));
			}

			timeElapsed = System.nanoTime() - start;
		}
		for (int i = 1; i < results.size(); i++)
			serie.add((double) results.get(i), i);

		dataset.addSeries(serie);
		return dataset;
	}
}

