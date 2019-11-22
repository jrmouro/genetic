/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.test;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import com.jrmouro.genetic.evolutionstrategies.chromosome.ChromosomeOne;
import com.jrmouro.genetic.chromosome.ChromosomeDouble;
import com.jrmouro.genetic.evolutionstrategies.chromosome.ChromosomeTwo;
import com.jrmouro.genetic.evolutionstrategies.evolution.Evolution;
import com.jrmouro.genetic.evolutionstrategies.evolution.EvolutionScoutSniffer;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.junit.Test;


/**
 *
 * @author ronaldo
 */
public class MutationStrategyOneJUnitTest {
    
    public MutationStrategyOneJUnitTest() {
    }
    
    
    
    @Test
    public void test() {
        
        double[] v = {10.10};
        
        ChromosomeDouble first = new ChromosomeTwo(v, new FitnessFunction<Double>() {
            @Override
            public double fitness(ChromosomeAbstract<Double> chromosome) {
                double x1 = Math.pow(chromosome.getRepresentation().get(0), 6);
                double x2 = -4.8 * Math.pow(chromosome.getRepresentation().get(0), 4);
                double x3 = -5.0 * Math.cos(chromosome.getRepresentation().get(0));
                double x4 = 3.0 * Math.pow(chromosome.getRepresentation().get(0), 2);
                double x5 = 0.2 * chromosome.getRepresentation().get(0);
                
                return x1 + x2 + x3 + x4 + x5 + 5;
            }
            
        }, 3.3);
        
       
        System.out.println("new EvolutionScoutSniffer(10, 0.001).evolve(first, 100, false)");
        System.out.println(new EvolutionScoutSniffer(10, 0.001).evolve(first, 100, false));
        
        System.out.println("new Evolution().evolve(first, 100, false)");
        System.out.println(new Evolution().evolve(first, 100, false));
        
    }
}
