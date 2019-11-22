/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jrmouro.genetic.test;

import com.jrmouro.genetic.chromosome.ChromosomeAbstract;
import com.jrmouro.genetic.chromosome.ChromosomeDouble;
import com.jrmouro.genetic.evolutionstrategies.chromosome.ChromosomeOne;
import com.jrmouro.genetic.evolutionstrategies.evolution.EvolutionScoutSniffer;
import com.jrmouro.genetic.evolutionstrategies.util.RepresentationTransform;
import com.jrmouro.genetic.evolutionstrategies.util.RepresentationTransformIntegerDouble;
import com.jrmouro.genetic.solver.EvolutionFunctionGeneticSolver;
import com.jrmouro.genetic.solver.GeneticSolver;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author ronaldo
 */
public class ChromosomeNormalizeJUnitTest {
    
    
    
    double [][] mm = {   
                            {1,2,3,4,5,6,7,8,9,1,2,3},
                            {7,8,9,1,2,3,7,8,9,1,2,3},
                            {1,2,3,4,5,6,7,8,9,1,2,3},
                            {4,5,6,7,8,9,4,5,6,7,8,9},
                            {1,2,3,4,5,6,7,8,9,1,2,3},
                            {4,5,6,7,8,9,1,2,3,4,5,6},
                            {7,8,9,1,2,3,7,8,9,1,2,3},
                            {4,5,6,7,8,9,4,5,6,7,8,9},
                            {7,8,9,1,2,3,7,8,9,1,2,3},
                            {1,2,3,4,5,6,7,8,9,1,2,3},
                            {4,5,6,7,8,9,1,2,3,4,5,6},
                            {7,8,9,1,2,3,7,8,9,1,2,3}
                        };
    double [][] m = {   
                            {1,0,3,4,9,4,5,1,2,3},
                            {7,8,0,1,9,1,2,1,2,3},
                            {1,2,3,0,9,4,5,8,9,1},
                            {1,2,3,4,0,6,7,8,9,8},
                            {4,5,6,7,8,0,1,2,3,4},
                            {7,8,9,1,2,3,0,8,9,1},
                            {4,5,6,7,8,9,1,0,4,5},
                            {7,8,9,1,2,3,7,1,0,3},
                            {4,5,6,7,8,9,4,3,4,0},
                            {0,8,9,1,2,3,7,1,2,3},
                        };
    
    
    static public class CaixeiroFitness implements com.jrmouro.genetic.fitnessfunction.FitnessFunction<Double>{
        
        private final double [][] matrix;        
        
        public CaixeiroFitness(double[][] matrix) {
            this.matrix = matrix;
        }
                

        @Override
        public double fitness(ChromosomeAbstract<Double> chromosome) {
            
            RepresentationTransform<Integer,Double> transform = new RepresentationTransformIntegerDouble();
            
            boolean [] mark = new boolean[this.matrix.length];
            
            List<Integer> listInt = transform.get(chromosome.normalize(), 0, chromosome.getRepresentation().size() - 1);
                        
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
                
                while(true){
                                        
                    if(i >= this.matrix.length){
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
                
                if(prev >= 0)                
                    ret -= this.matrix[prev][i];
                else
                    beg = i;
                
                prev = i;
                
            }

            ret -= this.matrix[prev][beg];
            
            return ret;
        }
        
        
        static public double distance(double[][] matrix, List<Integer> path){
            double ret = 0;
            for (int i = 0; i < (path.size() - 1); i++) {
                ret += matrix[path.get(i)][path.get(i+1)];
            }
            ret += matrix[path.get(path.size() - 1)][path.get(0)];
            return ret;
        }
        
        static public List<Integer> path(ChromosomeAbstract<Double> chromosome) {
            
            RepresentationTransform<Integer,Double> transform = new RepresentationTransformIntegerDouble();
            
            List<Integer> li = transform.get(chromosome, 0, chromosome.getRepresentation().size()-1);
            
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
    
    public ChromosomeNormalizeJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void test2() {
        
        double[] v = {1.5, 1.5, 9.0, 15.0, 1.5, 9.0, 15.0, 1.5, 9.0, 15.0};
        
        CaixeiroFitness cx = new CaixeiroFitness(m);
    
        ChromosomeDouble c = new ChromosomeOne(v, cx, 0.1);
                        
        System.out.println("new EvolutionScoutSniffer(10, 0.001).evolve(c, 100, false)");
        
        GeneticSolver solver = new EvolutionFunctionGeneticSolver(new EvolutionScoutSniffer(50, 0.001), c, 100, true);
        
        c = (ChromosomeDouble) solver.chromosomeSolve();
        
                            
        List<Integer> p = CaixeiroFitness.path(c.normalize());
        
        double d = IntegerGAJUnitTest.CaixeiroFitness.distance(m, p);
        
        System.out.println(c);
        System.out.println(p);
        System.out.println(d);
        
    }
}
