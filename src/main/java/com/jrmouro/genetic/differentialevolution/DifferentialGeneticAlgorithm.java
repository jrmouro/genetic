/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.differentialevolution;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.StoppingCondition;
import org.apache.commons.math3.genetics.TournamentSelection;

/**
 *
 * @author ronaldo
 */
public class DifferentialGeneticAlgorithm extends GeneticAlgorithm{
    
    final Population population;
    final StoppingCondition condition;
    
    public DifferentialGeneticAlgorithm(Population population, 
                                        StoppingCondition condition,
                                        double crossoverRate, 
                                        double mutationRate,    
                                        double cr,
                                        int arity
    ) throws OutOfRangeException {
        super(  new DifferentialCrossover(cr), 
                crossoverRate, 
                new DifferentialMutation(population), 
                mutationRate, 
                new TournamentSelection(arity));
        this.population = population;
        this.condition = condition;
    }

    
    public Population evolve() {
        return super.evolve(population, condition); 
    }

    
}
