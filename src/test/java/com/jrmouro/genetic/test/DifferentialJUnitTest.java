/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.test;

import com.jrmouro.genetic.differentialevolution.DifferentialPopulation;
import org.junit.Test;
import com.jrmouro.genetic.differentialevolution.DifferentialGeneticAlgorithm;
import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.FixedGenerationCount;

/**
 *
 * @author ronaldo
 */
public class DifferentialJUnitTest {
    
    @Test
    public void test() {
        
        FitnessFunction ff = new FitnessFunction<Double>(){
            @Override
            public double fitness(ChromosomeAbstract<Double> chromosome) {
                double x1 = Math.pow(chromosome.getRepresentation().get(0), 6);
                double x2 = -4.8 * Math.pow(chromosome.getRepresentation().get(0), 4);
                double x3 = -5.0 * Math.cos(chromosome.getRepresentation().get(0));
                double x4 = 3.0 * Math.pow(chromosome.getRepresentation().get(0), 2);
                double x5 = 0.2 * chromosome.getRepresentation().get(0);
                
                return -(x1 + x2 + x3 + x4 + x5 + 5);
            }
            
        };
        
        DifferentialPopulation pop1 = DifferentialPopulation.getRandom(10, 0.1, ff, 1, -10.0, 10.0);
    
        DifferentialPopulation pop2 = DifferentialPopulation.getRandom(10, ff, 1, -10.5, 10.5);
        
        DifferentialGeneticAlgorithm de1 = new DifferentialGeneticAlgorithm(
                                                pop1, 
                                                new FixedGenerationCount(50),
                                                .5, 
                                                .5,                                         
                                                .2,
                                                3);
        
        
        DifferentialGeneticAlgorithm de2 = new DifferentialGeneticAlgorithm(
                                                pop2, 
                                                new FixedGenerationCount(50),
                                                .5, 
                                                .5,                                         
                                                .2,
                                                3);
        
        Chromosome best1 = de1.evolve().getFittestChromosome();
        
        System.out.println("best1: " + best1);
        
        Chromosome best2 = de2.evolve().getFittestChromosome();
        
        System.out.println("best2: " + best2);
        
        
    }
}
