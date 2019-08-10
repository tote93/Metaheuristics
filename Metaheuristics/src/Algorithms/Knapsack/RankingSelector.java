/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.Knapsack;

import BaseClasses.SelectionOperator;
import BaseClasses.Solution;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author i62gorej
 */
public class RankingSelector implements SelectionOperator{
    ArrayList<Solution> sortedArray = new ArrayList<>();
    ArrayList<Double> percentPopulation = new ArrayList<>();
    int size = 0;
    
    public RankingSelector(){}
    
    @Override
    public void select(ArrayList<Solution> orig, ArrayList<Solution> result) {
        result.clear();
        sortedArray = orig;
        this.sortPopulation();
        this.setPopulationPercentajes();
        for (int i = 0; i < orig.size() * 2; ++i) {
            result.add(selectOne(sortedArray));                    
        }         
    }

    private Solution selectOne(ArrayList<Solution> orig) {
        Random rand = new Random();
        Solution best;
        double randomValue = rand.nextDouble();
        double sumPercent = 0.;
        for(int i = 0; i < percentPopulation.size(); i++){
            sumPercent += percentPopulation.get(i);
            if(sumPercent >= randomValue){
                return orig.get(i);                
            }
        }
        return null;
    }

    private void sortPopulation() {
        //ordenamos de mayor a menor fitness
            Collections.sort(sortedArray, (Solution s1, Solution s2) -> 
                    Double.compare(s2.getFitness(), s1.getFitness()));
    }

    private void setPopulationPercentajes() {
        percentPopulation.clear();
        int numRank = 0;
        for(int i = 0; i < sortedArray.size(); i++)
            numRank+=(i+1);
        for(int i = 0; i < sortedArray.size(); i++){
            percentPopulation.add(Math.abs(sortedArray.get(i).getFitness())/numRank);
        }        
    }


}
