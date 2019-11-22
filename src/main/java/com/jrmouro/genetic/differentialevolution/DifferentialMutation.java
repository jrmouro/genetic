/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.differentialevolution;

import com.jrmouro.genetic.integer.IntegerMutation;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.Population;

/**
 *
 * @author ronaldo
 */
public class DifferentialMutation implements MutationPolicy{
    
    final private Population population;

    public DifferentialMutation(Population population) {
        this.population = population;
        
        if (population.iterator().hasNext() && !(population.iterator().next() instanceof DifferentialChromosome)) {
            try {
                throw new Exception("DifferentialMutation works only with ChromosomeDifferential");
            } catch (Exception ex) {
                Logger.getLogger(DifferentialMutation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Chromosome mutate(Chromosome original) throws MathIllegalArgumentException {
        
        if (!(original instanceof DifferentialChromosome)) {
            try {
                throw new Exception("DifferentialMutation works only with ChromosomeDifferential");
            } catch (Exception ex) {
                Logger.getLogger(DifferentialMutation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Random random = new Random();
        
        int r1 = random.nextInt(this.population.getPopulationSize());
        int r2 = random.nextInt(this.population.getPopulationSize());
        int r3 = random.nextInt(this.population.getPopulationSize());
        
        while(r1 == r2 || r2 == r3 || r3 == r1){
            r2 = random.nextInt(this.population.getPopulationSize());
            r3 = random.nextInt(this.population.getPopulationSize());
        }
        
        Chromosome o = null, a1 = null, a2 = null;
        int i = 0;
        for (Chromosome chromosome : this.population) {
            
            //if(chromosome.equals(original))
                //o = chromosome;
            if(!chromosome.equals(original) && r1 == i){
                a1 = a2 = chromosome;
            }
            if(!chromosome.equals(original) && r2 == i){
                a2 = chromosome;
            }
            if(!chromosome.equals(original) && r3 == i){
                a1 = chromosome;
            }
            i++;
        }
        
        return DifferentialChromosome.sum((DifferentialChromosome)original, DifferentialChromosome.difference((DifferentialChromosome)a1, (DifferentialChromosome)a2));
    }
    
    
}
