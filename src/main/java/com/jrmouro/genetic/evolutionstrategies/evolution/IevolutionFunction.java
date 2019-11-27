/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.evolutionstrategies.evolution;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import com.jrmouro.genetic.chromosome.ChromosomeValidity;

/**
 *
 * @author ronaldo
 */
public interface IevolutionFunction<T> {
    public ChromosomeAbstract<T> evolve(ChromosomeAbstract<T> original, int n, boolean max);
}
