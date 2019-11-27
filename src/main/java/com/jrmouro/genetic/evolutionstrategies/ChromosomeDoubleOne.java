/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.evolutionstrategies;

import com.jrmouro.genetic.chromosome.ChromosomeDouble;
import com.jrmouro.genetic.chromosome.ChromosomeValidity;
import com.jrmouro.genetic.chromosome.ValidityRepresentation;
import com.jrmouro.genetic.evolutionstrategies.chromosome.ChromosomeSD;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;

/**
 *
 * @author ronaldo
 */
public class ChromosomeDoubleOne extends ChromosomeSD {

    public ChromosomeDoubleOne(
            List<Double> representation, 
            FitnessFunction fitnessFunction, 
            double sd, 
            ValidityRepresentation<Double> validityRepresentation) throws InvalidRepresentationException {
        super(prepare(representation, sd), fitnessFunction, sd, validityRepresentation);

    }

    protected ChromosomeDoubleOne(
            double sd, List<Double> representation, 
            FitnessFunction fitnessFunction, 
            ValidityRepresentation<Double> validityRepresentation) throws InvalidRepresentationException {
        super(representation, fitnessFunction, sd, validityRepresentation);

    }

    public ChromosomeDoubleOne(
            Double[] representation, 
            FitnessFunction fitnessFunction, 
            double sd, 
            ValidityRepresentation<Double> validityRepresentation) throws InvalidRepresentationException {
        super(prepare(representation, sd), fitnessFunction, sd, validityRepresentation);

    }

    @Override
    public AbstractListChromosome<Double> newFixedLengthChromosome(List<Double> chromosomeRepresentation) {
        return new ChromosomeDoubleOne(chromosomeRepresentation, this.getFitnessFunction(), this.getSd(), this.getValidityRepresentation());
    }

    public List<Double> getCopyList() {
        List<Double> ret = super.getCopyListUtil();
        ret.remove(ret.size() - 1);
        return ret;
    }

    static private List<Double> prepare(List<Double> representation, double sd) {

        NormalDistribution n0 = new NormalDistribution(0, sd);
        representation.add(Math.abs(n0.inverseCumulativeProbability(new Random().nextDouble())));

        return representation;
    }

    static private Double[] prepare(Double[] representation, double sd) {
        int t = representation.length;
        Double[] ret = new Double[t + 1];
        for (int i = 0; i < t; i++) {
            ret[i] = representation[i];
        }

        NormalDistribution n0 = new NormalDistribution(0, sd);

        ret[t] = Math.abs(n0.inverseCumulativeProbability(new Random().nextDouble()));

        return ret;
    }

    @Override
    public ChromosomeDouble evolve(boolean max) {

        List<Double> representation = new ArrayList();

        NormalDistribution n0 = new NormalDistribution(0, sd);

        double z0 = n0.inverseCumulativeProbability(new Random().nextDouble());
        if (z0 + this.getRepresentation().get(this.getRepresentation().size() - 1) < 0) {
            z0 *= -1;
        }
        z0 += this.getRepresentation().get(this.getRepresentation().size() - 1);
        NormalDistribution n1 = new NormalDistribution(0, z0);

        for (int i = 0; i < this.getRepresentation().size() - 1; i++) {
            double zi = n1.inverseCumulativeProbability(new Random().nextDouble());
            representation.add(zi + this.getRepresentation().get(i));
        }

        representation.add(z0);

        ChromosomeDouble child = new ChromosomeDoubleOne(sd, representation, this.getFitnessFunction(), this.getValidityRepresentation());

        if (child.isValid()) {

            int r = this.compareTo(child);

            if (max) {
                if (r < 0) {
                    return child;
                }
            } else {
                if (r > 0) {
                    return child;
                }
            }

        }

        return this;

    }

}
