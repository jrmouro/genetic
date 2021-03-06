/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.chromosome;

import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;

/**
 *
 * @author ronaldo
 */
public class ChromosomeDouble extends ChromosomeAbstract<Double>{
    
    final protected FitnessFunction fitnessFunction;

    public ChromosomeDouble(
            List<Double> representation, 
            FitnessFunction fitnessFunction, 
            ValidityGenotype<Double> validityRepresentation) throws InvalidRepresentationException {
        super(representation, validityRepresentation);
        this.fitnessFunction = fitnessFunction;
    }

    public ChromosomeDouble(
            Double[] representation, 
            FitnessFunction fitnessFunction, 
            ValidityGenotype<Double> validityRepresentation) throws InvalidRepresentationException {
        super(representation, validityRepresentation);
        this.fitnessFunction = fitnessFunction;
    }

    public ChromosomeDouble(
            List<Double> representation, 
            boolean copyList, 
            FitnessFunction fitnessFunction, 
            ValidityGenotype<Double> validityRepresentation) {
        super(representation, copyList, validityRepresentation);
        this.fitnessFunction = fitnessFunction;
    }

    public FitnessFunction getFitnessFunction() {
        return fitnessFunction;
    }  
       
    @Override
    protected void checkValidity(List<Double> chromosomeRepresentation) throws InvalidRepresentationException {}

    @Override
    public AbstractListChromosome<Double> newFixedLengthChromosome(List<Double> chromosomeRepresentation) {
        return new ChromosomeDouble(chromosomeRepresentation, true, this.fitnessFunction, this.validityRepresentation);
    }

    @Override
    public double fitness() {
        return this.fitnessFunction.fitness(this);
    }

    @Override
    public ChromosomeAbstract<Double> evolve(boolean max) {
        
        List<Double> representation = new ArrayList();        
        
        NormalDistribution n0 = new  NormalDistribution(0, 1.0);    
             
        for (int i = 0; i < this.getRepresentation().size(); i++) {
            double zi = n0.inverseCumulativeProbability(new Random().nextDouble());
            representation.add(zi + this.getRepresentation().get(i));
        }
        
        ChromosomeAbstract<Double> ret = new ChromosomeDouble(representation, this.getFitnessFunction(), this.validityRepresentation);
        
        if(ret.isValid())
            return ret;
                      
        return this;
        
    }
    
    @Override
    public ChromosomeAbstract<Double> normalize() {
        List<Double> list = this.getGenotype();
        Double min = list.get(0);
        Double max = list.get(0);
        for (Double d : list) {
            if(d < min)
                min = d;
            if(d > max)
                max = d;
        }
        Double range = Math.abs(max - min);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, Math.abs((min - list.get(i)) / range));
        }
        return new ChromosomeDouble(list, this.getFitnessFunction(), this.validityRepresentation);
    }

    @Override
    public ChromosomeAbstract<Double> getRandom() {
        List<Double> list = new ArrayList();
        int t = this.getRepresentation().size();
        for (int i = 0; i < this.getRepresentation().size(); i++) {
            list.add(Math.random());
        }
                
        return new ChromosomeDouble(list, this.getFitnessFunction(), this.validityRepresentation); 
    }

   
    
    

       
    
}
