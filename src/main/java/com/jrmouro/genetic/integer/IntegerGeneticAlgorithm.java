/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.StoppingCondition;

/**
 *
 * @author ronaldo
 */
public class IntegerGeneticAlgorithm extends GeneticAlgorithm{
    
    private Population initialPolulation = null;
    private StoppingCondition stoppingCondition = null;
    
    public IntegerGeneticAlgorithm( int populationSize, 
                                    int populationReuse, 
                                    int populationLimit, 
                                    ChromosomeAbstractValidity<Integer> validity,
                                    FitnessFunction<Integer> fitnessFunction, 
                                    int sizeChromosome, 
                                    int leftBoundChromosome, 
                                    int rightBoundChromosome,
                                    int generations,                                    
                                    int crossoverPoints,
                                    double crossoverRate, 
                                    double mutationRate,
                                    double mutationRateGene,
                                    int aritySelection
    ) throws OutOfRangeException {
        super(  new IntegerCrossover(crossoverPoints), 
                crossoverRate, 
                new IntegerMutation(mutationRateGene), 
                mutationRate, 
                new IntegerSelection(aritySelection));
        
        this.initialPolulation = IntegerPopulation.getRandom( populationSize, 
                                                    populationReuse,
                                                    populationLimit, 
                                                    validity,
                                                    fitnessFunction, 
                                                    sizeChromosome, 
                                                    leftBoundChromosome, 
                                                    rightBoundChromosome);
        
        this.stoppingCondition = new IntegerStoppingCondition(generations);
    }
    
    public IntegerGeneticAlgorithm( int populationSize, 
                                    int populationReuse,
                                    int populationLimit, 
                                    ChromosomeAbstractValidity<Integer> validity,
                                    FitnessFunction fitnessFunction, 
                                    int sizeChromosome, 
                                    int leftBoundChromosome, 
                                    int rightBoundChromosome,
                                    StoppingCondition stoppingCondition, 
                                    CrossoverPolicy crossoverPolicy,
                                    double crossoverRate, 
                                    double mutationRate,
                                    double mutationRateGene,
                                    int aritySelection
    ) throws OutOfRangeException {
        super(  crossoverPolicy, 
                crossoverRate, 
                new IntegerMutation(mutationRateGene), 
                mutationRate, 
                new IntegerSelection(aritySelection));
        
        this.initialPolulation = IntegerPopulation.getRandom( populationSize, 
                                                    populationReuse,
                                                    populationLimit, 
                                                    validity,
                                                    fitnessFunction, 
                                                    sizeChromosome, 
                                                    leftBoundChromosome, 
                                                    rightBoundChromosome);
        
        this.stoppingCondition = stoppingCondition;
    }

    public IntegerChromosome run() {
        return (IntegerChromosome)this.evolve(initialPolulation, stoppingCondition).getFittestChromosome();
    }
    
    
    
}
