/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.SelectionOperator;
import BaseClasses.Solution;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author i62gorej
 */
public class TournamentSelector implements SelectionOperator{
    /**
     * Variables miembro de la clase
     * _k número de participantes en el torneo
     */
    int _k;

    /**
     * Constructor
     * @param k Número de participantes en el torneo
     */
    public TournamentSelector(int k) {
        _k = k;
    }

    
    /**
     * Función que selecciona tantas parejas de padres de un vector como elementos hay en dicho vector
     * @param orig Vector de soluciones sobre el que aplicar la selección
     * @param result Vector donde se almacenan las parejas de padres seleccionadas
     */    
    @Override
    public void select(ArrayList<Solution> orig, ArrayList<Solution> result) {
        // utilizando le método propio selectOne, seleccionar tantas parejas
        //de padres como elementos hay en orig
        result.clear();
        for (int i = 0; i < orig.size() * 2; ++i) {
            result.add(selectOne(orig));                    
        }        
    }
    /**
     * Función que selecciona una solución del conjunto mediante torneo
     * @param[in] set Vector de soluciones
     * @result Solución seleccionada
     */
    Solution selectOne(ArrayList<Solution> set) {

        /**
         * 
         * 1. Seleccionar una solución aleatoria como la actualmente ganadora
         *
         * 2. Repetir (_k-1) veces la selección de otra solución aleatoria y el torneo con la actualmente ganadora (mantener la mejor)
         */
        Random r = new Random();
        int randIndex = r.nextInt((int) set.size());
        Solution best;

        best = set.get(randIndex);

        for (int i = 0; i < _k - 1; ++i) {
            randIndex = r.nextInt((int) set.size());
            if (MQKPEvaluator.compare(best.getFitness(), set.get(randIndex).getFitness()) < 0) {
                best = set.get(randIndex);
            }
        }
        return best;
    }

   
}
