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
public class Evaluator {

    public Evaluator(){
    }
   
    public double compare(double f1, double f2){
        return (f1-f2);
    }
    
    public Boolean isToBeMinimised(){
        return(compare(0,1) > 0);
    }
}
