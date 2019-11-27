/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.differentialevolution;

import com.jrmouro.genetic.chromosome.ChromosomeDouble;
import com.jrmouro.genetic.chromosome.ValidityRepresentation;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;

/**
 *
 * @author ronaldo
 */
public class DifferentialChromosome extends ChromosomeDouble{
    
    
    final private double factorDifference;

    public DifferentialChromosome(
            double factorDifference, 
            List<Double> representation, 
            FitnessFunction fitnessFunction, 
            ValidityRepresentation<Double> validityRepresentation) throws InvalidRepresentationException {
        super(representation, fitnessFunction, validityRepresentation);
        this.factorDifference = factorDifference;
    }

    public DifferentialChromosome(
            double factorDifference, 
            Double[] representation, 
            FitnessFunction fitnessFunction, 
            ValidityRepresentation<Double> validityRepresentation) throws InvalidRepresentationException {
        super(representation, fitnessFunction, validityRepresentation);
        this.factorDifference = factorDifference;
    }

    public DifferentialChromosome(
            double factorDifference, 
            List<Double> representation, 
            boolean copyList, 
            FitnessFunction fitnessFunction, 
            ValidityRepresentation<Double> validityRepresentation) {
        super(representation, copyList, fitnessFunction, validityRepresentation);
        this.factorDifference = factorDifference;
    }

       

    public double getFactorDifference() {
        return factorDifference;
    }
        
    public static DifferentialChromosome difference(DifferentialChromosome one, DifferentialChromosome other){
        DifferentialChromosome ret = null;
        List<Double> list = new ArrayList();
        
        for (int i = 0; i < one.getRepresentation().size(); i++) {
            list.add((one.factorDifference + other.factorDifference) / 2.0 *(one.getRepresentation().get(i) - other.getRepresentation().get(i)));
        }
                
        return new DifferentialChromosome(one.factorDifference, list, one.getFitnessFunction(), one.getValidityRepresentation());
    }
    
    public static DifferentialChromosome sum(DifferentialChromosome one, DifferentialChromosome other){
        DifferentialChromosome ret = null;
        List<Double> list = new ArrayList();
        
        for (int i = 0; i < one.getRepresentation().size(); i++) {
            list.add((one.getRepresentation().get(i) + other.getRepresentation().get(i)));
        }
                
        return new DifferentialChromosome((one.factorDifference + other.factorDifference) / 2.0, list, one.getFitnessFunction(), one.getValidityRepresentation());
    }
    
    @Override
    public double fitness() {
        return this.getFitnessFunction().fitness(this);
    }

    @Override
    protected void checkValidity(List<Double> chromosomeRepresentation) throws InvalidRepresentationException {}

    @Override
    public AbstractListChromosome<Double> newFixedLengthChromosome(List<Double> chromosomeRepresentation) {
        return new DifferentialChromosome(this.factorDifference, chromosomeRepresentation, this.getFitnessFunction(), this.getValidityRepresentation());
    }

    @Override
    public List<Double> getCopyListUtil() {
        List<Double> ret = new ArrayList();   
        for(double d: this.getRepresentation())
            ret.add(d);
        return ret;
    }

    @Override
    public List<Double> getRepresentation() {
        return super.getRepresentation();
    }
    
    public static Chromosome getRandom(
            double factorDifference, 
            FitnessFunction fitnessFunction, 
            int size, 
            double boundLeft, 
            double boundRight, 
            ValidityRepresentation<Double> validityRepresentation){
        List<Double> ret = new ArrayList();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            ret.add(boundLeft + (r.nextDouble() * (boundRight - boundLeft)));
        }
        return new DifferentialChromosome(factorDifference, ret, fitnessFunction, validityRepresentation);
    }
    
}
