/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.solver;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;

/**
 *
 * @author ronaldo
 */
public abstract class GeneticSolver<T> implements Solver<T>{
    
    @Override
    public T[] solve() {
        return (T[]) this.chromosomeSolve().getRepresentation().toArray();
    }
    
    public abstract ChromosomeAbstract<T> chromosomeSolve();
    
}
