/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseClasses;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
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
    JFrame _frame= new JFrame();  
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
   public mainClass(){}
   /**
    * 
    * @param Al Algoritmo seleccionado por el usuario
    * @param hc Heuristica seleccionada por el usuario
    */
   public mainClass(String Al, String hc){
       this._algorithm = Al;
       this._heuristic = hc;
       Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       _dialog.setLocation(dim.width / 2 - _dialog.getSize().width / 2, dim.height / 2 - _dialog.getSize().height / 2); 
       _dialog.setLayout(new GridLayout(3,2));
   }
   /**
    * Inicializa los parametros, instancias, etc y ejecuta el algoritmo
    * @return 
    */
    public XYSeriesCollection initialise(){
        //Selector de algoritmo y _heuristica usada
        XYSeriesCollection dataset = null; 
       return dataset;
   }
    
    /**
     * Genera una ventana de dialogo modal
     * @param height Altura de la ventana
     * @param width Anchura de la ventana
     * @param title Titulo que tendrá la ventana
     */
   private void generateDialog(int height, int width, String title){       
   }
   
   /**
    * Funcion que ejecuta el algoritmo de Escalada
    * @param results Vector de resultados de fitness
    * @param instance Instancia del problema
    * @param exp Objeto que explorará el vecindario de soluciones
    * @return XYSeriesCollection un frame para representar visualmente
    */
   public XYSeriesCollection runALSExperiment(ArrayList results, ClimbInstance instance, ClimbSimpleFirstImprovement exp){
     return new XYSeriesCollection();
   }
}

