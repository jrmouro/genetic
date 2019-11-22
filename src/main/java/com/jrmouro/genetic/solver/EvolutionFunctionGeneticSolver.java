/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.solver;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import com.jrmouro.genetic.evolutionstrategies.util.ChromosomeFactory;
import com.jrmouro.genetic.evolutionstrategies.evolution.IevolutionFunction;

/**
 *
 * @author ronaldo
 */
public class EvolutionFunctionGeneticSolver extends GeneticSolver{
    
    private IevolutionFunction<Double> ievolutionFunction;
    private ChromosomeAbstract<Double> orig;
    private int nGen; 
    private boolean maxProb;

    public EvolutionFunctionGeneticSolver(
            IevolutionFunction<Double> ievolutionFunction, 
            ChromosomeAbstract<Double> orig, 
            int nGen, 
            boolean maxProb) {
        this.ievolutionFunction = ievolutionFunction;
        this.orig = orig;
        this.nGen = nGen;
        this.maxProb = maxProb;
    }
    
    public EvolutionFunctionGeneticSolver(
            IevolutionFunction<Double> ievolutionFunction, 
            ChromosomeFactory<Double> factory, 
            int nGen, 
            boolean maxProb) {
        this.ievolutionFunction = ievolutionFunction;
        this.orig = factory.get();
        this.nGen = nGen;
        this.maxProb = maxProb;
    }

    public IevolutionFunction<Double> getIevolutionFunction() {
        return ievolutionFunction;
    }

    public void setIevolutionFunction(IevolutionFunction<Double> ievolutionFunction) {
        this.ievolutionFunction = ievolutionFunction;
    }

    public ChromosomeAbstract<Double> getOrig() {
        return orig;
    }

    public void setOrig(ChromosomeAbstract<Double> orig) {
        this.orig = orig;
    }

    public int getnGen() {
        return nGen;
    }

    public void setnGen(int nGen) {
        this.nGen = nGen;
    }

    public boolean isMaxProb() {
        return maxProb;
    }

    public void setMaxProb(boolean maxProb) {
        this.maxProb = maxProb;
    }

    @Override
    public ChromosomeAbstract chromosomeSolve() {
        return this.ievolutionFunction.evolve(orig, nGen, maxProb);
    }
    
}
