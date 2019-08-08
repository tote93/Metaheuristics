/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainProgram;

import BaseClasses.mainClass;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartPanel;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author i62gorej
 * Clase principal para representar el programa
 */
public class main_panel extends javax.swing.JFrame {

	// Variable empleada para seleccionar la heuristica a usar
	String _heuristicSelected = null;
	Boolean CloseProgram = false;
	/**
	 * Constructor por defecto, inicializa los elementos visuales y les modifica el estado
	 * @throws IOException
	 */
	public main_panel() throws IOException {
		this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		initComponents();
		exportGraphic.setEnabled(false);
		exportGraphic.setToolTipText("Permite exportar la gráfica del algoritmo y heuristicos seleccionados.");
		Dimension sc = Toolkit.getDefaultToolkit().getScreenSize().getSize();
		this.setSize(sc);
		this.jpanelList.setVisible(false);
		this.InitialCheck();
		this.txtInfo.setVisible(false);
		this.jpanelInfo.setVisible(false);
		this.startAlgorithm.setVisible(false);
		this.DisplayPanel.setVisible(true);
                //jMenu3.setVisible(false);
                
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ParamsDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        jpanelList = new javax.swing.JScrollPane();
        listAlgorithms = new javax.swing.JList<>();
        jpanelInfo = new javax.swing.JScrollPane();
        txtInfo = new javax.swing.JTextArea();
        startAlgorithm = new javax.swing.JButton();
        DisplayPanel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        exportGraphic = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        LocalSearch = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        Grasp = new javax.swing.JMenuItem();
        IterativeGreedy = new javax.swing.JMenuItem();
        SimAnn = new javax.swing.JMenuItem();
        TabuSearch = new javax.swing.JMenuItem();
        AllMH = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        GeneticAlgorithm = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        testingButton = new javax.swing.JMenuItem();
        JMenuOut = new javax.swing.JMenu();

        javax.swing.GroupLayout ParamsDialogLayout = new javax.swing.GroupLayout(ParamsDialog.getContentPane());
        ParamsDialog.getContentPane().setLayout(ParamsDialogLayout);
        ParamsDialogLayout.setHorizontalGroup(
            ParamsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        ParamsDialogLayout.setVerticalGroup(
            ParamsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Metaheuristics");
        setMinimumSize(null);
        setResizable(false);

        jPanel1.setBackground(java.awt.Color.lightGray);
        jPanel1.setToolTipText("");

        labelTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelTitle.setText("Bienvenido a Metaheuristics");

        listAlgorithms.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        listAlgorithms.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Algoritmo de la mochila", "Proximamente..." };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listAlgorithms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listAlgorithmsMouseClicked(evt);
            }
        });
        jpanelList.setViewportView(listAlgorithms);

        txtInfo.setEditable(false);
        txtInfo.setColumns(20);
        txtInfo.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
        txtInfo.setLineWrap(true);
        txtInfo.setRows(5);
        txtInfo.setWrapStyleWord(true);
        txtInfo.setPreferredSize(new java.awt.Dimension(800, 900));
        jpanelInfo.setViewportView(txtInfo);

