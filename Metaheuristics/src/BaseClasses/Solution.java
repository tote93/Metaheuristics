package BaseClasses;

import java.util.ArrayList;

/**
 *
 * @author i62gorej
 */
public interface Solution {
    ArrayList<Integer> _sol = new ArrayList<>();
    boolean _fitnessAssigned = false;
    double getFitness();
    public void copy(Solution sol);
}
