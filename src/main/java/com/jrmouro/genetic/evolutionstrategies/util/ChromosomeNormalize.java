/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.evolutionstrategies.util;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;

/**
 *
 * @author ronaldo
 */
public interface ChromosomeNormalize<T> {
    public ChromosomeAbstract<T> normalize();
}
