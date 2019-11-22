/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.fitnessfunction;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;

/**
 *
 * @author ronaldo
 * @param <T>
 */
public interface FitnessFunction<T> {
    public double fitness(ChromosomeAbstract<T> chromosome);
}
