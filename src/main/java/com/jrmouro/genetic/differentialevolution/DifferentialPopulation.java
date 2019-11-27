/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.differentialevolution;

import com.jrmouro.genetic.chromosome.ValidityRepresentation;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ListPopulation;
import org.apache.commons.math3.genetics.Population;

/**
 *
 * @author ronaldo
 */
public class DifferentialPopulation extends ListPopulation{

    public DifferentialPopulation(int populationLimit) throws NotPositiveException {
        super(populationLimit);
    }
    
    public DifferentialPopulation(List<Chromosome> ret, int populationLimit) throws NotPositiveException {
        super(ret, populationLimit);
    }

    @Override
    public Population nextGeneration() {
        
        Population ret = new DifferentialPopulation(this.getPopulationLimit());
        ret.addChromosome(this.getFittestChromosome());
        System.out.println(this.getFittestChromosome());
        return ret;
    }
    
    public static DifferentialPopulation getRandom( 
            int size,
            double factorDifference,
            FitnessFunction fitnessFunction, 
            int sizeChromosome,
            double boundLeft, 
            double boundRight, 
            ValidityRepresentation<Double> validityRepresentation){
        List<Chromosome> ret = new ArrayList();
        for (int i = 0; i < size; i++) {
            ret.add(DifferentialChromosome.getRandom(factorDifference, fitnessFunction, sizeChromosome, boundLeft, boundRight, validityRepresentation));
        }
        return new DifferentialPopulation(ret, size);
    }
    
    
    public static DifferentialPopulation getRandom(
            int size, 
            FitnessFunction fitnessFunction, 
            int sizeChromosome,
            double boundLeft, 
            double boundRight, 
            ValidityRepresentation<Double> validityRepresentation){
        List<Chromosome> ret = new ArrayList();
        for (int i = 0; i < size; i++) {
            ret.add(DifferentialChromosome.getRandom(new Random().nextDouble(), fitnessFunction, sizeChromosome, boundLeft, boundRight, validityRepresentation));
        }
        return new DifferentialPopulation(ret, size);
    }
    
    
}
