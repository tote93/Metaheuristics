/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseClasses;

import java.util.ArrayList;

/**
 *
 * @author i62gorej
 */
public interface SelectionOperator {
	/**
	 * Función para seleccionar un conjunto de soluciones de un vector original
	 * @param orig Vector de soluciones sobre el cual aplicar la selección
	 * @param result Vector donde se almacenan las soluciones seleccionadas
	 */    
    void select(ArrayList<Solution> orig, ArrayList<Solution> result);
}
