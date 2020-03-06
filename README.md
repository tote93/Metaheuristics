# Metaheuristics
Program developed in Java language (Swing), to make the visualization and understanding of the operation of the different metaheuristics applied to toy problems simpler.

# How to use
The program has been developed under netbeans, so by importing and subsequent execution it would be sufficient.

# Graphic Interface
The program has a graphical interface in which the user can select the problem to be treated, as well as the heuristics to be performed, allowing the modification of the parameters of said heuristic.
Once executed, it will show the result visually using a two-dimensional or three-dimensional graph.

# What heuristics are avaiable?
                
* Local Search
	* Simple Hill Climbing
	* Maximum slope Hill Climbing
* Based in trayectories
	* Grasp
	* Iterative Greedy
	* Simulated Annealing
	* Tabu Search
	* Comparision between all of them
* Populations
	* Genetic Algorithm
* Ant Colony Optimization
                
# What toy problems?
+ Knapsack Problem
+ Function Optimization

# Features:
    * Genetic Algorithms:
	    * Allow set Mutation rate
	    * Allow set Cross rate
	    * Allow set Selection operator:
		    * Roulette selection
		    * Tournament Selector
		    * Tournament KL Selector
		    * Ranking Selector
		* Allow set Mutation Selector:
			* Uniform Selector
			* Exchange Selector
			* Insertion Selector
	* Ant Colony Optimization:
		* Allow set number of ants
		* Allow set number of alternatives of ants
	* Allow export graphics as image with a button
# Libraries
    * exp4j-0.4.8 (A simple mathematical expression evaluator for java)
    * jfreechart-1.0.19 (JFreeChart is a free (LGPL) chart library for the Java(tm) platform).
    * orsoncharts-1.6 (Orson Charts is a 3D chart library that can generate a wide variety of 3D charts for use in client-side).
    * junit (It would be developed to be able to test the operation of the classes and methods that make up our application).
 
 # Future Improvements
     * Allow multi-languaje application (currently everything it is Spanish languaje).
     * Fix 3D Graphic bug in function optimization problem
     * Refactorice code and debug application
 	
