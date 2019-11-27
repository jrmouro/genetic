/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.chromosome;

import com.jrmouro.genetic.evolutionstrategies.util.ChromosomeNormalize;
import com.jrmouro.genetic.evolutionstrategies.util.ChromosomeUtil;
import com.jrmouro.genetic.evolutionstrategies.util.IevolutionStrategy;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;



/**
 *
 * @author ronaldo
 * @param <T>
 */
public abstract class ChromosomeAbstract<T> extends AbstractListChromosome<T> implements ChromosomeValidity, IevolutionStrategy, ChromosomeUtil<T>, ChromosomeNormalize<T>{

    final ValidityRepresentation<T> validityRepresentation;
    
    public ChromosomeAbstract(List<T> representation, ValidityRepresentation<T> validityRepresentation) throws InvalidRepresentationException {
        super(representation);
        this.validityRepresentation = validityRepresentation;
    }

    public ChromosomeAbstract(T[] representation, ValidityRepresentation<T> validityRepresentation) throws InvalidRepresentationException {
        super(representation);
        this.validityRepresentation = validityRepresentation;
    }

    public ChromosomeAbstract(List<T> representation, boolean copyList, ValidityRepresentation<T> validityRepresentation) {
        super(representation, copyList);
        this.validityRepresentation = validityRepresentation;
    }

    @Override
    public List<T> getRepresentation() {
        return super.getRepresentation(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<T> getCopyListUtil() {
        List<T> ret = new ArrayList();   
        for(T d: this.getRepresentation())
            ret.add(d);
        return ret;
    }

    @Override
    public boolean isValid() {
        return this.validityRepresentation.isValid(this.getRepresentation());
    }

    public ValidityRepresentation<T> getValidityRepresentation() {
        return validityRepresentation;
    }
 
    
    
    
    public abstract ChromosomeAbstract<T> getRandom();
    
    
    
}
