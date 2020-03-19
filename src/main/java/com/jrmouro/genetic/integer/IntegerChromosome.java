/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import com.jrmouro.genetic.chromosome.ValidityGenotype;

/**
 *
 * @author ronaldo
 */
public class IntegerChromosome extends ChromosomeAbstract<Integer> {

    final private FitnessFunction<Integer> fitnessFunction;
    final private int leftBound, rightBound;

    public IntegerChromosome(
            FitnessFunction<Integer> fitnessFunction, 
            List<Integer> representation, 
            int leftBound, 
            int rightBound, 
            ValidityGenotype<Integer> validityRepresentation) throws InvalidRepresentationException {
        super(representation, validityRepresentation);
        this.fitnessFunction = fitnessFunction;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public IntegerChromosome(
            FitnessFunction<Integer> fitnessFunction, 
            Integer[] representation, 
            int leftBound, 
            int rightBound, 
            ValidityGenotype<Integer> validityRepresentation) throws InvalidRepresentationException {
        super(representation, validityRepresentation);
        this.fitnessFunction = fitnessFunction;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public IntegerChromosome(
            FitnessFunction<Integer> fitnessFunction, 
            List<Integer> representation, 
            boolean copyList, 
            int leftBound, 
            int rightBound, 
            ValidityGenotype<Integer> validityRepresentation) {
        super(representation, copyList, validityRepresentation);
        this.fitnessFunction = fitnessFunction;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public int getLeftBound() {
        return leftBound;
    }

    public int getRightBound() {
        return rightBound;
    }

    public List<Integer> getListRepresentation() {
        return this.getRepresentation();
    }

    public FitnessFunction getFitnessFunction() {
        return fitnessFunction;
    }


    @Override
    public double fitness() {
        return this.fitnessFunction.fitness(this);
    }

    @Override
    protected void checkValidity(List<Integer> chromosomeRepresentation) throws InvalidRepresentationException {

    }

    @Override
    public AbstractListChromosome<Integer> newFixedLengthChromosome(
            List<Integer> chromosomeRepresentation) {
        return new IntegerChromosome(
                this.fitnessFunction, 
                chromosomeRepresentation, 
                leftBound, 
                rightBound,
                this.getValidityRepresentation());
    }

    public static IntegerChromosome getRandom(
            FitnessFunction fitnessFunction, 
            int size, 
            int leftBound, 
            int rightBound, 
            ValidityGenotype<Integer> validityRepresentation) {

        List<Integer> list = new ArrayList();
        
        Random r = new Random();
        
        while (true) {
            
            list.clear();
            
            for (int i = 0; i < size; i++) {
                list.add(r.nextInt((rightBound - leftBound) + 1) + leftBound);
            }
            
            IntegerChromosome ret = new IntegerChromosome(fitnessFunction, list, leftBound, rightBound, validityRepresentation);
            
            if(ret.isValid())
                return ret;
        }
        
        
    }

    @Override
    public ChromosomeAbstract<Integer> getRandom() {

        return getRandom(this.getFitnessFunction(), this.getListRepresentation().size(), leftBound, rightBound, this.getValidityRepresentation());

    }

    @Override
    public ChromosomeAbstract evolve(boolean max) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ChromosomeAbstract<Integer> normalize() {
        List<Integer> list = this.getGenotype();
        Integer min = list.get(0);
        Integer max = list.get(0);
        for (Integer d : list) {
            if (d < min) {
                min = d;
            }
            if (d > max) {
                max = d;
            }
        }
        Integer range = Math.abs(max - min);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, Math.abs((min - list.get(i)) / range));
        }
        return new IntegerChromosome(this.getFitnessFunction(), list, min, max, this.getValidityRepresentation());
    }

}
