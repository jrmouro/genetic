/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import org.apache.commons.math3.genetics.Chromosome;

/**
 *
 * @author ronaldo
 */
public interface ChromosomeValidity {
    public boolean isValid(Chromosome chromosome);
}
