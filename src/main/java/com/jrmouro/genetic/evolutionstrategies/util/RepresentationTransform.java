/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.evolutionstrategies.util;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public interface RepresentationTransform<T1,T2> {
    List<T1> get(ChromosomeAbstract<T2> chromosome, int ini, int fim);
}
