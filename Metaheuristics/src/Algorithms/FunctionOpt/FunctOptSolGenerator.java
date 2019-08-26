/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FunctionOpt;
import Algorithms.Knapsack.*;
import java.util.Random;
/**
 * Clase que genera una solución aleatoria al problema MQKP
 * La representación de las soluciones será un vector de número enteros: 
 * De 1 a M para objetos que están en alguna de las M mochilas y 0 para objetos que no están en ninguna mochilas
 * @author i62gorej
 */
public class FunctOptSolGenerator {

    /**
    * Función que genera una solución aleatoria para el problema de las múltiples mochilas cuadráticas
    * @param instance Referencia a un objeto con la información de la instancia del problema MQKP
    * @param sol Referencia a un objeto que representa una solución al problema. IMPORTANTE: debe estar correctamente inicializado. En particular, su vector interno debe haber sido reservado previamente.
    */
    public static void genRandomSol(FunctOptInstance instance, FunctOptSolution sol) {
        int size = instance.getSize();
        int range = instance.getRange();

        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int randomKnapsack = rand.nextInt(range + 1);
            sol.putNumberInX(i, randomKnapsack);
        }
    }
}
