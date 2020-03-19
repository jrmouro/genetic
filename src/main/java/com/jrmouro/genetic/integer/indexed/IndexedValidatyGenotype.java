/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer.indexed;

import com.jrmouro.genetic.chromosome.ValidityGenotype;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ronaldo
 */
public class IndexedValidatyGenotype implements ValidityGenotype<Integer>{

    @Override
    public boolean isGenotypeValid(List<Integer> representation) {
        Set<Integer> set = new HashSet();
        for (Integer i : representation) {
            if(!set.add(i))
                return false;
        }
        return true;
    }
    
}
