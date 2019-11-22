/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.StoppingCondition;

/**
 *
 * @author ronaldo
 */
public class TargetMinStoppingCondition implements StoppingCondition{
    
    final double target;
    
    public TargetMinStoppingCondition(double target) {
        this.target = target;
    }
 
    @Override
    public boolean isSatisfied(Population population) {
        
                  
        return population.getFittestChromosome().getFitness() >= -target;
        
        
    }
    
}
