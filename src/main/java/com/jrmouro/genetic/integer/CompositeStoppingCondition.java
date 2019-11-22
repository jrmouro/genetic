/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.StoppingCondition;

/**
 *
 * @author ronaldo
 */
public class CompositeStoppingCondition implements StoppingCondition{
    
    public int numGenerations = 0;
    
    private final int genNr;
    private final double objective;

    public CompositeStoppingCondition(int genNr, double objtive) {
        this.genNr = genNr;
        this.objective = objtive;
    }    
    

    @Override
    public boolean isSatisfied(Population population) {
        numGenerations++;
        Chromosome c = population.getFittestChromosome();
        
        return numGenerations >= this.genNr || c.getFitness() > this.objective;
                
    }
    
    
}
