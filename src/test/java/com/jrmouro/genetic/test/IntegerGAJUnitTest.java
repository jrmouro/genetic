/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.test;

import com.jrmouro.genetic.integer.IntegerChromosome;
import com.jrmouro.genetic.integer.IntegerGeneticAlgorithm;
import com.jrmouro.genetic.integer.TargetMinStoppingCondition;
import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import com.jrmouro.genetic.fitnessfunction.FitnessFunction;
import com.jrmouro.genetic.integer.IntegerCrossover;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.jrmouro.genetic.chromosome.ValidityGenotype;

/**
 *
 * @author ronaldo
 */
public class IntegerGAJUnitTest {

    double[][] mm = {
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3},
        {7, 8, 9, 1, 2, 3, 7, 8, 9, 1, 2, 3},
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3},
        {4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3},
        {4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6},
        {7, 8, 9, 1, 2, 3, 7, 8, 9, 1, 2, 3},
        {4, 5, 6, 7, 8, 9, 4, 5, 6, 7, 8, 9},
        {7, 8, 9, 1, 2, 3, 7, 8, 9, 1, 2, 3},
        {1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 2, 3},
        {4, 5, 6, 7, 8, 9, 1, 2, 3, 4, 5, 6},
        {7, 8, 9, 1, 2, 3, 7, 8, 9, 1, 2, 3}
    };
    double[][] m = {
        {1, 0, 3, 4, 9, 4, 5, 1, 2, 3},
        {7, 8, 0, 1, 9, 1, 2, 1, 2, 3},
        {1, 2, 3, 0, 9, 4, 5, 8, 9, 1},
        {1, 2, 3, 4, 0, 6, 7, 8, 9, 8},
        {4, 5, 6, 7, 8, 0, 1, 2, 3, 4},
        {7, 8, 9, 1, 2, 3, 0, 8, 9, 1},
        {4, 5, 6, 7, 8, 9, 1, 0, 4, 5},
        {7, 8, 9, 1, 2, 3, 7, 1, 0, 3},
        {4, 5, 6, 7, 8, 9, 4, 3, 4, 0},
        {0, 8, 9, 1, 2, 3, 7, 1, 2, 3},};

    static public class CaixeiroFitness implements FitnessFunction<Integer> {

        private final double[][] matrix;

        public CaixeiroFitness(double[][] matrix) {
            this.matrix = matrix;
        }

        @Override
        public double fitness(ChromosomeAbstract<Integer> chromosome) {

            boolean[] mark = new boolean[this.matrix.length];

            for (int i = 0; i < this.matrix.length; i++) {
                mark[i] = false;
            }

            double ret = 0;
            int beg = -1;
            int prev = -1;

            
            
            List<Integer> repr = chromosome.getGenotype();

            for (int u = 0; u < repr.size(); u++) {

                int r = repr.get(u);
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

        static public List<Integer> path(List<Integer> chromosomeRepresentation) {

            List<Integer> ret = new ArrayList();

            int length = chromosomeRepresentation.size();

            boolean[] mark = new boolean[length];

            for (int i = 0; i < length; i++) {
                mark[i] = false;
            }

            for (int u = 0; u < length; u++) {

                int r = chromosomeRepresentation.get(u);
                int i = 0;

                while (true) {

                    if (i >= length) {
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

                ret.add(i);

            }

            return ret;

        }

    }

    public IntegerGAJUnitTest() {
    }

    void bruta(List<List<Integer>> result, List<Integer> visited, int t) {

        if (visited.size() == t) {
            result.add(visited);
        } else {
            for (int i = 0; i < t; i++) {
                if (!visited.contains(i)) {
                    List<Integer> l = new ArrayList(visited);
                    l.add(i);
                    bruta(result, l, t);
                }
            }
            visited = null;
        }
    }

    @Test
    public void forcaBruta() {
        List<List<Integer>> result = new ArrayList();
        this.bruta(result, new ArrayList<Integer>(), m.length);

        double max = 0;
        double min = 100.0;
        int indMin = 0;
        int indMax = 0;

        for (int i = 0; i < result.size(); i++) {

            double s = CaixeiroFitness.distance(m, result.get(i));
            if (s < min) {
                min = s;
                indMin = i;
            }
            if (s > max) {
                max = s;
            }
        }

        System.out.println(result.get(indMin));
        System.out.println(min);
        System.out.println(result.size());
        System.out.println(result.get(indMax));
        System.out.println(max);
        System.out.println(result.size());
    }

    @Test
    public void caixeiro() {

        FitnessFunction fit = new CaixeiroFitness(m);

        IntegerGeneticAlgorithm ga = new IntegerGeneticAlgorithm(
                20, //population size
                5,
                30, //population limit
                new ValidityGenotype<Integer>() {
                    @Override
                    public boolean isGenotypeValid(List<Integer> representation) {
                        return true;
                    }
                },
                fit, // fitness function
                10, //chromosome size
                0, // left bound chromosome
                10, // right bound chormosome
                new TargetMinStoppingCondition(10.0),
                new IntegerCrossover(5),
                .3, // crossover rate
                .5, // mutation rate
                .3, // mutation arity
                2); // selection arity

        IntegerChromosome c = (IntegerChromosome) ga.run().getFittestChromosome();
        List<Integer> p = CaixeiroFitness.path(c.getListRepresentation());
        double d = CaixeiroFitness.distance(m, p);

        System.out.println(c);
        System.out.println(p);
        System.out.println(d);

    }
}
