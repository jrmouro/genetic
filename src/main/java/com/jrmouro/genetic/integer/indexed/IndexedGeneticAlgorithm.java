/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer.indexed;

import com.jrmouro.genetic.chromosome.ValidityGenotype;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import com.jrmouro.genetic.integer.IntegerNPointCrossover;
import com.jrmouro.genetic.integer.IntegerGeneticAlgorithm;
import com.jrmouro.genetic.integer.IntegerPopulation;
import com.jrmouro.genetic.integer.IntegerTournamentSelection;
import com.jrmouro.genetic.integer.IntegerFixedGenerationCount;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.genetics.CrossoverPolicy;
import org.apache.commons.math3.genetics.SelectionPolicy;
import org.apache.commons.math3.genetics.StoppingCondition;

/**
 *
 * @author ronaldo
 */
public class IndexedGeneticAlgorithm  extends IntegerGeneticAlgorithm{

       
    
    public IndexedGeneticAlgorithm(
            int populationSize, 
            int populationReuse, 
            int sizeChromosome, 
            int crossoverPoints,
            int aritySelection,
            int generations,
            FitnessFunction fitnessFunction,  
            SelectionPolicy selectionPolicy, 
            StoppingCondition stoppingCondition,
            double crossoverRate, 
            double mutationRate,
            double mutationRateGene) throws OutOfRangeException {
        
        super(IndexedPopulation.getIndexedRandom(
                    populationSize, 
                    populationReuse, 
                    populationSize, 
                    fitnessFunction, 
                    sizeChromosome),
                new IndexedMutation(mutationRateGene),  
                new IntegerNPointCrossover(crossoverPoints), 
                new IntegerTournamentSelection(aritySelection), 
                new IntegerFixedGenerationCount(generations),
                crossoverRate, 
                mutationRate);
    }
    

    public IndexedGeneticAlgorithm(
            int populationSize, 
            int populationReuse, 
            int sizeChromosome, 
            FitnessFunction fitnessFunction,                          
            CrossoverPolicy crossoverPolicy, 
            SelectionPolicy selectionPolicy, 
            StoppingCondition stoppingCondition,
            double crossoverRate, 
            double mutationRate,
            double mutationRateGene) throws OutOfRangeException {
        
        super(  IndexedPopulation.getIndexedRandom(
                    populationSize, 
                    populationReuse, 
                    populationSize, 
                    fitnessFunction, 
                    sizeChromosome),
                new IndexedMutation(mutationRateGene),  
                crossoverPolicy, 
                selectionPolicy, 
                stoppingCondition,
                crossoverRate, 
                mutationRate);
    }

    public IndexedGeneticAlgorithm(
            IntegerPopulation initialPolulation,              
            CrossoverPolicy crossoverPolicy, 
            SelectionPolicy selectionPolicy, 
            StoppingCondition stoppingCondition,
            double crossoverRate, 
            double mutationRate,
            double mutationRateGene) {
        
        super(  initialPolulation, 
                new IndexedMutation(mutationRateGene),
                crossoverPolicy, 
                selectionPolicy, 
                stoppingCondition, 
                crossoverRate, 
                mutationRate);
    }

         
    
    
}
