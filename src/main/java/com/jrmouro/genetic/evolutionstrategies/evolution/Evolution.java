/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.evolutionstrategies.evolution;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;

/**
 *
 * @author ronaldo
 */
public class Evolution<T> implements IevolutionFunction<T>{
        

    @Override
    public ChromosomeAbstract<T> evolve(ChromosomeAbstract<T> original, int n, boolean max) {
        ChromosomeAbstract<T> c = original;
        for (int i = 0; i < n; i++) {
            c = c.evolve(max);
        }
        return c;
    }
    
}
