/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.SelectionOperator;
import BaseClasses.Solution;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
/*
El algoritmo para realizar este proceso será:

    1) [SumaTotal] Calcular la suma total acumulada de los fitness de todos los individuos de la población actual.
    2) [Elegir un número aleatorio r] Generar un número aleatorio entre 0 y la SumaTotal.
    3) [Recorrer] Recorrer la población acumulando nuevamente los fitness. 
          Cuando la suma que se lleve sea mayor o igual a r seleccionamos el individuo donde se vaya recorriendo.

*/
/**
 *
 * @author i62gorej
 */
public class RouletteSelector implements SelectionOperator{
    double sumTotal = 0.;

    /**
     * Función que selecciona tantas parejas de padres de un vector como elementos hay en dicho vector
     * @param orig Vector de soluciones sobre el que aplicar la selección
     * @param result Vector donde se almacenan las parejas de padres seleccionadas
     */     
    @Override
    public void select(ArrayList<Solution> orig, ArrayList<Solution> result) {
        // utilizando el método propio selectOne, seleccionar tantas parejas
        //de padres como elementos hay en orig
        result.clear();
        //Obtenemos la suma de todo el vector poblacion
        calculateSum(orig);
        for (int i = 0; i < orig.size() * 2; ++i) {
            result.add(selectOne(orig));                    
        } 
    }
    void calculateSum(ArrayList<Solution> orig){
        this.sumTotal = 0.;
        for(int i = 0; i < orig.size();i++)
            sumTotal += abs(orig.get(i).getFitness());
    }
/**
     * Función que selecciona una solución del conjunto mediante torneo
     * @param set Vector de soluciones
     * @result Solución seleccionada
     */
    Solution selectOne(ArrayList<Solution> population) {

        Random r = new Random();
        int random = r.nextInt((int) this.sumTotal);
        Solution best = population.get(0);
        double sumatoria = 0.;
        int i = 0;
        for(i = 0; i < population.size(); i++){
            
            if(sumatoria >= random){
                //best.copy(population.get(i));
                best = population.get(i);
                return best;
            }
            sumatoria += abs(population.get(i).getFitness());
        }
        if(random == sumatoria)
            return population.get(population.size()-1);
        //System.out.println("La I: "+i+" El random es: "+random +" y la sumatoria: "+sumatoria+" y el total: "+this.sumTotal);
        //En caso de no 
        return best;
    }    
}
