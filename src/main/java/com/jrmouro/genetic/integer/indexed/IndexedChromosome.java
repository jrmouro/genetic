/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer.indexed;

import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import com.jrmouro.genetic.integer.IntegerChromosome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.genetics.InvalidRepresentationException;

/**
 *
 * @author ronaldo
 */
public class IndexedChromosome extends IntegerChromosome{

    public IndexedChromosome(FitnessFunction<Integer> fitnessFunction, List<Integer> representation) throws InvalidRepresentationException {
        super(fitnessFunction, representation, 0, representation.size(), new IndexedValidatyGenotype());
    }

    public IndexedChromosome(FitnessFunction<Integer> fitnessFunction, Integer[] representation) throws InvalidRepresentationException {
        super(fitnessFunction, representation, 0, representation.length, new IndexedValidatyGenotype());
    }

    public IndexedChromosome(FitnessFunction<Integer> fitnessFunction, List<Integer> representation, boolean copyList) {
        super(fitnessFunction, representation, copyList, 0, representation.size(), new IndexedValidatyGenotype());
    }
    
    public static IntegerChromosome getIndexedRandom(
            FitnessFunction fitnessFunction, 
            int size) {

        List<Integer> list = new ArrayList();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        
        Collections.shuffle(list);
        
        return new IndexedChromosome(fitnessFunction, list);
        
        
    }
    
}
