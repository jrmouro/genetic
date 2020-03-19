/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer.indexed;

import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import com.jrmouro.genetic.integer.IntegerCrossover;
import com.jrmouro.genetic.integer.IntegerMutation;
import com.jrmouro.genetic.integer.IntegerPopulation;
import com.jrmouro.genetic.integer.IntegerSelection;
import com.jrmouro.genetic.integer.IntegerStoppingCondition;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.StoppingCondition;

/**
 *
 * @author ronaldo
 */
public class IndexedGeneticAlgorithm  extends GeneticAlgorithm{
    
    private IntegerPopulation initialPolulation = null;
    private StoppingCondition stoppingCondition = null;
    
    public IndexedGeneticAlgorithm( int populationSize, 
                                    int populationReuse, 
                                    int populationLimit,
                                    FitnessFunction<Integer> fitnessFunction, 
                                    int sizeChromosome, 
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
        
        this.initialPolulation = IndexedPopulation.getIndexedRandom( 
                                                    populationSize, 
                                                    populationReuse,
                                                    populationLimit, 
                                                    fitnessFunction, 
                                                    sizeChromosome);
        
        this.stoppingCondition = new IntegerStoppingCondition(generations);
    }
    
    public IndexedGeneticAlgorithm( int populationSize, 
                                    int populationReuse,
                                    int populationLimit,
                                    FitnessFunction fitnessFunction, 
                                    int sizeChromosome,
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
        
        this.initialPolulation = IndexedPopulation.getIndexedRandom( 
                                                    populationSize, 
                                                    populationReuse,
                                                    populationLimit, 
                                                    fitnessFunction, 
                                                    sizeChromosome);
        
        this.stoppingCondition = stoppingCondition;
    }
    
    public IndexedGeneticAlgorithm( IndexedPopulation initialPolulation, 
                                    int populationReuse, 
                                    int populationLimit,
                                    FitnessFunction<Integer> fitnessFunction, 
                                    int sizeChromosome,
                                    int generations,                                    
                                    int crossoverPoints,
                                    double crossoverRate, 
                                    double mutationRate,
                                    double mutationRateGene,
                                    int aritySelection
    ){
        super(  new IntegerCrossover(crossoverPoints), 
                crossoverRate, 
                new IntegerMutation(mutationRateGene), 
                mutationRate, 
                new IntegerSelection(aritySelection));
        
        this.initialPolulation = initialPolulation;
        
        this.stoppingCondition = new IntegerStoppingCondition(generations);
    }

    
    
    public IntegerPopulation run() {
        return (IntegerPopulation)this.evolve(initialPolulation, stoppingCondition);
    }
    
    
    
}
