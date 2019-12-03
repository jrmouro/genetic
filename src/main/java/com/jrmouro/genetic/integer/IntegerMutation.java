/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.MutationPolicy;
import com.jrmouro.genetic.chromosome.ValidityGenotype;

/**
 *
 * @author ronaldo
 */
public class IntegerMutation implements MutationPolicy{
    
    final double rate;

    public IntegerMutation(double rate) {
        this.rate = rate;
    }
      

    @Override
    public Chromosome mutate(Chromosome original) throws MathIllegalArgumentException {
        
        if (!(original instanceof IntegerChromosome)) {
            try {
                throw new Exception("IntegerMutation works only with IntegerChromosome");
            } catch (Exception ex) {
                Logger.getLogger(IntegerMutation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Random rand = new Random();
        
        FitnessFunction f = ((IntegerChromosome)original).getFitnessFunction();  
        ValidityGenotype<Integer> v = ((IntegerChromosome)original).getValidityRepresentation();
        int t = ((IntegerChromosome)original).getListRepresentation().size();
        int l = ((IntegerChromosome)original).getLeftBound();
        int r = ((IntegerChromosome)original).getRightBound();
        
        IntegerChromosome c = IntegerChromosome.getRandom(f, t, l, r, v);
                
        List<Integer> list = new ArrayList();
        
        int i = 0;
        for (Integer integer : ((IntegerChromosome)original).getListRepresentation()) {
            if(rand.nextDouble() < this.rate)
                list.add(c.getListRepresentation().get(i));
            else
                list.add(integer);
            i++;
        }
                
        return new IntegerChromosome(f, list, l, r, v);
        
    }
    
}
