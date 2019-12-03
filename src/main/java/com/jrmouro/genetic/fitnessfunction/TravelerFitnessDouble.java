/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.fitnessfunction;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import com.jrmouro.genetic.evolutionstrategies.util.RepresentationTransform;
import com.jrmouro.genetic.evolutionstrategies.util.RepresentationTransformIntegerDouble;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ronaldo
 */
public class TravelerFitnessDouble implements FitnessFunction<Double> {

    private final Double[][] matrix;

    public TravelerFitnessDouble(Double[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public double fitness(ChromosomeAbstract<Double> chromosome) {
        
        RepresentationTransform<Integer,Double> transform = new RepresentationTransformIntegerDouble();
        
        boolean[] mark = new boolean[this.matrix.length];

        List<Integer> listInt = transform.get(chromosome.normalize());

        for (int i = 0; i < this.matrix.length; i++) {
            mark[i] = false;
        }

        double ret = 0;
        int beg = -1;
        int prev = -1;

        int t = listInt.size();

        for (int u = 0; u < t; u++) {

            int r = listInt.get(u);
            int i = 0;

            while (true) {

                if (i >= this.matrix.length) {
                    i = 0;
                    continue;
                }

                if (mark[i] == false) {
                    if (r == 0) {
                        mark[i] = true;
                        break;
                    }
                    r--;

                }

                i++;
            }

            if (prev >= 0) {
                ret -= this.matrix[prev][i];
            } else {
                beg = i;
            }

            prev = i;

        }

        ret -= this.matrix[prev][beg];
        
        return ret;
    }

    static public double distance(double[][] matrix, List<Integer> path) {
        double ret = 0;
        for (int i = 0; i < (path.size() - 1); i++) {
            ret += matrix[path.get(i)][path.get(i + 1)];
        }
        ret += matrix[path.get(path.size() - 1)][path.get(0)];
        return ret;
    }

    static public List<Integer> path(ChromosomeAbstract<Double> chromosome) {
            
            RepresentationTransform<Integer,Double> transform = new RepresentationTransformIntegerDouble();
            
            List<Integer> li = transform.get(chromosome);
            
            List<Integer> ret = new ArrayList();
            
            int length = li.size();
            
            boolean [] mark = new boolean[length];
            
            
            for (int i = 0; i < length; i++) {
                mark[i] = false;
            }
                        
            
            for (int u = 0; u < length; u++) {
                
            
                int r = li.get(u);
                int i = 0;
                
                while(true){
                                        
                    if(i >= length){
                        i = 0;
                        continue;
                    }
                    
                    if(mark[i] == false){
                        if(r == 0){
                            mark[i] = true;
                            break;
                        }
                        r--;
                        
                    }
                    
                    i++;                    
                }
                
                                
                ret.add(i);
                
            }

            return ret;
            
        }

}
