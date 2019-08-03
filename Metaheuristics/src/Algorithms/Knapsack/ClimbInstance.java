/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.Instance;
import BaseClasses.Solution;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;



/**
 *
 * @author josel
 */
public class ClimbInstance extends Instance {
    int _numKnapsack;
    int _numObjs;
    int [][]_profits;
    ArrayList<Double> _weights = new ArrayList<>();
    ArrayList<Double> _capacities = new ArrayList<>();

    public ClimbInstance() {
        this._numObjs = 0;
        this._numKnapsack = 0;
    }
    public int getNumObjs() {
        return this._numObjs;
    }

    public int getNumKnapsacks() {
        return this._numKnapsack;
    }

    public Double getWeight(int index) {
        return this._weights.get(index);
    }

    public Double getCapacity(int index) {
        return this._capacities.get(index);
    }

    public void setNumObjs(int NumObjs) {
        this._numObjs = NumObjs;
    }

    public void setKnapsacks(int numKnapsacks) {
        this._numKnapsack = numKnapsacks;
    }


    public double getMaxCapacityViolation(ClimbSolution solution) {

        Double [] arr = new Double[1 + this._numKnapsack];
        ArrayList<Double> sumWeights = new ArrayList<>(Arrays.asList(arr));
        Collections.fill(sumWeights, 0.);

        for (int i = 0; i < this._numObjs; i++) {
            int value = solution.whereIsObject(i);
            if (value > 0) sumWeights.set(value, (this.getWeight(i) + sumWeights.get(value)));
        }

        //Inicializamos la máxima violación de alguna mochila a 0, que significa que no hay ninguna violación
        double maxCapacityViolation = 0;

        for (int j = 1; j <= this._numKnapsack; j++) {
            double violation = sumWeights.get(j) - this.getCapacity(j);
            if (violation > maxCapacityViolation) maxCapacityViolation = violation;
        }

        return maxCapacityViolation;
    }
    public double getSumProfits(ClimbSolution solution) {

        double sumProfits = 0.;
        int _numObjs = this._numObjs, indexX = 0, indexY = 0;

        for (int i = 0; i < _numObjs; i++) {
            indexX = solution.whereIsObject(i);

            if (indexX > 0) {
                sumProfits += this._profits[i][i];
                for (int j = (i + 1); j < _numObjs; j++) {
                    indexY = solution.whereIsObject(j);
                    if (indexX == indexY) sumProfits += this._profits[i][j];
                }
            }

        }
        return sumProfits;
    }

    public void readInstance(String filename, int numKnapsacks) throws FileNotFoundException, IOException {
        //Establecemos y limpiamos la ruta donde se encuentra el archivo
        String url = new java.io.File(".").getCanonicalPath();
        url = url.replace("\\", "/");
        url = url + filename;

        final Scanner scanner = new Scanner(new File(url));
        final int numObj = scanner.nextInt();
        Double sumWeights = 0.;
        //Seteamos las variables de clase
        this.setNumObjs(numObj);
        this.setKnapsacks(numKnapsacks);
        //Generamos una matriz auxiliar que posteriormente volcaremos a la de clase
        int[][] matrix = new int[numObj][numObj];

        //Establecemos la diagonal
        for (int i = 0; i < this._numObjs; ++i)
            matrix[i][i] = scanner.nextInt();

        for (int i = 0; i < this._numObjs; ++i) {
            for (int j = (i + 1); j < this._numObjs; ++j) {
                if (scanner.hasNextInt()) {
                    matrix[i][j] = scanner.nextInt();
                    matrix[j][i] = matrix[i][j];
                }
            }
        }
        ArrayList<Double> Weights = new ArrayList<>();

        Double [] arr = new Double[1 + this._numKnapsack];
        ArrayList<Double> capacities = new ArrayList<>(Arrays.asList(arr));

        Collections.fill(capacities, 0.);

        for (int j = 0; j < this._numObjs; ++j) {
            double val = scanner.nextInt();
            Weights.add(val);
            sumWeights += Weights.get(j);
        }

        sumWeights *= 0.8;
        sumWeights /= numKnapsacks;
        for (int i = 1; i <= this.getNumKnapsacks(); i++)
            capacities.set(i, sumWeights);

        this._profits = matrix;
        this._weights = Weights;
        this._capacities = capacities;

    }

    public double getProfit(int o1, int o2) {
        return _profits[o1][o2];
    }

    public double getProfit(int object) {
        return _profits[object][object];
    }

    public double getDeltaSumProfits(ClimbSolution solution, int indexObject,
                                     int indexKnapsack) {

        double deltaSumProfits = 0;

        /* Si el objeto estaba en una mochila, resta a deltaSumProfits su beneficio más el beneficio
         * conjunto con cualquier otro objeto que estuviese en esa misma mochila
         */

        int originKnapsack = solution.whereIsObject(indexObject);

        if (originKnapsack > 0) {
            deltaSumProfits -= this.getProfit(indexObject);

            for (int i = 0; i < this._numObjs; i++) {

                if (solution.whereIsObject(i) == originKnapsack && i != indexObject) {
                    deltaSumProfits -= this.getProfit(indexObject, i);
                }
            }
        }

        /* Si el objeto se va a insertar en alguna mochila, suma a deltaSumProfits su beneficio más el beneficio
         * conjunto con cualquier otro objeto que ya esté en dicha mochila
         */

        if (indexKnapsack > 0) {
            deltaSumProfits += this.getProfit(indexObject);

            for (int i = 0; i < _numObjs; i++) {

                if (solution.whereIsObject(i) == indexKnapsack && i != indexObject) {
                    deltaSumProfits += this.getProfit(indexObject, i);
                }
            }
        }
        return deltaSumProfits;
    }

    public static void randomPermutation(int size, ArrayList<Integer> perm) {

        /**
         * 1. Vacía el vector perm
         * 2. Llénalo con la permutación identidad
         * 3. Recórrelo intercambiando cada elemento con otro escogido de forma aleatoria.
         */
        for (int i = 0; i < size; i++) {
            perm.add(i);
        }

        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int pos = rand.nextInt(size);
            int aux = perm.get(i);
            perm.set(i, perm.get(pos));
            perm.set(pos, aux);
        }

    }
    double getDeltaMaxCapacityViolation(ClimbSolution solution,
                                        int indexObject, int indexKnapsack) {

        /**
         * 1. Obten la mochila donde está el objeto
         * 2. Obten la máxima violación actual de la solución
         * 3. Asigna el objeto a la nueva mochila en solución
         * 4. Obten la nueva violación de la solución
         * 5. Deshaz el cambio anterior, volviendo a poner el objeto en la mochila en la que estaba
         * 6. Devuelve la diferencia (nueva violación - violación actual)
         */
        int originKnapsack = solution.whereIsObject(indexObject);
        double originViolation = getMaxCapacityViolation(solution);
        solution.putObjectIn(indexObject, indexKnapsack);
        double newViolation = getMaxCapacityViolation(solution);
        solution.putObjectIn(indexObject, originKnapsack);

        return (newViolation - originViolation);
    }
}
