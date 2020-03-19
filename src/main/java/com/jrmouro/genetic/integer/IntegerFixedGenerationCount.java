/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.genetics.FixedGenerationCount;

/**
 *
 * @author ronaldo
 */
public class IntegerFixedGenerationCount extends FixedGenerationCount{
    
    public IntegerFixedGenerationCount(int maxGenerations) throws NumberIsTooSmallException {
        super(maxGenerations);
    }
    
}
