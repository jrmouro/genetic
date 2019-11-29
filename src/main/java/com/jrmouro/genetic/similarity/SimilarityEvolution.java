/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.similarity;

import com.jrmouro.genetic.chromosome.ValidityRepresentation;
import com.jrmouro.genetic.evolutionstrategies.chromosome.ChromosomeOne;
import com.jrmouro.genetic.evolutionstrategies.evolution.IevolutionFunction;
import com.jrmouro.genetic.fitnessfunction.SimilarityFitnessFunction;

/**
 *
 * @author ronaldo
 */
public class SimilarityEvolution implements Runnable {

    final IevolutionFunction<Double> evolution;
    final SimilarityFitnessFunction fitness;
    final ValidityRepresentation<Double> validityRepresentation;
    final int generations;
    final boolean max;
    final double[] weights;
    final double chromosomeSd;
    ChromosomeOne chromosomeOne;

    public SimilarityEvolution(
            IevolutionFunction<Double> evolution,
            double[][] matrix,
            double[] result,
            int generations,
            double chromosomeSd,
            boolean max,
            ValidityRepresentation<Double> validityRepresentation
    ) {
        this.evolution = evolution;
        this.fitness = new SimilarityFitnessFunction(matrix, result);
        this.generations = generations;
        this.max = max;
        this.validityRepresentation = validityRepresentation;
        this.chromosomeSd = chromosomeSd;
        this.weights = new double[fitness.getMatrix()[0].length];
        for (double d : weights) {
            d = 1.0;
        }  
        chromosomeOne = new ChromosomeOne(weights, fitness, chromosomeSd, validityRepresentation);
    }

    @Override
    public void run() {                               
        
        chromosomeOne = (ChromosomeOne) evolution.evolve(chromosomeOne, generations, max);        
        
        for (int k = 0; k < this.weights.length; k++)            
            weights[k] = chromosomeOne.getRepresentation().get(k);    
        
        System.out.println("Chromosome: " + chromosomeOne);
        
    }
    
    public double getSimilarity(double[] vector){
        double s = 0.0;
        for (int k = 0; k < this.weights.length; k++)
            s += weights[k] * vector[k];
        return s;
    }

}
