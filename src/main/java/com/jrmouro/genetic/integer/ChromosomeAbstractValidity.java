/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math3.genetics.Chromosome;

/**
 *
 * @author ronaldo
 */
public abstract class ChromosomeAbstractValidity<T>implements ChromosomeValidity{

    @Override
    public boolean isValid(Chromosome chromosome) {
        
        if (!(chromosome instanceof ChromosomeAbstract<?>)) {
            try {
                throw new Exception("ChromosomeAbstractValidity works only with ChromosomeAbstract");
            } catch (Exception ex) {
                Logger.getLogger(ChromosomeAbstractValidity.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
        
        return this.isRepresentationValid(((ChromosomeAbstract)chromosome).getRepresentation());
    }
    
    public abstract boolean isRepresentationValid(List<T> representation);
    
}
