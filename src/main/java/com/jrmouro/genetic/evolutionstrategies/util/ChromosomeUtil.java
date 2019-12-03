/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.evolutionstrategies.util;

import java.util.List;

/**
 *
 * @author ronaldo
 */
public interface ChromosomeUtil<T> {
    
    public List<T> getGenotype();
    public int getGenotypeSize();
    
}
