/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.fitnessfunction;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ronaldo
 */
public final class SimilarityFitnessFunction implements FitnessFunction<Double> {

    private double[][] matrix = null;
    private double[] result = null;

    public SimilarityFitnessFunction(double[][] matrix, double[] result) {
        try {
            if (matrix.length != result.length) {
                throw new Exception("Matrix and result invalid dimensions");

            } else {

                this.matrix = matrix;
                this.result = result;
            }

        } catch (Exception ex) {
            Logger.getLogger(SimilarityFitnessFunction.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] getResult() {
        return result;
    }

    @Override
    public double fitness(ChromosomeAbstract<Double> chromosome) {
        Double ss = 0.0;
        Double[] w = new Double[chromosome.getRepresentation().size()];
        w = (Double[]) chromosome.getRepresentation().toArray(w);
        if (result != null && w.length >= result.length) {
            int j = 0;
            for (double[] ts : matrix) {
                Double s = 0.0;
                int i = 0;
                for (Double t : ts) {
                    s += t * w[i++];
                }
                s -= this.result[j++];
                ss += s * s;
            }
        } else {
            try {
                throw new Exception("Chromosome invalid dimensions");
            } catch (Exception ex) {
                Logger.getLogger(SimilarityFitnessFunction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (Double.isNaN(ss) || Double.isInfinite(ss)) 
            return -Double.MAX_VALUE;
        

        return -ss;
    }

}
