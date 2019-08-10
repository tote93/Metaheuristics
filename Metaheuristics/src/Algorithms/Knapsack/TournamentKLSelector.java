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
public class TournamentKLSelector implements SelectionOperator{
    int _k = 0;
   
    public TournamentKLSelector(int k){
        _k = k;
    }
    @Override
    public void select(ArrayList<Solution> orig, ArrayList<Solution> result) {
       result.clear();
        for (int i = 0; i < orig.size() * 2; ++i) {
            result.add(selectOne(orig));                    
        }   
    }

    private Solution selectOne(ArrayList<Solution> orig) {
        Random rand = new Random();
        ArrayList<Solution> indiv_select = new ArrayList<>();
        
        for(int i = 0; i < this._k; i++){
            int index = rand.nextInt(orig.size()-1);
            indiv_select.add(orig.get(index));
        }
        
        Solution best = indiv_select.get(0);
        
        for(int i = 1; i < indiv_select.size(); i++){
            if(best.getFitness() < indiv_select.get(i).getFitness())
                best = indiv_select.get(i);
        }
        return best;       
    }
    
}
