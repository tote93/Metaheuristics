/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseClasses;

/**
 *
 * @author josel
 */
public class Solution {
    private double _fitness = 0;
    
    public double getFitness(){
        return _fitness;
    }
    
    public void copy(Solution sol) {}

    public int whereIsObject(int i) {
        return 0;
    }
    
    public void setFitness(double d){
        _fitness = d;
    }
    
}
