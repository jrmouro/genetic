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

/**
 *
 * @author ronaldo
 */
public class IntegerChromosome extends ChromosomeAbstract<Integer> {

    final private FitnessFunction<Integer> fitnessFunction;
    final private int leftBound, rightBound;
    final private ChromosomeAbstractValidity<Integer> validity;

    public IntegerChromosome(ChromosomeAbstractValidity<Integer> validity, FitnessFunction<Integer> fitnessFunction, List<Integer> representation, int leftBound, int rightBound) throws InvalidRepresentationException {
        super(representation);
        this.fitnessFunction = fitnessFunction;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.validity = validity;
    }

    public IntegerChromosome(ChromosomeAbstractValidity<Integer> validity, FitnessFunction<Integer> fitnessFunction, Integer[] representation, int leftBound, int rightBound) throws InvalidRepresentationException {
        super(representation);
        this.fitnessFunction = fitnessFunction;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.validity = validity;
    }

    public IntegerChromosome(ChromosomeAbstractValidity<Integer> validity, FitnessFunction<Integer> fitnessFunction, List<Integer> representation, boolean copyList, int leftBound, int rightBound) {
        super(representation, copyList);
        this.fitnessFunction = fitnessFunction;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.validity = validity;
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

    public ChromosomeAbstractValidity<Integer> getValidity() {
        return validity;
    }

    public boolean isValid() {
        return this.validity.isValid(this);
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
                this.validity, 
                this.fitnessFunction, 
                chromosomeRepresentation, 
                leftBound, 
                rightBound);
    }

    public static IntegerChromosome getRandom(
            ChromosomeAbstractValidity<Integer> validity, 
            FitnessFunction fitnessFunction, 
            int size, 
            int leftBound, 
            int rightBound) {

        List<Integer> ret = new ArrayList();
        Random r = new Random();
        while (true) {
            ret.clear();
            
            for (int i = 0; i < size; i++) {
                ret.add(r.nextInt((rightBound - leftBound) + 1) + leftBound);
            }
            
            if(validity.isRepresentationValid(ret))
                break;
        }
        
        return new IntegerChromosome(validity, fitnessFunction, ret, leftBound, rightBound);
    }

    @Override
    public ChromosomeAbstract<Integer> getRandom() {

        return getRandom(this.validity, this.getFitnessFunction(), this.getListRepresentation().size(), leftBound, rightBound);

    }

    @Override
    public ChromosomeAbstract evolve(boolean max) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ChromosomeAbstract<Integer> normalize() {
        List<Integer> list = this.getCopyListUtil();
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
        return new IntegerChromosome(this.validity, this.getFitnessFunction(), list, min, max);
    }

}
