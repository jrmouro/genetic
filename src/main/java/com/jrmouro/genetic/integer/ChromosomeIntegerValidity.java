/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.genetics.Chromosome;

/**
 *
 * @author ronaldo
 */
public abstract class ChromosomeIntegerValidity extends ChromosomeAbstractValidity<Integer> {

    @Override
    public boolean isValid(Chromosome chromosome) {
        
        if (!(chromosome instanceof IntegerChromosome)) {
            try {
                throw new Exception("ChromosomeIntegerValidity works only with IntegerChromosome");
            } catch (Exception ex) {
                Logger.getLogger(ChromosomeIntegerValidity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return this.isRepresentationValid(((IntegerChromosome)chromosome).getListRepresentation());
    }
    
}
