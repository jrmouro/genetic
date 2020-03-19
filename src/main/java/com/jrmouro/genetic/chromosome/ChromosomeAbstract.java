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

    final ValidityGenotype<T> validityRepresentation;
    
    public ChromosomeAbstract(List<T> representation, ValidityGenotype<T> validityRepresentation) throws InvalidRepresentationException {
        super(representation);
        this.validityRepresentation = validityRepresentation;
    }

    public ChromosomeAbstract(T[] representation, ValidityGenotype<T> validityRepresentation) throws InvalidRepresentationException {
        super(representation);
        this.validityRepresentation = validityRepresentation;
    }

    public ChromosomeAbstract(List<T> representation, boolean copyList, ValidityGenotype<T> validityRepresentation) {
        super(representation, copyList);
        this.validityRepresentation = validityRepresentation;
    }

    
    @Override
    public List<T> getGenotype() {  
        List<T> ret = new ArrayList();
        for (T t : this.getRepresentation()) {
            ret.add(t);
        }
        return ret;
    }

    @Override
    public int getGenotypeSize() {
        return this.getRepresentation().size();
    }

    @Override
    public boolean isValid() {
        return this.validityRepresentation.isGenotypeValid(this.getGenotype());
    }

    public ValidityGenotype<T> getValidityRepresentation() {
        return validityRepresentation;
    }
 
    public abstract ChromosomeAbstract<T> getRandom();
    
    
    
}