        startAlgorithm.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        startAlgorithm.setText("Iniciar");
        startAlgorithm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startAlgorithmMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(startAlgorithm)
                        .addGap(145, 145, 145))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpanelList, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpanelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jpanelList, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jpanelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(startAlgorithm)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout DisplayPanelLayout = new javax.swing.GroupLayout(DisplayPanel);
        DisplayPanel.setLayout(DisplayPanelLayout);
        DisplayPanelLayout.setHorizontalGroup(
            DisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        DisplayPanelLayout.setVerticalGroup(
            DisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jMenuBar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jMenu1.setBackground(java.awt.Color.lightGray);
        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        exportGraphic.setBackground(java.awt.Color.lightGray);
        exportGraphic.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        exportGraphic.setText("Exportar Gráfica");
        exportGraphic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exportGraphicMousePressed(evt);
            }
        });
        jMenu1.add(exportGraphic);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(java.awt.Color.lightGray);
        jMenu2.setText("Heurísticas");
        jMenu2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        LocalSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        LocalSearch.setText("Búsqueda Local");
        LocalSearch.setToolTipText("La búsqueda se realiza desde una solución inicial que intentamos mejorar modificándola en torno a su vecindario de soluciones.");
        LocalSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                LocalSearchMousePressed(evt);
            }
        });
        jMenu2.add(LocalSearch);

        jMenu4.setBackground(java.awt.Color.lightGray);
        jMenu4.setText("Trayectorias");
        jMenu4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        Grasp.setBackground(java.awt.Color.lightGray);
        Grasp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Grasp.setText("Grasp");
        Grasp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GraspMousePressed(evt);
            }
        });
        jMenu4.add(Grasp);

        IterativeGreedy.setBackground(java.awt.Color.lightGray);
        IterativeGreedy.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        IterativeGreedy.setText("Iterative Greedy");
        IterativeGreedy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                IterativeGreedyMousePressed(evt);
            }
        });
        jMenu4.add(IterativeGreedy);

        SimAnn.setBackground(java.awt.Color.lightGray);
        SimAnn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SimAnn.setText("Enfriamiento Simulado");
        SimAnn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SimAnnMousePressed(evt);
            }
        });
        jMenu4.add(SimAnn);

        TabuSearch.setBackground(java.awt.Color.lightGray);
        TabuSearch.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        TabuSearch.setText("Búsqueda Tabú");
        TabuSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TabuSearchMousePressed(evt);
            }
        });
        jMenu4.add(TabuSearch);

        AllMH.setBackground(java.awt.Color.lightGray);
        AllMH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        AllMH.setText("Todas las MH");
        AllMH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AllMHMousePressed(evt);
            }
        });
        jMenu4.add(AllMH);

        jMenu2.add(jMenu4);

        jMenu5.setBackground(java.awt.Color.lightGray);
        jMenu5.setText("Poblacionales");
        jMenu5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        GeneticAlgorithm.setBackground(java.awt.Color.lightGray);
        GeneticAlgorithm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        GeneticAlgorithm.setText("Genetic Algorithm");
        GeneticAlgorithm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GeneticAlgorithmMousePressed(evt);
            }
        });
        jMenu5.add(GeneticAlgorithm);

        jMenu2.add(jMenu5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Extra");
        jMenu3.setFocusable(false);
        jMenu3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        testingButton.setText("Testing");
        testingButton.setEnabled(false);
        testingButton.setRequestFocusEnabled(false);
        testingButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                testingButtonMousePressed(evt);
            }
        });
        jMenu3.add(testingButton);

        jMenuBar1.add(jMenu3);

        JMenuOut.setBackground(java.awt.Color.lightGray);
        JMenuOut.setText("Salir");
        JMenuOut.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        JMenuOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JMenuOutMouseClicked(evt);
            }
        });
        jMenuBar1.add(JMenuOut);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DisplayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(DisplayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	/**
	 * Función usada para agilizar el proceso de depuración
	 * @param evt
	 */
	private void testingButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_testingButtonMousePressed

		mainClass mc = new mainClass("Algoritmo de la mochila", "GeneticAlgorithm");
		XYSeriesCollection dataset = mc.initialise();
		GenGraphic g = new GenGraphic(dataset, "GeneticAlgorithm");
		ChartPanel chart = g.createChartPanel();
		chart.setSize(new Dimension(DisplayPanel.getWidth(), DisplayPanel.getHeight()));
		DisplayPanel.removeAll();
		DisplayPanel.add(chart);
		DisplayPanel.validate();
		DisplayPanel.repaint();
	}//GEN-LAST:event_testingButtonMousePressed

	/**
	 * Función ejecutada al seleccionar la heuristica de Climb
	 * @param evt
	 */
	private void LocalSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LocalSearchMousePressed
		
		labelTitle.setText("Búsqueda Local");
		_heuristicSelected = "LocalSearch";
		jpanelList.setVisible(true);
                listAlgorithms.clearSelection();
                txtInfo.setText("La busqueda local blabla");
                startAlgorithm.setVisible(false);
	}//GEN-LAST:event_LocalSearchMousePressed

	/**
	 * Función con la que obtenemos el algoritmo seleccionado de la lista
	 * @param evt
	 */
	private void listAlgorithmsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listAlgorithmsMouseClicked
		
		switch (listAlgorithms.getSelectedValue()) {
		case "Algoritmo de la mochila":
			this.txtInfo.setVisible(true);
			this.jpanelInfo.setVisible(true);
			this.txtInfo.setText("");
			this.txtInfo.append("El problema de la mochila, es un problema de optimización combinatoria."
			                    + "\nModela una situación análoga al llenar una mochila con todo o parte de un conjunto de objetos, cada uno con un peso y valor específicos. \n\nLos objetos colocados en la mochila deben maximizar el valor total sin exceder el peso máximo.\n\n"
			                    + "Algunos algoritmos existentes pueden resolverlo en la práctica para casos de un gran tamaño.\n ");
			this.startAlgorithm.setVisible(true);
			break;
		default:
			break;
		}
	}//GEN-LAST:event_listAlgorithmsMouseClicked
	/**
	 * Una vez obtenidos la heuristica y el algoritmo, se ejecutará dicha función, iniciando el algoritmo
	 * @param evt
	 */
	private void startAlgorithmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startAlgorithmMouseClicked
		
		mainClass mc = new mainClass(listAlgorithms.getSelectedValue(), _heuristicSelected );

		XYSeriesCollection dataset = mc.initialise();
		GenGraphic g = new GenGraphic(dataset, _heuristicSelected);
		ChartPanel chart = g.createChartPanel();
		chart.setSize(new Dimension(DisplayPanel.getWidth(), DisplayPanel.getHeight()));

		DisplayPanel.removeAll();
		DisplayPanel.add(chart);
		DisplayPanel.validate();
		DisplayPanel.repaint();
		this.exportGraphic.setEnabled(true);
	}//GEN-LAST:event_startAlgorithmMouseClicked

	private void JMenuOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JMenuOutMouseClicked
		
		int n = JOptionPane.showConfirmDialog(
		            getParent(),
		            "¿Seguro desea salir?",
		            "Salir de Metaheuristics",
		            JOptionPane.YES_NO_OPTION);
		//Cerrar programa
		if (n == 0)
			this.dispose();
	}//GEN-LAST:event_JMenuOutMouseClicked

	private void exportGraphicMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exportGraphicMousePressed
		
		JOptionPane.showMessageDialog(this, "Gráfica exportada con éxito");
		this.saveImage();
		this.exportGraphic.setEnabled(false);
	}//GEN-LAST:event_exportGraphicMousePressed

    private void GraspMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GraspMousePressed
       
                labelTitle.setText("MH Grasp");
		_heuristicSelected = "Grasp";
		jpanelList.setVisible(true);
                listAlgorithms.clearSelection();                
                this.txtInfo.setVisible(true);
		this.jpanelInfo.setVisible(true);
		this.txtInfo.setText("");
		this.txtInfo.append("GRASP es un procedimiento iterativo donde cada paso consiste en una fase de construcción y una de mejora.\n\n"
                    + "En la fase de construcción se construye iterativamente una solución factible, añadiendo un elemento en cada paso. En cada iteración la elección del próximo elemento para ser añadido a la solución parcial viene determinado por una función greedy. Esta función mide el beneficio de añadir cada uno de los elementos y se elige la mejor."
                    + "Se ha de saber que esta MH no tiene en cuenta qué ocurrirá en iteraciones sucesivas una vez que se hace una elección, sino únicamente en la iteración actual");
		startAlgorithm.setVisible(false);                
    }//GEN-LAST:event_GraspMousePressed

    private void SimAnnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SimAnnMousePressed
        // TODO add your handling code here:
		labelTitle.setText("MH Enfriamiento Simulado");
		_heuristicSelected = "SimulatedAnnealing";
                listAlgorithms.clearSelection();                
		jpanelList.setVisible(true);
                this.txtInfo.setVisible(true);
		this.jpanelInfo.setVisible(true);
		this.txtInfo.setText("");
		this.txtInfo.append("Es un algoritmo de búsqueda meta-heurística para problemas de optimización global; el objetivo general de este tipo de algoritmos es encontrar una buena aproximación al valor óptimo de una función en un espacio de búsqueda grande. A este valor óptimo se lo denomina \"óptimo global\""
                    + "En cada iteración, el método evalúa algunos vecinos del estado actual s y probabilísticamente decide entre efectuar una transición a un nuevo estado S' o quedarse en el estado S. "
                    + "El vecindario de un estado s está compuesto por todos los estados a los que se pueda llegar a partir de s mediante un cambio en la solución actual."
                    + "A medida que la temperatura tiende al mínimo, la probabilidad de transición a un estado de mayor energía tiende a cero asintóticamente. Cuando T llega a cero, el algoritmo solo aceptará cambios a estados con menor energía. Debido a esta propiedad, la temperatura juega un papel muy importante en el control de la evolución del sistema. A temperaturas altas, el sistema tenderá a saltos de energía grandes entre los estados, mientras que a temperaturas más bajas, los cambios en energía serán menores.");
		startAlgorithm.setVisible(false);	                      
    }//GEN-LAST:event_SimAnnMousePressed

    private void TabuSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabuSearchMousePressed
        // TODO add your handling code here:
        labelTitle.setText("Búsqueda Tabú");
        _heuristicSelected = "TabuSearch";
        listAlgorithms.clearSelection();                
        jpanelList.setVisible(true);       
        this.txtInfo.setText("");
        this.txtInfo.append("La búsqueda tabú es un método de optimización matemática, perteneciente a la clase de técnicas de búsqueda local.\n\n La búsqueda tabú aumenta el rendimiento del método de búsqueda local mediante el uso de estructuras de memoria: una vez que una potencial solución es determinada, se la marca como \"tabú\" de modo que el algoritmo no vuelva a visitar esa posible solución");
        startAlgorithm.setVisible(false);            
    }//GEN-LAST:event_TabuSearchMousePressed

    private void IterativeGreedyMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IterativeGreedyMousePressed

        labelTitle.setText("MH Greedy Iterativo");
        _heuristicSelected = "IteratedGreedy";
        listAlgorithms.clearSelection();        
	jpanelList.setVisible(true);
	this.txtInfo.setText("");
	this.txtInfo.append("El Greedy Iterativo basa su funcionamiento en una conducta similar al GRASP."
                + "De tal forma que a partir de una solución inicial, explorará el vecindario y se moverá a la mejor solución disopnible.\n\n"
                + "Sin embargo, se procederá a realizar la destrucción parcial de la solución, de tal forma que así nos aseguramos que el IG, permita además de explotar un vecindario, explorar nuevos vecindarios que de otra forma podría no haber visitado.\n\n"
                + "Por tanto, podemos definirlo con una fase de construcción, una segunda fase de destrucción de la solución y una última parte de reconstrucción de la solución.");
        startAlgorithm.setVisible(false);    
    }//GEN-LAST:event_IterativeGreedyMousePressed

    private void AllMHMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AllMHMousePressed
        // TODO add your handling code here:
        listAlgorithms.clearSelection();        
        labelTitle.setText("Basadas en Trayectorias");
        _heuristicSelected = "AllTrayectories";
	jpanelList.setVisible(true);
        startAlgorithm.setVisible(false);
    }//GEN-LAST:event_AllMHMousePressed

    private void GeneticAlgorithmMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GeneticAlgorithmMousePressed
        // TODO add your handling code here:
        listAlgorithms.clearSelection();        
        labelTitle.setText("Algoritmos Poblacionales");
        _heuristicSelected = "GeneticAlgorithm";
	jpanelList.setVisible(true);
        startAlgorithm.setVisible(false);        
    }//GEN-LAST:event_GeneticAlgorithmMousePressed



	/**
	* Se realizan las comprobaciones iniciales y en caso de que no se cumpla, se ejecuta el método para crear la jerarquia
	 * @see Reset
	 * @throws IOException
	 */
	public final void InitialCheck() throws IOException {
		String url = new java.io.File(".").getCanonicalPath();
		url = url.replace("\\", "/");
		url = url + "/Algorithms";
		File f = new File(url);
		if (!f.exists()) {
			Reset reset = new Reset();
			reset.createDirectoryHierarchy();
		}
	}
	/**
	 * Función que permite exportar como fichero jpeg la gráfica obtenida
	 */
	private void saveImage() {
		BufferedImage imagebuf = null;
		try {
			imagebuf = new Robot().createScreenCapture(DisplayPanel.bounds());
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
		}
		Graphics2D graphics2D = imagebuf.createGraphics();
		DisplayPanel.paint(graphics2D);
		try {
			ImageIO.write(imagebuf, "jpeg", new File(this._heuristicSelected + "_" + listAlgorithms.getSelectedValue() + ".jpeg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(main_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(main_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(main_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(main_panel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(() -> {
			try {
				new main_panel().setVisible(true);
			} catch (IOException ex) {
				Logger.getLogger(main_panel.class.getName()).log(Level.SEVERE, null, ex);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AllMH;
    private javax.swing.JPanel DisplayPanel;
    private javax.swing.JMenuItem GeneticAlgorithm;
    private javax.swing.JMenuItem Grasp;
    private javax.swing.JMenuItem IterativeGreedy;
    private javax.swing.JMenu JMenuOut;
    private javax.swing.JMenuItem LocalSearch;
    private javax.swing.JDialog ParamsDialog;
    private javax.swing.JMenuItem SimAnn;
    private javax.swing.JMenuItem TabuSearch;
    private javax.swing.JMenuItem exportGraphic;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jpanelInfo;
    private javax.swing.JScrollPane jpanelList;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JList<String> listAlgorithms;
    private javax.swing.JButton startAlgorithm;
    private javax.swing.JMenuItem testingButton;
    private javax.swing.JTextArea txtInfo;
    // End of variables declaration//GEN-END:variables

	private BufferedImage getScreenShot(Panel DisplayPanel) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
