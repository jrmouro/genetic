/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.evolutionstrategies.chromosome;

import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import com.jrmouro.genetic.chromosome.ChromosomeDouble;
import java.util.List;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import com.jrmouro.genetic.chromosome.ValidityGenotype;

/**
 *
 * @author ronaldo
 */
public abstract class ChromosomeSD  extends ChromosomeDouble{
    
    final protected double sd;

    public ChromosomeSD(List<Double> representation, FitnessFunction fitnessFunction, double sd, ValidityGenotype<Double> validityRepresentation) throws InvalidRepresentationException {
        super(representation, fitnessFunction, validityRepresentation);
        this.sd = sd;
    }

    public ChromosomeSD(Double[] representation, FitnessFunction fitnessFunction, double sd, ValidityGenotype<Double> validityRepresentation) throws InvalidRepresentationException {
        super(representation, fitnessFunction, validityRepresentation);
        this.sd = sd;
    }
           
    public double getSd() {
        return sd;
    }
    
    
    
    
    
}
