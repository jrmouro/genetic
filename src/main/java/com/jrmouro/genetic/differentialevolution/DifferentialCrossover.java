/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.differentialevolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.CrossoverPolicy;

/**
 *
 * @author ronaldo
 */
public class DifferentialCrossover implements CrossoverPolicy {

    final private double cr;

    public DifferentialCrossover(double cr) {
        this.cr = cr;
    }

    @Override
    public ChromosomePair crossover(Chromosome first, Chromosome second) throws MathIllegalArgumentException {
        if (!(first instanceof DifferentialChromosome) && !(second instanceof DifferentialChromosome)) {
            try {
                throw new Exception("DifferentialCrossover works only with ChromosomeDifferential");
            } catch (Exception ex) {
                Logger.getLogger(DifferentialCrossover.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Random random = new Random();  
        int t = ((DifferentialChromosome) first).getRepresentation().size();
        int d11 = random.nextInt(t);
        double d12 = random.nextDouble();
        int d21 = random.nextInt(t);
        double d22 = random.nextDouble();
        
        List<Double> l1 = new ArrayList();
        List<Double> l2 = new ArrayList();

        for (int i = 0; i < t; i++) {
            
            if(d11 == i || d12 < this.cr){
                l1.add(((DifferentialChromosome) second).getRepresentation().get(i));
            }else{
                l1.add(((DifferentialChromosome) first).getRepresentation().get(i));
            }
            
            if(d21 == i || d22 < this.cr){
                l2.add(((DifferentialChromosome) first).getRepresentation().get(i));
            }else{
                l2.add(((DifferentialChromosome) second).getRepresentation().get(i));
            }
        }
        Chromosome c1 = null;
        Chromosome c2 = null;
        if(((DifferentialChromosome) first).getFitnessFunction() != null){
            c1 = new DifferentialChromosome(((DifferentialChromosome) first).getFactorDifference(), l1, ((DifferentialChromosome) first).getFitnessFunction(), ((DifferentialChromosome) first).getValidityRepresentation());
            c2 = new DifferentialChromosome(((DifferentialChromosome) second).getFactorDifference(), l2, ((DifferentialChromosome) first).getFitnessFunction(), ((DifferentialChromosome) first).getValidityRepresentation());
        }else{
            try {
                throw new Exception("FitnessFunction is null");
            } catch (Exception ex) {
                Logger.getLogger(DifferentialCrossover.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return new ChromosomePair(c1,c2);

    }

}
