/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer.indexed;

import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import com.jrmouro.genetic.integer.IntegerChromosome;
import com.jrmouro.genetic.integer.IntegerPopulation;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.genetics.Chromosome;

/**
 *
 * @author ronaldo
 */
public class IndexedPopulation extends IntegerPopulation {

    public IndexedPopulation(int reuse, int populationLimit) throws NotPositiveException {
        super(reuse, populationLimit);
    }

    public IndexedPopulation(int reuse, List<Chromosome> chromosomes, int populationLimit) throws NullArgumentException, NotPositiveException, NumberIsTooLargeException {
        super(reuse, chromosomes, populationLimit);
    }

    @Override
    public void addChromosome(Chromosome chromosome) throws NumberIsTooLargeException {

        if ((chromosome instanceof IndexedChromosome)) {
            if (((IntegerChromosome) chromosome).isValid()) {
                super.addChromosome(chromosome);
            } else {
                try {
                    throw new Exception("Invalid IndexedChromosome");
                } catch (Exception ex) {
                    Logger.getLogger(IndexedPopulation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            try {
                throw new Exception("IndexedPopulation works only with IndexedChromosome");
            } catch (Exception ex) {
                Logger.getLogger(IndexedPopulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
        
        

    public static IntegerPopulation getIndexedRandom(
            int size,
            int reuse,
            int populationLimit,
            FitnessFunction<Integer> fitnessFunction,
            int sizeChromosome) {

        List<Chromosome> ret = new ArrayList();
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            ret.add(IndexedChromosome.getIndexedRandom(fitnessFunction, sizeChromosome));
        }
        return new IndexedPopulation(reuse, ret, populationLimit);
    }



}
