/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer.indexed;

import com.jrmouro.genetic.chromosome.ValidityGenotype;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import com.jrmouro.genetic.integer.IntegerChromosome;
import com.jrmouro.genetic.integer.IntegerMutation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.Chromosome;

/**
 *
 * @author ronaldo
 */
public class IndexedMutation extends IntegerMutation{
    
    public IndexedMutation(double rate) {
        super(rate);
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
        int l = ((IntegerChromosome)original).getLeftBound();
        int r = ((IntegerChromosome)original).getRightBound();
        
        List<Integer> representation = new ArrayList(((IntegerChromosome)original).getListRepresentation());
        
        int n = (int)(this.rate * (double)representation.size());
        if(n > representation.size())
            n = representation.size();
        else if(n < 0)
            n = 0;
        
        int ini = rand.nextInt(representation.size() - n);
        List<Integer> aux = new ArrayList();
        for (int i = 0; i < n; i++) {
            aux.add(representation.get(ini + i));
        }
        Collections.shuffle(aux);
        for (int i = 0; i < n; i++) {
            representation.set(ini + i, aux.get(i));
        }
        
        return new IntegerChromosome(f, representation, l, r, v);
        
    }
    
    
    
}
