/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.evolutionstrategies.util;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class RepresentationTransformIntegerDouble extends RepresentationTransform<Integer,Double>{

    @Override
    public List<Integer> get(ChromosomeAbstract<Double> chromosome, int ini, int fim) {
        List<Integer> ret = new ArrayList();
        List<Double> repr = chromosome.getGenotype();        
        int s = fim - ini;
        for (int i = ini; i < fim && i < repr.size(); i++) {
            ret.add((int)(s*repr.get(i)));
        }
        return ret;
    }

       
    
}
