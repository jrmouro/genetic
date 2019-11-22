/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.genetics.NPointCrossover;

/**
 *
 * @author ronaldo
 */
public class IntegerCrossover extends NPointCrossover<IntegerChromosome>{
    
    public IntegerCrossover(int crossoverPoints) throws NotStrictlyPositiveException {
        super(crossoverPoints);
    }
    
}
