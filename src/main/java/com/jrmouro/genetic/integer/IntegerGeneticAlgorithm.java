/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import com.jrmouro.genetic.chromosome.ChromosomeValidity;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.StoppingCondition;
import com.jrmouro.genetic.chromosome.ValidityGenotype;
import org.apache.commons.math3.genetics.MutationPolicy;
import org.apache.commons.math3.genetics.SelectionPolicy;

/**
 *
 * @author ronaldo
 */
public class IntegerGeneticAlgorithm extends GeneticAlgorithm{
    
    private IntegerPopulation initialPolulation = null;
    private StoppingCondition stoppingCondition = null;
    
    
    public IntegerGeneticAlgorithm( int populationSize, 
                                    int populationReuse, 
                                    int populationLimit, 
                                    ValidityGenotype<Integer> validityRepresentation,
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
        super(  new IntegerNPointCrossover(crossoverPoints), 
                crossoverRate, 
                new IntegerMutation(mutationRateGene), 
                mutationRate, 
                new IntegerTournamentSelection(aritySelection));
        
        this.initialPolulation = IntegerPopulation.getRandom( populationSize, 
                                                    populationReuse,
                                                    populationLimit, 
                                                    fitnessFunction, 
                                                    sizeChromosome, 
                                                    leftBoundChromosome, 
                                                    rightBoundChromosome,
                                                    validityRepresentation);
        
        this.stoppingCondition = new IntegerFixedGenerationCount(generations);
    }
    
    public IntegerGeneticAlgorithm( int populationSize, 
                                    int populationReuse,
                                    int populationLimit, 
                                    ValidityGenotype<Integer> validityRepresentation,
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
                new IntegerTournamentSelection(aritySelection));
        
        this.initialPolulation = IntegerPopulation.getRandom( populationSize, 
                                                    populationReuse,
                                                    populationLimit, 
                                                    fitnessFunction, 
                                                    sizeChromosome, 
                                                    leftBoundChromosome, 
                                                    rightBoundChromosome, 
                                                    validityRepresentation);
        
        this.stoppingCondition = stoppingCondition;
    }
    
    public IntegerGeneticAlgorithm( IntegerPopulation initialPolulation, 
                                    int populationReuse, 
                                    int populationLimit,
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
    ){
        super(  new IntegerNPointCrossover(crossoverPoints), 
                crossoverRate, 
                new IntegerMutation(mutationRateGene), 
                mutationRate, 
                new IntegerTournamentSelection(aritySelection));
        
        this.initialPolulation = initialPolulation;
        
        this.stoppingCondition = new IntegerFixedGenerationCount(generations);
    }
    
    public IntegerGeneticAlgorithm( IntegerPopulation initialPolulation,  
                                    IntegerMutation mutationPolicy,
                                    CrossoverPolicy crossoverPolicy,
                                    SelectionPolicy selectionPolicy,
                                    StoppingCondition stoppingCondition,
                                    double crossoverRate, 
                                    double mutationRate
    ){
        super(  crossoverPolicy, 
                crossoverRate, 
                mutationPolicy, 
                mutationRate, 
                selectionPolicy);
        
        this.initialPolulation = initialPolulation;        
        this.stoppingCondition = stoppingCondition;
    }

    
    
    public IntegerPopulation run() {
        return (IntegerPopulation)this.evolve(initialPolulation, stoppingCondition);
    }
    
    
    
}
