/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.integer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ChromosomePair;
import org.apache.commons.math3.genetics.CrossoverPolicy;

/**
 *
 * @author ronaldo
 */
public class VectorPointsIntegerCrossover implements CrossoverPolicy {

    final Integer[] points;

    public VectorPointsIntegerCrossover(Integer[] points) {
        this.points = points;
    }
    
    public VectorPointsIntegerCrossover(Integer nrPoints, Integer pass) {
        
        
        this.points = new Integer[nrPoints];
        
        int u = pass;
        
        for (int i = 0; i < nrPoints; i++) {
            this.points[i] = u;
            u += pass;
        }
    }
    
    

    @Override
    public ChromosomePair crossover(Chromosome first, Chromosome second) throws MathIllegalArgumentException {

        List<Integer> child1Rep = new ArrayList();
        List<Integer> child2Rep = new ArrayList();

        int i = 0;
        boolean flag = false;

        Collections.sort(Arrays.asList(points));

        for (Integer point : points) {

            for (; i < point
                    && i < ((IntegerChromosome) first).getListRepresentation().size()
                    && i < ((IntegerChromosome) second).getListRepresentation().size(); i++) {

                if (flag) {

                    child1Rep.add(((IntegerChromosome) first).getListRepresentation().get(i));
                    child2Rep.add(((IntegerChromosome) second).getListRepresentation().get(i));

                } else {

                    child2Rep.add(((IntegerChromosome) first).getListRepresentation().get(i));
                    child1Rep.add(((IntegerChromosome) second).getListRepresentation().get(i));

                }

            }

            flag = !flag;

        }

        for (; i < ((IntegerChromosome) first).getListRepresentation().size()
                && i < ((IntegerChromosome) second).getListRepresentation().size(); i++) {

            if (!flag) {

                child1Rep.add(((IntegerChromosome) first).getListRepresentation().get(i));
                child2Rep.add(((IntegerChromosome) second).getListRepresentation().get(i));

            } else {

                child2Rep.add(((IntegerChromosome) first).getListRepresentation().get(i));
                child1Rep.add(((IntegerChromosome) second).getListRepresentation().get(i));

            }

        }

        return new ChromosomePair(((AbstractListChromosome<Integer>) first).newFixedLengthChromosome(child1Rep),
                ((AbstractListChromosome<Integer>) second).newFixedLengthChromosome(child2Rep));

    }

}
